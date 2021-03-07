package com.gogo.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardContentRepository extends JpaRepository<BoardContent, Long> {
    BoardContent findBoardContentById(Long id);
    List<BoardContent> findAllByBoard(Board board);
}
