<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<t:genericpage>
    <jsp:attribute name="head">
    </jsp:attribute>
    <jsp:attribute name="header">
        <t:navbar active="availableBalance"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
                integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
                crossorigin="anonymous"></script>
        <script src='http://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/js/bootstrapvalidator.min.js'></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/mask.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/usercheck-validator.js"></script>
<script>
    function availableBalance() {

        var loginName = $('#requesterInput').val();
        var certificateIdInput = $('#certificateIdInput').val();
        var pemInput = $('#pemInput').val();
        var pemInputResponse = $('#pemInputResponse').val();

        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/rest/api/availableBalance",
            data: "loginName=" + loginName + "&certificateIdInput=" + certificateIdInput + "&pemInput=" + encodeURIComponent(pemInput) + "&pemInputResponse=" + encodeURIComponent(pemInputResponse)

        }).done(function (data) {
            console.log(data);

            if (data.agentBalanceResponse == undefined) {
                $("#alert").removeClass("invisible").addClass("visible");
                return;
            }

            var html = "";
            html += "<h3>" + data.agentBalanceResponse + "</h3>";
            $(".response-text").append(html);
        })
    }
</script>
    </jsp:attribute>
    <jsp:body>
        <div class="panel panel-info col-xs-12 col-md-6 col-md-offset-3" style="margin-top:142px;">
            <div class="panel-heading">
                <h3 class="panel-title text-center">Информация</h3>
            </div>
            <div class="panel-body">
                <form class="form-horizontal" accept-charset="UTF-8" id="usercheck"
                      method="post"
                      action="${pageContext.request.contextPath}/rest/api/checkUser">
                    <div class="form-group">
                        <label for="requesterInput" class="control-label">Логин агента</label>
                        <input type="text" class="form-control"
                               spellcheck="false"
                               value='' required="true"
                               id="requesterInput" name="loginName" placeholder="Логин агента">
                    </div>

                    <div class="form-group">
                        <label class="pemInput">Приватный ключ(для подписи запроса)</label>
                        <div class="inputGroupContainer">
                            <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                                <textarea class="form-control" id="pemInput" name="pemInput" placeholder="Приватный ключ(для подписи запроса)"></textarea>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="pemInputResponse">Публичный ключ(для проверки подписи ответа)</label>
                        <div class="inputGroupContainer">
                            <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                                <textarea class="form-control" id="pemInputResponse" name="pemInputResponse" placeholder="Публичный ключ(для проверки подписи ответа)"></textarea>
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

                    <div class="col-xs-12">
                        <div class="col-xs-6 text-center">
                            <a class="btn btn-danger text-uppercase"
                               href="${pageContext.request.contextPath}/">Назад</a>
                        </div>
                        <div class="col-xs-6 text-center text-uppercase">
                            <button class="btn btn-success text-uppercase" onclick="availableBalance()">Узнать</button>
                        </div>
                    </div>

                    <div class="alert alert-danger invisible" id="alert">
                        Неправильные данные!
                    </div>
                </form>

                <div class="response-text">
                </div>

            </div>
        </div>
    </jsp:body>
</t:genericpage>
