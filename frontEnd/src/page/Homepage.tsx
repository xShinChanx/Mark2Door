import React from 'react';
import '../css/homepage.css';
import NavBar from "../components/navbars/CommonNavbar"


const HomePage: React.FC = () => {
    return (
      <div className="homepage">
        <NavBar /> 

        <div className="description">
          <div className="Question">What is SounDrive?</div>
          <hr className="line" />
  
          <div className="container">
            <div className="box">
              <h2 className="items">First Step</h2>
              <p className="itemsInfo">Create an account and Login.</p>
            </div>
  
            <div className="box">
              <h2 className="items">Second Step</h2>
              <p className="itemsInfo">Create a playlist and add songs to it.</p>
            </div>
  
            <div className="box">
              <h2 className="items">Third Step</h2>
              <p className="itemsInfo">Explore other playlists and share your playlist with others.</p>
            </div>
          </div>
        </div>
      </div>
    );
  }
  
  export default HomePage;