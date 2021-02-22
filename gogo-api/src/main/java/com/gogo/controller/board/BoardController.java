package com.gogo.controller.board;

import com.gogo.controller.ApiResponse;
import com.gogo.service.board.BoardService;
import com.gogo.service.board.dto.request.CreateBoardRequest;
import com.gogo.service.board.dto.response.BoardInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    // TODO 차후 토큰 -> memberId로 변경해야함.
    @PostMapping("/api/v1/board")
    public ApiResponse<BoardInfoResponse> createBoard(@Valid @RequestBody CreateBoardRequest request, Long memberId) {
        return ApiResponse.of(boardService.createBoard(request, memberId));
    }

    // TODO 차후 페이지네이션 등 구현해야함.
    @GetMapping("/api/v1/board/list")
    public ApiResponse<List<BoardInfoResponse>> getBoards() {
        return ApiResponse.of(boardService.getBoards());
    }

    @GetMapping("/api/v1/board/{uuid}")
    public ApiResponse<BoardInfoResponse> getBoard(@PathVariable String uuid) {
        return ApiResponse.of(boardService.getBoardInfo(uuid));
    }

}
