import axios from "axios";
import { useEffect, useState } from "react"
import NavigationBar from './NavigationBar';


export default function Search()
{
    const [data, setData] = useState();

    
    useEffect(()=>{
        axios.get('http://localhost:8080/api/v1/reservations')
    .then(response=>{
        console.log(response);
        setData(response.data);
    })
    .catch(error=>{
        console.log(error);
    })

    },[])

    if(!data) return null;
    return(
        <div className="text-center">
            <NavigationBar />
            <h1 style={{marginTop:"50px", marginBottom:"30px"}}> 예약 현황 </h1>
            {data?data.map(a=>(
                <table class="table table-striped mx-auto" style={{ maxWidth: '80%', marginTop:"50px" }}>
                    <thead>
                        <tr>
                            <th>전시명</th>
                            <th>예약자</th>
                            <th>예약날짜</th>
                            <th>예약시간</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>{a.exhibitionName}</td>
                            <td>{a.nickname}</td>
                            <td>{a.reservationTime.split('T')[0]}</td>
                            <td>{a.reservationTime.split('T')[1]}</td>
                        </tr>
                    </tbody>
                </table> 
            )) : null }
        </div>
    )
}