package com.zerozae.exhibition.factory.reservation;

import com.zerozae.exhibition.domain.reservation.entity.Reservation;

import java.time.LocalDateTime;

import static com.zerozae.exhibition.factory.exhibition.ExhibitionFactory.createExhibition;
import static com.zerozae.exhibition.factory.member.MemberFactory.createMember;

public class ReservationFactory {

    public static Reservation createReservation() {
        return new Reservation(
                LocalDateTime.of(2023, 11, 3, 12, 0),
                createMember(),
                createExhibition());
    }
}
