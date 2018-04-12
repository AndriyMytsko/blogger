<%@include file="templates/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<div class="w3-container w3-content" style="max-width:1900px;margin-top:80px">
    <div class="w3-row">
        <div class="w3-col m3">

            <div class="w3-card-2 w3-round w3-white">
                <div class="w3-container">
                    <h4 class="w3-center"></h4>
                    <p class="w3-center"><img src="${user.avatar}" alt="" style="height:260px;width:240px;
                                cursor:pointer" onclick="onClick(this)" class="w3-hover-opacity w3"></p>
                    <div class="w3-container w3-myfont">
                        <p class="w3-center w3-xxxlarge">${user.username}</p>
                    </div>
                </div>
            </div>

            <br>

            <sec:authentication property="principal" var="user"/>
            <input type="button" class="w3-button w3-block w3-theme-l1 w3-left-align" value="Моя сторінка"
                   onClick='location.href="/user/show-${user.id}"'>
            <button onclick="document.getElementById('createPost').style.display='block'"
                    class="w3-button w3-block w3-theme-l1 w3-left-align">Додати пост
            </button>

            <div id="createPost" class="w3-modal">
                <div class="w3-modal-content">
                    <div class="w3-container">
                        <span onclick="document.getElementById('createPost').style.display='none'"
                              class="w3-button w3-display-topright">&times;</span>
                        <br><br>
                        <sf:form action="/post/savePost" method="post" modelAttribute="emptyPost"
                                 enctype="multipart/form-data">
                            <sf:input class="form-control" placeholder="Введіть назву посту" path="title"
                                      required="required"/><br>
                            <sf:textarea class="form-control" placeholder="Введіть пост" path="descript"
                                         required="required"/><br>
                            <input type="file" name="file" class="w3-button  w3-theme-l1 w3-left-align">
                            <button type="submit" class="w3-button w3-theme"><i class="fa fa-pencil"></i>Додати</button>
                        </sf:form><br>
                    </div>
                </div>
            </div>

            <input type="button" class="w3-button w3-block w3-theme-l1 w3-left-align" value="Додати категорії"
                   onClick='location.href="/category/createCategory"'>
            <button onclick="document.getElementById('id01').style.display='block'"
                    class="w3-button w3-block w3-theme-l1 w3-left-align">Список категорій
            </button>

            <div id="id01" class="w3-modal">
                <div class="w3-modal-content w3-card-4">
                    <header class="w3-container w3-theme-l1">
                        <span onclick="document.getElementById('id01').style.display='none'"
                              class="w3-button w3-display-topright">&times;</span>
                        <h2>Список категорій</h2>
                    </header>
                    <div class="w3-container">
                        <c:forEach items="${categorise}" var="category">
                            <input type="button" class="w3-button w3-block w3-left-align" value="${category.name}"
                                   onClick='location.href="/category/showCategory-${category.id}"'>
                        </c:forEach>
                    </div>
                    <footer class="w3-container w3-theme-l1">
                        <p></p>
                    </footer>
                </div>
            </div>
            <br>

            <div class="w3-card-2 w3-round w3-white w3-padding-32 w3-center">
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d164705.55762681522!2d23.87215286350617!3d49.83260464310914!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x473add7c09109a57%3A0x4223c517012378e2!2z0JvRjNCy0ZbQsiwg0JvRjNCy0ZbQstGB0YzQutCwINC-0LHQu9Cw0YHRgtGM!5e0!3m2!1suk!2sua!4v1486512390381"
                        frameborder="0" style="border:0" allowfullscreen></iframe>
            </div>
            <br>


            <div class="w3-card-2 w3-round w3-white w3-padding-32 w3-center">
                <p><i class="fa fa-bug w3-xxlarge"></i></p>
            </div>

        </div>

        <div class="w3-col m7">

            <div class="w3-row-padding">
                <div class="w3-col m12">
                    <div class="w3-card-2 w3-round w3-white">
                        <div class="w3-container w3-padding">
                            <img class="media-object img-circle"
                                 src="http://status-m.com.ua/images/pages/589c51f8c3eb6.png" style="width: 35%">
                        </div>
                    </div>
                </div>
            </div>
            <br>

            <%--<div class="w3-container w3-card-2 w3-white w3-round w3-margin"><br>--%>
            <%--<sf:form action="/message/saveMessage" method="post" modelAttribute="emptyMessage" enctype="multipart/form-data">--%>
            <%--<sf:textarea class="form-control" path="message" required="required"/>--%>
            <%--<br>--%>
            <%--<sf:select  path="user">--%>
            <%--<sf:option  value="${user.id}"></sf:option>--%>
            <%--</sf:select>--%>
            <%--<button type="submit" class="w3-button w3-theme"><i class="fa fa-pencil"></i>Додати</button>--%>
            <%--</sf:form><br>--%>
            <%--</div>--%>
            <%--<c:forEach items="${message}" var="message">--%>
            <%--<p>${message.message}</p>--%>
            <%--</c:forEach><br>--%>

            <sec:authentication property="principal" var="user"/>
            <c:forEach items="${posts}" var="post">
                <c:if test="${post.category.id == null}">

                    <div class="w3-container w3-card-2 w3-white w3-round w3-margin"><br>
                        <img src="${post.category.avatar}" alt="" class="w3-left w3-circle w3-margin-right"
                             style="width:50px" onclick="onClick(this)">
                        <c:if test="${user.roles == 'ROLE_ADMIN'}">
                            <button class="w3-margin-bottom w3-right"
                                    onClick='location.href="/post/deletePost-${post.id}"'><i class="fa fa-close"></i>
                            </button>
                        </c:if>
                        <c:if test="${post.category.id == null}">
                            <img src="${post.user.avatar}" alt="" class="w3-left w3-circle w3-margin-right"
                                 style="width:50px" onclick="onClick(this)">
                            <a href="/user/show-${post.user.id}">${post.user.username}</a>
                        </c:if><br>
                        <span><a href="/category/showCategory-${post.category.id}">${post.category.name}</a></span><br>
                        <hr class="w3-clear">
                        <span class="w3-opacity w3-right">${post.date}</span>
                        <h3><b>${post.title}</b></h3>
                        <img src="${post.images}" alt="" style="width:300px; cursor:pointer" class="w3-hover-opacity w3"
                             onclick="onClick(this)">
                        <p>${post.descript}</p>
                        <br>
                        <form>
                            <input type="button" class="w3-button w3-theme-d2 w3-margin-bottom" value="Переглянути"
                                   onClick='location.href="/post/showPost-${post.id}"'>
                        </form>
                    </div>
                </c:if>
            </c:forEach>
        </div>

        <div class="w3-col m2">
        </div>

    </div>
</div>
<br>

<%@include file="templates/footer.jsp" %>