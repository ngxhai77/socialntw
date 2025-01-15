package com.example.socialntw.service.implement;

import com.example.socialntw.dto.AreaDto;
import com.example.socialntw.entity.Area;
import com.example.socialntw.entity.User;
import com.example.socialntw.exception.NotFoundException;
import com.example.socialntw.mapper.AreaMapper;
import com.example.socialntw.repository.AreaRepository;
import com.example.socialntw.repository.UserRepository;
import com.example.socialntw.service.AreaService;
import com.example.socialntw.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AreaDto addArea(User user,  AreaDto areaDto) {
        Area area = AreaMapper.toEntity(areaDto);
        area.setCreatedBy(user);
        areaRepository.save(area);
        return AreaMapper.toDto(area);
    }

    @Override
    public AreaDto updateArea(Integer id, AreaDto areaDto) {
        Area area = areaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Area not found"));
        area.setName(areaDto.getName());
        area.setDescription(areaDto.getDescription());
        areaRepository.save(area);
        return AreaMapper.toDto(area);
    }

    @Override
    public void deleteArea(Integer id) {
        Area area = areaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Area not found"));
        areaRepository.delete(area);
    }

    @Override
    public AreaDto getArea(Integer id) {
        Area area = areaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Area not found"));
        return AreaMapper.toDto(area);
    }

    @Override
    public List<AreaDto> getAreas() {
        List<Area> areas = areaRepository.findAll();
        return areas.stream().map(AreaMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<AreaDto> getAreasByUser(Integer userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new NotFoundException("User not found"));
        List<Area> areas = areaRepository.findByCreatedBy(userId);
        return areas.stream().map(AreaMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public AreaDto getAreaById(Integer areaId) {
        Area area = areaRepository.findById(areaId)
                .orElseThrow(() -> new NotFoundException("Area not found"));
        return AreaMapper.toDto(area);
    }


}