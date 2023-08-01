<%--
  Created by IntelliJ IDEA.
  User: alex2
  Date: 7/22/2023
  Time: 11:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DMF Search Result</title>
</head>

<br>
<br>

<style>
    div.parent {
        text-align: center;
    }
    ul {
        display: inline-block;
        text-align: left;
    }

</style>
<body>
    <div class="parent">
        <ul>
            <b>Search Result </b> <br> <br> <%= request.getAttribute("result") %>
        <br>
        <button onclick="history.back()">
            Go Back
        </button>
        </ul>
    </div>
</body>
</html>
