package com.example.Adrian.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//import com.example.Adrian.entity.User;
import com.example.Adrian.entity.UserRole;
import com.example.Adrian.repository.UserJpaRepository;

@Service("userServiceImpl")
public class UserServiceImpl implements UserDetailsService{

	@Autowired
	@Qualifier("userJpaRepository")
	private UserJpaRepository userJpaRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.example.Adrian.entity.User user = userJpaRepository.findByUsername(username);
		List<GrantedAuthority> authorities = buildAuthorities(user.getUserRole());
		return buildUser(user, authorities);
	}
	
	private User buildUser(com.example.Adrian.entity.User user, List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, 
				true, true, authorities);
	}
	
	private List<GrantedAuthority> buildAuthorities(List<UserRole> userRoles){
		List<GrantedAuthority> authorities = userRoles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
		return authorities;
	}
	
}

// Método alternativo por si stream() no funciona

//List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//userRoles.forEach(role -> {
//	authorities.add(new SimpleGrantedAuthority(role.getRole()));
//});
