package jpabook.jpashop.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.boot.SpringApplication;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="orders")
@Getter @Setter
public class Order {
	
	@Id @GeneratedValue
	@Column(name ="order_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // 일대일관계에서 fk는 접근이 많은 order에 넣는다
	@JoinColumn(name="delivery_id")
	private Delivery delivery;
	
	private LocalDateTime orderDate; // 주문시간
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status; // 주문상태 order, cancle
	
	// 연관관계 편의 메서드 // 생성하는쪽과 양방향 연관관계는 편의 메서드가 있으면 좋음
	public void setMember(Member member) {
		this.member = member;
		member.getOrders().add(this); // List<Order>에 order를 넣어둠
		
		// order 클래스의 member update
		// member 클래스의 orders List에 order "추가"
	}
	
//	원래의 로직이라면
//	public static void main(String[] args) throws Exception {
//		Member member = new Member();
//		Order order = new Order();
//	
//		member.getOrders().add(order);
//		order.setMember(member);
//	}

	
	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}
	
	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
		delivery.setOrder(this);
	}
}
