package com.cc.member.controller;

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

import com.cc.member.payload.request.MemberDto;
import com.cc.member.service.MemberService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/member")
@CrossOrigin
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
	
	@GetMapping("/cerca/{id}")
    public ResponseEntity<MemberDto> getMemberDetailsById(@PathVariable Long id) {


        return new ResponseEntity<>(
        		memberService.getMemberById(id),
                HttpStatus.OK
        );
    }
	
	@GetMapping("/lista/utenti")
    public ResponseEntity<List<MemberDto>> getAllMembers() {

        List<String> lstNomi = new ArrayList<String>();
        
        List<MemberDto> lstdto = memberService.getAllMembers();
        
        for (MemberDto memberDto : lstdto) {
			
        	lstNomi.add(memberDto.getCognome());
			
		}
        
        return new ResponseEntity<>(
        		lstdto,
                HttpStatus.OK
        );
    }
	
	@GetMapping("/health")
    public ResponseEntity<Long> getHealth() {

        
        return new ResponseEntity<>(
        		1L,
                HttpStatus.OK
        );
    }
	
	@PostMapping("/addmember")
	public ResponseEntity<Long> addMember(@RequestBody MemberDto member){
		Long idMemberCard = 0L;
		if (member != null) {
			idMemberCard = memberService.addMember(member);
		} else {
			log.info("Vuoto");
		}
		
		
		return new ResponseEntity<>(idMemberCard, HttpStatus.OK);

	}
	
	@PutMapping("/update/totalelibri")
	public ResponseEntity<Long> updTotLibri (@RequestBody MemberDto member){
		
		return new ResponseEntity<>(
        		memberService.updTotLibri(member),
                HttpStatus.OK
        );
	}
	


}
