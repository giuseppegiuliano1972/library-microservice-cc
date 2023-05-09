package com.cc.member.assembler;

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
	
	public Member DtoToDao(MemberDto book) {
		Member dao = new Member();
		
		
		return dao;

	}
}
