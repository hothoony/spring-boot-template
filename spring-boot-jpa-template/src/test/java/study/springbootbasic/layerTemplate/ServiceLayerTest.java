package study.springbootbasic.layerTemplate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import study.springbootbasic.boardExample.Board;
import study.springbootbasic.boardExample.BoardRepository;
import study.springbootbasic.boardExample.BoardService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ServiceLayerTest {

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardService boardService;

    @Test
    void saveTest() {
        Board board = Board.builder().title("test").build();
        given(boardRepository.findById(anyLong()))
                .willReturn(Optional.of(board));

        Board findBoard = boardService.findOne(anyLong()).get();

        assertThat(findBoard.getTitle()).isEqualTo("test");
    }
}
