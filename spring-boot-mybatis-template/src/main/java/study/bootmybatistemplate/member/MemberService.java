package study.bootmybatistemplate.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void saveMember(Member member) {
        memberRepository.save(member);
    }

    public Member findMemberById(Long id) {
        Member member = memberRepository.findById(id);
        return member;
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Transactional
    public void deleteMemberById(Long id) {
        memberRepository.deleteById(id);
    }

    @Transactional
    public void deleteMembers() {
        memberRepository.deleteAll();
    }

    @Transactional
    public void update(Long id, MemberDto memberDto) {
        Member findMember = memberRepository.findById(id);
        findMember.setName(memberDto.getName());
        findMember.setAge(memberDto.getAge());
        memberRepository.update(id, findMember);
    }
}
