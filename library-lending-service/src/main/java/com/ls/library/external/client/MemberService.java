package com.ls.library.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ls.library.payload.request.MemberDto;


@FeignClient(name = "library-member-service")
public interface MemberService {
	
	@GetMapping("/member/{codFiscale}")
    public ResponseEntity<MemberDto> getMemberDetailsByCodFIscale(@PathVariable String codFiscale);
	
	@GetMapping("/member/cerca/{id}")
    public ResponseEntity<MemberDto> getMemberDetailsById(@PathVariable Long id);
	
	@PutMapping("/member/update/totalelibri")
	public ResponseEntity<Long> updTotLibri (@RequestBody MemberDto member);

}
