<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
<div class="panel panel-default col-sm-12 col-md-6 col-md-offset-3">
    <div class="panel-heading">
        <h2 class="panel-title text-center h2">Снять или пополнить allpay</h2>
    </div>
    <div class="panel-body">
        <div class="text-center">
            <%--Кнопка снятие--%>
            <div class="col-sm-6">
                <a class="btn btn-warning text-uppercase" href="cashout.jsp">Снятие</a>
            </div>

            <%--Кнопка пополнение--%>
            <div class="col-sm-6">
                <a class="btn btn-info text-uppercase" href="cashin.jsp">Пополнение</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
