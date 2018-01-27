<%@include file="templates/header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="w3-container w3-content" style="max-width:1900px;margin-top:80px">
    <!-- The Grid -->
    <div class="w3-row">
        <!-- Left Column -->
        <div class="w3-col m3">
            <!-- Profile -->
            <div class="w3-card-2 w3-round w3-white">
                <div class="w3-container">
                    <p class="w3-center"><img src="${user.avatar}" class="w3" style="height:210px;width:190px" alt="Avatar"></p>
                    <hr>
                    <p><i class="fa fa-pencil fa-fw w3-margin-right w3-text-theme"></i>User id : ${user.id}</p>
                    <p><i class="fa fa-pencil fa-fw w3-margin-right w3-text-theme"></i>User name: ${user.username}</p>
                </div>
            </div>
            <br>


            <!-- Accordion -->
            <div class="w3-card-2 w3-round">
                <div class="w3-white">
                    <a href="/blog/createBlog" class="w3-button w3-block w3-theme-l1 w3-left-align"><i class="fa fa-calendar-check-o fa-fw w3-margin-right"></i> Create Blogs</a>
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
                <c:forEach items="${blogs}" var="blog">
                    <div class="w3-container w3-card-2 w3-white w3-round w3-margin">
                        <br>
                        <img src="${blog.user.avatar}" class="w3-left w3-circle w3-margin-right" style="width:60px" alt="Avatar">
                        <span class="w3-right w3-opacity"><%= new java.util.Date() %></span>
                        <h4>${blog.user.username}</h4>
                        <br>
                        <hr class="w3-clear">
                        <h3>${blog.title}</h3>
                        <img src="${blog.avatar}" style="width:50%" class="w3-margin-bottom">
                        <h4>${blog.descript}</h4>

                        <form>
                            <input type="button" class="w3-button w3-theme-d2 w3-margin-bottom"  value="Коментувати" onClick='location.href="/post/createPost"'>
                            <input type="button" class="w3-button w3-theme-d2 w3-margin-bottom"  value="Переглянути" onClick='location.href="/blog/showBlog-${blog.id}"'>
                        </form>
                    </div>
                </c:forEach>

                <!-- End Middle Column -->
            </div>



            <!-- Right Column -->
        <div class="w3-col m2">
            <div class="w3-card-2 w3-round w3-white w3-center">
                <div class="w3-container">
                    <p>Upcoming Events:</p>
                    <p><strong>Holiday</strong></p>
                    <p>Friday 15:00</p>
                    <p><button class="w3-button w3-block w3-theme-l4">Info</button></p>
                </div>
            </div>
            <br>

        </div>

        <!-- End Page Container -->
    </div>
    <br>


    <%@include file="templates/footer.jsp"%>

