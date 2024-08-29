import React, { Component } from 'react';
import './css/style.css'

import underlineImage from './underline.png'
import talkingImage from './talking.png'
import yellowbox from './yellowbox.png'
import redbox from './redbox.png'
import flying_manImage from './flying_man.png'
import blueshade from './blue-shade.png'
import yellowshade from './yellow-shade.png'
import yellowBreakBox from './yellow-break-box.png'
import redSoundBox from './red-sonud-box.png'

// import AboveNavBar from './compnents/AboveNavBar';
// import dottedLine from './dottedLineImage.png'
// import NavBar from './compnents/NavBar'


class App extends Component {
  render() {
    return( 
      <div>

        <div className="black-box">
          Free courses available. 🔥 Grab it now before it goes! -&gt;        
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
    
        <div class="parent-container">
          <div class="text-container">
            <p class="text-content">A Real-Time Learning Experience</p>
            <p class="text-content">that Leads to Your <span class="highlight">Dream Career</span></p>
            <img class="yellowBoxImage" src={yellowbox} alt=""/> 
            <img class="redboxImage" src={redbox} alt=""/> 
            <img class="flying_manImage" src={flying_manImage} alt=""/>     
            <img class="underlineImage" src={underlineImage} alt=""/>      
          </div>


          <div class="sub-text-container">
            <p class="sub-text-content">Experience the tech job before you land it. Impress</p>
            <p class="sub-text-content">recruiters with in-demand skills you learn by building what</p>
            <p class="sub-text-content">matters.</p>
          </div>    

          <div class="button-container">
            <button class="btn btn-primary">Explore Courses</button>
            <button class="btn btn-secondary">Connect with us!</button>
          </div>

          <div class="sub-heading-text-container">
            <p class="sub-heading-content">Number speak the truth</p>
          </div>

          <div class="inner-container">
              <div class="box">
                  <div class="first-line">95%</div>
                  <div class="second-line">Placement Rate</div>
              </div>
              <div class="box">
                  <div class="first-line">150+</div>
                  <div class="second-line">Students Placed</div>
              </div>
              <div class="box">
                  <div class="first-line">750+</div>
                  <div class="second-line">Hours of training</div>
              </div>
          </div>  

          <div class="second-sub-heading-text-container">
            <p class="second-sub-heading-content">Wiates Academy, where learning meets</p>
            <p class="blue-second-sub-heading-content">real-world application</p>
          </div>

          <div class="secondary-container">
            <div class="secondary-box">
              <p>At <span class="second-highlight">Wiates</span>, we understand the frustration of traditional learning. We believe education should be dynamic, engaging, and directly applicable to your career goals. That's why we offer a variety of versatile courses designed to boost your tech skillset and knowledge.</p>
              <p>We are all about real outcomes - whether it's certifications or career advancements, we're here to help you achieve your goals and reap the rewards. And it's not just about learning solo - join our vibrant online community for collaboration, networking, and peer learning. Best of all, our affordable tech-specific course alternatives make real-time project-based education accessible to everyone.</p>
            </div>

            <div class="secondary-box">
                <div class="text-line-1"></div>
            </div>
            <img class="talkingImage" src={talkingImage} alt=""/>
          </div>


          <div class="third-sub-heading-text-container">
            <p class="third-sub-heading-content">Diffrenttiation our students through</p>
            <p class="third-sub-heading-content"><span class="highlight">Real time </span>project <span class="highlight">experience</span></p>
          </div>
          
          <div class="main-container">
              <div class="overlapping-container">
                  <div class="overlapping-number">01</div>
                  <div class="overlapping-box">
                      <p>4-5 hours with experts every week</p>
                      <p class="justify">Out of X hours of training, you spend 4-5 hours/week with industry experts who've worked as mobile developers and web programming leaders with global brands.</p>
                  </div>
                  <img class="blueShade" src={blueshade} alt=""/>    
              </div>
              <div class="dotted-line"></div> 
              <div class="overlapping-container">
                <div class="overlapping-number number-02">02</div>
                  <div class="overlapping-box">
                      <p>Over n+assignments</p> 
                      <p class="justify">After tackling a number of assignments and mock interviews, you will practically be solving buisness problems in your sleep! Prepare yourself through any job interview that comes your way. </p>
                  </div>
                  <img class="yellowShade" src={yellowshade} alt=""/>    
              </div>
          </div>

          <div class="fourth-sub-heading-text-container">
              <img class="yellowBreakBox" src={yellowBreakBox} alt=""/>
              <img class="redSoundBox" src={redSoundBox} alt=""/>
              <p class="fourth-sub-heading-content">Hear from our tech squad</p>

              <div class="shapes-wrapper">
                <div class="shape-container">
                  <div class="curved-shape">
                    <p>Arun placed in a fortune 500 brands with 6 figure income as a fresher</p>
                    <p class="bottom-text-curved">Flutter Advanced Development</p>
                  </div>
                  <div class="circle">
                    <p class="top-text-circle">9</p>
                    <p class="bottom-text-circle">LPA</p>
                  </div>
                </div>

                <div class="shape-container">
                  <div class="curved-shape">
                    <p>Vaibhav secured a position in leading e-com brand as a fresher</p>
                    <p class="bottom-text-curved">Flutter Advanced Development</p>
                  </div>
                  <div class="circle">
                    <p class="top-text-circle">7</p>
                    <p class="bottom-text-circle">LPA</p>
                  </div>
                </div>
              </div>
            </div>

            <footer class="footer-container">
                <div class="top-container">
                  <p class="top-container-text">Wanna get hired at your dream job?</p>
                </div>
            </footer>

          
        </div>
      </div>

    )
  }
}

export default App;