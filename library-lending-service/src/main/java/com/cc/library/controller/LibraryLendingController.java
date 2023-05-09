package com.cc.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cc.library.payload.request.LendingDto;
import com.cc.library.service.LendingService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/prestito")
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

}
