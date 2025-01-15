package com.example.socialntw.service.implement;

import com.example.socialntw.dto.VoteDto;
import com.example.socialntw.entity.Post;
import com.example.socialntw.entity.Comment;
import com.example.socialntw.entity.User;
import com.example.socialntw.entity.Vote;
import com.example.socialntw.enums.VoteType;
import com.example.socialntw.exception.NotFoundException;
import com.example.socialntw.repository.PostRepository;
import com.example.socialntw.repository.CommentRepository;
import com.example.socialntw.repository.VoteRepository;
import com.example.socialntw.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void votePost(User user, VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new NotFoundException("Post not found"));
        Vote vote = new Vote();
        vote.setUser(user);
        vote.setPost(post);
        vote.setVoteType(voteDto.getVoteType());
        voteRepository.save(vote);
    }

    @Override
    public void voteComment(User user, VoteDto voteDto) {
        Comment comment = commentRepository.findById(voteDto.getCommentId())
                .orElseThrow(() -> new NotFoundException("Comment not found"));
        Vote vote = new Vote();
        vote.setUser(user);
        vote.setComment(comment);
        vote.setVoteType(voteDto.getVoteType());
        voteRepository.save(vote);
    }

    @Override
    public int getPostVoteScore(Integer postId) {
        long upvotes = voteRepository.countByPostIdAndVoteType(postId, VoteType.UPVOTE);
        long downvotes = voteRepository.countByPostIdAndVoteType(postId, VoteType.DOWNVOTE);
        return (int) (upvotes - downvotes);
    }

    @Override
    public int getCommentVoteScore(Integer commentId) {
        long upvotes = voteRepository.countByCommentIdAndVoteType(commentId, VoteType.UPVOTE);
        long downvotes = voteRepository.countByCommentIdAndVoteType(commentId, VoteType.DOWNVOTE);
        return (int) (upvotes - downvotes);
    }
}