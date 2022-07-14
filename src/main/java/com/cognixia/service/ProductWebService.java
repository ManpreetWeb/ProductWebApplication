package com.cognixia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognixia.model.ProductWeb;
import com.cognixia.repository.ProductWebRepository;

@Service
@Transactional
public class ProductWebService {

	@Autowired
	private ProductWebRepository productWebRepository;

	private static final String COST_TEMPLATE = "Category is: %s  and Product name is %s .Price of it is %.2f $. Tax on product is %.2f $ .Price after tax is--> %.2f$.";
	private static final String DISCOUNT_TAMPLATE = "Product name is %s .Price of it is %.2f $. Discount on product is %.2f $ .Price after Discount is--> %.2f$.";

	//Create ----- POST Method ---------
	//TASK 3:  Add a Product
	public void saveProduct(ProductWeb productWeb){
		productWebRepository.save(productWeb);
	}

	//Read ----- GET Method
	//Product Detail: Print all of the Products
	public List<ProductWeb> getAllProducts(){
		return productWebRepository.findAll();
	}

	//  Get product
	public ProductWeb getId(Integer id) {
		return productWebRepository.findById(id).get();
	}

	//Product Detail: Print details of the Product
	public ProductWeb retrieveProductDetailsById(int id) {
		for (ProductWeb productWeb : productWebRepository.findAll()) {
			if(productWeb.getId()==id) {
				return productWeb;
			}
		}
		return null;
	}

	//Product Filter: Based on the product category entered, print the name, code, rating and quantity using path variables.
	public List<String> retrieveProductsByCategory(String category) {
		List<String> filteredProducts = new ArrayList<String>();
		filteredProducts.add("Category is: " + category);
		for (ProductWeb p : productWebRepository.findAll()) {
			if(p.getCategory().equalsIgnoreCase(category)) {

				filteredProducts.add(p.filtered());
			}
		}
		return filteredProducts;
	}

	//Product Availability: Display the inStock and Quantity
	public List<String > retrieveProductsQuantityAndInstock(String code) {

		List<String> filteredProducts = new ArrayList<String>();
		for (ProductWeb p : productWebRepository.findAll()) {
			if(p.getCode().equalsIgnoreCase(code)) {
				filteredProducts.add(p.checkAvailability());
			}
		}
		return filteredProducts;
	}

	//TASK 5: Display the cost of the product along with 13% tax for a product category entered.
	private String calculateTax(ProductWeb productWeb) {
		double tax = productWeb.getPrice() * .13;
		//	double priceAfterTax = productWeb.getPrice() + tax;
		return String.format(COST_TEMPLATE, productWeb.getCategory(), productWeb.getName(), productWeb.getPrice(), tax, productWeb.getPrice() + tax);
	}

	public List<String> calculatePriceIncludingTax(String category) {
		return productWebRepository.findByCategory(category).stream()
				.filter(product -> product.getCategory().equalsIgnoreCase(category))
				.map(this::calculateTax).collect(Collectors.toList());
	}

	//Product Discount: Using a template, print a user friendly message for a 10% discount on the product
	public String calculateDiscount(ProductWeb productWeb) {
		double discount = productWeb.getPrice() * .1;
		double priceAfterDiscount = productWeb.getPrice() - discount;
		return String.format(DISCOUNT_TAMPLATE, productWeb.getName(), productWeb.getPrice(), discount, productWeb.getPrice() + priceAfterDiscount);
	}
	public List<String> calculatePriceAfterDiscount(Integer rating) {
		return productWebRepository.getMaxRating().stream()

				.map(this::calculateDiscount).collect(Collectors.toList());
	}

	//TASK 7: Update a product
	public void updateProduct(ProductWeb productWeb) {
		productWebRepository.save(productWeb);

	}

	//	delete
	public void delete() {
		productWebRepository.deleteByRating();
	}
}
