<%--
  Created by IntelliJ IDEA.
  User: Home-PC
  Date: 19.07.2022
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html>
<head>
    <title>Error page</title>
</head>
<body>
<h1>
    Status code: <c:out value="${statusCode}"/>
</h1>
<h2>
    URL: <c:out value="${url}"/>
</h2>
<h3>
    Error message: <c:out value="${exMsg}"/>
</h3>
</body>
</html>
