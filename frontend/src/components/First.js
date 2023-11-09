import '../App.css';
import 'bootstrap/dist/css/bootstrap.css';
import React, { useEffect, useState, useRef } from 'react';
import {ExhibitionList} from "./ExhibitionList";
import {Summary} from "./ExhibitionSummary";
import axios from "axios";
import { useNavigate } from 'react-router-dom';
import NavigationBar from './NavigationBar';

export default function First(){
    const [exhibitions, setExhibitions] = useState();
    const [items, setItems] = useState();
    const [price, setPrice] = useState(0);
    const [oneExhibition, setOneExhibition] = useState();
    const [name, setName] = useState();
    
    const fetchExhibitions = () => {
        axios.get('http://localhost:8080/api/v1/exhibitions')
            .then(v => setExhibitions(v.data));
    };
    
    useEffect(() => {
        fetchExhibitions();
    }, []);
    
    const handleReservationSubmit = (reservation) => {
        axios.post('http://localhost:8080/api/v1/reservations', {
            reservationTime: new Date(reservation.reservationTime), // 문자열에서 Date 객체로 변경
            email: reservation.email,
            exhibitionName: oneExhibition
        })
        .then(() => {
            alert('예약이 완료되었습니다');
            window.location.replace('/');
        })
        .catch(error => {
            alert("서버 장애");
            console.error(error);
        });
    }

    const handleDateChange = (e) => {
        axios.get('http://localhost:8080/api/v1/exhibitions/condition', {
            params: {
                exhibitionTime: `${e.target.value}T00:00`,
            }
        })
        .then((v) => {
            setExhibitions(v.data);
        })
    }

    const handleNameChange = (e) => {
        const value = e.target.value;
        setName(value);
    
    }

    const submitNameChange = () => {
        axios.get('http://localhost:8080/api/v1/exhibitions/condition', {
            params: {
                exhibitionName: name,
            }
        })
        .then((v) => {
            setExhibitions(v.data);
        })
        .catch(error => {
            alert("서버 장애");
            console.error(error);
        });
    }

    return (
        <div>
            <NavigationBar />
            <div className="row justify-content-center m-4">
            <div style={{display:"flex", flexDirection:"row", marginLeft:"1240px"}}>
            <div style={{ marginLeft: '20px' }}>
                <label htmlFor="dateInput">날짜로 검색하기:</label>
                <input
                    type="date"
                    id="dateInput"
                    onChange={handleDateChange}
                />
                </div>

                <div style={{ marginLeft: '20px' }}>
                <label htmlFor="nameInput">이름으로 검색하기:</label>
                <input
                    type="text"
                    id="nameInput"
                    onChange={handleNameChange}
                />
            </div>
            <button className='btn btn-primary' onClick={submitNameChange}>검색</button>
            <button className='btn btn-primary' onClick={fetchExhibitions}>전체 전시 목록 보기</button>
            </div>
            </div>
            <div className="card">
            <div className="row" style={{width:"1100px"}}>
                <div className="col-md-8 mt-4 d-flex flex-column align-items-start p-3 pt-0">
                <ExhibitionList setOneExhibition={setOneExhibition} setPrice={setPrice} exhibitions={exhibitions} />
                </div>
                <div className="col-md-4 summary p-4">
                <Summary items={items} oneExhibition={oneExhibition} price = {price} onReservationSubmit={handleReservationSubmit}/>
                </div>
            </div>
            </div>
        </div>
    );
}
