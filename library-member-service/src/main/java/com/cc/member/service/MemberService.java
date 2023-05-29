package com.cc.member.service;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<MemberDto> getAllMembers() {
		
		List<Member> member = memberDao.findAll();
		
			
		List<MemberDto> lstdto = new ArrayList<MemberDto>();
		
		for (Member dao : member) {
			MemberDto mdto = new MemberDto();
			mdto = memberAssembler.DaoToDto(dao);
			
			lstdto.add(mdto);
			
			
		}
		
		return lstdto;
		
	}
	
	public MemberDto getMemberByUserid(String userid) {
		
		Member member = memberDao.findByUserid(userid);
		
		if (member.getId() == null)	{
			throw new MemberServiceCustomException("Error: LOGIN FAILED", "MEMBER_NOT_ALLOWED");
		}
			
		MemberDto dto = memberAssembler.DaoToDto(member);
		
		return dto;
		
	}
	
	public MemberDto getMemberById(Long id) {
		
		Member member = memberDao.findById(id).orElse(null);
		
		if (member.getId() == null)	{
			throw new MemberServiceCustomException("Error: LOGIN FAILED", "MEMBER_NOT_FOUND");
		}
			
		MemberDto dto = memberAssembler.DaoToDto(member);
		
		return dto;
		
	}
	
	@Transactional
	public Long addMember(MemberDto dto) {
		
		Member member = memberDao.findByCodFiscale(dto.getCodFiscale().toUpperCase());
		
		if (member == null) {
		
			member = memberAssembler.NewDtoToDao(dto);
			memberDao.save(member);
		} else {
			throw new MemberServiceCustomException("User already exist", "99");
		}
		
		
		
		return member.getCardId();
		
	}
	
	@Transactional
	public Long updTotLibri(MemberDto dto) {
		
		Member member = memberDao.findById(dto.getId()).orElseThrow(() -> {
				throw new MemberServiceCustomException("User doesn't exist", "99");
		});
		
		member.setTotBookBorrowed(member.getTotBookBorrowed() + 1); 
		memberDao.save(member);
		
		return member.getTotBookBorrowed();
		
	}

}
