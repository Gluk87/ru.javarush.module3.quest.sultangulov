<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quest</title>
    <style>
        .layer {
            padding: 10px;
        }
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
</head>
<body>
<div class="layer">
<h1 class="display-6">${question}</h1>
<br>
<form method="post">
<c:forEach items="${answers}" var="answer">
    <div class="form-check">
        <input class="form-check-input" type="radio" name="nextQuestionId" value="${answer.getNextQuestionId()}" id="flexRadioDefault" required>
        <input type="hidden" name="isLastQuestion" value="${isLastQuestion}">
        <input type="hidden" name="userName" value="${userName}">
        <label class="form-check-label" for="flexRadioDefault">
                ${answer.getText()}
        </label>
    </div>

</c:forEach>
<br>
<button type="submit" class="btn btn-primary">Ответить</button>
</form>
    <br>
    <br>
    <br>
    <div>
    <table>
        <tr>
            <th>Статистика</th>
            <th></th>
        </tr>
        <tr>
            <td>Ваше имя: </td>
            <td>${userName}</td>
        </tr>
        <tr>
            <td>Ваш IP: </td>
            <td><%= session.getAttribute("IP")%></td>
        </tr>
        <tr>
            <td>Количество игр: </td>
            <td>${countGames}</td>
        </tr>
        <tr>
            <td>Количество побед:  </td>
            <td>${countWin}</td>
        </tr>
    </table>
    </div>
</div>
</body>
</html>
