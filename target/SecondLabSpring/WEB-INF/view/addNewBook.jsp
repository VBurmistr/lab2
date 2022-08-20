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
    <link rel="icon" type="image/x-icon" href="<c:url value="/static/favicon.ico"/>">
    <title>Add new book</title>
    <script type="text/javascript" src="<c:url value="/static/js/bookProcessing.js"/>"></script>
    <link href="<c:url value="/static/css/main.css"/>" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<jsp:include page="navigation.jsp" />
<div class="nav justify-content-center">
    <form action="<c:out value="${pageContext.request.contextPath}/book/add/"/>">
        <div class="input-group mb-3">
            <span class="input-group-text" id="inputGroup-sizing-default">Set title:</span>
            <input name="title" type="text" class="form-control titleField">
        </div>
        <div class="input-group mb-3">
            <label class="input-group-text w-50" for="inputAuthorSelect">Select Author</label>
            <select name="author"  class="form-select selectAuthor w-50" id="inputAuthorSelect">
                <option value="0">Choose</option>
            </select>
        </div>

        <div class="input-group mb-3">
            <label class="input-group-text w-50" for="inputCategorySelect">Select category</label>
            <select name="category" class="form-select selectCategory w-50" id="inputCategorySelect">
                <option value="0">Choose</option>
            </select>
        </div>

        <div class="input-group mb-3">
            <label class="input-group-text w-50" for="inputLanguageSelect">Select language</label>
            <select name="language" class="form-select selectLanguage w-50" id="inputLanguageSelect">
                <option value="0">Choose</option>
            </select>
        </div>

        <div class="input-group mb-3">
            <label class="input-group-text w-50" for="inputPublisherSelect">Select publisher</label>
            <select name="publisher" class="form-select selectPublisher w-50" id="inputPublisherSelect">
                <option value="0">Choose</option>
            </select>
        </div>

        <div class="input-group mb-3">
            <label class="input-group-text w-50" for="inputPublisherSelect">Select prequel</label>
            <select name="prequel" class="form-select selectPrequel w-50" id="inputSequelSelect">
                <option value="0">Choose</option>
            </select>
        </div>
        <input value="Add book" type="button" onclick="bookProcessingNew(this.form)" class="w-100 btn btn-primary form-control">
    </form>
</div>
</body>
</html>
