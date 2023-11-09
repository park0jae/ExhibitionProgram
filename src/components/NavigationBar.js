import React from 'react';
import Navbar from 'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav';
import { useNavigate } from 'react-router-dom';

function NavigationBar() {
  const navigate = useNavigate();

  return (
    <Navbar bg="dark" variant="dark" style={{ width: "100vw" }}>
      <Navbar.Brand>Zerozae's Exhibition</Navbar.Brand>
      <Nav className="mr-auto">
        <Nav.Link onClick={() => navigate("/sign-up")}>회원가입</Nav.Link>
        <Nav.Link onClick={() => navigate("/search")}>예약 현황 보기</Nav.Link>
        <Nav.Link onClick={() => navigate("/")}>홈으로</Nav.Link>
      </Nav>
    </Navbar>
  );
}

export default NavigationBar;