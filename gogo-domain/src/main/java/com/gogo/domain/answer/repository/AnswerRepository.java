package com.gogo.domain.answer.repository;

import com.gogo.domain.answer.Answer;
import com.gogo.domain.board.BoardContent;
import com.gogo.domain.member.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends CrudRepository<Answer,Long> {
    List<Answer> findAllByMemberAndBoardContentAndStatus(Member member, BoardContent boardContent,String status);
}
