package com.example.socialntw.mapper;


import com.example.socialntw.dto.AreaDto;
import com.example.socialntw.entity.Area;

public class AreaMapper {
    public static Area toEntity(AreaDto areaDto) {
        Area area = new Area();
        area.setName(areaDto.getName());
        area.setDescription(areaDto.getDescription());
        return area;
    }

    public static AreaDto toDto(Area area) {
        AreaDto areaDto = new AreaDto();
        areaDto.setId(area.getAreaId());
        areaDto.setName(area.getName());
        areaDto.setDescription(area.getDescription());
        return areaDto;
    }
}