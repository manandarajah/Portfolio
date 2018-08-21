<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Default.aspx.cs" Inherits="PortfolioWeb.Default" %>

<!DOCTYPE html>

<html ng-app="root">
    <head>
        <title>Mugiesshan's Portfolio</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            body, h1, h2, h3, h4, h5, h6 {
                font-family: "Montserrat", sans-serif
            }

            li > div, li > p {
                position: relative;
                bottom: 10em;
                left: 22em;
            }

            .intimate-code-span {
                background-color: darkblue;
                color: yellow;
            }

            .passion-code-span {
                background-color: #ffc299;
                color: blue;
            }

            .w3-row-padding img {
                margin-bottom: 12px
            }

            .icon-img {
                width: 130px;
                height: 130px;
                margin: 0em 5em 5em 0em;
            }

            .icon-img-long {
                width: 300px;
            }

            .bgimg {
                background-position: center;
                background-repeat: no-repeat;
                background-size: cover;
                background-image: url('Images/Profile Picture.jpg');
                min-height: 100%;
            }
        </style>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
        <script>
            angular.module('root', []).controller('main', function ($scope) {
                $scope.techs = [
                    { "Technology": "Java", "Image": "Java.png", "Class": "icon-img", "Experience": "3" },
                    { "Technology": "NetBeans", "Image": "netbeans.png", "Class": "icon-img icon-img-long", "Experience": "3" },
                    { "Technology": "HTML5-CSS3-JavaScript", "Image": "html5-css3-javascript.png", "Class": "icon-img", "Experience": "3" },
                    { "Technology": "JQuery", "Image": "jquery-icon.png", "Class": "icon-img", "Experience": "3" },
                    { "Technology": "Linux", "Image": "linux.png", "Class": "icon-img icon-img-long", "Experience": "3" },
                    { "Technology": "SQL", "Image": "SQL.png", "Class": "icon-img", "Experience": "3" },
                    { "Technology": "MySQL", "Image": "MySQL.png", "Class": "icon-img icon-img-long", "Experience": "3" },
                    { "Technology": "C#", "Image": "C-sharp.png", "Class": "icon-img", "Experience": "1" },
                    { "Technology": "Unity", "Image": "Unity.png", "Class": "icon-img", "Experience": "1" },
                    { "Technology": "Bootstrap", "Image": "Bootstrap.png", "Class": "icon-img", "Experience": "1" },
                    { "Technology": "JavaFX", "Image": "javafx.png", "Class": "icon-img", "Experience": "0.66666666666666666666666666666667" },
                    { "Technology": "Android", "Image": "android.png", "Class": "icon-img", "Experience": "0.66666666666666666666666666666667" },
                    { "Technology": "Firebase", "Image": "firebase.png", "Class": "icon-img", "Experience": "0.66666666666666666666666666666667" },
                    { "Technology": "LibGDX", "Image": "Libgdx.png", "Class": "icon-img", "Experience": "0.5" },
                    { "Technology": "C/C++", "Image": "C-plus-plus.png", "Class": "icon-img", "Experience": "0.41666666666666666666666666666667" },
                    { "Technology": "Eclipse", "Image": "eclipse-icon.png", "Class": "icon-img", "Experience": "0.33333333333333333333333333333333" },
                    { "Technology": ".NET", "Image": ".NET.png", "Class": "icon-img", "Experience": "0.33333333333333333333333333333333" },
                    { "Technology": "Stripe", "Image": "stripe.png", "Class": "icon-img icon-img-long", "Experience": "0.33333333333333333333333333333333" },
                    { "Technology": "PHP", "Image": "PHP.png", "Class": "icon-img", "Experience": "0.33333333333333333333333333333333" },
                    { "Technology": "Hibernate", "Image": "hibernate.png", "Class": "icon-img", "Experience": "0.33333333333333333333333333333333" },
                    { "Technology": "Spring", "Image": "spring-by-pivotal.png", "Class": "icon-img icon-img-long", "Experience": "0.33333333333333333333333333333333" },
                    { "Technology": "E-Commerce", "Image": "e-commerce.png", "Class": "icon-img", "Experience": "0.16666666666666666666666666666667" },
                    { "Technology": "Unreal Engine", "Image": "unreal-engine.png", "Class": "icon-img", "Experience": "0.08333333333333333333333333333333" },
                    { "Technology": "AngularJS", "Image": "angularjs.png", "Class": "icon-img icon-img-long", "Experience": "0.08333333333333333333333333333333" },
                ];

                $scope.loadData = function () {
                    $scope.techs = [
                        { "Technology": "Java", "Image": "Java.png", "Class": "icon-img", "Experience": "3" },
                        { "Technology": "NetBeans", "Image": "netbeans.png", "Class": "icon-img icon-img-long", "Experience": "3" },
                        { "Technology": "HTML5-CSS3-JavaScript", "Image": "html5-css3-javascript.png", "Class": "icon-img", "Experience": "3" },
                        { "Technology": "JQuery", "Image": "jquery-icon.png", "Class": "icon-img", "Experience": "3" },
                        { "Technology": "Linux", "Image": "linux.png", "Class": "icon-img icon-img-long", "Experience": "3" },
                        { "Technology": "SQL", "Image": "SQL.png", "Class": "icon-img", "Experience": "3" },
                        { "Technology": "MySQL", "Image": "MySQL.png", "Class": "icon-img icon-img-long", "Experience": "3" },
                        { "Technology": "C#", "Image": "C-sharp.png", "Class": "icon-img", "Experience": "1" },
                        { "Technology": "Unity", "Image": "Unity.png", "Class": "icon-img", "Experience": "1" },
                        { "Technology": "Bootstrap", "Image": "Bootstrap.png", "Class": "icon-img", "Experience": "1" },
                        { "Technology": "JavaFX", "Image": "javafx.png", "Class": "icon-img", "Experience": "0.66666666666666666666666666666667" },
                        { "Technology": "Android", "Image": "android.png", "Class": "icon-img", "Experience": "0.66666666666666666666666666666667" },
                        { "Technology": "Firebase", "Image": "firebase.png", "Class": "icon-img", "Experience": "0.66666666666666666666666666666667" },
                        { "Technology": "LibGDX", "Image": "Libgdx.png", "Class": "icon-img", "Experience": "0.5" },
                        { "Technology": "C/C++", "Image": "C-plus-plus.png", "Class": "icon-img", "Experience": "0.41666666666666666666666666666667" },
                        { "Technology": "Eclipse", "Image": "eclipse-icon.png", "Class": "icon-img", "Experience": "0.33333333333333333333333333333333" },
                        { "Technology": ".NET", "Image": ".NET.png", "Class": "icon-img", "Experience": "0.33333333333333333333333333333333" },
                        { "Technology": "Stripe", "Image": "stripe.png", "Class": "icon-img icon-img-long", "Experience": "0.33333333333333333333333333333333" },
                        { "Technology": "PHP", "Image": "PHP.png", "Class": "icon-img", "Experience": "0.33333333333333333333333333333333" },
                        { "Technology": "Hibernate", "Image": "hibernate.png", "Class": "icon-img", "Experience": "0.33333333333333333333333333333333" },
                        { "Technology": "Spring", "Image": "spring-by-pivotal.png", "Class": "icon-img icon-img-long", "Experience": "0.33333333333333333333333333333333" },
                        { "Technology": "E-Commerce", "Image": "e-commerce.png", "Class": "icon-img", "Experience": "0.16666666666666666666666666666667" },
                        { "Technology": "Unreal Engine", "Image": "unreal-engine.png", "Class": "icon-img", "Experience": "0.08333333333333333333333333333333" },
                        { "Technology": "AngularJS", "Image": "angularjs.png", "Class": "icon-img icon-img-long", "Experience": "0.08333333333333333333333333333333" },
                    ];
                    $scope.$apply();
                }
            });
        </script>
    </head>
    <body>
        <div id="main-div" ng-controller="main">
            <!-- Sidebar with image -->
            <nav class="w3-sidebar w3-hide-medium w3-hide-small" style="width: 40%">
                <div class="bgimg w3-grayscale-max"></div>
            </nav>

            <!-- Hidden Sidebar (reveals when clicked on menu icon)-->
            <nav class="w3-sidebar w3-gray w3-animate-right w3-xxlarge" style="display: none; padding-top: 150px; right: 0; z-index: 2" id="mySidebar">
                <a href="javascript:void(0)" onclick="closeNav()" class="w3-button w3-xxxlarge w3-display-topright" style="padding: 0 12px;">
                    <i class="fa fa-remove"></i>
                </a>
                <div class="w3-bar-block w3-center">
                    <a href="#" class="w3-bar-item w3-button w3-text-grey w3-hover-black" onclick="closeNav()">Home</a>
                    <a href="#about" class="w3-bar-item w3-button w3-text-grey w3-hover-black" onclick="closeNav()">About</a>
                    <a href="#skills" class="w3-bar-item w3-button w3-text-grey w3-hover-black" onclick="closeNav()">My Skills</a>
                    <div class="w3-dropdown-click">
                        <button class="w3-bar-item w3-button w3-text-grey w3-hover-black" onclick="dropDown()">Portfolio</button>
                        <div id="streams" class="w3-dropdown-content w3-animate-zoom w3-light-grey w3-text-grey w3-bar-block w3-card">
                            <a href="#webdev" class="w3-bar-item w3-button w3-hover-black w3-hover-text-white" onclick="closeNav()">Web Development</a>
                            <a href="#deskdev" class="w3-bar-item w3-button w3-hover-black w3-hover-text-white" onclick="closeNav()">Desktop Development</a>
                            <a href="#mobdev" class="w3-bar-item w3-button w3-hover-black w3-hover-text-white" onclick="closeNav()">Mobile Development</a>
                            <a href="#gamedev" class="w3-bar-item w3-button w3-hover-black w3-hover-text-white" onclick="closeNav()">Game Development</a>
                        </div>
                    </div>
                    <a href="#contact" class="w3-bar-item w3-button w3-text-grey w3-hover-black" onclick="closeNav()">Contact</a>
                </div>
            </nav>

            <!-- Page Content -->
            <div class="w3-main w3-dark-grey w3-padding-large" style="margin-left: 40%">

                <!-- Menu icon to open sidebar -->
                <span class="w3-button w3-top w3-xxlarge w3-text-light-grey w3-hover-text-white" style="width: auto; right: 0;" onclick="openNav()"><i class="fa fa-bars"></i></span>

                <!-- Header -->
                <header class="w3-container w3-center" style="padding: 128px 16px" id="home">
                    <h1 class="w3-jumbo"><b>Mugiesshan Anandarajah</b></h1>
                    <h2>Growth Driven Software Developer</h2>
                    <!--<button class="w3-button w3-light-grey w3-padding-large w3-margin-top">
                        <i class="fa fa-download"></i>Download Resume
                    </button>-->
                </header>
                    <p class="w3-xxlarge">In progress:</p>
                    <i class="w3-xlarge w3-serif">Muay Thai Simulator</i>

                <!-- About Section -->
                <div class="w3-content w3-justify w3-text-light-grey w3-padding-32" id="about">
                    <h2>About</h2>
                    <hr class="w3-opacity">
                    <p>
                        Are you looking for someone who doesn't just have the  <code class="w3-codespan">technical skills</code> in software solutions, but who can also be a  <code class="w3-codespan intimate-code-span">valuable asset</code> to your business?
                        <br /><br />
