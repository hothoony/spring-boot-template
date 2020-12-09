package study.bootmybatistemplate.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    void notNull() {
        assertThat(memberService).isNotNull();
    }

    @Test
    void save_findById_deleteById() {
        long id = 1L;
        Member member = new Member(1L, "member1", 10);
        memberService.saveMember(member);
        Member findMember = memberService.findMemberById(id);
        assertThat(findMember).isEqualTo(member);

        memberService.deleteMemberById(id);
        findMember = memberService.findMemberById(id);
        assertThat(findMember).isNull();
    }

    @Test
    void findMembers() {
        saveMembers();

        List<Member> members = memberService.findMembers();
        assertThat(members.size()).isEqualTo(3);
    }

    @Test
    void deleteMembers() {
        saveMembers();

        List<Member> members = memberService.findMembers();
        assertThat(members.size()).isEqualTo(3);

        memberService.deleteMembers();

        members = memberService.findMembers();
        assertThat(members.size()).isEqualTo(0);
    }

    private void saveMembers() {
        memberService.saveMember(new Member(1L, "member1", 10));
        memberService.saveMember(new Member(2L, "member2", 20));
        memberService.saveMember(new Member(3L, "member3", 30));
    }
}
