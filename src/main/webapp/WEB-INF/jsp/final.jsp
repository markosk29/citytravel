<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <title>Title</title>
</head>
<body>
    <h2>Your destination: ${route.get(route.size()-1).name} </h2>
    <p>Your route: </p>
      <c:forEach items="${route}" var="city">
                  <c:out value="${city.name}"/>
      </c:forEach>

      <br>
      <a href="http://localhost:8080">
                            <button>
                            Start over
                            </button>
          </a>
</body>
</html>