<%--
  Created by IntelliJ IDEA.
  User: Home-PC
  Date: 22.08.2022
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage category's</title>
    <link rel="icon" type="image/x-icon" href="<c:url value="/static/favicon.ico"/>">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous">
    </script>
    <meta charset="UTF-8">
    <script type="text/javascript" src="<c:url value="/static/js/manageEntitys.js"/>"></script>
    <link href="<c:url value="/static/css/main.css"/>" rel="stylesheet"/>
</head>
<body>
<jsp:include page="navigation.jsp" />
<div class="tableArea">
    <table class="table" id="category">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Category</th>
            <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody class="table_body">
        </tbody>
    </table>
    <ul class="justify-content-center pagination tablePagination">
    </ul>
</div>
</body>
</html>
