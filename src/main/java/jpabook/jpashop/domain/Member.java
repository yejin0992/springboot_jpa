package jpabook.jpashop.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
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
}
