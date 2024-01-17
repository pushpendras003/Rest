package com.example.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import com.example.rest.dao.BookRepo;
import com.example.rest.model.Book;

@RestController
public class BookController {
	@Autowired
	BookRepo repo;
	
	@GetMapping("/book")
	public ResponseEntity<List<Book>> getAllBook() {
		List<Book> b=(List<Book>)repo.findAll();
		try {
			if(b.size()==0) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		b.forEach(e->System.out.println(e));
		return ResponseEntity.of(Optional.of(b));
	}
	
	@GetMapping("/book/{id}")
	public ResponseEntity<Optional<Book>> getBook(@PathVariable("id") int id) {
		try{
			Optional<Book> t= repo.findById(id);
			return ResponseEntity.of(Optional.of(t));
					
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PostMapping("/book")
	public ResponseEntity<Book> postBook(@RequestBody Book book) {
		Book t;
		try {
			t=repo.save(book);
			return ResponseEntity.of(Optional.of(t));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	

}
