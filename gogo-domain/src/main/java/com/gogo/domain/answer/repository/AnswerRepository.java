package com.gogo.domain.answer.repository;

import com.gogo.domain.answer.Answer;
import com.gogo.domain.board.Board;
import com.gogo.domain.board.BoardContent;
import com.gogo.domain.member.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends CrudRepository<Answer,Long> {
    Answer findAnswerByMemberAndBoardContentAndStatus(Member member, BoardContent boardContent,String status);
    Answer findAnswerByMemberAndBoardAndStatus(Member member, Board board, String status);
    int countAllByBoardContentAndStatus(BoardContent boardContent,String status);
    int countAllByBoardAndStatus(Board board,String status);
    //Answer findAnswerByBoardContentAndStatus(BoardContent boardContent,String status);
}