My name is  <code class="w3-codespan intimate-code-span">Mugiesshan Anandarajah</code>, and I'm an entry level <code class="w3-codespan passion-code-span">software developer</code> who is all about <code class="w3-codespan passion-code-span">learning</code>. I'm not just someone who is <code class="w3-codespan intimate-code-span">results driven</code>, I'm all about <code class="w3-codespan intimate-code-span">growth</code> whether as an <code class="w3-codespan intimate-code-span">employee</code> or an <code class="w3-codespan passion-code-span">entrepreneur</code>. I'm currently seeking a software developer position.
<br /><br />
With <code class="w3-codespan">3 years</code> of combined <code class="w3-codespan passion-code-span">academic and workplace</code> expertise in software development, I can deliver tailored software solutions.
                        <br /><br />

                        <h3>Accomplishments:</h3><br /><br />

    -Collaborated with aspiring developers to develop a VR horror game to experience horror and suspense using <code class="w3-codespan">Unity3D</code> and <code class="w3-codespan">C#</code>.<br />
    -Assisted in developing an application to provide visual feedback to physiotherapy patients using <code class="w3-codespan">Java</code>, <code class="w3-codespan">Android Studio</code>, and <code class="w3-codespan">LibGDX</code> Framework.<br />
    -Developed a graphics based social networking website to allow users to communicate clearly and concisely using <code class="w3-codespan">PHP</code> web development standard and <code class="w3-codespan">JQuery</code> Framework.<br />
    -etc....<br /><br />

    <h3>Highlights:</h3><br /><br />

    -3 years of Java & Web Dev<br />
    -<code class="w3-codespan">C++</code> Certified<br />
    -Data Structures and Algorithms Certified<br />
    -Detailed oriented individual<br /><br />
                    </p>

                </div>
                    <!-- End About Section -->

                <div id="skills" class="w3-padding-32 w3-content w3-text-light-grey">
                    
                    <!--Uses AngularJS to display a list of software technologies and lenght of experience-->
                    <h3 class="w3-padding-16">My Skills</h3>
                    <hr class="w3-opacity">
                    <input id="search" type="text" class="w3-input" ng-model="search" />
                    <label>Search for what you're looking for here</label>
                    <ul style="list-style-type:none" class="w3-ul">
                        <li ng-repeat="list in techs | filter:search">
                            <img class="{{list.Class}}" src="Images/{{list.Image}}" />
                            <div></div>
                            <input type="hidden" value="{{list.Experience}}" />
                        </li>
                    </ul><br /><br />
                </div>

                <!-- Portfolio Section -->
                <div class="w3-padding-32 w3-content w3-text-light-grey" id="portfolio">
                    <h2>My Portfolio</h2>
                    <hr class="w3-opacity">
                    <div class="w3-padding-large" id="webdev">
                        <h3>Web Development</h3>
    Graphical Social Networking:<br /><br />
    My very first development project is a social networking website which implements a draw function which allows for visual communication between users. Developed 
                        artsy tools using <code class="w3-codespan">JavaScript</code> and <code class="w3-codespan">JQuery</code>, used <code class="w3-codespan">AJAX</code> for the client to communicate with the server, and used PHP and SQL to allow users to exchange messages with each other.<br /><br />

                        Bus E-card Application:<br /><br />

                        My team and I assembled to participate in a Hackathon, and we decided to create an application that allows transit commuters to pay
    their fares using a mobile application instead of a presto card. We had limited hours to work with during the competition, so my team
    and I decided to develop a design prototype of the application. It was developed using <code class="w3-codespan">HTML5</code>, <code class="w3-codespan">CSS3</code>, <code class="w3-codespan">JavaScript</code>, <code class="w3-codespan">Bootstrap</code>, and <code class="w3-codespan">JQuery</code>.<br /><br />

                        Movie Review Application:<br /><br />
                        A simple web assignment while taking Advanced Java Frameworks. This was made using <code class="w3-codespan">HTML5</code>, <code class="w3-codespan">CSS3</code>, <code class="w3-codespan">Java</code>, <code class="w3-codespan">Hibernate</code>, and <code class="w3-codespan">Lombok</code><br /><br />

                        Trivial Hangman:<br /><br />

                        Trivial Hangman is an enhanced version of Hangman where players answers a set of trivia questions. If they answered a question 
                        correctly, they unlock a letter to the passage, otherwise draw the hangman. They have the option of answering questions or solving 
                        the passage at hand. This project uses simple web technologies such as <code class="w3-codespan">HTML5</code>, <code class="w3-codespan">CSS3</code>, <code class="w3-codespan">JavaScript</code> and <code class="w3-codespan">AJAX</code>.<br /><br />

                        This website:<br /><br />
                        I count this as my portfolio because as most of my projects are utilized using languages such as <code class="w3-codespan">JSPs</code> and PHP, this portfolio website
                        is created using <code class="w3-codespan">ASP.NET</code>. Recently learned technologies i.e <code class="w3-codespan">W3.CSS</code> and <code class="w3-codespan">AngularJS</code> have been implemented here on this site.
                        The development of this website will be an ongoing process, each update featuring something new. Be patient
                        and stay tuned for some awesome updates.
                    </div>
                    <div class="w3-padding-large" id="deskdev">
                        <h3>Desktop Development</h3>
                        Story Game Prototype:<br /><br />
                        During my 2 month vacation from my first year in college, I decided to develop a 2D prototype of my story character. It's a small project
    but the inspiration behind it are my interests in video games and fiction writing. This prototype is developed using <code class="w3-codespan">Java</code> with <code class="w3-codespan">JavaFX</code>
    framework, as well as paint.NET software to design my 2D sprite and spritesheet.
                    </div>
                    <div class="w3-padding-large" id="mobdev">
                        <h3>Mobile Development</h3>
    Automated Rehabilitation System (ARS) Application:<br /><br />
    A physiotherapy application that provides visual feedback to patients to communicate their bodily conditions effectively. Programmed using <code class="w3-codespan">Java</code>, 
                        <code class="w3-codespan">Android Studio/IntelliJ</code>, <code class="w3-codespan">LibGDX</code> Graphics Framework to allow users to interact with 3D graphics and uses Bluetooth remote control API to sync a 
                        patient's bone movement in the application automatically.<br /><br />

                        Checkers:<br /><br />
                        I've drawn the board and checker pieces. This project was made using <code class="w3-codespan">Android Studio</code>, <code class="w3-codespan">Java</code>, and <code class="w3-codespan">LibGDX</code> game development framework.
                    </div>
                    <div class="w3-padding-large" id="gamedev">
                        <h3>Game Development</h3>
                        Muay Thai Simulator:<br /><br />

    Inspired by my interest in Muay Thai, this gaming prototype simulates the basic and lethal strikes of Muay Thai. This was developed
    using <code class="w3-codespan">Unity3D</code> and <code class="w3-codespan">C#</code>.<br /><br />

                        Power Up:<br /><br />

    This game has your robotic model keeping up by energizing itself with batteries to keep running. It served as my transition from <code class="w3-codespan">Unity3D</code>
    to <code class="w3-codespan">Unreal Engine</code> and <code class="w3-codespan">C++</code>. From there I learned about blueprints, visual scripting, and configuring properties using macros.<br /><br />

                        Combat 3D:<br /><br />

    My very first game development assignment when learning game development in <code class="w3-codespan">Unity3D</code> and <code class="w3-codespan">C#</code>. Through this one project, I learned a
    lot about mesh manipulation, detecting object collision, designing user interfaces, keeping 3D models grounded etc.
                    </div>

                    <!-- Grid for photos -->
                    <div class="w3-row-padding" style="margin: 0 -16px">

                        <!-- End photo grid -->
                    </div>
                    <!-- End Portfolio Section -->
                </div>

                    <!-- Testimonials -->
                    <!--<h3 class="w3-padding-24">My Reputation</h3>
                    <img src="/w3images/avatar_smoke.jpg" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width: 80px">
                    <p><span class="w3-large w3-text-black w3-margin-right">Chandler Bing.</span> Web Designer.</p>
                    <p>Jane Doe is just awesome. I am so happy to have met her!</p>
                    <br>

                    <img src="/w3images/bandmember.jpg" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width: 80px">
                    <p><span class="w3-large w3-text-black w3-margin-right">Chris Fox.</span> CEO at Mighty Schools.</p>
                    <p>Jane Doe saved us from a web disaster.</p>
                    <br>

                    <img src="/w3images/avatar_g2.jpg" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width: 80px">
                    <p><span class="w3-large w3-text-black w3-margin-right">Rebecca Flex.</span> CEO at Company.</p>
                    <p>No one is better than Jane Doe.</p>
                    <br>-->
            
                    <!-- Contact Section -->
                    <div class="w3-padding-32 w3-content w3-text-light-grey" id="contact" style="margin-bottom: 64px">
                        <h2>Contact Me</h2>
                        <p>
                            Email: mugiesshan.anandarajah@hotmail.com<br />
                            Phone: (647)-622-9597<br />
                            Toronto, ON
                        </p>
                    </div>
                    <!--<div class="w3-padding-32 w3-content w3-text-grey" id="contact" style="margin-bottom: 64px">
                        <h2>Contact Me</h2>
                        <hr class="w3-opacity">

                        <div class="w3-section">
                            <p><i class="fa fa-map-marker fa-fw w3-xxlarge w3-margin-right"></i>Toronto, Ontario</p>
                            <p><i class="fa fa-phone fa-fw w3-xxlarge w3-margin-right"></i>Phone: +00 151515</p>
                            <p><i class="fa fa-envelope fa-fw w3-xxlarge w3-margin-right"></i>Email: mail@mail.com</p>
                        </div>-->

                        <!-- Google Map -->
                        <!--<div id="googleMap" class="w3-greyscale-max" style="width: 100%; height: 300px; margin: 36px 0;"></div>

                        <p>Lets get in touch. Send me a message:</p>
                        <form action="/action_page.php" target="_blank">
                            <p>
                                <input class="w3-input w3-padding-16 w3-border" type="text" placeholder="Name" required name="Name"></p>
                            <p>
                                <input class="w3-input w3-padding-16 w3-border" type="text" placeholder="Email" required name="Email"></p>
                            <p>
                                <input class="w3-input w3-padding-16 w3-border" type="text" placeholder="Subject" required name="Subject"></p>
                            <p>
                                <input class="w3-input w3-padding-16 w3-border" type="text" placeholder="Message" required name="Message"></p>
                            <p>
                                <button class="w3-button w3-light-grey w3-padding-large" type="submit">
                                    <i class="fa fa-paper-plane"></i>SEND MESSAGE
                                </button>
                            </p>
                        </form>-->
                        <!-- End Contact Section -->
                    <!--</div>-->

                    <!-- Footer -->
                    <footer class="w3-container w3-padding-64 w3-light-grey w3-center w3-text-black w3-opacity w3-xlarge" style="margin: -24px">
                        <h2>Follow Me!</h2>
                        <!--<a target="_blank" href="https://www.facebook.com/mugie.anandarajah" class="fa fa-facebook-official w3-hover-opacity"></a>-->
                        <a target="_blank" href="https://www.linkedin.com/in/mugiesshan-anandarajah-502b48a3/" class="fa fa-linkedin w3-hover-opacity"></a>
                        <a target="_blank" href="https://github.com/manandarajah/Portfolio" class="fa fa-github w3-hover-opacity"></a>
                        <p class="w3-medium">Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank" class="w3-hover-text-green">w3.css</a></p>
                        <!-- End footer -->
                    </footer>

                    <!-- END PAGE CONTENT -->

                <!-- Add Google Maps -->
                <script>
                    var once = false;

                    $(document).ready(function () {
                        setInterval(drawBars, 1000);
                    });

                    function myMap() {
                        myCenter = new google.maps.LatLng(41.878114, -87.629798);
                        var mapOptions = {
                            center: myCenter,
                            zoom: 12, scrollwheel: false, draggable: false,
                            mapTypeId: google.maps.MapTypeId.ROADMAP
                        };
                        var map = new google.maps.Map(document.getElementById("googleMap"), mapOptions);

                        var marker = new google.maps.Marker({
                            position: myCenter,
                        });
                        marker.setMap(map);
                    }

                    //Draw bars for length of experience
                    function drawBars() {
                        var lists = $("li");

                        if (!once && lists.length > 0) {
                            lists.remove();
                            angular.element("#main-div").scope().loadData();
                            once = true;
                        }

                        var width = window.innerWidth;
                        console.log(width);

                        for (i = 0; i < lists.length; ++i) {
                            var durType = "year";
                            var duration = lists[i].getElementsByTagName("input")[0].value;
                            var element = $("li:nth(" + i + ") > div");

                            if (!element.hasClass("w3-light-gray")) {
                                element.addClass("w3-light-gray");
                                element.css({ "height": "50px", "width": (duration * 100) + "px" });
                            }

                            if (duration < 1) {
                                duration *= 12;
                                durType = "month";
                            }

                            if (duration > 1) {
                                durType += "s";
                            }
                            var p = "<p style='margin-right: 1em;'>" + duration + " " + durType + "</p>";

                            if (element.hasClass("w3-right-align") || $("li:nth(" + i + ") > p").hasClass("w3-text-white")) {

                            }

                            else if (durType.includes("year")) {
                                element.append(p);
                                element.addClass("w3-right-align");
                            }

                            else {
                                $("li:nth(" + i + ")").append(p);
                                $("li:nth(" + i + ") > p").addClass("w3-text-white");
                            }
                        }
                    }

                    //Open dropdown menu for portfolio subcategories
                    function dropDown() {
                        var x = document.getElementById("streams");

                        if (x.className.indexOf("w3-show") == -1) {
                            x.className += " w3-show";
                        }

                        else {
                            x.className = x.className.replace("w3-show", "");
                        }
                    }

                    // Open and close sidebar
                    function openNav() {
                        document.getElementById("mySidebar").style.width = "60%";
                        document.getElementById("mySidebar").style.display = "block";
                    }

                    function closeNav() {
                        document.getElementById("mySidebar").style.display = "none";
                    }
                </script>
                <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBu-916DdpKAjTmJNIgngS6HL_kDIKU0aU&callback=myMap"></script>
                <!--
            To use this code on your website, get a free API key from Google.
            Read more at: https://www.w3schools.com/graphics/google_maps_basic.asp
            -->
            </div>
        </div>
    </body>
</html>