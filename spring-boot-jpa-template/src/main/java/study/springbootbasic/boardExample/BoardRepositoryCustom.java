package study.springbootbasic.boardExample;

import java.util.List;

public interface BoardRepositoryCustom {

    List<Board> findAll_with_fetch_paging();
}
