package com.gogo.service.comment;

import com.gogo.domain.board.Board;
import com.gogo.domain.board.BoardRepository;
import com.gogo.domain.comment.Comment;
import com.gogo.domain.comment.repository.CommentRepository;
import com.gogo.service.comment.dto.request.CreateCommentRequest;
import com.gogo.service.comment.dto.request.UpdateCommentRequest;
import com.gogo.service.comment.dto.response.CommentInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public CommentInfoResponse createComment(CreateCommentRequest createCommentRequest, Long boardId, Long memberId) {
        String description = createCommentRequest.getDescription();
        Comment comment = new Comment(memberId, boardId, description);
        commentRepository.save(comment);
        return CommentInfoResponse.of(comment);
    }

    //todo 임시적인 페이징 -> 추후 수정 예정
    @Transactional(readOnly = true)
    public List<CommentInfoResponse> getCommentList(Long boardId) {
        //Pageable pageable = PageRequest.of(page-1,size,Sort.by(Sort.Direction.ASC,"commentIdx"));
        Board board = boardRepository.findBoardById(boardId);
        if (board == null) {
            throw new IllegalArgumentException(String.format("해당하는 (%s)의 게시판은 존재하지 않습니다", boardId));
        }
        return commentRepository.findAllByBoardIdAndStatus(boardId, "ACTIVE").stream()
            .map(CommentInfoResponse::of)
            .collect(Collectors.toList());
    }

    @Transactional
    public CommentInfoResponse updateComment(UpdateCommentRequest updateCommentRequest, Long commentId, Long memberId) {
        String description = updateCommentRequest.getDescription();
        Comment comment = commentRepository.findCommentByIdAndStatus(commentId, "ACTIVE");
        if (comment == null) {
            throw new IllegalArgumentException(String.format("해당 댓글은 존재하지 않습니다."));
        }
        if (comment.getMemberId() != memberId) {
            throw new IllegalArgumentException(String.format("본인이 쓴 댓글이 아닙니다."));
        }
        comment.setDescription(description);
        commentRepository.save(comment);
        return CommentInfoResponse.of(comment);
    }

    @Transactional
    public void deleteComment(Long commentId, Long memberId) {
        Comment comment = commentRepository.findCommentByIdAndStatus(commentId, "ACTIVE");
        if (comment == null) {
            throw new IllegalArgumentException(String.format("해당 댓글은 존재하지 않습니다."));
        }
        if (comment.getMemberId() != memberId) {
            throw new IllegalArgumentException(String.format("본인이 쓴 댓글이 아닙니다."));
        }
        comment.setStatus("INACTIVE");
        commentRepository.save(comment);
    }
}
