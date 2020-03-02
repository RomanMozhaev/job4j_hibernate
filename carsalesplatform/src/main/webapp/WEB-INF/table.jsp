<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cars</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function registration() {
            var url = "${pageContext.servletContext.contextPath}/registration";
            document.location.href = url;
        }

        function login() {
            var url = "${pageContext.servletContext.contextPath}/login";
            document.location.href = url;
        }

        function sold(status) {
            var result;
            if (status) {
                result = "Sold";
            } else {
                result = "Open";
            }
            return result;
        }
    </script>

</head>
<body>
<div class="container">
    <table id="head" class="table">
        <tr>
            <td>
                <button class="form-control" onclick="registration()">Registration</button>
            </td>
            <td>
                <button class="form-control" onclick="login()"><c:out value="${name}"></c:out></button>
            </td>
    </table>
</div>
<table>
    <div class="container">
        <table id="table" class="table">
            <c:forEach items="${list}" var="list">
                <tr>
                    <td width="250px">
                        <form>
                            <a href="${pageContext.servletContext.contextPath}/download?pic=${list.picture}">
                                <img src="${pageContext.servletContext.contextPath}/download?pic=${list.picture}"
                                     width="200px" height="200px"/>
                            </a>
                        </form>
                    </td>
                    <td>
                        Status:
                        <script type='text/javascript'>
                            document.write(sold(${list.sold}));
                        </script>
                        <br>
                        Price:
                        <c:out value="${list.price}"></c:out>
                        <br>
                        Type:
                        <c:out value="${list.type}"></c:out>
                        <br>
                        Brand:
                        <c:out value="${list.brand}"></c:out>
                        <br>
                        Model:
                        <c:out value="${list.model}"></c:out>
                        <br>
                        Year:
                        <c:out value="${list.year}"></c:out>
                        <br>
                        Usage of vehicle:
                        <c:out value="${list.usage}"></c:out>
                        <br>
                        Description:
                        <c:out value="${list.description}"></c:out>

                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</table>


</body>
</html>
