package jpabook.jpashop;

//import org.junit.Test; <- junit4 전용이라고 한다. junit5로 테스트 하는경우 import org.junit.jupiter.api.Test;로 바꿔야한다.
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@RunWith(SpringRunner.class) junit5에서는 사용되지 않음
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testMember() throws Exception {
        //given
        Member member = new Member();
        member.setUsername("memberA");

        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        //then
        // junit5
        assertEquals(findMember.getId(), member.getId());
        assertEquals(findMember.getUsername(), member.getUsername());
        // 같은 트렌젝션 안에서는 id가 같으면 영속성컨택스트에서 가져온다
        assertEquals(findMember, member);

        // junit4
//        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        // 같은 트렌젝션 안에서는 id가 같으면 영속성컨택스트에서 가져온다
//        Assertions.assertThat(findMember).isEqualTo(member);
    }
}