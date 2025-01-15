package com.example.socialntw.service;


import com.example.socialntw.dto.ModeratorDto;
import com.example.socialntw.entity.Moderator;

import java.util.List;

public interface ModeratorService {
    ModeratorDto addModerator(ModeratorDto moderatorDto);
    void removeModerator(Integer moderatorId);
    List<Moderator> getAllModerators();
    List<Moderator> getAllModeratorByAreaId(Integer areaId);

    void banUser(Integer userId);
    void deletePost(Integer postId);
    void banArea(Integer areaId);
}