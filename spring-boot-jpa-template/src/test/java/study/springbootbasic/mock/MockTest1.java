package study.springbootbasic.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import study.springbootbasic.boardExample.Board;
import study.springbootbasic.boardExample.BoardRepository;
import study.springbootbasic.boardExample.BoardService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

class MockTest1 {

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardService boardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void test() {
        Board board = Board.builder().title("test").build();
        given(boardRepository.findById(anyLong()))
                .willReturn(Optional.of(board));

        Board findBoard = boardService.findOne(anyLong()).get();

        assertThat(findBoard.getTitle()).isEqualTo("test");
    }
}
