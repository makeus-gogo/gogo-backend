package com.gogo.service.board;

import com.gogo.domain.answer.repository.AnswerRepository;
import com.gogo.domain.board.Board;
import com.gogo.domain.board.BoardCreatorCollection;
import com.gogo.domain.board.BoardRepository;
import com.gogo.domain.member.Member;
import com.gogo.domain.member.MemberRepository;
import com.gogo.service.board.dto.request.CreateBoardRequest;
import com.gogo.service.board.dto.response.*;
import com.gogo.service.hashtag.HashTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final HashTagService hashTagService;
    private final AnswerRepository answerRepository;

    @Transactional
    public BoardDetailInfoResponse createBoard(CreateBoardRequest request, Long memberId) {
        Board board = boardRepository.save(request.toEntity(memberId));
        List<String> hashTags = hashTagService.addHashTags(request.getHashTags(), board.getId(), memberId);
        return BoardDetailInfoResponse.of(board, hashTags);
    }

    @Transactional(readOnly = true)
    public List<BoardWithCreatorInfoResponse> getBoardsLessThanBoardId(Long lastBoardId, int size) {
        List<Board> boardList = BoardServiceUtils.findBoardsOrderByIdWithPagination(boardRepository, lastBoardId, size);
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
        if (StringUtils.isEmpty(keyword)) {
            return findPaginationBoards(boardRepository, lastBoardId, size);
        }
        return findPaginationBoardsWithKeyword(keyword, lastBoardId, size);
    }

    private List<BoardInfoResponse> findPaginationBoards(BoardRepository boardRepository, Long lastBoardId, int size) {
        List<Board> boardList = BoardServiceUtils.findBoardsOrderByIdWithPagination(boardRepository, lastBoardId, size);
        return boardList.stream()
            .map(BoardInfoResponse::of)
            .collect(Collectors.toList());
    }

    private List<BoardInfoResponse> findPaginationBoardsWithKeyword(String keyword, Long lastBoardId, int size) {
        List<Board> boardList = BoardServiceUtils.getBoardsByLikeKeyword(boardRepository, keyword, lastBoardId, size);
        return boardList.stream()
            .map(BoardInfoResponse::of)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BoardTop3Response> getTop3Board(){
        List<Board> boardList = boardRepository.findAll();
        List<BoardAnswerCountDto> boardAnswerCountDtoList = new ArrayList<>();
        for(int i=0;i<boardList.size();i++){
            int answerCount = answerRepository.countAllByBoardAndStatus(boardList.get(i),"ACTIVE");
            BoardAnswerCountDto boardAnswerCountDto = new BoardAnswerCountDto(answerCount,boardList.get(i));
            boardAnswerCountDtoList.add(boardAnswerCountDto);
        }

        Collections.sort(boardAnswerCountDtoList,new CompareBoardAnswerCount());

        List<BoardTop3Response> boardTop3ResponseList = new ArrayList<>();
        for(int i=0;i<3;i++){
            Long boardId = boardAnswerCountDtoList.get(i).getBoard().getId();
            Long memberId = boardAnswerCountDtoList.get(i).getBoard().getMemberId();
            Member member = memberRepository.findMemberById(memberId);
            String nickname = member.getName();
            String profileImageUrl = member.getProfileUrl();
            String description = boardAnswerCountDtoList.get(i).getBoard().getDescription();
            String boardImageUrl = boardAnswerCountDtoList.get(i).getBoard().getPictureUrl();
            BoardTop3Response boardTop3Response = new BoardTop3Response(boardId,memberId,nickname,profileImageUrl,description,boardImageUrl);
            boardTop3ResponseList.add(boardTop3Response);
        }
        return boardTop3ResponseList;
    }
}

class CompareBoardAnswerCount implements Comparator<BoardAnswerCountDto> {
    @Override
    public int compare(BoardAnswerCountDto o1, BoardAnswerCountDto o2) {
        if(o1.getAnswerCount() < o2.getAnswerCount()){
            return 1;
        }else if(o1.getAnswerCount() > o2.getAnswerCount()){
            return -1;
        }
        return 0;
    }
}
