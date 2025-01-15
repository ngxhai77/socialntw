package com.example.socialntw.mapper;

import com.example.socialntw.dto.ReportDto;
import com.example.socialntw.entity.Area;
import com.example.socialntw.entity.Report;
import com.example.socialntw.service.AreaService;
import com.example.socialntw.service.CommentService;
import com.example.socialntw.service.PostService;
import com.example.socialntw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class ReportMapper {


    private static UserService userService;

    private static UserMapper userMapper;
    private static CommentService commentService;
    private static PostMapper postMapper;
    private static CommentMapper commentMapper;
    private static AreaMapper areaMapper;
    private static AreaService areaService;
    private static PostService postService;

    public static Report toEntity(ReportDto reportDto) {
        Report report = new Report();
        report.setUser(userMapper.toCreateEntity(userService.getUserById(reportDto.getUserId())));
        report.setArea(areaMapper.toEntity(areaService.getAreaById(reportDto.getAreaId())));
        report.setIsResolved(false);
        report.setComment(commentMapper.toEntity(commentService.getCommentById(reportDto.getCommentId())));
        report.setPost(postMapper.toEntity(postService.getPostById(reportDto.getPostId())));

        report.setReason(reportDto.getReason());
        return report;
    }

    public static ReportDto toDto(Report report) {
        ReportDto reportDto = new ReportDto();
        reportDto.setUserId(report.getUser().getId());
        reportDto.setPostId(report.getPost().getPostId());
        reportDto.setCommentId(report.getComment().getCommentId());
        reportDto.setAreaId(report.getArea().getAreaId());
        reportDto.setReason(report.getReason());
        return reportDto;
    }
}
