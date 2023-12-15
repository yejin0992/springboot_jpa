package jpabook.jpashop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true) // 전체 트랜잭션에 읽기 전용, 더티체킹 허용 x(데이터 변경 안함)
@RequiredArgsConstructor // final 가지고 있는 필드만 가지고 생성자를 만들어줌, 자동 autowired
public class MemberService {

	private final MemberRepository memberRepository; // 변경할 일이 없기 때문에 final 넣으면 좋음
	
//	생성자 인젝션, 테스트 하기에도 편함, 자동 autowired됨, 이 역할을 해주는 어노테이션은 @RequiredArgsConstructor
//	@Autowired
//	public MemberService(MemberRepository memberRepository) {
//		this.memberRepository = memberRepository;
//	}

	// 회원가입
	@Transactional // 트랜잭션 안에서 데이터변경이 있어야 함 //spring과 java가 제공하는 트랜잭션이 있는데 spring을 쓰는것을 권장
	public Long join(Member member) {
		validateDuplicateMember(member); // 중복 회원 검증
		memberRepository.save(member);
		return member.getId();
	}
	
	// 실무에서는 동시에 조회하는 경우가 있으므로, 실전에서는 name에 unique 제약조건 필요
	private void validateDuplicateMember(Member member) {
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if(!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}
	
	// 회원 전체 조회
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	public Member findOne(Long memberId) {
		return memberRepository.findOne(memberId);
	}
	
}
