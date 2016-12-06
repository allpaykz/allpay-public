<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
<div class="col-sm-12 col-lg-6 col-lg-offset-3 text-center">
    <h2 class="h2">Снять или пополнить</h2>
</div>

<div class="col-sm-12 col-lg-6 col-lg-offset-3 text-center">
    <%--Кнопка снятие--%>
    <div class="col-sm-6">
        <button type="button" class="btn btn-warning text-uppercase">Снятие</button>
    </div>

    <%--Кнопка пополнение--%>
    <div class="col-sm-6">
        <button type="button" class="btn btn-info text-uppercase">Пополнение</button>
    </div>
</div>

</body>
</html>
