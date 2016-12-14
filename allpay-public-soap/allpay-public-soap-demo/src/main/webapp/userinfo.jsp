<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<t:genericpage>
    <jsp:attribute name="head">
    </jsp:attribute>
    <jsp:attribute name="header">
        <t:navbar active="userInfo"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
                integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
                crossorigin="anonymous"></script>
        <script src='http://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/js/bootstrapvalidator.min.js'></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/mask.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/usercheck-validator.js"></script>
<script>
    function checkUser() {

        var loginName = $('#requesterInput').val();
        var userLoginName = $('#userLoginName').val();
        var userLoginName = $('#userLoginName').val();
        var certificateIdInput = $('#certificateIdInput').val();
        var pemInput = $('#pemInput').val();



        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/rest/api/checkUser",
            data: "loginName=" + loginName + "&userLoginName=" + userLoginName
                    + "&certificateIdInput=" + certificateIdInput + "&pemInput=" + encodeURIComponent(pemInput)

        }).done(function (data) {
            console.log(data);

            if (data.basicUserInfo == undefined) {
                $("#alert").removeClass("invisible").addClass("visible");
                return;
            }

            var html = "";
            html += "<tr class='clickable-row'>";
            html += "<td>" + data.basicUserInfo.firstName + "</td>";
            html += "<td>" + data.basicUserInfo.lastName + "</td>";
            html += "<td>" + data.basicUserInfo.identified + "</td>";
            html += "<td>" + data.basicUserInfo.iin + "</td>";
            html += "</tr>";

            $("#table").append(html);

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
                        <label class="pemInput">Подпись</label>
                        <div class="inputGroupContainer">
                            <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                                <textarea class="form-control" id="pemInput" name="pemInput" placeholder="Подпись"></textarea>
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
                        <label for="userLoginName" class="control-label">Логин клиента</label>
                        <input type="tel" class="form-control"
                               spellcheck="false"
                               value='' required="true"
                               id="userLoginName" name="userLoginName" placeholder="Логин клиента">
                    </div>

                    <div class="col-xs-12">
                        <div class="col-xs-6 text-center">
                            <a class="btn btn-danger text-uppercase"
                               href="${pageContext.request.contextPath}/">Назад</a>
                        </div>
                        <div class="col-xs-6 text-center text-uppercase">
                            <button class="btn btn-success text-uppercase" onclick="checkUser()">Узнать</button>
                        </div>
                    </div>

                    <div class="alert alert-danger invisible" id="alert">
                        <strong>Ужас!</strong> Такого пользователя не существует
                    </div>
                </form>

                <div class="table-responsive" style="margin-top: 142px">
                    <table class="table table-bordered" id="table">
                        <thead>
                        <tr>
                            <th>Имя</th>
                            <th>Фамилия</th>
                            <th>Идентифицирован</th>
                            <th>ИИН</th>
                        </tr>
                        </thead>
                    </table>
                </div>

            </div>
        </div>
    </jsp:body>
</t:genericpage>
