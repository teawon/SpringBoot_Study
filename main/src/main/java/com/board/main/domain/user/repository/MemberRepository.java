package com.board.main.domain.user.repository;

import com.board.main.domain.user.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);  //회원 저장
    Optional<Member> findById(String id);  //회원의 id값으로 정보 찾기
   // Optional<Member> findByName(String name); //회원의 이름으로 아이디 찾기 (이 부분은 필요할까?)
    List<Member> findAll();
}
