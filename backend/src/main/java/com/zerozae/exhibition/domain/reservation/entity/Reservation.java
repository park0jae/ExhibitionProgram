package com.zerozae.exhibition.domain.reservation.entity;

import com.zerozae.exhibition.domain.exhibition.entity.Exhibition;
import com.zerozae.exhibition.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @Column(nullable = false)
    private LocalDateTime reservationTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibition_id")
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Exhibition exhibition;

    public Reservation(LocalDateTime reservationTime, Member member, Exhibition exhibition) {
        this.reservationTime = reservationTime;
        this.member = member;
        this.exhibition = exhibition;
    }

    public void updateReservation(LocalDateTime reservationTime, Exhibition exhibition) {
        if (reservationTime != null) {
            this.reservationTime = reservationTime;
        }
        if (exhibition != null) {
            this.exhibition = exhibition;
        }
    }
}
