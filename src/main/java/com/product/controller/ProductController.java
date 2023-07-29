package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.entity.Product;
import com.product.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
	//Fatching all date
	@GetMapping
	public List<Product>getAllProducts()
	{
		return productRepository.findAll();
	}
	//Fatching single date
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable Long id)
	{
		return productRepository.findById(id).orElseThrow(()->new RuntimeException("Product is not found"));
	}
	//creating product
	@PostMapping
	public Product createProduct(@RequestBody Product product)
	{
		return productRepository.save(product);
	}
	//update product
	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable Long id,@RequestBody Product updatedProduct)
	{
		Product product=productRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Product Not Found"));
		product.setName(updatedProduct.getName());
		product.setPrice(updatedProduct.getPrice());
		return productRepository.save(product);
	}
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id)
	{
		productRepository.deleteById(id);
	}

}
