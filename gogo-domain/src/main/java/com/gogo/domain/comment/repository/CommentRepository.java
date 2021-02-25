package com.gogo.domain.comment.repository;

import com.gogo.domain.comment.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment,Long> {
    List<Comment> findAllByUuidAndStatus(String uuid, String status);
    Comment findCommentByIdAndStatus(Long commentId,String status);
}
