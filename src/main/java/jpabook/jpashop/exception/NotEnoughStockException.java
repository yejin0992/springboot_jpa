package jpabook.jpashop.exception;

public class NotEnoughStockException extends RuntimeException{
	
	public NotEnoughStockException() {
		// TODO Auto-generated constructor stub
	}

	public NotEnoughStockException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public NotEnoughStockException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NotEnoughStockException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	

		
}
