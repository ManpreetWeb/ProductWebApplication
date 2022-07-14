package com.cognixia.model;

//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name ="Products")
@Entity
public class ProductWeb {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//  @Column(name="Id")
	private int id;
	// @Column(name="name")
	private String name;
	// @Column(name="code")
	private String code;
	// @Column(name="category")
	private String category;
	// @Column(name="price")
	private double price;
	// @Column(name="quantity")
	private int quantity;
	// @Column(name="inStock")
	private boolean inStock;
	// @Column(name="rating")
	private int rating;

	public ProductWeb(int id, String name, String code, String category, double price, int quantity, boolean inStock,
			int rating) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		category = category;
		this.price = price;
		this.quantity = quantity;
		this.inStock = inStock;
		this.rating = rating;
	}


	public ProductWeb() {
		super();
	}


	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public String getCategory() {
		return category;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public boolean isInStock() {
		return inStock;
	}

	public int getRating() {
		return rating;
	}



	public void setId(int id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	@Override
	public String toString() {
		return "ProductWeb [id=" + id + ", name=" + name + ", code=" + code + ", category=" + category + ", price="
				+ price + ", quantity=" + quantity + ", inStock=" + inStock + ", rating=" + rating + "]";
	}

	public String checkAvailability(){
		return "Product in stock: " + inStock + " and Product quantity: " + quantity;
	}

	public String filtered(){
		return "Product name: " + name + ", Product code: " + code + ", Product rating: " + rating + ", Product quantity: " + quantity;
	}
}
