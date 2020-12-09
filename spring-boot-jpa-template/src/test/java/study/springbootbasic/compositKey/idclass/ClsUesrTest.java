package study.springbootbasic.compositKey.idclass;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
class ClsUesrTest {

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    @Commit
    void test1() {
        User user1 = new User("user1");
        em.persist(user1);

        Cls clsA = new Cls("clsA");
        em.persist(clsA);

        ClsUser clsUser = new ClsUser(clsA.getId(), user1.getId());
        em.persist(clsUser);
    }
}