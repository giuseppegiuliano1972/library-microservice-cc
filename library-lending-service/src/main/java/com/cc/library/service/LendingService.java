package com.cc.library.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cc.library.assembler.LendingAssembler;
import com.cc.library.domain.Lending;
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
	
	@Transactional
	public LendingDto updateReturnBook(LendingDto lendingDto) {
		
		LendingDto lend = new LendingDto();
		
		ResponseEntity<BookDto> book = bookService.getBookById(lendingDto.getIdBook());
		
		if (book.getBody().getId() == null) {
			lend.setStatus("LEND_ERROR");
			throw new LendingCustomException("Book not found", "BOOK_NOT_FOUND", 1);
		}
		
		Lending lendDao = lendingDao.findById(lendingDto.getId()).orElse(null);
		
		lendingDao.save(lendingAssembler.updateDtoToDao(lendDao));
		lend.setStatus("OK");
		return lend;
		
	}
	
	public List<LendingDto> getLibriPrestatiByIdMember(Long idMember){
		
		List<Lending> lst = lendingDao.findByIdMemberAndReturnDateIsNull(idMember);
		List<LendingDto> lstdto = new ArrayList<LendingDto>();
		
		for (Lending lending : lst) {
			LendingDto dto = lendingAssembler.DaoToDto(lending);
			
			lstdto.add(dto);		
			
		}
		
		
		return lstdto;
		
	}
	
	public List<LendingDto> getLibriPrestatiAll(){
		
		List<LendingDto> lstdto = new ArrayList<LendingDto>();
		
		List<Lending> lst = lendingDao.findByReturnDateIsNull();
		if (lst != null && lst.size() > 0) {
			LendingDto lenddto = new LendingDto();
			for (Lending lending : lst) {
				ResponseEntity<BookDto> book = bookService.getBookById(lending.getIdBook());
				
				if (book != null) {
					lenddto = lendingAssembler.DaoToEnrichDto(lending, book.getBody());
					
					lstdto.add(lenddto);
				}
			}
			
			
		}
				
		return lstdto;
		
	}
	
	public List<LendingDto> getListaLibriDisponibili(){
		
		ResponseEntity<List<BookDto>> book = bookService.getListaLibri();
		
		List<LendingDto> lstdto = new ArrayList<LendingDto>();
		
		if (book.getBody() != null ) {
			LendingDto lenddto = new LendingDto();
			for (BookDto dto : book.getBody()) {
				List<Lending> lst = lendingDao.findByIdBookAndReturnDateIsNotNull(dto.getId());
				if (lst != null && lst.size()>0) {
					lenddto = lendingAssembler.DaoToEnrichDto(lst.get(0), dto);
				} else {
					lenddto = lendingAssembler.DaoToEnrichDto(null, dto);
				}
				
				lstdto.add(lenddto);
			}
		}
		
		return lstdto;
		
	}

}
