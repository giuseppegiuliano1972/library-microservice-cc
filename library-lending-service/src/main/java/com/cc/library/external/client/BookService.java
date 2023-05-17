package com.cc.library.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cc.library.payload.request.BookDto;


@FeignClient(name = "library-service")
public interface BookService {

	@GetMapping("/libri/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long bookId);
}
