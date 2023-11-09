import axios from 'axios';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import NavigationBar from './NavigationBar';

function Signup() {
    const navigate = useNavigate();
    const [nickname, setNickname] = useState();
    const [email, setEmail] = useState();
    const [phoneNumber, setPhoneNumber] = useState();

    return (
    <div>
      <NavigationBar />
      <div className='wrap'>
      <h2>회원 가입</h2>
        <div className="mb-3">
          <label htmlFor="nickname">닉네임</label>
          <input type="text" className="form-control" id="nickname" onChange={(e) => {setNickname(e.target.value)}} value={nickname}/>
        </div>
        <div className="mb-3">
          <label htmlFor="email">이메일</label>
          <input type="email" className="form-control" id="email" onChange={(e) => {setEmail(e.target.value)}} value={email}/>
        </div>
        <div className="mb-3">
          <label htmlFor="phoneNumber">전화번호(하이픈(-) 없이 입력하세요.)</label>
          <input type="tel" className="form-control" id="phoneNumber" onChange={(e) => {setPhoneNumber(e.target.value)}} value={phoneNumber}/>
        </div>
        <button type="submit" className="btn btn-primary" onClick={() => {
            let data = {
                nickname : nickname,
                email : email,
                phoneNumber : phoneNumber
            }
            axios.post('http://localhost:8080/api/v1/members', data)
            .then(response=>{
                console.log(response);
                if(response.status == '201'){
                    alert('회원가입이 정상적으로 되었습니다.');
                    navigate('/');
                }
            })
        }}>가입하기</button>
        </div>
    </div>
  );
}

export default Signup;
