package com.mealmatters.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mealmatters.dao.UserEntity;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String>{
	
	public UserEntity findUserEntityByUsername(String email);
	
	 public List<UserEntity> findByUsernameLike(String username);

}
