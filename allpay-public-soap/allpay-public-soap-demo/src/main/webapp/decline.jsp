<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script
            src="https://code.jquery.com/jquery-3.1.1.min.js"
            crossorigin="anonymous"></script>
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.3/jquery.mask.min.js"
            crossorigin="anonymous"></script>

</head>
<body>

<div class="panel panel-info col-sm-12 col-md-6 col-md-offset-3" style="margin-top:142px;">
    <div class="panel-heading">
        <h3 class="panel-title text-center">Отмена транзакции</h3>
    </div>
    <div class="panel-body">
        <form class="form-horizontal" accept-charset="UTF-8" id="cashin-form"
              action="${pageContext.request.contextPath}/rest/api/completeTransaction"
              method="post">

            <div class="form-group">
                <label for="fromUser" class="control-label">Введите логин</label>
                <input type="text" class="form-control"
                       spellcheck="false"
                       value='' required="true"
                       id="fromUser" name="fromUser" placeholder="Логин агента">
            </div>

            <div class="form-group">
                <label for="txNumber" class="control-label">Введите номер транзакции</label>
                <input type="tel" class="form-control"
                       spellcheck="false"
                       value='' required="true"
                       id="txNumber" name="txNumber" placeholder="Номер транзакции">
            </div>

            <div class="col-sm-12">
               <div class="col-sm-6 text-center">
                   <a class="btn btn-danger text-uppercase" href="${pageContext.request.contextPath}/transactions.jsp">Назад</a>
               </div>
                <div class="col-sm-6 text-center text-uppercase">
                    <button type="submit" class="btn btn-success text-uppercase">Отменить</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/js/bootstrapvalidator.min.js'></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mask.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/transaction-validator.js"></script>
</html>
