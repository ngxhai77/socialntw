package com.example.socialntw.service.implement;

import com.example.socialntw.dto.ModeratorDto;
import com.example.socialntw.entity.Area;
import com.example.socialntw.entity.Moderator;
import com.example.socialntw.entity.User;
import com.example.socialntw.mapper.ModeratorMapper;
import com.example.socialntw.repository.AreaRepository;
import com.example.socialntw.repository.ModeratorRepository;
import com.example.socialntw.repository.PostRepository;
import com.example.socialntw.repository.UserRepository;
import com.example.socialntw.service.ModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.socialntw.enums.UserType.CREATOR;

@Service
public class ModeratorServiceImpl implements ModeratorService {

    @Autowired
    private ModeratorRepository moderatorRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private ModeratorMapper moderatorMapper;

    @Override
    public ModeratorDto addModerator(ModeratorDto moderatorDTO) {
        User user = userRepository.findById(moderatorDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setUserType(CREATOR);
        userRepository.save(user);

        Moderator moderator = moderatorMapper.toEntity(moderatorDTO);
        moderator.setUser(user);

        Moderator savedModerator = moderatorRepository.save(moderator);
        return moderatorMapper.toDto(savedModerator);
    }

    @Override
    public void removeModerator(Integer moderatorId) {
        moderatorRepository.deleteById(moderatorId);
    }

    @Override
    public List<Moderator> getAllModerators() {
        return moderatorRepository.findAll();
    }

    @Override
    public List<Moderator> getAllModeratorByAreaId(Integer areaId) {
        return moderatorRepository.findAllByAreaId(areaId);
    }


    @Override
    public void banUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setIsActive(false);
        userRepository.save(user);
    }

    @Override
    public void deletePost(Integer postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public void banArea(Integer areaId) {
        Area area = areaRepository.findById(areaId).orElseThrow(() -> new RuntimeException("Area not found"));
        area.setIsActive(false);
        areaRepository.save(area);
    }


}