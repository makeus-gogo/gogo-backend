package com.gogo.service.answer;

import com.gogo.domain.answer.Answer;
import com.gogo.domain.answer.repository.AnswerRepository;
import com.gogo.domain.board.Board;
import com.gogo.domain.board.BoardContent;
import com.gogo.domain.board.BoardContentRepository;
import com.gogo.domain.board.BoardRepository;
import com.gogo.domain.comment.Comment;
import com.gogo.domain.member.Member;
import com.gogo.domain.member.MemberRepository;
import com.gogo.exception.NotFoundException;
import com.gogo.service.answer.dto.request.CreateAnswerRequest;
import com.gogo.service.answer.dto.request.PatchAnswerRequest;
import com.gogo.service.answer.dto.response.AnswerInfoResponse;
import com.gogo.service.answer.dto.response.AnswerResultDto;
import com.gogo.service.answer.dto.response.AnswerResultResponse;
import com.gogo.service.comment.dto.request.CreateCommentRequest;
import com.gogo.service.comment.dto.response.CommentInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final MemberRepository memberRepository;
    private final BoardContentRepository boardContentRepository;
    private final AnswerRepository answerRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public AnswerInfoResponse createAnswer(CreateAnswerRequest createAnswerRequest, Long memberId, Long boardId) {
        Member member = memberRepository.findMemberById(memberId);

        Board board = boardRepository.findBoardById(boardId);
        if (board == null) {
            throw new NotFoundException(String.format("해당하는 (%s)의 게시판은 존재하지 않습니다", boardId), "해당하는 게시판은 존재하지 않습니다");
        }
        if (board.getMemberId() == member.getId()) {
            throw new NotFoundException(String.format("본인의 고민글에 답변할 수는 없습니다."), "본인의 고민글에 답변할 수는 없습니다.");
        }

        BoardContent boardContent = boardContentRepository.findBoardContentById(createAnswerRequest.getContentId());
        if (boardContent == null) {
            throw new NotFoundException(String.format("해당하는 선택지는 없습니다"), "해당하는 선택지는 없습니다");
        }

        Answer existingAnswer = answerRepository.findAnswerByMemberAndBoardContentAndStatus(member, boardContent, "ACTIVE");
        if (existingAnswer != null) {
            throw new NotFoundException(String.format("이미 해당 답변을 고른적이 있습니다."), "이미 해당 답변을 고른적이 있습니다.");
        }
        //이미 다른 해당 게시물에 대해 답변을 한 경우
        Answer existingBoardAnswer = answerRepository.findAnswerByMemberAndBoardAndStatus(member, board, "ACTIVE");
        if (existingBoardAnswer != null) {
            throw new NotFoundException(String.format("이미 해당 게시물에 답변을 고른적이 있습니다."), "이미 해당 게시물에 답변을 고른적이 있습니다.");
        }


        Answer answer = new Answer(member, board, boardContent);
        answerRepository.save(answer);
        return AnswerInfoResponse.of(answer);
    }

    @Transactional
    public AnswerInfoResponse patchAnswer(PatchAnswerRequest patchAnswerRequest, Long memberId, Long boardId) {
        Member member = memberRepository.findMemberById(memberId);

        BoardContent boardContent = boardContentRepository.findBoardContentById(patchAnswerRequest.getContentId());
        if (boardContent == null) {
            throw new NotFoundException(String.format("해당하는 선택지는 없습니다"), "해당하는 선택지는 없습니다");
        }

        Board board = boardRepository.findBoardById(boardId);
        if (board == null) {
            throw new NotFoundException(String.format("해당하는 (%s)의 게시판은 존재하지 않습니다", boardId), "해당하는 게시판은 존재하지 않습니다");
        }

        Answer answer = answerRepository.findAnswerByMemberAndBoardAndStatus(member, board, "ACTIVE");
        answer.setBoardContent(boardContent);
        answerRepository.save(answer);

        return AnswerInfoResponse.of(answer);
    }

    @Transactional
    public AnswerResultResponse getAnswer(Long boardId) {
        Board board = boardRepository.findBoardById(boardId);
        if (board == null) {
            throw new NotFoundException(String.format("해당하는 (%s)의 게시판은 존재하지 않습니다", boardId), "해당하는 게시판은 존재하지 않습니다");
        }

        List<BoardContent> boardContentList = boardContentRepository.findAllByBoard(board);
        int totalAnswerCount = answerRepository.countAllByBoardAndStatus(board, "ACTIVE");
        List<AnswerResultDto> answerResultDtoList = new ArrayList<>();
        for (int i = 0; i < boardContentList.size(); i++) {
            BoardContent boardContent = boardContentList.get(i);
            Long contentId = boardContent.getId();
            String content = boardContent.getContent();
            int answerCount = answerRepository.countAllByBoardContentAndStatus(boardContent, "ACTIVE");
            double percentage = 0.0;

            if (totalAnswerCount != 0) {
                percentage = ((double) answerCount / (double) totalAnswerCount) * 100;
                percentage = Math.round((percentage * 100) / 100.0);
            }
            AnswerResultDto answerResultDto = new AnswerResultDto(contentId, content, percentage);
            answerResultDtoList.add(answerResultDto);
        }

        Long id = board.getId();
        String description = board.getDescription();
        AnswerResultResponse answerResultResponse = new AnswerResultResponse(id, description, answerResultDtoList);
        return answerResultResponse;
    }

}
