package com.cc.library.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cc.library.payload.request.MemberDto;


@FeignClient(name = "library-member-service")
public interface MemberService {
	
	@GetMapping("/{codFiscale}")
    public ResponseEntity<MemberDto> getMemberDetailsByCodFIscale(@PathVariable String codFiscale);
}
