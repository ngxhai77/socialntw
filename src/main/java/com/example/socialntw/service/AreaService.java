package com.example.socialntw.service;

import com.example.socialntw.dto.AreaDto;
import com.example.socialntw.entity.User;

import java.util.List;

public interface AreaService {
    AreaDto addArea(User user, AreaDto areaDto);
    AreaDto updateArea(Integer id, AreaDto areaDto);
    void deleteArea(Integer id);
    AreaDto getArea(Integer id);
    List<AreaDto> getAreas();
    List<AreaDto> getAreasByUser(Integer userId);
    AreaDto getAreaById(Integer areaId);
}