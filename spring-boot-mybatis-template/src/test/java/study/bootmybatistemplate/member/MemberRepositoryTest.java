package study.bootmybatistemplate.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

//    @BeforeEach
//    private void setUp() {
//        memberRepository.deleteAll();
//    }

    @Test
    void notNull() {
        assertThat(memberRepository).isNotNull();
    }

    @Test
    void save() {
        long id = 1L;
        Member member = new Member(id, "member1", 10);

        Long cnt = memberRepository.save(member);
        assertThat(cnt).isEqualTo(1);

        Member findMember = memberRepository.findById(id);
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    void update() {
        long id = 1L;
        Member member = new Member(id, "member1", 10);
        memberRepository.save(member);

        Member findMember = memberRepository.findById(id);
        assertThat(findMember).isEqualTo(member);

        findMember.setName("member22");
        findMember.setAge(22);
        memberRepository.update(id, findMember);

        findMember = memberRepository.findById(id);
        assertThat(findMember.getName()).isEqualTo("member22");
        assertThat(findMember.getAge()).isEqualTo(22);
    }

    @Test
    void deleteById_deleteAll() {
        Long cnt = saveMembers();
        assertThat(cnt).isEqualTo(3);

        cnt = memberRepository.deleteById(1L);
        assertThat(cnt).isEqualTo(1);

        cnt = memberRepository.deleteAll();
        assertThat(cnt).isEqualTo(2);
    }

    @Test
    void findById_findAll() {
        Long cnt = saveMembers();
        assertThat(cnt).isEqualTo(3);

        Member findMember = memberRepository.findById(1L);
        assertThat(findMember.getName()).isEqualTo("member1");
        assertThat(findMember.getAge()).isEqualTo(10);

        List<Member> members = memberRepository.findAll();
        assertThat(members.size()).isEqualTo(3);
    }

    private Long saveMembers() {
        Long cnt = 0L;
        cnt += memberRepository.save(new Member(1L, "member1", 10));
        cnt += memberRepository.save(new Member(2L, "member2", 20));
        cnt += memberRepository.save(new Member(3L, "member3", 30));
        return cnt;
    }
}
