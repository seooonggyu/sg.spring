package com.project.spring.repository;

import com.project.spring.domain.PermissionUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionUserRepository extends JpaRepository<PermissionUser, Long> {
    PermissionUser findByPermissionIdAndUserId(Long permissionId, Long userId);
}
