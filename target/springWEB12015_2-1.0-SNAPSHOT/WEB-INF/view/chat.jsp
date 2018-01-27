<%@include file="templates/header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="/resources/sockjs-0.3.4.js"></script>
<script src="/resources/stomp.js"></script>
<link href="style/chat.css" rel="stylesheet" type="text/css" >


<div class="w3-container w3-content" style="max-width:1900px;margin-top:80px">
    <!-- The Grid -->
    <div class="w3-row">
        <!-- Left Column -->
        <div class="w3-col m3">
            <!-- Profile -->
            <div class="w3-card-2 w3-round w3-white">
                <div class="w3-container">
                    <h4 class="w3-center">My Profile</h4>

                </div>
            </div>
            <br>

            <!-- Accordion -->
            <div class="w3-card-2 w3-round">
                <div class="w3-white">
                    <a href="/blog/createBlog" class="w3-button w3-block w3-theme-l1 w3-left-align"><i class="fa fa-calendar-check-o fa-fw w3-margin-right"></i> Create Blogs</a>
                    <button class="w3-button w3-block w3-theme-l1 w3-left-align"><i class="fa fa-users fa-fw w3-margin-right"></i> My Photos</button>
                    <div class="w3-hide w3-container">
                        <div class="w3-row-padding">

                        </div>
                    </div>

                </div>
            </div>
            <br>

            <div class="w3-card-2 w3-round w3-white w3-padding-16 w3-center">
                <p>My Blog</p>
            </div>
            <br>

            <div class="w3-card-2 w3-round w3-white w3-padding-32 w3-center">
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d164705.55762681522!2d23.87215286350617!3d49.83260464310914!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x473add7c09109a57%3A0x4223c517012378e2!2z0JvRjNCy0ZbQsiwg0JvRjNCy0ZbQstGB0YzQutCwINC-0LHQu9Cw0YHRgtGM!5e0!3m2!1suk!2sua!4v1486512390381" frameborder="0" style="border:0" allowfullscreen></iframe>
            </div>
            <br>

            <div class="w3-card-2 w3-round w3-white w3-padding-32 w3-center">
                <p><i class="fa fa-bug w3-xxlarge"></i></p>
            </div>

            <!-- End Left Column -->
        </div>

        <!-- Middle Column -->
        <div class="w3-col m7">
            <div class="w3-container w3-card-2 w3-white w3-round w3-margin">
                <br>

                <div class="container">
                    <div class="row">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <button class="connect" id="connect" onclick="connect()">Connect</button>
                                <button class="disconnect" id="disconnect" disabled="disabled" onclick="disconnect()">Disconnect</button>
                            </div>
                            <div class="panel-body">

                                <div class="row message-bubble">
                                    <h4>Мій чат!</h4>
                                </div>

                                <div class="row message-bubble">
                                    <p id="response"></p>
                                </div>
                                <div class="panel-footer">
                                    <div class="input-group" id="conversationDiv">
                                        <input type="text" class="form-control" id="name">
                                        <span class="input-group-btn">
                    <button class="btn btn-default" type="button" id="sendName" onclick="sendName()">send name</button>
                  </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- End Middle Column -->
            </div>
        </div>
    </div>

    <!-- End Page Container -->
</div>

<script>
    var stompClient = null;
    function connect() {
        var socket = new SockJS('/hello');
        stompClient = Stomp.over(socket);
        stompClient.connect({},function (frame) {
            setConnected(true);
            console.log("Connected " + frame);
            stompClient.subscribe('/topic/greetings',function (greeting) {
                showGreeting(JSON.parse(greeting.body).content);
            })
        })
    }
    function setConnected(connected) {
        document.getElementById("connect").disabled = connected;
        document.getElementById("disconnect").disabled = !connected;
        document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
        document.getElementById('response').innerHTML = '';
    }
    function  showGreeting(message) {
        var response = document.getElementById('response');
        var p = document.createElement('p');
        p.style.wordWrap = "break-word";
        p.appendChild(document.createTextNode(message));
        response.appendChild(p);
    }
    function  disconnect() {
        stompClient.disconnect();
        setConnected(false);
        console.log("Disconnected");
    }
    function sendName() {
        var name = document.getElementById("name").value;
        stompClient.send("/app/hello", {},JSON.stringify({'name':name}));
    }
</script>

<br>
<%@include file="templates/footer.jsp"%>

