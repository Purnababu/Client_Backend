package com.example.Client_Lms.Repoisitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Client_Lms.Entity.Role;

@Repository
public interface RoleRepoisitory extends JpaRepository<Role, String>{

}
