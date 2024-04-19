package com.sanlugar.sanluapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanlugar.sanluapp.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
}
