<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
<div class="panel panel-warning col-sm-12 col-md-6 col-md-offset-3" style="margin-top:142px;">
    <div class="panel-heading">
        <h3 class="panel-title text-center">Снятие со счета</h3>
    </div>
    <div class="panel-body">
        <form class="form-horizontal" accept-charset="UTF-8" id="form"
              action="${pageContext.request.contextPath}/cashout"
              method="post">

            <div class="form-group">
                <label for="loginInput" class="control-label">Введите логин</label>
                <input type="text" class="form-control"
                       spellcheck="false"
                       value='' required="true"
                       id="loginInput" name="loginName" placeholder="Логин агента">
            </div>

            <div class="form-group">
                <label for="toUser" class="control-label">Логин юзера</label>
                <input type="tel" class="form-control"
                       spellcheck="false"
                       value='' required="true"
                       id="toUser" name="toUser" placeholder="У кого снимаем деньги">
            </div>

            <div class="form-group">
                <label for="amount" class="control-label">Токен</label>
                <input type="number" class="form-control"
                       spellcheck="false"
                       value='' required="true"
                       id="amount" name="amount" placeholder="Токен авторизации">
            </div>
        </form>
    </div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mask.js"></script>
</html>
