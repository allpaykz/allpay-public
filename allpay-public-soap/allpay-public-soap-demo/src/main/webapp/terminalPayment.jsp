<%--
  Created by IntelliJ IDEA.
  User: aigerim
  Date: 2/23/18
  Time: 3:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<t:genericpage>
    <jsp:attribute name="head">
    </jsp:attribute>
    <jsp:attribute name="header">
        <t:navbar active="terminalPayment"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
                integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
                crossorigin="anonymous"></script>
        <script src='http://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/js/bootstrapvalidator.min.js'></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/mask.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/terminal-payment-validator.js"></script>
    </jsp:attribute>
    <jsp:body>
        <div class="panel panel-info col-xs-12 col-md-6 col-md-offset-3" style="margin-top:142px;">
            <div class="panel-heading">
                <h3 class="panel-title text-center">Операции через терминал</h3>
            </div>

            <div class="panel-body">
                <form class="form-horizontal" accept-charset="UTF-8" id="terminal-payment-form"
                      action="${pageContext.request.contextPath}/terminal_payment"
                      method="post">

                    <label for="cashInOperationType">CashIn</label><input id="cashInOperationType" type = "radio" checked="checked" onclick="cashInOnCheck()" name = "operationType" value="CASHIN">
                    <label for="vostokPlatOperationType">VostokPlat</label><input id="vostokPlatOperationType" type = "radio" onclick="cashInOnCheck()" name = "operationType" value="VOSTOKPLAT">

                    <div class="form-group">
                        <label for="loginInput" class="control-label">Логин</label>
                        <input type="text" class="form-control"
                               spellcheck="false"
                               value='' required="true"
                               id="loginInput" name="loginName" placeholder="Логин агента">
                    </div>

                    <div class="form-group">
                        <label class="pemInput">Приватный ключ(для подписи запроса)</label>
                        <div class="inputGroupContainer">
                            <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                                <textarea class="form-control" name="pemInput" placeholder="Приватный ключ(для подписи запроса)"></textarea>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="pemInputResponse">Публичный ключ(для проверки подписи ответа)</label>
                        <div class="inputGroupContainer">
                            <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                                <textarea class="form-control" name="pemInputResponse" placeholder="Публичный ключ(для проверки подписи ответа)"></textarea>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="certificateIdInput" class="control-label">Номер сертификата</label>
                        <input type="text" class="form-control"
                               spellcheck="false"
                               value='' required="true"
                               id="certificateIdInput" name="certificateIdInput" placeholder="Номер сертификата">
                    </div>


                    <div class="form-group">
                        <label for="toUser" class="control-label">Allpay пользователь</label>
                        <input type="tel" class="form-control"
                               spellcheck="false"
                               value='' required="true"
                               id="toUser" name="toUserName" placeholder="Логин клиента">
                    </div>

                    <div class="form-group">
                        <label for="amount" class="control-label">Сумма пополнения</label>
                        <input type="number" class="form-control"
                               spellcheck="false"
                               value='' required="true"
                               id="amount" name="amount" placeholder="Сумма">
                    </div>

                    <div class="form-group vostokPlat" style="visibility: hidden; display: none">
                        <label for="utilityAccountNumber" class="control-label">Номер счета</label>
                        <input type="tel" class="form-control"
                               spellcheck="false"
                               value='' required="true"
                               id="utilityAccountNumber" name="utilityAccountNumber" placeholder="Номер счета">
                    </div>

                    <div class="form-group vostokPlat" style="visibility: hidden; display: none">
                        <label for="rrn" class="control-label">RRN</label>
                        <input type="tel" class="form-control"
                               spellcheck="false"
                               value='' required="true"
                               id="rrn" name="rrn" placeholder="RRN">
                    </div>

                    <div class="form-group cashIn">
                        <label for="guid" class="control-label">GUID</label>
                        <input type="tel" class="form-control"
                               spellcheck="false"
                               value='' required="true"
                               id="guid" name="guid" placeholder="GUID">
                    </div>

                    <div class="col-xs-12">
                        <div class="col-xs-6 text-center">
                            <a class="btn btn-danger text-uppercase" href="${pageContext.request.contextPath}/">Назад</a>
                        </div>
                        <div class="col-xs-6 text-center text-uppercase">
                            <button type="submit" class="btn btn-success text-uppercase">Пополнить</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </jsp:body>
</t:genericpage>

