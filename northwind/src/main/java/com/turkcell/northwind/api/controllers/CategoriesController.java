package com.turkcell.northwind.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.northwind.business.abstracts.CategoryService;
import com.turkcell.northwind.business.dtos.ListCategoryDto;
import com.turkcell.northwind.business.requests.CreateCategoryRequest;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
	private CategoryService categoryService;
	
	@Autowired          // kim prService implemente ediyorsa gidip onu bulup new liyo.
	public CategoriesController(CategoryService categoryService) {
		this.categoryService = categoryService;
	} 
	
	@GetMapping("/getall")                   // localhost:8080/api/categories/getall sayfası
	public List<ListCategoryDto> getAll() {		
		return this.categoryService.getAll();
	}
	@PostMapping("/add")
	public void add(@RequestBody @Valid CreateCategoryRequest createCategoryRequest) {
		this.categoryService.add(createCategoryRequest);
	}	
	
}