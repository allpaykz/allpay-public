<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/allpay.css">
</head>
<body>
<div class="panel panel-default col-sm-12 col-md-6 col-md-offset-3" style="margin-top:142px;">
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
