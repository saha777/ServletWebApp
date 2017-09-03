<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("user") != null){
        response.sendRedirect("index.jsp");
    }
%>

<%@include file="../WEB-INF/jspf/header.jspf" %>
<div class="reg_wrap">
    <div class="reg_body">
        <h1>Registration</h1>
        <form action="/reg_user" method="POST">
            <div class="e_wrap">
                <a class="m_label">Name  : </a>
                <input class="e_form" type="text" name="name" value="" size="20"/>
            </div>
            <div class="e_wrap">
                <a class="m_label">Email : </a>
                <input class="e_form" type="email" name="email" value="" size="20"/>
            </div>
            <div class="e_wrap">
                <a class="m_label">Pass  : </a>
                <input class="e_form" type="password" name="pass" value="" size="20"/>
            </div>
            <div class="e_wrap">
                <a class="m_label">Repass: </a>
                <input class="e_form" type="password" name="repass" value="" size="20"/>
            </div>
            <div class="e_wrap">
                <a class="m_label">Country: </a>
                <input class="e_form" type="text" name="country" value="" size="20"/>
            </div>
            <div class="e_wrap">
                <a class="m_label">Location: </a>
                <input class="e_form" type="text" name="loc" value="" size="20"/>
            </div>
            <input id="button" type="submit" value="Register"/>
        </form>
    </div>
</div>
<%@include file="../WEB-INF/jspf/footer.jspf" %>