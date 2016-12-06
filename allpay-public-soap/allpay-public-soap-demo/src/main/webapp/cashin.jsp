<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script
            src="https://code.jquery.com/jquery-3.1.1.min.js"
            crossorigin="anonymous"></script>
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.3/jquery.mask.min.js"
            crossorigin="anonymous"></script>

</head>
<body>

<div class="panel panel-info col-sm-12 col-md-6 col-md-offset-3">
    <div class="panel-heading">
        <h3 class="panel-title text-center">Пополнение счета</h3>
    </div>
    <div class="panel-body">
        <form class="form-horizontal" accept-charset="UTF-8" id="form"
              action="${pageContext.request.contextPath}/cashin"
              method="post">

            <div class="form-group">
                <label for="loginInput" class="control-label">Введите логин</label>
                <input type="text" class="form-control"
                       spellcheck="false"
                       value='' required="true"
                       id="loginInput" name="loginName" placeholder="Логин агента">
            </div>

            <div class="form-group">
                <label for="toUser" class="control-label">Кому пополняем</label>
                <input type="tel" class="form-control"
                       spellcheck="false"
                       value='' required="true"
                       id="toUser" name="toUser" placeholder="Логин агента">
            </div>

            <div class="form-group">
                <label for="amount" class="control-label">Кому пополняем</label>
                <input type="number" class="form-control"
                       spellcheck="false"
                       value='' required="true"
                       id="amount" name="amount" placeholder="Сумма">
            </div>
        </form>
    </div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mask.js"></script>
</html>
