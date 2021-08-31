<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
  <style>
    table, td {
      border:1px solid black;
    }
  </style>
</head>
<body>
    <h2> Current city : ${city.name} </h2>
    <p> Neighbours: </p>
     <table>
     <c:forEach var="city" items="${neighbours}">
          <tr>
             <td>
                 ${city.name}

                 <form action="/city" method="get">
                          <td><input type="submit" value="Travel to"</td>
                          <input type="hidden" name="name" value="${city.name}"/>
                 </form>
             </td>
          </tr>
     </c:forEach>
     </table>

    <br>
    <br>
    <a href="http://localhost:8080/route" style="text-decoration: none">
                      <button>
                      Stop
                      </button>
    </a>
    <a href="http://localhost:8080/route/back" style="text-decoration: none">
                          <button>
                          Go back
                          </button>
    </a>
</body>
</html>