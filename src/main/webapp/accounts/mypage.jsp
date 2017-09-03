<%@ page import="dbService.datasets.Pet" %>
<%@ page import="dbService.DBService" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../WEB-INF/jspf/header.jspf" %>
<%
    if(user == null){
        response.sendRedirect("loginsystem/login.jsp");
    }
%>

<%
    DBService dbService = new DBService();
    List<Pet> pets = dbService.getUserPets(user);
    Iterator iter =  pets.iterator();
    for( ; iter.hasNext(); ){
        Pet pet = (Pet) iter.next();
%>
<div class="ad-wrap">
    <div class="ad-block">
        <div id="animal-class"><%=pet.getAnimalClass()%></div><br>
        <div id="animal-name"><%=pet.getName()%></div><br>
        <div id="user-acc" href="#"><%=user.getName()%></div><br>
        <div id="user-loc"><%=user.getCountry()+user.getLocation()%></div><br>
        <a href="/petservlet?id=<%=pet.getId()%>">delete</a>
    </div>
</div>
<%}%>
<div class="log_wrap">
    <div class="log_body">
        <h1>Add animal</h1>
        <form action="/petservlet" method="POST">
            <div class="e_wrap">
                <a class="m_label">Name  : </a>
                <input class="e_form" type="text" name="name" value="" size="20"/>
            </div>
            <br>
            <div class="e_wrap">
                <a class="m_label">Class : </a>
                <input class="e_form" type="text" name="class" value="" size="20"/>
            </div>
            <br>
            <div class="e_wrap">
                <a class="m_label">Age : </a>
                <input class="e_form" type="number" name="age" value="" size="20"/>
            </div>
            <br>
            <input id="button" type="submit" value="Add"/>
        </form>
    </div>
</div>
<%@include file="../WEB-INF/jspf/footer.jspf" %>
