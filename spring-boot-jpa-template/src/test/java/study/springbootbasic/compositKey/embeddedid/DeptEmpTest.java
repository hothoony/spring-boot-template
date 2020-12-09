package study.springbootbasic.compositKey.embeddedid;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeptEmpTest {

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    @Commit
    void test1() {
        Dept deptA = new Dept("deptA");
        em.persist(deptA);

        Emp emp1 = new Emp("emp1");
        em.persist(emp1);

        DeptEmp deptEmp = new DeptEmp(new DeptEmpId(deptA.getId(), emp1.getId()));
        em.persist(deptEmp);
    }
}