package com.assignment.mcb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assignment.mcb.model.File;

@Repository
public interface FileRepository extends JpaRepository<File, String> {

	@Query("SELECT f FROM File f WHERE f.status=:status")
	List<File> findAllByStatus(@Param("status") String status);
}
