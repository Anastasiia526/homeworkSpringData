<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Card search result</title>
</head>
<body>
<h1>List of card : </h1>

<c:forEach items="${cards}" var="card" varStatus="status">
    <p>
    <h2>Result #${status.index + 1}</h2></p>
    <p>
        <strong>Thema:</strong> ${card.thema}<br/>
        <strong>Type:</strong> ${card.type}<br/>
        <strong>Sent:</strong> ${card.sent}<br/>
        <strong>Country:</strong> ${card.country}<br/>
        <strong>Author:</strong> ${card.author}<br/>
        <strong>Year:</strong> ${card.year}<br/>
        <strong>Valuable:</strong> ${card.valuable}
    </p>
</c:forEach>
</body>
</html>
