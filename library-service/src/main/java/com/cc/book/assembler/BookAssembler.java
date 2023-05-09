package com.cc.book.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cc.book.domain.Book;
import com.cc.book.payload.request.BookDto;

@Component
public class BookAssembler {

	public BookDto DaoToDto(Book book) {
		BookDto dto = new BookDto();
		dto.setId(book.getId());
		dto.setTitolo(book.getTitolo());
		dto.setAutore(book.getEditore());
		dto.setDescrizione(book.getDescrizione());
		
		return dto;

	}
	
	public Book DtoToDao(BookDto book) {
		Book dao = new Book();
		
		dao.setTitolo(book.getTitolo());
		dao.setAutore(book.getEditore());
		dao.setDescrizione(book.getDescrizione());
		
		return dao;

	}
	
	public List<BookDto> DaoToDto(List<Book> dao) {
		
		List<BookDto> lst = new ArrayList<BookDto>();
		for (Book book : dao) {
			BookDto dto = new BookDto();
			dto.setId(book.getId());
			dto.setTitolo(book.getTitolo());
			dto.setAnno(book.getAnno());
			dto.setAutore(book.getAutore());
			dto.setDescrizione(book.getDescrizione());
			
			lst.add(dto);
		}
		
		return lst;
		
	}
}
