<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <title>Title</title>
</head>
<body>
    <h2> Current city : ${city.name} </h2>
    <p> Neighbours: </p>
      <c:forEach items="${neighbours}" var="city">
          <a href="http://localhost:8080/city?name=${city.name}">
                  <button>
                  <c:out value="${city.name}"/>
                  </button>
          </a>
      <br>
      </c:forEach>

    <br>
    <br>
    <a href="http://localhost:8080/route">
                      <button>
                      Stop
                      </button>
    </a>

    <br>
    <a href="http://localhost:8080/route/back"">
                          <button>
                          Go back
                          </button>
    </a>
</body>
</html>