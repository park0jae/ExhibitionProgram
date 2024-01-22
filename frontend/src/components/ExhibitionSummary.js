import DatePicker from "react-datepicker";
import React, { useState } from "react";
import "react-datepicker/dist/react-datepicker.css";

export function Summary({ items = [], onReservationSubmit, oneExhibition, price}) {
  const [reservation, setReservation] = useState({
    reservationTime: "",
    email: "",
    exhibitionName: oneExhibition
  });

  const handleReservationTimeChanged = (date) => {
    let temp = new Date(date);
    setReservation({ ...reservation, reservationTime: date });
  }
  
  const handleEmailInputChanged = (e) => setReservation({ ...reservation, email: e.target.value });
  const handleReservationNameInputChanged = (e) => setReservation({ ...reservation, exhibitionName: e.target.value });

  const handleSubmit = (e) => {
    if (reservation.reservationTime === "" || reservation.email === "") {
      alert("입력값을 확인해주세요!");
    } else {
      onReservationSubmit(reservation);
    }
  }

  return (
    <>
      <div>
        <h5 className="m-0 p-0"><b>예약 정보</b></h5>
      </div>
      <hr />
      <form>
        <div className="mb-3">
          <label htmlFor="reservationTime" className="form-label">예약 시간</label>
          <div>
          <DatePicker
            selected={reservation.reservationTime}
            onChange={handleReservationTimeChanged}
            showTimeSelect
            timeFormat="HH:mm"
            timeIntervals={15}
            dateFormat="yyyy-MM-dd HH:mm시"
            className="form-control mb-1"
            id="reservationTime"
          />
          </div>
        </div>
        <div className="mb-3">
          <label htmlFor="email" className="form-label">이메일</label>
          <input
            type="email"
            className="form-control mb-1"
            value={reservation.email}
            onChange={handleEmailInputChanged}
            id="email"
          />
        </div>
        <div className="mb-3">
                  <label htmlFor="exhibitionName" className="form-label">전시회 명</label>
                  <input
                    type="exhibitionName"
                    className="form-control mb-1"
                    value={oneExhibition}
                    onChange={handleReservationNameInputChanged}
                    id="exhibitionName"
                  />
        </div>
        <div>전시 예약은 선결제를 통해 예약됩니다.</div>
      </form>
      <div className="row pt-2 pb-2 border-top">
        <h5 className="col">총금액</h5>
        <h5 className="col text-end">{price}원</h5>
      </div>
      <button className="btn btn-dark col-12" onClick={handleSubmit}>결제하기</button>
    </>
  );
}
