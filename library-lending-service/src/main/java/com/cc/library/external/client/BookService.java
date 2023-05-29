package com.cc.library.external.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cc.library.payload.request.BookDto;


@FeignClient(name = "library-service")
public interface BookService {

	@GetMapping("/libri/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long bookId);
	
	@GetMapping("/libri/lista/libri")
	public ResponseEntity<List<BookDto>> getListaLibri();
	
	@PutMapping("/update/disponibilita")
	public ResponseEntity<BookDto> setDisponibilitaLibro(@RequestBody BookDto book);
}
