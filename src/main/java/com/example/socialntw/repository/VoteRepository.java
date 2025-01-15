package com.example.socialntw.repository;

import com.example.socialntw.entity.Vote;
import com.example.socialntw.enums.VoteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
    @Query("SELECT v FROM Vote v WHERE v.post.postId = :postId")
    List<Vote> findByPostId(@Param("postId") Integer postId);

    @Query("SELECT v FROM Vote v WHERE v.comment.commentId = :commentId")
    List<Vote> findByCommentId(@Param("commentId") Integer commentId);

    @Query("SELECT COUNT(v) FROM Vote v WHERE v.post.postId = :postId AND v.voteType = :voteType")
    long countByPostIdAndVoteType(@Param("postId") Integer postId, @Param("voteType") VoteType voteType);

    @Query("SELECT COUNT(v) FROM Vote v WHERE v.comment.commentId = :commentId AND v.voteType = :voteType")
    long countByCommentIdAndVoteType(@Param("commentId") Integer commentId, @Param("voteType") VoteType voteType);
}