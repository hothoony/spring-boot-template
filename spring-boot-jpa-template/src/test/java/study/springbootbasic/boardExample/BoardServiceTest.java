package study.springbootbasic.boardExample;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardService boardService;

    @Test
    void saveTest() {
        Board board = Board.builder().title("test").build();
        given(boardRepository.findById(anyLong()))
                .willReturn(Optional.of(board));

        Board findBoard = boardService.findOne(1L).get();

        assertThat(findBoard.getTitle()).isEqualTo("test");
    }

    @Test
    @Transactional
    void findTest() {
        Board board1 = Board.builder().title("title1").build();
        Board board2 = Board.builder().title("title2").build();
        Board board3 = Board.builder().title("title3").build();
        List<Board> list = Arrays.asList(board1, board2, board3);

        given(boardRepository.findAll()).willReturn(list);

        List<Board> boards = boardService.findAll();

        assertThat(boards)
                .hasSize(3)
                .extracting("title")
                .containsExactly("title1", "title2", "title3");
    }
}
