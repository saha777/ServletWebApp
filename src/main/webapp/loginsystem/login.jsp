<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("user") != null){
        response.sendRedirect("index.jsp");
    }
%>
<%@include file="../WEB-INF/jspf/header.jspf" %>
<div class="log_wrap">
    <div class="log_body">
        <h1>Log in</h1>
        <form action="/login_user" method="POST">
            <div class="e_wrap">
                <a class="m_label">E-mail: </a>
                <input class="e_form" type="email" name="email" value="" size="20"/>
            </div>
            <br>
            <div class="e_wrap">
                <a class="m_label">Pass : </a>
                <input class="e_form" type="password" name="pass" value="" size="20"/>
            </div>
            <br>
            <input id="button" type="submit" value="Log in"/>
        </form>
    </div>
</div>
<%@include file="../WEB-INF/jspf/footer.jspf" %>