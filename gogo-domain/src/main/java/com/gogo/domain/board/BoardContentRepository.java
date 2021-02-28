package com.gogo.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardContentRepository extends JpaRepository<BoardContent, Long> {
    BoardContent findBoardContentById(Long id);
}
