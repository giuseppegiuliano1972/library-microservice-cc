package com.ls.library.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ls.library.payload.request.LendingDto;
import com.ls.library.service.LendingService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/prestito")
@CrossOrigin
@Log4j2
public class LibraryLendingController {
	
	@Autowired
	private LendingService lendingService;
	
	@PostMapping("/esegui")
    public ResponseEntity<LendingDto> richiediLibro(@RequestBody LendingDto lendingDto) {

		LendingDto dto = lendingService.lendBook(lendingDto);
        log.info("Id: {}", lendingDto.getId());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
	
	@GetMapping("/lista/{idMember}")
	public ResponseEntity<List<LendingDto>> getListaLibriPresi(@PathVariable Long idMember) {
		List<LendingDto> dto = new ArrayList<LendingDto>();
		
		dto = lendingService.getLibriPrestatiByIdMember(idMember);
		
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	@GetMapping("/lista/libri/disponibili")
	public ResponseEntity<List<LendingDto>> getListaLibriDisponibili() {
		List<LendingDto> dto = new ArrayList<LendingDto>();
		
		dto = lendingService.getListaLibriDisponibili();
		
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
	
	@GetMapping("/lista/libri/borrowed")
	public ResponseEntity<List<LendingDto>> getListaLibriBorrowedAll() {
		List<LendingDto> dto = new ArrayList<LendingDto>();
		
		dto = lendingService.getLibriPrestatiAll();
		
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
	
	@PutMapping("/libro/return")
	public ResponseEntity<LendingDto> updateBookReturned(@RequestBody LendingDto lendingDto) {
		log.info("quiiiii");
		lendingDto = lendingService.updateReturnBook(lendingDto);
		
        return new ResponseEntity<>(lendingDto, HttpStatus.OK);
    }
	
	@GetMapping("/info/{id}")
	public ResponseEntity<LendingDto> getInfoBorrowedByIdBook(@PathVariable Long id) {
		LendingDto dto = new LendingDto();
		
		dto = lendingService.getInfoPrestatoByIdBook(id);
		
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
