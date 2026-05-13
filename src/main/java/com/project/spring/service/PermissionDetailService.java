package com.project.spring.service;

import com.project.spring.dto.DefaultDto;
import com.project.spring.dto.PermissionDto;
import com.project.spring.dto.PermissionDetailDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PermissionDetailService {
    PermissionDetailDto.AllowResDto allow(PermissionDto.ExistReqDto param, Long reqUserId);
    List<PermissionDetailDto.DetailResDto> access(PermissionDto.ExistReqDto param, Long reqUserId);

    DefaultDto.CreateResDto toggle(PermissionDetailDto.ToggleReqDto param, Long reqUserId);
    /**/
    DefaultDto.CreateResDto create(PermissionDetailDto.CreateReqDto param, Long reqUserId);
    void update(PermissionDetailDto.UpdateReqDto param, Long reqUserId);
    void delete(DefaultDto.DeleteReqDto param, Long reqUserId);
    void deleteList(DefaultDto.DeleteListReqDto param, Long reqUserId);
    PermissionDetailDto.DetailResDto detail(DefaultDto.DetailReqDto param, Long reqUserId);
    List<PermissionDetailDto.DetailResDto> list(PermissionDetailDto.ListReqDto param, Long reqUserId);
    DefaultDto.PagedListResDto pagedList(PermissionDetailDto.PagedListReqDto param, Long reqUserId);
    List<PermissionDetailDto.DetailResDto> scrollList(PermissionDetailDto.ScrollListReqDto param, Long reqUserId);
}
