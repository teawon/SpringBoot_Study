package com.board.main.domain.user.controller;

import com.board.main.domain.user.entity.Member;
import com.board.main.domain.user.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {


    private final MemberService memberService;    //memberService는 단 하나만 있어도 된다. Autowired를 생성자로 호출해서 자바에서 하나만 관리하게끔
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("ourboard/users/signup")
    public String createSignupForm(){
        return "users/signupForm";
    }

    @PostMapping("/ourboard/users/signup")
    public String creat(SignupForm form){
        Member member = new Member();
        member.setUserID(form.getUserId());
        member.setPassword(form.getPassword());
        member.setUserName(form.getUserName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/ourboard/users/view")
    public String List(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "users/view";
    }

}
