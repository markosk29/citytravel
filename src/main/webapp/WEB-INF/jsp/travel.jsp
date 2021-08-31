
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>

</head>
<body>
<h1>Welcome</h1>

<h2>Please choose one of the following cities:</h2>

<c:forEach var="city" items="${cities}">
<a href="http://localhost:8080/city/${city.name}">
    <button>
    <c:out value="${city.getName()}"/>
    </button>
</a>
<br>
</c:forEach>

</body>
</html>