package com.gogo.controller.board;

import com.gogo.config.resolver.LoginUser;
import com.gogo.controller.ApiResponse;
import com.gogo.service.answer.AnswerService;
import com.gogo.service.answer.dto.response.AnswerResultResponse;
import com.gogo.service.board.BoardService;
import com.gogo.service.board.dto.request.CreateBoardRequest;
import com.gogo.service.board.dto.response.BoardDetailInfoResponse;
import com.gogo.service.board.dto.response.BoardInfoResponse;
import com.gogo.service.board.dto.response.BoardTop3Response;
import com.gogo.service.board.dto.response.BoardWithCreatorInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;
    private final AnswerService answerService;

    @Operation(summary = "고민 게시물을 업로드하는 API", description = "Bearer 토큰이 필요합니다")
    @PostMapping("/api/v1/board")
    public ApiResponse<BoardDetailInfoResponse> createMultiChoiceBoard(@Valid @RequestBody CreateBoardRequest request, @LoginUser Long memberId) {
        return ApiResponse.of(boardService.createBoard(request, memberId));
    }

    @Operation(summary = "메인 페이지에서 고민 리스트를 스크롤 페이지네이션으로 보여주는 API", description = "lastBoardId=가장 마지막에 보여지는 게시물의 id, size=몇 개의 게시물을 받아올 것인지")
    @GetMapping("/api/v1/board/list")
    public ApiResponse<List<BoardWithCreatorInfoResponse>> getBoards(@RequestParam Long lastBoardId, @RequestParam int size) {
        return ApiResponse.of(boardService.getBoardsLessThanBoardId(lastBoardId, size));
    }

    @Operation(summary = "특정 고민 게시물을 상세하게 조회하는 API")
    @GetMapping("/api/v1/board/{boardId}")
    public ApiResponse<AnswerResultResponse> getBoard(@PathVariable Long boardId,
                                                       @LoginUser Long memberId){
        return new ApiResponse("","",answerService.getAnswer(boardId,memberId));
    }

    @Operation(summary = "고민 게시물들을 키워드로 검색하는 API")
    @GetMapping("/api/v1/board/search")
    public ApiResponse<List<BoardInfoResponse>> searchBoardsByName(@RequestParam(required = false) String keyword, @RequestParam Long lastBoardId, @RequestParam int size) {
        return ApiResponse.of(boardService.searchBoardsByKeyword(keyword, lastBoardId, size));
    }

    @Operation(summary = "TOP3 고민 게시물 조회 API")
    @GetMapping("/api/v1/board/top")
    public ApiResponse<List<BoardTop3Response>> getTop3Board(){
        return new ApiResponse("","",boardService.getTop3Board());
    }
}
