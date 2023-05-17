package com.cc.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cc.library.domain.Lending;

@Repository
public interface LendingDao extends JpaRepository <Lending,Long> {
	
	public List<Lending> findByIdMemberAndReturnDateIsNull(Long idMember);

}
