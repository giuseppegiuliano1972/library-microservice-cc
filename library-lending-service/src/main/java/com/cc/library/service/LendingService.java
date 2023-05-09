package com.cc.library.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cc.library.assembler.LendingAssembler;
import com.cc.library.exception.LendingCustomException;
import com.cc.library.external.client.BookService;
import com.cc.library.external.client.MemberService;
import com.cc.library.payload.request.BookDto;
import com.cc.library.payload.request.LendingDto;
import com.cc.library.payload.request.MemberDto;
import com.cc.library.repository.LendingDao;

import jakarta.transaction.Transactional;

@Service
public class LendingService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private LendingDao lendingDao;
	
	@Autowired
	private LendingAssembler lendingAssembler;
	
	@Transactional
	public LendingDto lendBook(LendingDto lendingDto) {
		
		LendingDto lend = new LendingDto();
		
		ResponseEntity<BookDto> book = bookService.getBookById(lendingDto.getIdBook());
		
		if (book.getBody().getId() == null) {
			lend.setStatus("LEND_ERROR");
			throw new LendingCustomException("Book not found", "BOOK_NOT_FOUND", 1);
		}
		
		ResponseEntity<MemberDto> member = memberService.getMemberDetailsByCodFIscale(lendingDto.getCodFiscale());
		Date currDate = new Date();
		if (member.getBody().getId() == null ) {
			lend.setStatus("LEND_ERROR");
			throw new LendingCustomException("Member not found", "MEMBER_NOT_FOUND", 2);
		} else if (member.getBody().getExpirationDate().compareTo(currDate) < 0) { //check data membership scaduta
			lend.setStatus("LEND_ERROR");
			throw new LendingCustomException("Membership has expired", "MEMBER_EXPIRED", 3);
		}
		
		lendingDao.save(lendingAssembler.DtoToDao(lendingDto, book.getBody(), member.getBody()));
		lend.setStatus("OK");
		return lend;
		
	}

}
