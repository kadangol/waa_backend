package com.waa.AmazonMini.auth.repository;

import com.waa.AmazonMini.auth.model.ERole;
import com.waa.AmazonMini.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
