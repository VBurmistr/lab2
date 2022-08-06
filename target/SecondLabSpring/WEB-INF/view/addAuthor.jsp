<%--
  Created by IntelliJ IDEA.
  User: Home-PC
  Date: 11.07.2022
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <script type="text/javascript" src="<c:url value="/static/js/addingnewitems.js"/>"></script>
  <link rel="icon" type="image/x-icon" href="<c:url value="/static/favicon.ico"/>">
  <title>Add author</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<jsp:include page="navigation.jsp" />
<div class="nav justify-content-center">
  <form method="post" action="<c:out value="${pageContext.request.contextPath}/author/add/"/>">
    <div class="input-group mb-3">
      <span class="input-group-text">Set first name:</span>
      <input type="text" name="firstName" class="form-control" >
    </div>
    <div class="input-group mb-3">
      <span class="input-group-text">Set last name:</span>
      <input type="text" name="lastName" class="form-control">
    </div>
    <input value="Add author" type="button" onclick="sendAddRequest(this.form)" class="w-100 btn btn-primary form-control">
  </form>
</div>
</body>
</html>
