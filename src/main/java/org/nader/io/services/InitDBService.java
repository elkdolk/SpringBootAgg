package org.nader.io.services;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.nader.io.entities.Role;
import org.nader.io.entities.Youser;
import org.nader.io.repositories.RoleRepository;
import org.nader.io.repositories.YouserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * This service initializes the database on startup
 */
@Transactional
@Service
public class InitDBService {

	@Autowired
	private YouserRepository youserRepository;
	
	@Autowired
	private RoleRepository roleRepository;
			
	/**
	 * This method will be called after spring context creation
	 */
	@PostConstruct
	public void init(){
		
		/**
		 * Create Role_User and save it
		 */
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);
		
		/**
		 * Create Role_Admin and save it
		 */
		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);
		
		Youser youserAdmin = new Youser();
		youserAdmin.setEnabled(true);
		youserAdmin.setName("admin");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		youserAdmin.setPassword(encoder.encode("admin"));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		youserAdmin.setRoles(roles);
		youserRepository.save(youserAdmin);
	}
}
