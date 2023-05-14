package com.cc.member.assembler;

import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.cc.member.domain.Member;
import com.cc.member.payload.request.MemberDto;

@Component
public class MemberAssembler {

	public MemberDto DaoToDto(Member member) {
		MemberDto dto = new MemberDto();
		
		dto.setCardId(member.getCardId());
		dto.setCodFiscale(member.getCodFiscale());
		dto.setCognome(member.getCognome());
		dto.setNome(member.getNome());
		dto.setExpirationDate(member.getExpirationDate());
		dto.setId(member.getId());
		
		return dto;

	}
	
	public Member DtoToDao(MemberDto dto) {
		Member dao = new Member();
		
		dao.setCodFiscale(dto.getCodFiscale());
		dao.setCardId(dto.getCardId());
		
		return dao;

	}
	
	public Member NewDtoToDao(MemberDto dto) {
		Member dao = new Member();
		
		Random rd = new Random();
		dao.setCodFiscale(dto.getCodFiscale());
		dao.setCardId(rd.nextLong());
		dao.setCognome(dto.getCognome());
		dao.setNome(dto.getNome());
		dao.setIndirizzo(dao.getIndirizzo());
		dao.setDateCard(new Date());
		
		return dao;

	}
}
