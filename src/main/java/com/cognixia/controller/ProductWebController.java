package com.cognixia.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.model.ProductWeb;
import com.cognixia.service.ProductWebService;

@RestController
public class ProductWebController {

	@Autowired
	ProductWebService productWebService;
	//

	//******************** CRUD OPERATIONS ***************
	//Create ----- POST Method ---- 
	@PostMapping("/products")
	public void saveProduct(@RequestBody ProductWeb productWeb){
		productWebService.saveProduct(productWeb);
	}

	//Read ----- GET Method
	// TASK 1: Print details of all the Products
	@GetMapping("/")
	public List<ProductWeb> getAllProducts(){
		return productWebService.getAllProducts();
	}
	//TASK 1.(Part 2) Product Detail: Print details of the Product
	@GetMapping("/productDetails/ID/{id}")
	public ProductWeb printProductDetails(@PathVariable int id) {
		return productWebService.retrieveProductDetailsById(id);
	}

	//TASK 2. Product Filter: Based on the product category entered, print the name, code, rating and quantity using path variables.
	@GetMapping("/productDetails/CATEGORY/{category}")
	public List<String> retrieveDetailsByCategory(@PathVariable String category) {
		return productWebService.retrieveProductsByCategory(category);
	}


	//TASK 4: Product Availability: Display the inStock and Quantity
	@GetMapping("/productDetails/InstockAvailability/{code}")
	public List<String> displayAvailability(@PathVariable String code) {
		return productWebService.retrieveProductsQuantityAndInstock(code);
	}

	//TASK 5: Display the cost of the product along with 13% tax for a product category entered.
	@GetMapping("/cost/{category}")
	public List<String> costProduct(@PathVariable String category) {
		return productWebService.calculatePriceIncludingTax(category);
	}


	//TASK 6: Using a template, print a user friendly message for a 10% discount on the product
	@GetMapping("/products/discount/{rating}")
	public List<String> giveDiscount(@PathVariable Integer rating) {
		return productWebService.calculatePriceAfterDiscount(rating);
	}

	//TASK 7:  Update a product 
	@PutMapping("/id/{id}")
	public ResponseEntity<?> updateProduct(@RequestBody ProductWeb product, @PathVariable Integer id) {
		try {
			ProductWeb existProduct = productWebService.getId(id);

			if (existProduct != null) {
				existProduct.setId(id);
				existProduct.setName(product.getName());
				existProduct.setCategory(product.getCategory());
				existProduct.setCode(product.getCode());
				existProduct.setPrice(product.getPrice());
				existProduct.setQuantity(product.getQuantity());
				existProduct.setInStock(false);
				existProduct.setRating(product.getRating());
				productWebService.saveProduct(existProduct);
			}
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	//TASK 8: 
	//DELETE
	@DeleteMapping("/")
	public void delete() {
		productWebService.delete();
	}
}



