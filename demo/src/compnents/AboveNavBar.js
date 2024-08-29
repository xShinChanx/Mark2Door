import style from '../css/style.css'
import React from "react"

function AboveNavBar (){
    return( 
        <div>
          <div className="black-box">
            Free courses available. 🔥 Grab it now before it goes!
          </div>

          <nav className="menubar">
  <div className="logo">
    <div href="/">Wiates</div>
  </div>
  <ul>
    <li><a href="#">Home</a></li>
    <li><a href="#">About Us</a></li>
    <li><a href="#">Courses</a></li>
    <li><a href="#">Contact</a></li>
  </ul>
</nav>
        </div>

        
    )
}

export default AboveNavBar;