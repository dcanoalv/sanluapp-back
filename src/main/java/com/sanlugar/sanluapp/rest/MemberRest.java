package com.sanlugar.sanluapp.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanlugar.sanluapp.model.Member;
import com.sanlugar.sanluapp.service.MemberService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping ("/api/v1")
@RequiredArgsConstructor
public class MemberRest {

    @Autowired
    private MemberService memberService;

    @PostMapping(value = "demo")
    public String welcome() {
        return "Welcome from secure endpoint";
    }

    @GetMapping(value= "member")
    private ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("{id}")
    private ResponseEntity<Member> getMemberById(@PathVariable("id") Long memberId) {
        return ResponseEntity.ok(memberService.getMemberById(memberId));
    }

    @PostMapping
    private ResponseEntity<Member> saveMember (@RequestBody Member member) {
        try {
            Member memberSaved = memberService.save(member);
            return ResponseEntity.created(new URI("member/"+memberSaved.getId())).body(memberSaved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        
    }

    
    
}
