package com.gogo.service.board;

import com.gogo.domain.board.Board;
import com.gogo.domain.board.BoardCreatorCollection;
import com.gogo.domain.board.BoardRepository;
import com.gogo.domain.member.MemberRepository;
import com.gogo.service.board.dto.request.CreateBoardRequest;
import com.gogo.service.board.dto.response.BoardDetailInfoResponse;
import com.gogo.service.board.dto.response.BoardInfoResponse;
import com.gogo.service.board.dto.response.BoardWithCreatorInfoResponse;
import com.gogo.service.hashtag.HashTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final HashTagService hashTagService;

    @Transactional
    public BoardDetailInfoResponse createBoard(CreateBoardRequest request, Long memberId) {
        Board board = boardRepository.save(request.toEntity(memberId));
        List<String> hashTags = hashTagService.addHashTags(request.getHashTags(), board.getId(), memberId);
        return BoardDetailInfoResponse.of(board, hashTags);
    }

    @Transactional(readOnly = true)
    public List<BoardWithCreatorInfoResponse> getBoardsLessThanBoardId(Long lastBoardId, int size) {
        return lastBoardId == 0 ? getLatestBoards(size) : getLatestBoardLessThanId(lastBoardId, size);
    }

    private List<BoardWithCreatorInfoResponse> getLatestBoards(int size) {
        List<Board> boardList = boardRepository.findBoardsOrderByIdDesc(size);
        BoardCreatorCollection collection = BoardCreatorCollection.of(memberRepository, boardList);
        return boardList.stream()
            .map(board -> BoardWithCreatorInfoResponse.of(board, collection.getCreator(board.getMemberId())))
            .collect(Collectors.toList());
    }

    private List<BoardWithCreatorInfoResponse> getLatestBoardLessThanId(Long lastBoardId, int size) {
        List<Board> boardList = boardRepository.findBoardsLessThanOrderByIdDescLimit(lastBoardId, size);
        BoardCreatorCollection collection = BoardCreatorCollection.of(memberRepository, boardList);
        return boardList.stream()
            .map(board -> BoardWithCreatorInfoResponse.of(board, collection.getCreator(board.getMemberId())))
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BoardDetailInfoResponse getBoardInfo(Long boardId) {
        Board board = BoardServiceUtils.findBoardById(boardRepository, boardId);
        List<String> hashTags = hashTagService.retrieveHashTagsInBoard(boardId);
        return BoardDetailInfoResponse.of(board, hashTags);
    }

    @Transactional(readOnly = true)
    public List<BoardInfoResponse> searchBoardsByKeyword(String keyword, Long lastBoardId, int size) {
        if (lastBoardId == 0) {
            return boardRepository.findLastBoardsByLikeTitle(keyword, size).stream()
                .map(BoardInfoResponse::of)
                .collect(Collectors.toList());
        }
        return boardRepository.findBoardsByLikeTitle(keyword, lastBoardId, size).stream()
            .map(BoardInfoResponse::of)
            .collect(Collectors.toList());
    }

}
