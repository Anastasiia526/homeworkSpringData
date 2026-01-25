<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OldCard</title>
    <style>
        table, th, td {
            border: 1px solid black;
            padding: 10px;
            margin: 5px;
        }
        input[type=text] {
            width: 25%;
            padding: 8px;
            margin: 6px 0;
        }
        h2 {
            margin-top: 30px;
        }
    </style>
</head>
<body>

<h1>OldCards table</h1>

<table>
    <thead>
    <tr>
        <th>#</th>
        <th>ID</th>
        <th>Thema</th>
        <th>Type</th>
        <th>Sent</th>
        <th>Country</th>
        <th>Author</th>
        <th>Year</th>
        <th>Valuable</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${cards}" var="card" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${card.id}</td>
            <td>${card.thema}</td>
            <td>${card.type}</td>
            <td>${card.sent}</td>
            <td>${card.country}</td>
            <td>${card.year}</td>
            <td>${card.author}</td>
            <td>${card.valuable}</td>
            <td>
                <a href="${pageContext.request.contextPath}/card/remove/${card.id}">
                    Remove
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<!-- ADD CARD -->
<h2>Add new card</h2>
<form method="post" action="${pageContext.request.contextPath}/card/add">
    <p>Thema:</p>
    <input type="text" name="thema"/>

    <p>Type:</p>
    <input type="text" name="type"/>

    <p>Sent (true / false):</p>
    <input type="text" name="sent"/>

    <p>Country:</p>
    <input type="text" name="country"/>

    <p>Year:</p>
    <input type="text" name="year"/>

    <p>Author:</p>
    <input type="text" name="author"/>

    <p>Valuable:</p>
    <input type="text" name="valuable"/>

    <br/>
    <input type="submit" value="Add card"/>
</form>

<!-- SEARCH BY TYPE -->
<h2>Search card by type</h2>
<form method="post" action="${pageContext.request.contextPath}/card/findCardByType">
    <input type="text" name="type" placeholder="Type"/>
    <input type="submit" value="Search"/>
</form>

<!-- SEARCH BY YEAR AND SENT -->
<h2>Search card by year and sent</h2>
<form method="post" action="${pageContext.request.contextPath}/card/findCardByYearAndSent">
    <p>Year:</p>
    <input type="text" name="year"/>

    <p>Sent (true / false):</p>
    <input type="text" name="sent"/>

    <input type="submit" value="Search"/>
</form>

<!-- UPDATE CARD TYPE -->
<h2>Update card type by author and year</h2>
<form method="post" action="${pageContext.request.contextPath}/card/update">
    <p>New type:</p>
    <input type="text" name="type"/>

    <p>Author:</p>
    <input type="text" name="author"/>

    <p>Year:</p>
    <input type="text" name="year"/>

    <input type="submit" value="Update"/>
</form>

</body>
</html>