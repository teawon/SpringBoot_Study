package com.board.main.domain.user.controller;

import com.board.main.domain.user.dto.LoginFormDto;
import com.board.main.domain.user.dto.MemberDto;
import com.board.main.domain.user.dto.SignupForm;
import com.board.main.domain.user.entity.Member;
import com.board.main.domain.user.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("ourboard/users")
public class MemberController {


    private final MemberService memberService;    //memberService는 단 하나만 있어도 된다. Autowired를 생성자로 호출해서 자바에서 하나만 관리하게끔
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/signup")
    public String createSignupForm(@ModelAttribute("signupform") SignupForm signupform){
        return "users/signupForm";
    }


    @PostMapping("/signup")
    public String creat(@ModelAttribute("signupform") @Valid SignupForm signupform , BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "/users/signupForm";
        }



        try {
            memberService.join(signupform);
            }catch(IllegalStateException e){
               e.printStackTrace();
               bindingResult.reject("signupFail","이미 등록된 사용자입니다.");

               return "/users/signupForm";
       }


        return "redirect:/";
    }

    @GetMapping("/view")
    public String List(Model model){
        List<MemberDto> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "users/view";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("loginForm") LoginFormDto loginForm) {
        return "users/loginForm";
    }


}
