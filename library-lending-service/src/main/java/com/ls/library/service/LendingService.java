package com.ls.library.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.ls.library.assembler.LendingAssembler;
import com.ls.library.domain.Lending;
import com.ls.library.exception.LendingCustomException;
import com.ls.library.external.client.BookService;
import com.ls.library.external.client.MemberService;
import com.ls.library.payload.request.BookDto;
import com.ls.library.payload.request.LendingDto;
import com.ls.library.payload.request.MemberDto;
import com.ls.library.repository.LendingDao;

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
		
		if ((book.getBody().getId() == null) || (book.getBody().getDisponibile() == 0L)) {
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
		
		ResponseEntity<BookDto> bookRet = bookService.setDisponibilitaLibro(book.getBody());
		
		ResponseEntity<Long> Long = memberService.updTotLibri(member.getBody());
		
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
		
		ResponseEntity<BookDto> bookRet = bookService.setDisponibilitaLibro(book.getBody());
		
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
	public LendingDto getInfoPrestatoByIdBook(Long idBook) {
		LendingDto lenddto = new LendingDto();
		Lending lend = lendingDao.findByIdBookAndReturnDateNull(idBook);
		
		if (lend == null) {
			lenddto.setStatus("LEND_ERROR");
			throw new LendingCustomException("Book not found", "BOOK_NOT_FOUND", 1);
		} else {
			lenddto = lendingAssembler.DaoToDto(lend);
		}
		
		
		return lenddto;
		
	}
	
	public List<LendingDto> getLibriPrestatiAll(){
		
		List<LendingDto> lstdto = new ArrayList<LendingDto>();
		
		List<Lending> lst = lendingDao.findByReturnDateIsNull();
		if (lst != null && lst.size() > 0) {
			LendingDto lenddto = new LendingDto();
			for (Lending lending : lst) {
				ResponseEntity<BookDto> book = bookService.getBookById(lending.getIdBook());
				
				if (book != null) {
					
					ResponseEntity<MemberDto> member = memberService.getMemberDetailsById(lending.getIdMember());
					
					lenddto = lendingAssembler.DaoToEnrichDto(lending, book.getBody(), member.getBody());
					
					lstdto.add(lenddto);
				}
			}
			
			
		}
				
		return lstdto;
		
	}
	
	public List<LendingDto> getListaLibriDisponibili(){
		
		ResponseEntity<List<BookDto>> book = bookService.getListaLibri();
		
		List<LendingDto> lstdto = new ArrayList<LendingDto>();
		Boolean found = false;
		
		if (book.getBody() != null ) {
			LendingDto lenddto = new LendingDto();
			
			List<Lending> lst = lendingDao.findByReturnDateIsNull();
			
			for (BookDto dto : book.getBody()) {
				//List<Lending> lst = lendingDao.findByIdBookAndReturnDateIsNull(dto.getId());
				for (Lending lending : lst) {
					if (lending.getIdBook().equals(dto.getId())){
						found=true;
					}
				}
				if (!found) {
					lenddto = lendingAssembler.DaoToEnrichDto(null, dto, null);
				} else {
					found = false;
				}
				lstdto.add(lenddto);
			}
		}
		
		return lstdto;
		
	}

}