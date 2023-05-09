package com.cc.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cc.member.payload.request.MemberDto;
import com.cc.member.service.MemberService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/member")
@Log4j2
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	@GetMapping("/{codFiscale}")
    public ResponseEntity<MemberDto> getMemberDetailsByCodFIscale(@PathVariable String codFiscale) {

        log.info("MemberController | doPayment is called");

        log.info("MemberController | doPayment | codFiscale : " + codFiscale);

        return new ResponseEntity<>(
        		memberService.getMembershipByCodFiscale(codFiscale),
                HttpStatus.OK
        );
    }
}
