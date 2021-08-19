package todos;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Repository;



@Repository
@Document(collection  = "order")
public class Order {
	
	

	public Order(String id, String orderingCurrency, String toISOCode, double dealAmount, 
			Date timestamp) {
		super();
		this.id = id;
		this.orderingCurrency = orderingCurrency;
		this.toISOCode = toISOCode;
		this.dealAmount = dealAmount;
		this.timestamp = timestamp;
	}

	@Id
	private String id;
	
	@Field(value="orderingCurrency")
	private String orderingCurrency;
	
	@Field(value="toISOCode")
	private String toISOCode;	
	
	@Field(value="dealAmount")
	private double dealAmount;
	
	@Field(value="timestamp")
	private Date timestamp;
	
	@Field(value="accpted")
	private boolean accpted;

	
	public boolean isAccpted() {
		return accpted;
	}
	public void setAccpted(boolean accpted) {
		this.accpted = accpted;
	}
	public String getOrderingCurrency() {
		return orderingCurrency;
	}
	public void setOrderingCurrency(String orderingCurrency) {
		this.orderingCurrency = orderingCurrency;
	}
	public String getToISOCode() {
		return toISOCode;
	}
	public void setToISOCode(String toISOCode) {
		this.toISOCode = toISOCode;
	}
	public double getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(double dealAmount) {
		this.dealAmount = dealAmount;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Order() {
		
	}
	
	 
}
