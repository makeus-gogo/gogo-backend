package com.gogo.service.board;

import com.gogo.domain.board.Board;
import com.gogo.domain.board.BoardRepository;
import com.gogo.domain.hashtag.HashTag;
import com.gogo.domain.hashtag.HashTagRepository;
import com.gogo.service.board.dto.request.CreateBoardRequest;
import com.gogo.service.board.dto.response.BoardDetailInfoResponse;
import com.gogo.service.board.dto.response.BoardInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final HashTagRepository hashTagRepository;

    @Transactional
    public BoardInfoResponse createBoard(CreateBoardRequest request, Long memberId) {
        Board board = boardRepository.save(request.toEntity(memberId));
        hashTagRepository.saveAll(request.toHashTagEntity(board.getId(), memberId));
        return BoardInfoResponse.of(board);
    }

    @Transactional(readOnly = true)
    public List<BoardInfoResponse> getBoards() {
        return boardRepository.findAllBoards().stream()
            .map(BoardInfoResponse::of)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BoardDetailInfoResponse getBoardInfo(String uuid) {
        Board board = findBoardByUuid(boardRepository, uuid);
        List<String> hashTags = hashTagRepository.getAllHashTagByBoardId(board.getId()).stream()
            .map(HashTag::getTag)
            .collect(Collectors.toList());
        return BoardDetailInfoResponse.of(board, hashTags);
    }

    private Board findBoardByUuid(BoardRepository boardRepository, String uuid) {
        Board board = boardRepository.findBoardByUuid(uuid);
        if (board == null) {
            throw new IllegalArgumentException(String.format("해당하는 (%s)의 게시판은 존재하지 않습니다", uuid));
        }
        return board;
    }

}
