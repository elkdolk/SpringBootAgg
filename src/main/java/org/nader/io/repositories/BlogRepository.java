package org.nader.io.repositories;

import java.util.List;
import org.nader.io.entities.Blog;
import org.nader.io.entities.Youser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

	List<Blog> findByYouser(Youser youser);
}
