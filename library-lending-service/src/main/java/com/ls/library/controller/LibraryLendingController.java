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
import com.ls.library.payload.response.ResponseHandler;
import com.ls.library.service.LendingService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/prestito")
@CrossOrigin
@Log4j2
public class LibraryLendingController {
	
	@Autowired
	private LendingService lendingService;
	
	@PostMapping("/libro/esegui")
    public ResponseEntity<Object> richiediLibro(@RequestBody LendingDto lendingDto) {

		try {
			LendingDto dto = lendingService.lendBook(lendingDto);
	        log.info("Id: {}", lendingDto.getId());
			return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.OK, dto);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
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
	public ResponseEntity<Object> updateBookReturned(@RequestBody LendingDto lendingDto) {
		log.info("quiiiii");
		
		try {
			lendingDto = lendingService.updateReturnBook(lendingDto);
			return ResponseHandler.generateResponse("Successfully updated data!", HttpStatus.OK, lendingDto);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
        //return new ResponseEntity<>(lendingDto, HttpStatus.OK);
    }
	
	@GetMapping("/info/{id}")
	public ResponseEntity<LendingDto> getInfoBorrowedByIdBook(@PathVariable Long id) {
		LendingDto dto = new LendingDto();
		
		dto = lendingService.getInfoPrestatoByIdBook(id);
		
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
	
	@GetMapping("/health")
    public ResponseEntity<Long> getHealth() {

        
        return new ResponseEntity<>(
        		1L,
                HttpStatus.OK
        );
    }
	
	@GetMapping(value = "/get-greeting-from-service-b")
	  public ResponseEntity<Object> getGreetingFromServiceB() {
	    try {
	     	      
	      String ret = lendingService.getGreetings();
	      return ResponseHandler.generateResponse("Successfully updated data!", HttpStatus.OK, ret);
	    } catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	  }
	

}
