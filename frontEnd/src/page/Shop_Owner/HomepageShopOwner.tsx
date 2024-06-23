import React from 'react';
import '../../css/homepage.css';
import NavBar from "../../components/navbars/ShopOwnerNavbar"


const HomePage: React.FC = () => {
    return (
      <div className="homepage">
        <NavBar /> 

        <div className="description">

        <div className="Question">Your Shop!</div>
          <hr className="line" />


          <div className="Question">What is Market2Door?</div>
          <hr className="line" />
  
          <div className="container">
            <div className="box">
              <h2 className="items">First</h2>
              <p className="itemsInfo">Connect Local Markets to People</p>
            </div>
  
            <div className="box">
              <h2 className="items">Second</h2>
              <p className="itemsInfo">Reduces Carbon Waste</p>
            </div>
  
            <div className="box">
              <h2 className="items">Third</h2>
              <p className="itemsInfo">Both Customer and Seller Benefits</p>
            </div>
          </div>
        </div>
      </div>
    );
  }
  
  export default HomePage;