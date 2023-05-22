package com.cc.library.assembler;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.cc.library.domain.Lending;
import com.cc.library.payload.request.BookDto;
import com.cc.library.payload.request.LendingDto;
import com.cc.library.payload.request.MemberDto;

@Component
public class LendingAssembler {
	
	public LendingDto DaoToDto(Lending dao) {
		LendingDto dto = new LendingDto();
		
		dto.setDateLending(dao.getDateLending());
		dto.setIdBook(dao.getIdBook());
		dto.setIdMember(dao.getIdMember());
		dto.setReturnDate(dao.getReturnDate());
		
		return dto;

	}
	
	public LendingDto DaoToEnrichDto(Lending dao, BookDto bDto) {
		LendingDto dto = new LendingDto();
		if (dao != null) {
			dto.setDateLending(dao.getDateLending());
			dto.setIdBook(dao.getIdBook());
			dto.setIdMember(dao.getIdMember());
			dto.setReturnDate(dao.getReturnDate());
			dto.setDueReturnDate(dao.getDueReturnDate());
		}
		dto.setTitolo(bDto.getTitolo());
		
		return dto;

	}
	
	public Lending DtoToDao(LendingDto dto, BookDto book, MemberDto member) {
		Lending dao = new Lending();
		
		dao.setDateLending(dto.getDateLending());
		dao.setIdBook(book.getId());
		dao.setIdMember(member.getId());
		dao.setReturnDate(dto.getReturnDate());
		
		return dao;

	}
	
	public Lending updateDtoToDao(Lending dao) {
		
		dao.setReturnDate(new Date());
		
		return dao;

	}
}
