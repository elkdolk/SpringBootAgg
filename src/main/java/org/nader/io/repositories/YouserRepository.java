package org.nader.io.repositories;

import org.nader.io.entities.Youser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YouserRepository extends JpaRepository<Youser, Integer> {

	Youser findByName(String name);
}


