package study.springbootbasic.layerTemplate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import study.springbootbasic.boardExample.Board;
import study.springbootbasic.boardExample.BoardRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RepositoryLayerTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void test() {
        assertThat(boardRepository).isNotNull();

        String title = "title11";
        Board board = Board.builder().title(title).build();

        Board saveBoard = boardRepository.save(board);
        Optional<Board> optBoard = boardRepository.findById(saveBoard.getId());
        Board findBoard = optBoard.orElse(Board.builder().title("").build());

        assertThat(findBoard.getId()).isEqualTo(saveBoard.getId());
        assertThat(findBoard.getTitle()).isEqualTo(saveBoard.getTitle());
        assertThat(findBoard.getTitle()).isEqualTo(title);
    }
}
