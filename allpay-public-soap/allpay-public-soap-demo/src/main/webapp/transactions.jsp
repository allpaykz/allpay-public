<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<t:genericpage>
    <jsp:attribute name="head">
    </jsp:attribute>
    <jsp:attribute name="header">
        <t:navbar active="transactions"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
<script>
    $.ajax({
        type: "GET",
        url: "${pageContext.request.contextPath}/rest/api/transactions"

    }).done(function (data) {
        var html = "";

        $.each(data, function (index, value) {
            html += "<tr class='clickable-row'>";
            html += "<td>" + value.transactionInfo.transactionId + "</td>";
            html += "<td>" + value.transactionInfo.transactionStatus + "</td>";
            html += "<td>" + value.transactionInfo.amount + "</td>";
            html += "<td>" + value.transactionInfo.commission + "</td>";
            html += "</tr>";
        });

        $("#table").append(html);

        $('#table').on('click', '.clickable-row', function(event) {
            console.log(event);
            if($(this).hasClass('active')){
                $(this).removeClass('active');
            } else {
                $(this).addClass('active').siblings().removeClass('active');
            }
        });
    })
</script>
    </jsp:attribute>
    <jsp:body>
        <div class="panel panel-info col-xs-12 col-md-6 col-md-offset-3" style="margin-top:142px;">
            <div class="panel-heading">
                <h2 class="panel-title text-center h2">Совершенные транзакции</h2>
            </div>
            <div class="panel-body">
                <table class="table table-bordered" id="table">
                    <thead>
                    <tr>
                        <th>Номер транзакции</th>
                        <th>Статус транзакции</th>
                        <th>Сумма</th>
                        <th>Комиссия</th>
                    </tr>
                    </thead>
                </table>

                <div class="col-xs-12">
                    <div class="col-xs-6 text-center">
                        <a class="btn btn-success text-uppercase" href="${pageContext.request.contextPath}/complete.jsp">Подтверждение</a>
                    </div>
                    <div class="col-xs-6 text-center">
                        <a class="btn btn-danger text-uppercase" href="${pageContext.request.contextPath}/decline.jsp">Отклонение</a>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:genericpage>
