package com.cc.book.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.book.assembler.BookAssembler;
import com.cc.book.domain.Book;
import com.cc.book.exception.BookCustomException;
import com.cc.book.payload.request.BookDto;
import com.cc.book.repository.BookDao;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class BookService {
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private BookAssembler bookAssembler;
	
	public long addBook(BookDto dto) {
        
		Book book = bookAssembler.DtoToDao(dto);

        book = bookDao.save(book);

        log.info("book | addBook | book Created");
        log.info("book | addBook | book Id : " + book.getId());
        return book.getId();
    }
	
	public BookDto getBookById(Long id) {
		
		Book book = bookDao.findById(id).orElseThrow(
				  () -> new BookCustomException("Book with given Id not found","BOOK_NOT_FOUND"));
		
		BookDto dto = bookAssembler.DaoToDto(book);
		
		return dto;
	}
	
	public List<BookDto> getBookByTitoloLike(String titolo) {
		
		List<Book> dao = bookDao.findByTitoloContainingIgnoreCase(titolo);
		
		if (dao == null) {
			return new ArrayList<BookDto>();
		}  else {
			List<BookDto> lst = bookAssembler.DaoToDto(dao);
			return lst;
		}
	}
	
	public List<BookDto> getListaLibriAll() {
		
		List<Book> dao = bookDao.findAll();
		
		if (dao == null) {
			return new ArrayList<BookDto>();
		}  else {
			List<BookDto> lst = bookAssembler.DaoToDto(dao);
			return lst;
		}
	}
}
