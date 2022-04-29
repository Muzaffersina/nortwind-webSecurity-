package com.turkcell.northwind.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.northwind.business.abstracts.ProductService;
import com.turkcell.northwind.business.dtos.GetProductByNameDto;
import com.turkcell.northwind.business.dtos.ListProductDto;
import com.turkcell.northwind.business.requests.CreateProductRequest;
import com.turkcell.northwind.core.utilitys.results.DataResult;
import com.turkcell.northwind.core.utilitys.results.Result;


@RestController
@RequestMapping("/api/products")
public class ProductsController {
	private ProductService productService;
	
	@Autowired          // kim prService implemente ediyorsa gidip onu bulup new liyo.
	public ProductsController(ProductService productService) {
		this.productService = productService;
	} 	
	
	@GetMapping("/getall")                   // localhost:8080/getall sayfası
	public DataResult<List<ListProductDto>> getAll() {
		return this.productService.getAll();
	}
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateProductRequest createProductRequest) {
		return this.productService.add(createProductRequest);
	}
	
	@GetMapping("/getProductByName") 
	DataResult<GetProductByNameDto> getProductByName(String productName){
		return this.productService.getProductByName(productName);
	}
	
	@GetMapping("/getAllPaged") 
	DataResult<List<ListProductDto>> getAllPaged(int pageNumber , int pageSize){
		return this.productService.getAllPaged(pageNumber, pageSize);
	}	
	
	@GetMapping("/getAllSorted") 
	DataResult<List<ListProductDto>> getAllSorted(Sort.Direction direction){
		return this.productService.getAllSorted(direction);
	}
	
}
