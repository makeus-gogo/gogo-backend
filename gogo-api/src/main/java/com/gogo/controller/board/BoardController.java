package com.gogo.controller.board;

import com.gogo.config.resolver.LoginUser;
import com.gogo.controller.ApiResponse;
import com.gogo.service.board.BoardService;
import com.gogo.service.board.dto.request.CreateBoardRequest;
import com.gogo.service.board.dto.response.BoardDetailInfoResponse;
import com.gogo.service.board.dto.response.BoardInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/api/v1/board")
    public ApiResponse<BoardDetailInfoResponse> createMultiChoiceBoard(@Valid @RequestBody CreateBoardRequest request, @LoginUser Long memberId) {
        return ApiResponse.of(boardService.createBoard(request, memberId));
    }

    @GetMapping("/api/v1/board/list")
    public ApiResponse<List<BoardInfoResponse>> getBoards(@RequestParam Long lastBoardId, @RequestParam int size) {
        return ApiResponse.of(boardService.getBoardsLessThanBoardId(lastBoardId, size));
    }

    @GetMapping("/api/v1/board/{boardId}")
    public ApiResponse<BoardDetailInfoResponse> getBoard(@PathVariable Long boardId) {
        return ApiResponse.of(boardService.getBoardInfo(boardId));
    }

    @GetMapping("/api/v1/board/search")
    public ApiResponse<List<BoardInfoResponse>> searchBoardsByName(@RequestParam String keyword) {
        return ApiResponse.of(boardService.searchBoardsByKeyword(keyword));
    }

}
