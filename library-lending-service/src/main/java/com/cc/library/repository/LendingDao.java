package com.cc.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cc.library.domain.Lending;

@Repository
public interface LendingDao extends JpaRepository <Lending,Long> {
	
	public List<Lending> findByIdMemberAndReturnDateIsNull(Long idMember);

	@Query(value = "SELECT l FROM Lending l where l.returnDate is not null and l.idBook = ?1 and "
			+ "l.idBook not in (SELECT b.idBook FROM Lending b where b.returnDate is null   ) ORDER by id desc")
	public List<Lending> findByIdBookAndReturnDateIsNotNullDesc(Long idBook);
	
	public List<Lending> findByIdBookAndReturnDateIsNull(Long idBook);
	
	public List<Lending> findByReturnDateIsNull();

}
