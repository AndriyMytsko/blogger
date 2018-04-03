<%@include file="templates/header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<div class="w3-container w3-content" style="max-width:1900px;margin-top:80px">

    <div class="w3-row">

        <div class="w3-col m3">

            <div class="w3-card-2 w3-round w3-white">
                <div class="w3-container">
                    <h4 class="w3-center"></h4>

                    <sec:authorize access="isAuthenticated()">
                        <sec:authentication property="principal" var="user" />
                        <p class="w3-center"><img src="${user.avatar}" class="w3" style="height:260px;width:240px;
                                cursor:pointer" onclick="onClick(this)" class="w3-hover-opacity" alt="Avatar" ></p>
                        <p class="w3-center">${user.username}</p>
                    </sec:authorize>
                </div>
            </div>

            <br>

            <sec:authentication property="principal" var="user" />
            <input type="button" class="w3-button w3-block w3-theme-l1 w3-left-align"  value="Моя сторінка" onClick='location.href="show-${user.id}"'>
            <input type="button" class="w3-button w3-block w3-theme-l1 w3-left-align"  value="Додати категорії" onClick='location.href="/category/createCategory"'>
            <button onclick="document.getElementById('id01').style.display='block'" class="w3-button w3-block w3-theme-l1 w3-left-align">Список категорій</button>

            <div id="id01" class="w3-modal">
                    <div class="w3-modal-content w3-card-4">
                        <header class="w3-container w3-theme-l1">
                            <button id="modal-window" onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-display-topright">×</button>
                            <h2>Список категорій</h2>
                        </header>
                        <div class="w3-container">
                            <c:forEach items="${categorise}" var="category">
                                    <input type="button" class="w3-button w3-block w3-left-align" value="${category.name}" onClick='location.href="/category/showCategory-${category.id}"'>
                                <span class="w3-button w3-display-topright">&times;</span>
                            </c:forEach>
                        </div>
                        <footer class="w3-container w3-theme-l1">
                            <p></p>
                        </footer>
                    </div>
                </div><br>

            <div class="w3-card-2 w3-round w3-white w3-padding-32 w3-center">
                <p><i class="fa fa-bug w3-xxlarge"></i></p>
            </div>

        </div>

        <div class="w3-col m7">

            <div class="w3-row-padding">
                <div class="w3-col m12">
                    <div class="w3-card-2 w3-round w3-white">
                        <div class="w3-container w3-padding">
                            <img class="media-object img-circle" src="http://status-m.com.ua/images/pages/589c51f8c3eb6.png" style="width: 80%">
                        </div>
                    </div>
                </div>
            </div>

            <br>

            <c:forEach items="${posts}" var="post">
                <div class="w3-container w3-card-2 w3-white w3-round w3-margin"><br>
                    <img src="${post.user.avatar}" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:50px"  onclick="onClick(this)">
                    <c:if test = "${user.roles == 'ROLE_ADMIN'}">
                        <button class="w3-margin-bottom w3-right" onClick='location.href="/post/deletePost-${post.id}"'></button>
                    </c:if>
                    <h4>${post.user.username}</h4>
                    <hr class="w3-clear">
                    <h3><b>${post.title}</b></h3>
                    <h5>Title description
                        <span class="w3-opacity">${post.date}</span></h5>
                    <img src="${post.avatar}" style="width:300px" class="w3-margin-bottom" onclick="onClick(this)">
                    <p>${post.descript}</p>
                    <form>
                        <input type="button" class="w3-button w3-theme-d2 w3-margin-bottom"  value="Переглянути" onClick='location.href="/post/showPost-${post.id}"'>
                    </form>
                </div>
            </c:forEach>
        </div>

        <div class="w3-col m2">
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d164705.55762681522!2d23.87215286350617!3d49.83260464310914!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x473add7c09109a57%3A0x4223c517012378e2!2z0JvRjNCy0ZbQsiwg0JvRjNCy0ZbQstGB0YzQutCwINC-0LHQu9Cw0YHRgtGM!5e0!3m2!1suk!2sua!4v1486512390381" frameborder="0" style="border:0" allowfullscreen></iframe>
        </div>

        </div>
    </div>
    <br>

    <div id="modal01" class="w3-modal" onclick="this.style.display='none'">
        <span class="w3-button w3-hover-red w3-xlarge w3-display-topright">&times;</span>
        <div class="w3-modal-content w3-animate-zoom">
            <img id="img01" style="width:100%">
        </div>
    </div>

    <script>
        function onClick(element) {
            document.getElementById("img01").src = element.src;
            document.getElementById("modal01").style.display = "block";
        }
    </script>

<%@include file="templates/footer.jsp"%>