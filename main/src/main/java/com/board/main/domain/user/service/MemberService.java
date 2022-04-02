package com.board.main.domain.user.service;

import com.board.main.domain.user.entity.Member;
import com.board.main.domain.user.repository.MemberRepository;
import com.board.main.domain.user.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class MemberService {

    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
   

    /**
     * 회원가입
     * @param member
     * @return string UserId;
     */
    public String join(Member member) {
        duplicateIDCheck(member);

        memberRepository.save(member);
        return member.getUserId();
    }

    /**
     * 중복 ID체크
     * @param member
     */
    private void duplicateIDCheck(Member member) {
        memberRepository.findById(member.getUserId())
                .ifPresent(m -> {
            throw new IllegalStateException("이미 가입된 아이디 입니다."); //optional로 감싸서 다음과 같은 문법 사용가능.
        });
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 특정 id값을 가지는 회원정보 조회
     * @param id
     * @return
     */
    public Optional<Member> findMember(String id){
        return memberRepository.findById(id);
    }
}
