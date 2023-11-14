package jpabook.jpashop;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {
	
		@Id @GeneratedValue
		@Column(name="member_id")
		private Long id;
		private String name;
		@Embedded
		private Address address;
		private List<Order> orders = new ArrayList<>();
}
