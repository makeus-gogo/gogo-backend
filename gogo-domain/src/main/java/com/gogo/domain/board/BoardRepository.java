package com.gogo.domain.board;

import com.gogo.domain.board.repository.BoardRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {

}
