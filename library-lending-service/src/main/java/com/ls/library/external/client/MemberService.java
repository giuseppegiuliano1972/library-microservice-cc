package com.ls.library.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.ls.library.payload.request.MemberDto;


@FeignClient(name = "library-member-service", url = "${app.ct.client.memberservice.url}")
public interface MemberService {
	
	@GetMapping("/member/{codFiscale}")
    public ResponseEntity<MemberDto> getMemberDetailsByCodFIscale(@PathVariable String codFiscale, @RequestHeader("SERVICE-AUTH-TOKEN") String serviceAuthToken);
	
	@GetMapping("/member/cerca/{id}")
    public ResponseEntity<MemberDto> getMemberDetailsById( @PathVariable Long id);
	
	@PutMapping("/member/update/totalelibri")
	public ResponseEntity<Long> updTotLibri ( @RequestBody MemberDto member, @RequestHeader("SERVICE-AUTH-TOKEN") String serviceAuthToken);
	
	//test
	@GetMapping("/member/getgreet")
    public String getGreetings(@RequestHeader("SERVICE-AUTH-TOKEN") String serviceAuthToken);

}
