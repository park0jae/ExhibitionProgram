import React from "react";
import {Exhibition} from "./Exhibition";

export function ExhibitionList({exhibitions = [],  setOneExhibition, setPrice}) {
  return (
    <React.Fragment>
      <h5 className="flex-grow-0"><b>전시 목록</b></h5>
      <ul className="list-group exhibitions w-100">
        {exhibitions.map(v =>
          <li key={v.exhibitionId} className="list-group-item d-flex mt-3">
            <Exhibition setOneExhibition={setOneExhibition} setPrice={setPrice} {...v} />
          </li>
        )}
      </ul>
    </React.Fragment>
  )
}
