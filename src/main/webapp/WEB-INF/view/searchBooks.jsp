<%--
  Created by IntelliJ IDEA.
  User: Home-PC
  Date: 11.07.2022
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="nc.apps.entities.OrderingBy" %>
<% pageContext.setAttribute("orderingValues", OrderingBy.values()); %>

<html>
<head>
    <title>Search books</title>
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
    <script type="text/javascript" src="<c:url value="/static/js/tableProcessing.js"/>"></script>
    <link href="<c:url value="/static/css/main.css"/>" rel="stylesheet"/>
</head>
<body>
<jsp:include page="navigation.jsp" />
<div class="d-flex justify-content-center">
    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample"
            aria-expanded="false" aria-controls="collapseExample">
        Open/Close search filters
    </button>
</div>
<div class="collapse" id="collapseExample">
    <div class="card card-body">
        <div class="searchArea">
            <form class="searchBox">
                <div class="input-group col-md-3 center">
                    <span class="input-group-text" id="titleInput">Title</span>
                    <input name="title" type="text" width="500" class="form-control">
                </div>
                <div class="input-group col-md-3 center">
                    <span class="input-group-text" id="authorInput">Author</span>
                    <input name="authorName" type="text" width="500" class="form-control">
                </div>
                <div class="input-group col-md-3 center">
                    <span class="input-group-text" id="categoryInput">Category</span>
                    <input name="category" type="text" width="500" class="form-control">
                </div>
                <div class="input-group col-md-3 center">
                    <span class="input-group-text" id="languageInput">Language</span>
                    <input name="language" type="text" width="500" class="form-control">
                </div>
                <div class="input-group col-md-3 center">
                    <span class="input-group-text" id="publisherInput">Publisher</span>
                    <input name="publisher" type="text" width="500" class="form-control">
                </div>
                <div class="input-group col-md-3 center">
                    <label class="input-group-text" for="orderedBySelect">Order by</label>
                    <select name="orderBy" class="form-select form-control selectOrderByField" id="orderedBySelect">
                        <option value="0">Choose</option>
                        <c:forEach items="${orderingValues}" var="ordValue">
                            <option value="<c:out value="${ordValue.value}"/>" ><c:out value="${ordValue}"/></option>
                        </c:forEach>
                    </select>
                </div>

                <div class="input-group col-md-3 center">
                    <label class="input-group-text" for="orderingSelect">Ordering</label>
                    <select name="ordering" class="form-select form-control selectOrderingField" id="orderingSelect">
                        <option value="1">Ascending</option>
                        <option value="2">Descending</option>
                    </select>
                </div>

                <div class="input-group col-md-3 center">
                    <input value="Search" type="button" onclick="clearTableBody();fillTableOnPage(1,);" class="btn btn-primary form-control" id="SearchBooks"/>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="tableArea">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Title</th>
            <th scope="col">Author</th>
            <th scope="col">Category</th>
            <th scope="col">Language</th>
            <th scope="col">Publisher</th>
            <th scope="col">Prequel</th>
            <th scope="col">Edit</th>
            <th scope="col">Remove</th>
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
