package com.cc.book.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cc.book.domain.Book;

@Repository
public interface BookDao extends JpaRepository<Book, Long> {
	
	Optional<Book> findById(Long id);
	
	public List<Book> findByTitoloContainingIgnoreCase(String titolo);

}
