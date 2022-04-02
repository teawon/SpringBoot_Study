package com.board.main.domain.user.controller;

import com.board.main.domain.user.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {


    private final MemberService memberService;    //memberService는 단 하나만 있어도 된다. Autowired를 생성자로 호출해서 자바에서 하나만 관리하게끔
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
}
