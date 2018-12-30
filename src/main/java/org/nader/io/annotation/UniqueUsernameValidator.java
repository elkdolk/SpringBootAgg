package org.nader.io.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.nader.io.repositories.YouserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

	@Autowired
	private YouserRepository youserRepository;
	
	@Override
	public void initialize(UniqueUsername constraintAnnotation) {
		
	}

	/**
	 * Returns true if username is already taken and exists in database
	 */
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		if(youserRepository == null){
			return true;
		}
		return youserRepository.findByName(username) == null;
	}
}
