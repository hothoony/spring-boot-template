package study.springbootbasic.boardExample;

import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Board> findAll_with_fetch_paging() {
        List<Board> boards = em.createQuery(
                "select b" +
                        " from Board b", Board.class)
                .getResultList();
        return boards;
    }

}
