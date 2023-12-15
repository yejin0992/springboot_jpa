package jpabook.jpashop;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.service.MemberService;

@RunWith(SpringRunner.class) //Junit 실행 시 스프링과 엮어서 실행
@SpringBootTest // spring container 안에서 Test 실행
@Transactional // 기본적으로 rollback
public class MemberserviceTest {

	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	@Autowired EntityManager em;
	
	@Test
	@Rollback(false) // rollback 하지않고 commit함 , console에 insert query 출력
	public void 회원가입() throws Exception {
		// given (이런게 주어졌을때)
		Member member = new Member();
		member.setName("kim");
		
		// when (이렇게 하면)
		Long saveId = memberService.join(member);
		
		// then (이렇게 된다. 검증해라)
		em.flush(); // 영속성 컨텍스트에 member가 들어간다음 flush하면 db에 반영
		assertEquals(member, memberRepository.findOne(saveId));
	}
	
	@Test(expected = IllegalStateException.class)
	public void 중복_회원_예외() throws Exception {
		// given
		Member member1 = new Member();
		member1.setName("kim");
		Member member2 = new Member();
		member2.setName("kim");
		
		// when
		memberService.join(member1);
		memberService.join(member2); // 예외가 발생해야 한다
		// then
		fail("예외가 발생해야 한다.");
	}


}
