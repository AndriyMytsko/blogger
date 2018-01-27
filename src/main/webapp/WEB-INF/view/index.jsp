<%@include file="templates/header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<body class="w3-theme-l5">

<div class="w3-container w3-content" style="max-width:1900px;margin-top:80px">
    <!-- The Grid -->
    <div class="w3-row">
        <!-- Left Column -->
        <div class="w3-col m3">
            <!-- Profile -->
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

            <!-- Accordion -->
            <div class="w3-card-2 w3-round">
                <div class="w3-white">
                    <a href="/blog/createBlog" class="w3-button w3-block w3-theme-l1 w3-left-align"><i class="fa fa-pencil"></i>Додати блог</a>
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

        </div>

        <div class="w3-col m7">

            <div class="w3-row-padding">
                <div class="w3-col m12">
                    <div class="w3-card-2 w3-round w3-white">
                        <div class="w3-container w3-padding">
                            <img src="images/blog1.jpg" class="w3-round"  style="width:60%">
                        </div>
                    </div>
                </div>
            </div>

            <c:forEach items="${blogs}" var="blog">
                <div class="w3-container w3-card-2 w3-white w3-round w3-margin"><br>
                    <img src="${blog.user.avatar}" class="w3-left w3-circle w3-margin-right" style="width:50px" alt="Avatar" onclick="onClick(this)">
                    <c:if test = "${user.roles == 'ROLE_ADMIN'}">
                        <input type="button" class="w3-button w3-theme-d2 w3-margin-bottom w3-right"  value="X" onClick='location.href="/blog/deleteBlog-${blog.id}"'>
                    </c:if>
                    <h3>${blog.user.username}</h3>
                    <span class="w3-opacity">${blog.date}</span>
                    <hr class="w3-clear">
                    <h3>${blog.title}</h3>
                    <img src="${blog.avatar}" style="width:85%; cursor:pointer" onclick="onClick(this)" class="w3-hover-opacity">
                    <p>${blog.descript}</p>

                    <form>
                        <button type="button" class="w3-button w3-theme-d2 w3-margin-bottom" onClick='location.href="/comment/createComment"' ><i class="fa fa-comment"></i>Коментувати</button>
                        <input type="button" class="w3-button w3-theme-d2 w3-margin-bottom"  value="Переглянути" onClick='location.href="/blog/showBlog-${blog.id}"'>
                    </form>
                </div>
            </c:forEach>
        </div>

        <!-- Right Column -->
        <div class="w3-col m2">
            <div class="w3-card-2 w3-round w3-white w3-center">
                <div class="w3-container">
                </div>
            </div>
            <br>

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

