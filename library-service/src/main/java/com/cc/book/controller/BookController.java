package com.cc.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cc.book.payload.request.BookDto;
import com.cc.book.service.BookService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/libri")
@Log4j2
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long bookId) {

        log.info("ProductController | getProductById is called");

        log.info("ProductController | getProductById | bookId : " + bookId);

        BookDto dtoResp
                = bookService.getBookById(bookId);
        
        return new ResponseEntity<>(dtoResp, HttpStatus.OK);
    }
	
	  @PostMapping("/add")
	  public ResponseEntity<Long> addBook(@RequestBody BookDto book) {

	        log.info("ProductController | addProduct is called");

	        log.info("ProductController | addProduct | productRequest : " + book.toString());

	        Long bookId = bookService.addBook(book);
	        return new ResponseEntity<>(bookId, HttpStatus.CREATED);
	    }
	  
	  @GetMapping("/lista/{titolo}")
	  public List<BookDto> getLibroByTitolo(@PathVariable("titolo") String titolo) {
			
			List<BookDto> book = bookService.getBookByTitoloLike(titolo);
			log.info("qui: " + book.toString());
			return book;
			
			
		}
	  
	  @GetMapping("/lista/libri")
	  public ResponseEntity<List<BookDto>> getListaLibri() {
			
			List<BookDto> book = bookService.getListaLibriAll();
			log.info("qui: " + book.toString());
			
			return new ResponseEntity<>(book, HttpStatus.OK);
		}

}
