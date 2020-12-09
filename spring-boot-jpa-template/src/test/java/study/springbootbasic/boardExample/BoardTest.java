package study.springbootbasic.boardExample;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @Test
    void name() {
        Board board = new Board();
        assertThat(board).isNotNull();
    }

    @Test
    void copyProperties_test() {
        BoardController.BoardUpdateRequestDto boardUpdateRequestDto = new BoardController.BoardUpdateRequestDto();
        boardUpdateRequestDto.setContent("content 11");

        Board board = new Board();
        board.setId(1L);
        board.setTitle("title 11");

        System.out.println("boardDto = " + boardUpdateRequestDto);
        System.out.println("board    = " + board);

        BeanUtils.copyProperties(boardUpdateRequestDto, board);

        System.out.println("boardDto = " + boardUpdateRequestDto);
        System.out.println("board    = " + board);
    }

    @Test
    void modelMapper_test() {
        BoardController.BoardUpdateRequestDto boardUpdateRequestDto = new BoardController.BoardUpdateRequestDto();
        boardUpdateRequestDto.setContent("content 11");

        Board board = new Board();
        board.setId(1L);
        board.setTitle("title 11");

        System.out.println("boardDto = " + boardUpdateRequestDto);
        System.out.println("board    = " + board);

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(boardUpdateRequestDto, board);

        System.out.println("boardDto = " + boardUpdateRequestDto);
        System.out.println("board    = " + board);
    }
}
