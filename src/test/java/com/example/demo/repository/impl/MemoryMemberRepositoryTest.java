package com.example.demo.repository.impl;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemberRepository memberRepository = new MemoryMemberRepository();

    @BeforeEach
    void beforeEach() {
        memberRepository.deleteAll();

        Member member = new Member();
        member.setName("admin");
        memberRepository.save(member);
    }

    @AfterEach
    void afterEach() {
        memberRepository.deleteAll();
    }

    @Test
    void save() {
        // Given
        Member member = new Member();
        member.setName("rolroralra");

        // When
        memberRepository.save(member);

        // Then
        Optional<Member> findMember = memberRepository.findById(member.getId());
        assertThat(findMember)
                .isPresent()
                .get()
                .isEqualTo(member);
    }

    @Test
    void findById() {
        // Given
        Member member = new Member();
        member.setName("rolroralra");
        memberRepository.save(member);

        Member member2 = new Member();
        member2.setName("guest");
        memberRepository.save(member2);

        // When
        Optional<Member> findMember = memberRepository.findById(member.getId());

        // Then
        assertThat(findMember)
                .isPresent()
                .get()
                .isEqualTo(member);

    }

    @Test
    void findAll() {
        // Given
        Member member = new Member();
        member.setName("rolroralra");
        memberRepository.save(member);

        Member member2 = new Member();
        member2.setName("guest");
        memberRepository.save(member2);

        // When
        List<Member> findAllMemberList = memberRepository.findAll();

        // Then
        assertThat(findAllMemberList).hasSize(3);
    }

    @Test
    void findByName() {
        // Given
        Member member = new Member();
        member.setName("rolroralra");
        memberRepository.save(member);

        Member member2 = new Member();
        member2.setName("guest");
        memberRepository.save(member2);

        // When
        Optional<Member> findMember = memberRepository.findByName(member.getName());

        // Then
        assertThat(findMember)
                .isPresent()
                .get()
                .isEqualTo(member);
    }

    @Test
    void delete() {
        // Given
        Member member = new Member();
        member.setName("rolroralra");
        memberRepository.save(member);

        Member member2 = new Member();
        member2.setName("guest");
        memberRepository.save(member2);

        // When
        memberRepository.delete(member);

        // Then
        assertThat(memberRepository.findByName("rolroralra"))
                .isNotPresent();
        assertThat(memberRepository.findByName("guest"))
                .isPresent()
                .get()
                .isEqualTo(member2);
        assertThat(memberRepository.findAll())
                .hasSize(2);
    }

    @Test
    void deleteById() {
        // Given
        Member member = new Member();
        member.setName("rolroralra");
        memberRepository.save(member);

        Member member2 = new Member();
        member2.setName("guest");
        memberRepository.save(member2);

        // When
        memberRepository.deleteById(member.getId());

        // Then
        assertThat(memberRepository.findByName("rolroralra"))
                .isNotPresent();
        assertThat(memberRepository.findByName("guest"))
                .isPresent()
                .get()
                .isEqualTo(member2);
        assertThat(memberRepository.findAll())
                .hasSize(2);
    }

    @Test
    void deleteAll() {
        // Given
        Member member = new Member();
        member.setName("rolroralra");
        memberRepository.save(member);

        Member member2 = new Member();
        member2.setName("guest");
        memberRepository.save(member2);

        // When
        memberRepository.deleteAll();

        // Then
        assertThat(memberRepository.findAll())
                .isEmpty();
    }
}