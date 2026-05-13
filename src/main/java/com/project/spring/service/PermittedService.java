package com.project.spring.service;

import com.project.spring.dto.PermissionDto;
import org.springframework.stereotype.Service;

@Service
public interface PermittedService {
    void isPermitted(Long userId, String target, int func);
    boolean permitted(PermissionDto.PermittedReqDto param);
}
