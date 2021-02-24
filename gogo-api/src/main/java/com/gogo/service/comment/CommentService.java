package com.gogo.service.comment;

import com.gogo.domain.board.Board;
import com.gogo.domain.comment.Comment;
import com.gogo.domain.comment.repository.CommentRepository;
import com.gogo.service.board.BoardService;
import com.gogo.service.board.dto.response.BoardDetailInfoResponse;
import com.gogo.service.comment.dto.request.CreateCommentRequest;
import com.gogo.service.comment.dto.response.CommentInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public CommentInfoResponse createComment(CreateCommentRequest createCommentRequest, String uuid, Long memberId){
        String description = createCommentRequest.getDescription();
        Comment comment = new Comment(memberId,uuid,description);
        commentRepository.save(comment);
        return CommentInfoResponse.of(comment);
    }
}
