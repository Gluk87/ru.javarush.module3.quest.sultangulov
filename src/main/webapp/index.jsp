<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Quest</title>
    <style>
        .layer {
            padding: 10px;
        }
    </style>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src="<c:url value="/static/jquery-3.6.0.min.js"/>"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
</head>
<body>
<div class="layer">
<h2><%= "Пролог" %></h2>
    <p>Ты стоишь в космическом порту и готов подняться на борт своего корабля.</p>
    <p>Разве ты не об этом мечтал? Стать капитаном галактического судна с экипажем,</p>
    <p>который будет совершать подвиги под твоим командованием.</p>
    <p>Так что вперед!</p>
<br/>
<h2><%= "Знакомство с экипажем" %></h2>
    <p>Когда ты поднялся на борт корабля, тебя поприветствовала девушка с черной папкой в руках</p>
    <p>-Здравствуйте, командир! Я Зинаида, ваша помощница. Видите?</p>
    <p>Там в углу пьет кофе наш штурман - сержант Перегарный Штейф,</p>
    <p>под штурвалом спит бортмеханик - Черный Богдан,</p>
    <p>а фотографирует его Сергей Стальная пятка - наш навигатор.</p>
    <p>А как обращаться к вам?</p>
<form action="start" method="post">
    <input type="hidden" name="nextQuestionId" value="0">
    <input type="hidden" name="isLastQuestion" value="false">
    <input type="hidden" name="isWrongAnswer" value="false">
    <div class="input-group mb-3">
        <span class="input-group-text" id="inputGroup-sizing-default">Ваше имя</span>
        <input id="name" type="text" name="userName" class="form-control" aria-label="10" aria-describedby="inputGroup-sizing-default" required>
    </div>
    <button type="submit" class="btn btn-primary">Представиться</button>
</form>
</div>
</body>
</html>