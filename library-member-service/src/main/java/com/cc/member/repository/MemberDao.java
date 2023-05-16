package com.cc.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cc.member.domain.Member;

@Repository
public interface MemberDao extends JpaRepository<Member, Long>{
	
	 Optional<Member> findById(Long id);
	 
	 Member findByCodFiscale(String codFiscale);
	 Member findByUserid(String userid);
	 
}
