package com.turkcell.northwind.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryRequest {
	
	// id kısmı veriliyor cunku otomatik olarak artmıyor........
	private int categoryId;
	private String categoryName;

}
