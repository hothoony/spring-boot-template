package study.springbootbasic.boardExample;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {

    @Query("select b from Board b")
    Page<Board> find_Board_All_paging(Pageable pageable);

    @Query("select b from Board b")
    List<Board> find_Board_All_sorting(Sort sort);

}
