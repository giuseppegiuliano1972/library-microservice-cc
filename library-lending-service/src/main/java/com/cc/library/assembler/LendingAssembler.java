package com.cc.library.assembler;

import org.springframework.stereotype.Component;

import com.cc.library.domain.Lending;
import com.cc.library.payload.request.BookDto;
import com.cc.library.payload.request.LendingDto;
import com.cc.library.payload.request.MemberDto;

@Component
public class LendingAssembler {
	
	public LendingDto DaoToDto(Lending dao) {
		LendingDto dto = new LendingDto();
		
		
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
}
