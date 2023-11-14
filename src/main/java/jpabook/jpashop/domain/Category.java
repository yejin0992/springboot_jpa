package jpabook.jpashop.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Category {

	@Id @GeneratedValue
	@Column(name="category_id")
	private Long id;
	
	private String name;
	
	private int stockQuantity;
	
	@ManyToMany
	@JoinTable(name="category_item",
		joinColumns = @JoinColumn(name="category_id"),
		inverseJoinColumns = @JoinColumn(name="item_id")
			) // 중간테이블 매핑 rdbms는 
	private List<Item> items = new ArrayList<>();
	
	//셀프로 양방향 연관관계
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Category parent;

	@OneToMany(mappedBy="parent")
	private List<Category> child =new ArrayList<>();

}



