package com.example.Adrian.converter;

import org.dozer.DozerBeanMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.Adrian.entity.User;

@Component("userConverter")
public class UserConverter {

	private DozerBeanMapper dozer = new DozerBeanMapper();
	
	public User transform(UserDetails userDetails) {
		return dozer.map(userDetails, User.class);
	}
	
	public UserDetails transform(User user) {
		return dozer.map(user, UserDetails.class);
	}
}
