package jpabook.jpashop.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="orders")
@Getter @Setter
public class Order {
	
	@Id @GeneratedValue
	@Column(name ="order_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	
	@OneToMany(mappedBy = "order_id")
	private List<OrderItem> orderItems = new ArrayList<>();
	
	@OneToOne // 일대일관계에서 fk는 접근이 많은 order에 넣는다
	@JoinColumn(name="delivery_id")
	private Delivery delivery;
	
	private LocalDateTime oderDate; // 주문시간
	
	private OrderStatus status; // 주문상태 order, cancle
}
