package com.example.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.domain.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

	@Query("SELECT x FROM Profile x ORDER BY x.id")
	List<Profile> findAllOrderByName();

	@Query("SELECT x FROM Profile x ORDER BY x.id")
	Page<Profile> findAllOrderByName(Pageable pageable);
	
	// @Query("SELECT x FROM Profile x Where x.Userno")
	Profile findByUserno(String Userno);
	
	// @Query("DELETE FROM Profile x Where x.Userno")
	Profile deleteByUserno(String Userno);

	
}
