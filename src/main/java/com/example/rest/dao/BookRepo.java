package com.example.rest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.rest.model.Book;

@Repository
public interface BookRepo extends CrudRepository<Book, Integer> {
	


}
