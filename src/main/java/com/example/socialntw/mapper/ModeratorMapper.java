package com.example.socialntw.mapper;


import com.example.socialntw.dto.ModeratorDto;
import com.example.socialntw.entity.Area;
import com.example.socialntw.entity.Moderator;
import com.example.socialntw.mapper.UserMapper;
import com.example.socialntw.service.AreaService;
import com.example.socialntw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModeratorMapper {

    @Autowired
    private UserService userService;

    private UserMapper userMapper;
    private AreaMapper areaMapper;
    private AreaService areaService;

    public Moderator toEntity(ModeratorDto moderatorDto) {
        Moderator moderator = new Moderator();
        moderator.setUser(userMapper.toCreateEntity(userService.getUserById(moderatorDto.getUserId())));
        moderator.setArea(areaMapper.toEntity(areaService.getAreaById(moderatorDto.getAreaId()))); // Assuming Area has a constructor that accepts an ID
        return moderator;
    }

    public ModeratorDto toDto(Moderator moderator) {
        ModeratorDto moderatorDto = new ModeratorDto();
        moderatorDto.setUserId(moderator.getUser().getId());
        moderatorDto.setAreaId(moderator.getArea().getAreaId());
        return moderatorDto;
    }
}