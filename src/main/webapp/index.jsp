<%@ page import="dbService.DBService" %>
<%@ page import="dbService.datasets.Pet" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="WEB-INF/jspf/header.jspf" %>
<%
    DBService dbService = new DBService();
    List<Pet> pets = dbService.getAllPets();
    Iterator iter =  pets.iterator();
    for( ; iter.hasNext(); ){
        Pet pet = (Pet) iter.next();
%>
    <div class="ad-wrap">
        <div class="over-wrapper">
            <div class="photo-wrapper">
                <a class="photo"></a>
            </div>
            <div class="name-block">
                <a class="pet-title"><%=pet.getAnimalClass() + " " + pet.getName()%></a>
            </div>
        </div>
    </div>
<%--<div class="ad-wrap">
    <div class="ad-block">
        <div id="animal-class"><%=pet.getAnimalClass()%></div><br>
        <div id="animal-name"><%=pet.getName()%></div><br>
        <div id="user-acc" href="#"><%=pet.getAge()%></div><br>
        <div id="user-loc"><%=pet.getDate()%></div><br>
    </div>
</div>--%>
<%}%>
<h1>Hello</h1>
<%@include file="WEB-INF/jspf/footer.jspf" %>
