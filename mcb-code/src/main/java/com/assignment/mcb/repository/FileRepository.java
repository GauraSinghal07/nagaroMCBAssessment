package com.assignment.mcb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.mcb.model.File;
@Repository
public interface FileRepository extends JpaRepository<File, String> {

}
