package org.nader.io.repositories;

import org.nader.io.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findByName(String name);
}
