package study.springbootbasic.mock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import study.springbootbasic.boardExample.Board;
import study.springbootbasic.boardExample.BoardRepository;
import study.springbootbasic.boardExample.BoardService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BoardService.class})
class MockTest3 {

    @MockBean
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

    @Test
    void test() {
        Board board = Board.builder().title("test").build();
        given(boardRepository.findById(anyLong()))
                .willReturn(Optional.of(board));

        Board findBoard = boardService.findOne(anyLong()).get();

        assertThat(findBoard.getTitle()).isEqualTo("test");
    }
}
