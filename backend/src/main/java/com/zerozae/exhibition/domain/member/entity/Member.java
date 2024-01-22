package com.zerozae.exhibition.domain.member.entity;

import com.zerozae.exhibition.domain.member.dto.MemberUpdateRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    public Member(String nickname, String email, String phoneNumber) {
        this.nickname = nickname;
        if(checkAddress(email)) {
            this.email = email;
        }
        this.phoneNumber = phoneNumber;
    }

    private static boolean checkAddress(String email) {
        return Pattern.matches("\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b", email);
    }

    public void updateMember(MemberUpdateRequest memberUpdateRequest) {
        if(memberUpdateRequest.nickname() != null) {
            this.nickname = memberUpdateRequest.nickname();
        }
        if(memberUpdateRequest.phoneNumber() != null) {
            this.phoneNumber = memberUpdateRequest.phoneNumber();
        }
    }
}
