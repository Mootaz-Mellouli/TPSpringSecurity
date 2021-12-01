package com.rest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import com.rest.dao.CompteRepository;
import com.rest.dao.OperationRepository;
import com.rest.dao.RoleRepository;
import com.rest.dao.UserRepository;
import com.rest.entity.Compte;
import com.rest.entity.Operation;
import com.rest.entity.Role;
import com.rest.entity.User;
import com.rest.service.UserService;

@SpringBootApplication
public class SecurityRestApplication implements CommandLineRunner{
	@Autowired
	UserRepository userRepository;
	@Autowired
	CompteRepository compteRepository;
	@Autowired
	OperationRepository operationRepository;
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(SecurityRestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Role adminRole= roleRepository.save(new Role(1, "ADMIN"));
		Role userRole= roleRepository.save(new Role(2, "USER"));
		
		User user = userService.saveUser("USER", "USER", "USER");
		User admin = userService.saveUser("ADMIN", "ADMIN", "ADMIN");
		Compte compte1 = compteRepository.save(new Compte(1, 1500.0, new Date(),null));
		Compte compte2 = compteRepository.save(new Compte(2, 3000.0, new Date(),null));
		Compte compte3 = compteRepository.save(new Compte(3, 6000.0, new Date(),null));
		Operation operation1 = operationRepository.save(new Operation(new Date(), 180.0, 0, compte1));
		Operation operation2 = operationRepository.save(new Operation(new Date(), 400.0, 1, compte2));
		Operation operation3 = operationRepository.save(new Operation(new Date(), 600.0, 2, compte3));
	}

}