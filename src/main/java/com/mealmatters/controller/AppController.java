package com.mealmatters.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mealmatters.auth.AuthService;
import com.mealmatters.auth.service.JwtUtil;
import com.mealmatters.dao.Food;
import com.mealmatters.dao.FoodDetailEntity;
import com.mealmatters.dao.Location;
import com.mealmatters.dao.UserEntity;
import com.mealmatters.exception.CustomException;
import com.mealmatters.payloads.FoodEventPojo;
import com.mealmatters.payloads.IsAuthorized;
import com.mealmatters.payloads.LoginCredential;

import com.mealmatters.payloads.ResponseMessage;

import com.mealmatters.payloads.UserToken;
import com.mealmatters.repository.UserRepository;
import com.mealmatters.service.AppService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*" , allowedHeaders = "*")
@RestController
@RequestMapping("/mm")
public class AppController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AuthService authService;

	@Autowired
	AppService service;
	
	@Autowired
	JwtUtil jwtUtil;

	

	@PostMapping("/login")
	@ApiOperation(notes = "This is for login", value = "Enter the valid credential")
	public ResponseEntity<?> login(@RequestBody LoginCredential request) throws CustomException {
		try {
		UserToken userToken = authService.login(request);
		return new ResponseEntity<UserToken>(userToken, HttpStatus.OK);
		}
		catch(Exception e )
		{
			return new ResponseEntity<>(new ResponseMessage("either password or username is wrong"), HttpStatus.OK);
		}

	}
	@GetMapping("/authorize")
	@ApiOperation(notes = "This is for login", value = "Enter the valid credential")
	public ResponseEntity<?> authorize(@RequestHeader(value = "Authorization" , required = true) String token) throws CustomException {
		
		if(authService.validate(token).isValid()) {
			return new ResponseEntity<IsAuthorized>(new IsAuthorized(true), HttpStatus.OK);
			}
		return new ResponseEntity<IsAuthorized>(new IsAuthorized(false), HttpStatus.OK);

	}

	@PostMapping("/register")
	@ApiOperation(notes = "register", value = "Register a new user")
	public ResponseEntity<?> register(@RequestBody UserEntity user) {
		try {
			UserEntity u = userRepository.findUserEntityByUsername(user.getUsername());
			if (u != null) {
			
				return (ResponseEntity<?>) ResponseEntity.badRequest();
			}

			userRepository.save(user);
			ResponseMessage rm = new ResponseMessage(user.getUsername() + " you uccessfully registered");
			return new ResponseEntity<ResponseMessage>(rm , HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage("error"), HttpStatus.OK);
		}
	}
	
	@PostMapping("/addfood")
	@ApiOperation(notes = "t" ,value = "adding food")
	public ResponseEntity<?> addfood(@RequestHeader(value = "Authorization" , required = true) String token, @RequestBody FoodDetailEntity foodDetailEntity) throws CustomException
	{
       
		if(authService.validate(token).isValid()) {
			System.out.println(foodDetailEntity.toString());
		service.addnewfood(foodDetailEntity);
		return ResponseEntity.ok(new ResponseMessage("New Item successfully added"));
		}
		
		return new ResponseEntity<>("Unautorized", HttpStatus.UNAUTHORIZED);
		
	 }
	
	@PostMapping("/getnearestdistance")
	public ResponseEntity<?> getnearestfood(@RequestHeader(value = "Authorization" , required = true) String token, @RequestBody Location location) throws CustomException{
		if(authService.validate(token).isValid()) {
			
		  List<FoodEventPojo> fep = service.getnearestdistance(location);
		  return new ResponseEntity<List<FoodEventPojo>>(fep, HttpStatus.OK);
		}
		else
		
		return new ResponseEntity<>("Unautorized", HttpStatus.UNAUTHORIZED);
	}
	@PostMapping("/getnearestinthisrange/{dis}")
	public ResponseEntity<?> getnearestfoodinrange(@RequestHeader(value = "Authorization" , required = true) String token, @RequestBody Location location, @PathVariable("dis") Integer dis) throws CustomException{
		if(authService.validate(token).isValid()) {
			
		  List<FoodEventPojo> fep = service.getnearestdistanceinrange(location, dis);
		  return new ResponseEntity<List<FoodEventPojo>>(fep, HttpStatus.OK);
		}
		else
		
		return new ResponseEntity<>("Unautorized", HttpStatus.UNAUTHORIZED);
	}
	
	
	@GetMapping("/getfoods/{id}")
	public ResponseEntity<?> getfoodofuser(@RequestHeader(value = "Authorization" , required = true) String token, @PathVariable("id") String id) throws CustomException{
		if(authService.validate(token).isValid()) {
			
			  FoodDetailEntity fde = service.getthefoodby(id);
			  return new ResponseEntity<FoodDetailEntity>(fde, HttpStatus.OK);
			}
			else
			
			return new ResponseEntity<>("Unautorized", HttpStatus.UNAUTHORIZED);
	}
	
	
	
}

