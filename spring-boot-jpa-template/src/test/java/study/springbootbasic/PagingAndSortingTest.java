package study.springbootbasic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import study.springbootbasic.boardExample.Board;
import study.springbootbasic.boardExample.BoardRepository;

import java.util.List;

import static study.springbootbasic.utils.Util.*;

@SpringBootTest
public class PagingAndSortingTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    void paging_and_sorting() {
        Pageable pageable = PageRequest.of(0, 10,
                        Sort.by("content").descending()
                        .and(Sort.by("id").descending()));
        Page<Board> boardPage = boardRepository.find_Board_All_paging(pageable);

        long totalCount = boardPage.getTotalElements();
        int totalPages = boardPage.getTotalPages();
        List<Board> boards = boardPage.toList();

        System.out.println("totalCount = " + totalCount);
        System.out.println("totalPages = " + totalPages);

        System.out.println();
        for (Board board : boards) {
            System.out.println(board);
        }
    }

    @Test
    void sorting() {
        Sort sort = Sort.by("content").descending()
                .and(Sort.by("id").descending());
        List<Board> boards = boardRepository.find_Board_All_sorting(sort);

        System.out.println();
        for (Board board : boards) {
            System.out.println(board);
        }
    }

    @Test
    void paging() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Board> boardPage = boardRepository.find_Board_All_paging(pageable);

        long totalCount = boardPage.getTotalElements();
        int totalPages = boardPage.getTotalPages();
        List<Board> boards = boardPage.toList();

        System.out.println("totalCount = " + totalCount);
        System.out.println("totalPages = " + totalPages);

        System.out.println();
        for (Board board : boards) {
            System.out.println(board);
        }
    }

    @BeforeEach
    private void setUp() {
        int len = 100;
        Integer[] ints = createShuffledArray(len);
        for (int no = 1; no <= len; no++) {
            Board board = Board.builder()
                    .title("title" + no)
                    .content("content" + randomBetween(1, len))
                    .build();
            boardRepository.save(board);
        }
    }

}
