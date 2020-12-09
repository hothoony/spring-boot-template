package study.bootmybatistemplate.member;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public Long save(MemberDto memberDto) {
        Member member = new Member();
        member.setName(memberDto.getName());
        member.setAge(memberDto.getAge());
        memberService.saveMember(member);
        return member.getId();
    }

    @PostMapping("/member/{id}")
    public void update(@PathVariable("id") Long id, MemberDto memberDto) {
        memberService.update(id, memberDto);
    }

    @GetMapping("/member")
    public List<Member> findAll() {
        List<Member> members = memberService.findMembers();
        return members;
    }

    @GetMapping("/member/{id}")
    public Member findMember(@PathVariable("id") Long id) {
        Member member = memberService.findMemberById(id);
        return member;
    }
}
