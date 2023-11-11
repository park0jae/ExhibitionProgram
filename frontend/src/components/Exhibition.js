import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";


export function Exhibition(props) {
  const exhibitionId = props.id;
  const exhibitionName = props.exhibitionName;
  const price = props.price;
  const imageId = props.imageId;
  const [imageUrl, setImageUrl] = useState();
  
  const navigate = useNavigate();
  const handleAddBtnClicked = e => {
    console.log(imageId);
    props.setOneExhibition(exhibitionName);
    props.setPrice(price);
  };

  async function getImage(imageId) {
    try {
        const response = await axios.get(`http://localhost:8080/api/v1/images/${imageId}`, {
            responseType : 'arraybuffer',
        });
        const imageUrl = URL.createObjectURL(new Blob([response.data], {type: 'image/jpeg'}));
        console.log(imageUrl);
        return imageUrl;
    }catch(error) {
        console.log(error);
    }
  }

  useEffect(async () => {
    if(!imageId) return;
    setImageUrl(await getImage(imageId));
  }, [imageId]);

  return <>
    <div style={{display: 'flex', alignItems: 'center', width: '100%'}}>
      <img src={imageUrl} />
      <div style={{marginLeft: '5px', flexGrow: '1'}}>{exhibitionName}</div>
      <div className="col text-center price">{price}원</div>
      <div className="col text-end action" style={{display:'flex', gap: '5px'}}>
        <button onClick={()=>{
        navigate(`/detail/${exhibitionId}`, {
          state: {
            imageUrl: imageUrl,
          }
        });
      }} className="btn btn-small btn-outline-dark">상세보기</button>
        <button onClick={handleAddBtnClicked} className="btn btn-small btn-outline-dark">예약하기</button>
      </div>
    </div>
  </>
}
