package study.bootmybatistemplate.member;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
@Mapper
public interface MemberRepository {

    Long save(Member member);
    Member findById(Long id);
    List<Member> findAll();
    Long update(@Param("id2") Long id3, @Param("member2") Member member3);
    Long deleteById(Long id);
    Long deleteAll();

}
