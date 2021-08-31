
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
<h1>Welcome</h1>

<h2>Please choose one of the following cities:</h2>

<table>
<c:forEach var="city" items="${cities}">
     <tr>
        <td>
            ${city.name}

            <form action="/city" method="get">
                     <td><input type="submit" value="Start"</td>
                     <input type="hidden" name="name" value="${city.name}"/>
            </form>
        </td>
     </tr>
</c:forEach>
</table>

</body>
</html>