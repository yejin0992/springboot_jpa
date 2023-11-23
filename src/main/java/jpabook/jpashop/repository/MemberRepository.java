package jpabook.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;

@Repository // 컴포넌트 스캔에 의해 자동으로 springbean으로 관리
@RequiredArgsConstructor
public class MemberRepository {

	// @PersistenceContext // JPA의 EntityManager를 Spring이 생성한 EntityManager에 주입
	// 원래 엔티티 매니저는 @persistenceContext 어노테이션을 사용해야 하는데 스프링부트 jpa가 autowired 지원하기 때문에
	// 생성자로 넣을 멤버필드에 final 넣어주고 @RequiredArgsConstructor 어노테이션 사용할 수 있음
	private final EntityManager em;
	
	public void save(Member member) {
		em.persist(member);
		// 영속성 컨텍스트에 member 엔티티 넣음, 나중에 commit 되는 시점에 db insert 반영
	}
	
	public Member findOne(Long id) {
		// findOne : 단건조회, (Type, PK)
		return em.find(Member.class, id);
	}
	
	public List<Member> findAll(){
		List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
		// JPQL 사용, from의 대상이 테이블이 아닌 엔티티
		return result;
	}
	
	public List<Member> findByName(String name){
		return em.createQuery("select m from Member m where m.name = :name", Member.class)
				.setParameter("name", name)
				.getResultList();
	}
}
