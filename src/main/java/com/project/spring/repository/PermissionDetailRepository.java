package com.project.spring.repository;

import com.project.spring.domain.PermissionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionDetailRepository extends JpaRepository<PermissionDetail, Long> {
    PermissionDetail findByPermissionIdAndTargetAndFunc(Long permissionId, String target, Integer func);
}
