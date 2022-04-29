package com.turkcell.northwind.core.utilitys.mapping;

import org.modelmapper.ModelMapper;


public interface ModelMapperService {
	
	ModelMapper forDto();
	ModelMapper forRequest();
	
}
