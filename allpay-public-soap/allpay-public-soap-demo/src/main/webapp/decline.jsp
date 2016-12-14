<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<t:genericpage>
    <jsp:attribute name="head">
    </jsp:attribute>
    <jsp:attribute name="header">
        <t:navbar active="decline"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/js/bootstrapvalidator.min.js'></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mask.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/transaction-validator.js"></script>
    </jsp:attribute>
    <jsp:body>
        <div class="panel panel-info col-xs-12 col-md-6 col-md-offset-3" style="margin-top:142px;">
            <div class="panel-heading">
                <h3 class="panel-title text-center">Отмена транзакции</h3>
            </div>
            <div class="panel-body">
                <form class="form-horizontal" accept-charset="UTF-8" id="cashin-form"
                      action="${pageContext.request.contextPath}/rest/api/declineTransaction"
                      method="post">

                    <div class="form-group">
                        <label for="loginName" class="control-label">Введите логин</label>
                        <input type="text" class="form-control"
                               spellcheck="false"
                               value='' required="true"
                               id="loginName" name="loginName" placeholder="Логин агента">
                    </div>

                    <div class="form-group">
                        <label class="pemInput">Подпись</label>
                        <div class="inputGroupContainer">
                            <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                                <textarea class="form-control" name="pemInput" placeholder="Подпись"></textarea>
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
                        <label for="txNumber" class="control-label">Введите номер транзакции</label>
                        <input type="tel" class="form-control"
                               spellcheck="false"
                               value='' required="true"
                               id="txNumber" name="txNumber" placeholder="Номер транзакции">
                    </div>

                    <div class="col-xs-12">
                        <div class="col-xs-6 text-center">
                            <a class="btn btn-danger text-uppercase" href="${pageContext.request.contextPath}/transactions.jsp">Назад</a>
                        </div>
                        <div class="col-xs-6 text-center text-uppercase">
                            <button type="submit" class="btn btn-success text-uppercase">Отменить</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </jsp:body>
</t:genericpage>
