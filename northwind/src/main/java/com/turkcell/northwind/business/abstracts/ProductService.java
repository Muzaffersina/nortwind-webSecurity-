package com.turkcell.northwind.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.turkcell.northwind.business.dtos.GetProductByNameDto;
import com.turkcell.northwind.business.dtos.ListProductDto;
import com.turkcell.northwind.business.requests.CreateProductRequest;
import com.turkcell.northwind.core.utilitys.results.DataResult;
import com.turkcell.northwind.core.utilitys.results.Result;


public interface ProductService {
	DataResult<List<ListProductDto>> getAll();
	Result add(CreateProductRequest createProductRequest);
	DataResult<GetProductByNameDto> getProductByName(String productName);         /////// ????????????????
	DataResult<List<ListProductDto>> getAllPaged(int pageNumber , int pageSize);
	DataResult<List<ListProductDto>> getAllSorted(Sort.Direction direction);
	
}
