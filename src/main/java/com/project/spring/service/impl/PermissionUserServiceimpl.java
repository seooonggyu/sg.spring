package com.project.spring.service.impl;

import com.project.spring.domain.PermissionUser;
import com.project.spring.domain.User;
import com.project.spring.dto.DefaultDto;
import com.project.spring.dto.PermissionUserDto;
import com.project.spring.exception.NoMatchingDataException;
import com.project.spring.mapper.PermissionUserMapper;
import com.project.spring.repository.PermissionUserRepository;
import com.project.spring.repository.UserRepository;
import com.project.spring.service.PermissionUserService;
import com.project.spring.service.PermittedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PermissionUserServiceimpl implements PermissionUserService {

    private final String target = "permission";

    private final PermissionUserRepository permissionUserRepository;
    private final UserRepository userRepository;
    private final PermissionUserMapper permissionUserMapper;
    private final PermittedService permittedService;

    /**/
    @Override
    public DefaultDto.CreateResDto create(PermissionUserDto.CreateReqDto param, Long reqUserId) {
        permittedService.isPermitted(reqUserId, target, 110);

        //유저 아이디 입력한거 최대한 보정하기!
        User user = userRepository.findByUsername(param.getUsername());
        if(user == null){
            user = userRepository.findById(Long.parseLong(param.getUsername())).orElse(null);
        }
        if(user == null){
            throw new RuntimeException("no user");
        }
        param.setUserId(user.getId());

        //혹시 이미 존재하는 데이터 일수도 있으니까 확인!
        PermissionUser permissionUser = permissionUserRepository.findByPermissionIdAndUserId(param.getPermissionId(), param.getUserId());
        if(permissionUser == null) {
            permissionUser = param.toEntity();
        } else {
            permissionUser.setDeleted(false);
        }
        return permissionUserRepository.save(permissionUser).toCreateResDto();
    }

    @Override
    public void update(PermissionUserDto.UpdateReqDto param, Long reqUserId) {
        permittedService.isPermitted(reqUserId, target, 120);
        PermissionUser permissionUser = permissionUserRepository.findById(param.getId()).orElseThrow(() -> new NoMatchingDataException("no data"));
        permissionUser.update(param);
        permissionUserRepository.save(permissionUser);
    }

    @Override
    public void delete(DefaultDto.DeleteReqDto param, Long reqUserId) {
        update(PermissionUserDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build(), reqUserId);
    }

    @Override
    public void deleteList(DefaultDto.DeleteListReqDto param, Long reqUserId) {
        for(Long id : param.getIds()){
            delete(DefaultDto.DeleteReqDto.builder().id(id).build(), reqUserId);
        }
    }

    public PermissionUserDto.DetailResDto get(DefaultDto.DetailReqDto param, Long reqUserId) {
        permittedService.isPermitted(reqUserId, target, 200);
        PermissionUserDto.DetailResDto res = permissionUserMapper.detail(param.getId());
        return res;
    }

    @Override
    public PermissionUserDto.DetailResDto detail(DefaultDto.DetailReqDto param, Long reqUserId) {
        return get(param, reqUserId);
    }

    @Override
    public List<PermissionUserDto.DetailResDto> list(PermissionUserDto.ListReqDto param, Long reqUserId) {
        return detailList(permissionUserMapper.list(param), reqUserId);
    }

    public List<PermissionUserDto.DetailResDto> detailList(List<PermissionUserDto.DetailResDto> list, Long reqUserId){
        List<PermissionUserDto.DetailResDto> newList = new ArrayList<>();
        for(PermissionUserDto.DetailResDto each : list){
            newList.add(get(DefaultDto.DetailReqDto.builder().id(each.getId()).build(), reqUserId));
        }
        return newList;
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(PermissionUserDto.PagedListReqDto param, Long reqUserId) {
        DefaultDto.PagedListResDto res = param.init(permissionUserMapper.pagedListCount(param));
        res.setList(detailList(permissionUserMapper.pagedList(param), reqUserId));
        return res;
    }

    @Override
    public List<PermissionUserDto.DetailResDto> scrollList(PermissionUserDto.ScrollListReqDto param, Long reqUserId) {
        param.init();

        //타이틀 로 스크롤 더 요청하는 경우 어쩔수 없이 작업!
        /*if("title".equals(param.getOrderby())){
            String mark = param.getMark();
            if(mark != null && !mark.isEmpty()){
                PermissionUserDto.DetailResDto permissionUser = permissionUserMapper.detail(Long.parseLong(mark));
                if(permissionUser != null){
                    mark = permissionUser.getTitle() + "_" + permissionUser.getId();
                    param.setMark(mark);
                }
            }
        }*/

        return detailList(permissionUserMapper.scrollList(param), reqUserId);
    }

}
