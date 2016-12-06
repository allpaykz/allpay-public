<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>

    <!-- Bootstrap core CSS -->
    <%--<link href="css/bootstrap.min.css" rel="stylesheet">--%>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <!-- Material Design fonts -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:300,400,500,700" type="text/css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <!-- Bootstrap Material Design -->
    <%--<link href="css/bootstrap-material-design.min.css" rel="stylesheet">--%>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-material-design/0.5.7/css/bootstrap-material-design.min.css" rel="stylesheet">
    <%--<link href="css/ripples.min.css" rel="stylesheet">--%>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-material-design/0.5.7/css/ripples.min.css" rel="stylesheet">
</head>
<body>
<div class="container col-xs-12 col-md-6 col-md-offset-3" style="background-color: #EEEEEE">
    <form class="form-horizontal" accept-charset="UTF-8" id="form"
          action="${pageContext.request.contextPath}/sendPayment"
          method="post">
        <div class="form-group col-xs-10 col-md-10" id="shopName">
            <label for="shopNameInput" class="control-label"></label>
            <input type="text" class="form-control"
                   spellcheck="false"
                   value='' required="true"
                   id="shopNameInput" name="shopName" placeholder="Название магазина">
            <p class="help-block">Введите название магазина</p>
        </div>

        <div class="form-group col-xs-10 col-md-10" id="invoiceNumber">
            <label for="invoiceNumberInput" class="control-label"></label>
            <input type="text" class="form-control"
                   autocomplete="off" spellcheck="false"
                   value='' required="true"
                   id="invoiceNumberInput" name="invoiceNumber" placeholder="Номер заказа/инвойса">
            <p class="help-block">Введите номер заказа</p>
        </div>

        <div class="form-group col-xs-10 col-md-10" id="amount">
            <label for="amountInput" class="control-label"></label>
            <input type="number" class="form-control"
                   autocomplete="off" spellcheck="false"
                   value="" required="true"
                   id="amountInput" name="amount" placeholder="Сумма в тенге">
            <p class="help-block">Введите сумму</p>
        </div>

        <div class="form-group col-xs-10 col-md-10" id="terminalId">
            <label for="terminalIdInput" class="control-label"></label>
            <input type="text" class="form-control"
                   spellcheck="false"
                   value="" required="true"
                   id="terminalIdInput" name="terminalId" placeholder="На юзера">
            <p class="help-block">Введите ID терминала</p>
        </div>

        <%--This fields only for test purposes--%>
        <div class="form-group col-xs-12 col-md-12" id="testFields">
            <label for="terminalIdInput" class="control-label"></label>
            <input type="number" class="form-control"
                   spellcheck="false"
                   value="" required="true"
                   min="600" max="86400"
                   id="transactionTimeOutInSeconds" name="transactionTimeOutInSeconds" placeholder="Таймаут">
            <p class="help-block">Введите  таймаут транзакции</p>
            <div class="">
                <label>
                    <input type="checkbox" value="false" id="wrongKeyFile" name="wrongKeyFile">
                    Подписать неправильным ключом
                </label>
            </div>
            <div class="">
                <label>
                    <input type="checkbox" value="false" id="deleteRequiredFields" name="deleteRequiredFields">
                    Удалить обязательные поля
                </label>
            </div>
        </div>

        <input type="hidden" class="form-control" id="webshopRequest" name="webshopRequest">

        <div class="form-group col-xs-10 col-md-10">
            <div class="col-xs-5">
                <button type="submit" id="bs" class="btn btn-raised btn-info" onclick='getSignedXML()'>
                    Оплатить
                </button>
            </div>
            <div class="col-xs-5">
                <a href="${pageContext.request.contextPath}/transactions.jsp" class="btn btn-raised btn-default">
                    Просмотр транзакций
                </a>
            </div>
        </div>
    </form>
</div>

<div class="container col-md-6 col-xs-12 col-md-offset-3">
    <h2>Подсказки для тестов</h2>

    <div class="panel panel-success col-md-3">
        <div class="panel-heading">
            <h3 class="panel-title">Для test.all-pay.kz</h3>
        </div>
        <div class="panel-body">
            Логин - 75551234561<br>
            пароль - merchant<br>
            номер счета - LM200000<br>
            ID терминала - 55424
        </div>
    </div>
    <div class="panel panel-primary col-md-3">
        <div class="panel-heading">
            <h3 class="panel-title">Для тестирования магазина PHP</h3>
        </div>
        <div class="panel-body">
            ID:75551234569<br>
            password:merchant<br>
            номер счета - CH000000<br>
            terminal_id: 25252
        </div>
    </div>
    <div class="panel panel-success col-md-3">
        <div class="panel-heading">
            <h3 class="panel-title">Для mfsci</h3>
        </div>
        <div class="panel-body">
            ID: 75551234580<br>
            password: merchant<br>
            номер счета - MF000000<br>
            terminal_id: 75896
        </div>
    </div>
    <div class="panel panel-primary col-md-3">
        <div class="panel-heading">
            <h3 class="panel-title">Для локального тестирования разработчиками</h3>
        </div>
        <div class="panel-body">
            ID: 75551234560<br>
            password: merchant<br>
            номер счета - LM000000<br>
            terminal_id: 00013, 00014
        </div>
    </div>
</div>

<%--<script src="js/jquery-1.11.3.min.js"></script>--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="js/vendor/jquery.min.js"><\/script>')</script>
<%--<script src="js/bootstrap.min.js"></script>--%>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
        integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
        crossorigin="anonymous"></script>
<script>
    function getSignedXML() {
        var invoice = document.getElementById("invoiceNumberInput").value;
        var amount = document.getElementById("amountInput").value;
        var shopName = document.getElementById("shopNameInput").value;
        var terminalId = document.getElementById("terminalIdInput").value;
        var wrongKeyFile = document.getElementById("wrongKeyFile").value;
        var deleteRequiredFields = document.getElementById("deleteRequiredFields").value;
        var transactionTimeOutInSeconds=document.getElementById("transactionTimeOutInSeconds").value;

        $.ajax({
            type: "GET",
            async: false,
            url: "${pageContext.request.contextPath}/webresources/test/getSignedXML?invoiceNumber=" + invoice + "&amount=" + amount + "&shopName=" + shopName + "&terminalId=" + terminalId+"&wrongKeyFile=" + wrongKeyFile+"&deleteRequiredFields="+deleteRequiredFields+"&transactionTimeOutInSeconds="+transactionTimeOutInSeconds

        }).done(function (data) {
            document.getElementById('webshopRequest').value = data.webshopRequest;
        });
    };
</script>
</body>
</html>
