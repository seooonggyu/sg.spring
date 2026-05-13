package com.project.spring.mapper;

import com.project.spring.dto.PermissionUserDto;

import java.util.List;

public interface PermissionUserMapper {
	PermissionUserDto.DetailResDto detail(Long id);
	List<PermissionUserDto.DetailResDto> list(PermissionUserDto.ListReqDto param);

	List<PermissionUserDto.DetailResDto> pagedList(PermissionUserDto.PagedListReqDto param);
	int pagedListCount(PermissionUserDto.PagedListReqDto param);
	List<PermissionUserDto.DetailResDto> scrollList(PermissionUserDto.ScrollListReqDto param);
}