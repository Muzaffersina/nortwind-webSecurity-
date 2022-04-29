package com.turkcell.northwind.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.northwind.entities.concretes.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {

}
