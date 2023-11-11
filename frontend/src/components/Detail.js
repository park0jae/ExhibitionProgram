import { useLocation, useNavigate, useParams } from "react-router-dom"
import NavigationBar from './NavigationBar';
import React, { useEffect, useState } from 'react';
import axios from "axios";


const Detail = () => {
    const {exhibitionId} = useParams();
    const [exhibitionImage, setExhibitionImage] = useState();
    const [exhibi, setExhibi] = useState(null);
    const location = useLocation();
    
    const getExhibition = async() => {
        axios.get(`http://localhost:8080/api/v1/exhibitions/${exhibitionId}`)
        .then(async response => {
            const data = response.data;
            setExhibi(data);
        })
    }

    useEffect(() => {
        getExhibition();
    }, [exhibitionId]);

    return (
        exhibi ?
        <div>
            <NavigationBar />
            <div style={{width: '50%', margin: '0 auto'}}>
            <div style={{fontWeight: "bold", fontSize: '24px', margin: '50px 0'}}>{exhibi.exhibitionName}</div>
            <div style={{display:"flex", gap: '100px'}}>
                <img src={location.state.imageUrl} alt="기본 이미지" style={{width:"300px", height:"400px"}}/>
                <div style={{display: 'flex', flexDirection: 'column', gap: '10px'}}>
                    <div style={{display: "flex", gap: '10px'}}>
                        <div style={{fontWeight: 'bold'}}>시작 기간</div>
                        <div>{exhibi.startTime.split('T')[0]} - {exhibi.startTime.split('T')[1]}</div>
                    </div>
                    <div style={{display: "flex", gap: '10px'}}>
                        <div style={{fontWeight: 'bold'}}>마감 기간</div>
                        <div>{exhibi.endTime.split('T')[0]} - {exhibi.endTime.split('T')[1]}</div>
                    </div>
                    <div style={{display: "flex", gap: '10px'}}>
                        <div style={{fontWeight: 'bold'}}>장소</div>
                        <div>{exhibi.location}</div>
                    </div>
                    <div style={{display: "flex", gap: '10px'}}>
                        <div style={{fontWeight: 'bold'}}>가격</div>
                        <div>{exhibi.price}원</div>
                    </div>
                </div>
            </div>
            <div style={{fontWeight: 'bold', fontSize: '25px', marginTop: '20px'}}>설명</div>
            <hr></hr>
            <div>{exhibi.description}</div>
            </div>
        </div> : null
    );
};

export default Detail;