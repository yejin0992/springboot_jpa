package jpabook.jpashop.domain;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter // 값타입은 변경 불가능하게 설계해야 하기 때문에 setter 제거
public class Address {
	private String city;
	private String street;
	private String zipcode;
	
	// jpa 특성 상 만들어놓은 것
	protected Address() {
		
	}
	
	public Address(String city, String street, String zipcode) {
		super();
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
	
}
