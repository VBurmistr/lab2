<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Home-PC
  Date: 17.07.2022
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<div class="d-flex justify-content-center" role="group" aria-label="Large button group">
    <a class="btn btn-primary" href="<%=request.getContextPath()%>">Index</a>
    <a class="btn btn-primary" href="<%=request.getContextPath()%>/searchbooks">Search some book</a>
    <a class="btn btn-primary" href="<%=request.getContextPath()%>/addnewbook">Add Book</a>
    <a class="btn btn-primary" href="<%=request.getContextPath()%>/addauthor">Add author</a>
    <a class="btn btn-primary" href="<%=request.getContextPath()%>/addlanguage">Add language</a>
    <a class="btn btn-primary" href="<%=request.getContextPath()%>/addcategory">Add category</a>
    <a class="btn btn-primary" href="<%=request.getContextPath()%>/addpublisher">Add publisher</a>
    <a class="btn btn-primary" href="<%=request.getContextPath()%>/smartadder">Book Smart Adder</a>
    <a class="btn btn-primary" href="<%=request.getContextPath()%>/logout">Logout</a>
</div>
</body>
</html>
