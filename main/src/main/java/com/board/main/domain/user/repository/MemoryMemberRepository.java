package com.board.main.domain.user.repository;

import com.board.main.domain.user.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// @Repository

public class MemoryMemberRepository implements MemberRepository{

        private static Map<String, Member> store = new HashMap<>();  //메모리에 저장할 자료구


        @Override
        public Member save(Member member) {
            store.put(member.getUserId(), member);
            return member;
        }
       // user의 ID를 키 값으로 하는 member 객체를 store에 저장
        @Override
        public Optional<Member> findById(String userId) {
            return Optional.ofNullable(store.get(userId));
        }
        //저장된 ID값을 가지고 member객체 return

//        @Override
//        public Optional<Member> findByName(String name) {
//            return store.values().stream()
//                    .filter(member -> member.getName().equals(name))
//                    .findAny();
//        }

        @Override
        public List<Member> findAll() {
            return new ArrayList<>(store.values());
        }
}
