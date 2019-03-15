package com.example.cs5610spring2019javaserveradityalprabhu.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.cs5610spring2019javaserveradityalprabhu.model.Widget;

public interface WidgetRepository extends CrudRepository<Widget, Integer>{
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE widgets w SET w.dtype = :dtype WHERE w.id = :id", nativeQuery = true)
	public void changeWidgetDType(@Param("dtype")String dtype, @Param("id") Integer id);
	
}  
