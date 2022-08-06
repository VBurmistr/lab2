<%--
  Created by IntelliJ IDEA.
  User: Home-PC
  Date: 20.07.2022
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html>
<head>
    <title>You have no power here</title>
</head>
<body>
    <h1>Forbidden</h1>
    <h2>
        <c:out value="${msg}"/>
    </h2>
</body>
</html>
