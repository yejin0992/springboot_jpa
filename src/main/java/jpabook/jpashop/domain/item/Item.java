package jpabook.jpashop.domain.item;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 한테이블에 모두 넣는 방식
@DiscriminatorColumn(name="dtype")
@Getter @Setter
public abstract class Item {

	@Id 
	@GeneratedValue
	@Column(name = "item_id")
	private Long id;
	
	private String name;
	
	private int price;
	
	private int stockQuantity;
	
}
