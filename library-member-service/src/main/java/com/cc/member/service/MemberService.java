package com.cc.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.member.assembler.MemberAssembler;
import com.cc.member.domain.Member;
import com.cc.member.exception.MemberServiceCustomException;
import com.cc.member.payload.request.MemberDto;
import com.cc.member.repository.MemberDao;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private MemberAssembler memberAssembler;
	
	public MemberDto getMembershipByCodFiscale(String codFiscale) {
		
		Member member = memberDao.findByCodFiscale(codFiscale);
		
		if (member.getId() == null)	{
			throw new MemberServiceCustomException("Error: COD FISCALE NOT FOUND", "MEMBER_NOT_FOUND");
		}
			
		MemberDto dto = memberAssembler.DaoToDto(member);
		
		return dto;
		
	}
	
	@Transactional
	public Long addMember(MemberDto dto) {
		
		Member member = memberAssembler.NewDtoToDao(dto);
		
		memberDao.save(member);
		
		return member.getCardId();
		
	}

}
