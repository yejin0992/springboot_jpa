package jpabook.jpashop.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {
	
	@Id @GeneratedValue
	@Column(name="member_id")
	private Long id;

	private String name;
	
	@Embedded // Address 클래스는 Member 클래스에 포함
	private Address address;
	
	// member 클래스의 입장에선 하나의 회원이 여러개의 상품을 주문하기 때문에 일대다 관계
	@OneToMany(mappedBy = "member") // member는 매핑된 거울
	private List<Order> orders = new ArrayList<>();
	// 컬렉션은 되도록 필드에서 초기화, 하이버네이트에 의해 내장컬렉션으로 변경되면(하이버네이트가 추적하기위해) 내부 메커니즘에 문제가 발생할 수 있음

}
