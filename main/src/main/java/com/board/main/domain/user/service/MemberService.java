package com.board.main.domain.user.service;

import com.board.main.domain.user.dto.MemberDto;
import com.board.main.domain.user.entity.Member;
import com.board.main.domain.user.repository.MemberRepository;
import com.board.main.domain.user.repository.MemoryMemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder){
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    private MemberDto of(Member member){
        return modelMapper.map(member,MemberDto.class);  //member entitiy를 member Dto로 변경
    }


    /**
     * 회원가입
     * @param member
     * @return string UserId;
     */
    public String join(Member member) {
        duplicateIDCheck(member);
        Member newMember = new Member();
        newMember.setUserID(member.getUserId());
        newMember.setUserName(member.getUserName());
        newMember.setPassword(passwordEncoder.encode(member.getPassword()));

        memberRepository.save(newMember);
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
    public List<MemberDto> findMembers() {
        List<Member>  membersList= memberRepository.findAll();

        List<MemberDto> memberDtoList = membersList.stream().map(q -> of(q)).collect(Collectors.toList());
        return memberDtoList;
    }

    /**
     * 특정 id값을 가지는 회원정보 조회
     * @param id
     * @return
     */
    public Optional<MemberDto> findMember(String id){

        Optional<Member> memberById =  memberRepository.findById(id);
        Optional<MemberDto> memberDtoById = memberById.map(q -> of(q));
        return memberDtoById;

    }
}
