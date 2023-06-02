package com.ls.library.assembler;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.ls.library.domain.Lending;
import com.ls.library.payload.request.BookDto;
import com.ls.library.payload.request.LendingDto;
import com.ls.library.payload.request.MemberDto;

@Component
public class LendingAssembler {
	
	public LendingDto DaoToDto(Lending dao) {
		LendingDto dto = new LendingDto();
		
		dto.setDateLending(dao.getDateLending());
		dto.setIdBook(dao.getIdBook());
		dto.setIdMember(dao.getIdMember());
		dto.setReturnDate(dao.getReturnDate());
		dto.setId(dao.getId());
		
		return dto;

	}
	
	public LendingDto DaoToEnrichDto(Lending dao, BookDto bDto, MemberDto mDto) {
		LendingDto dto = new LendingDto();
		if (dao != null) {
			dto.setDateLending(dao.getDateLending());
			dto.setIdBook(dao.getIdBook());
			dto.setIdMember(dao.getIdMember());
			dto.setReturnDate(dao.getReturnDate());
			dto.setDueReturnDate(dao.getDueReturnDate());
			dto.setId(dao.getId());
		} else {
			dto.setIdBook(bDto.getId());
		}
		
		if (mDto != null) {
			dto.setCognomeMember(mDto.getCognome());
			dto.setNomeMember(mDto.getNome());
			dto.setIdCard(mDto.getCardId());
		}
		dto.setTitolo(bDto.getTitolo());
		
		return dto;

	}
	
	public Lending DtoToDao(LendingDto dto, BookDto book, MemberDto member) {
		Lending dao = new Lending();
		
		dao.setDateLending(dto.getDateLending());
		dao.setIdBook(book.getId());
		dao.setIdMember(member.getId());
		dao.setReturnDate(null);
		dao.setDueReturnDate(dto.getDueReturnDate());
		
		return dao;

	}
	
	public Lending updateDtoToDao(Lending dao) {
		
		dao.setReturnDate(new Date());
		
		return dao;

	}
}
