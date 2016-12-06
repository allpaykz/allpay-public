<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
<div class="panel panel-warning col-sm-12 col-md-6 col-md-offset-3" style="margin-top:142px;">
    <div class="panel-heading">
        <h2 class="panel-title text-center h2">Совершенные транзакции</h2>
    </div>
    <div class="panel-body">
        <table class="table" id="table">
            <thead>
            <tr>
                <th>Номер транзакции</th>
                <th>Статус транзакции</th>
                <th>Сумма</th>
                <th>Комиссия</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
<script
        src="https://code.jquery.com/jquery-3.1.1.min.js"
        crossorigin="anonymous"></script>
<script>
    $.ajax({
        type: "GET",
        url: "${pageContext.request.contextPath}/rest/api/transactions"

    }).done(function (data) {
        console.log(data);
        var html = "";

        $.each(data, function (index, value) {
            html += "<tr>";
            html += "<td>" + value.transactionInfo.transactionId + "</td>";
            html += "<td>" + value.transactionInfo.transactionStatus + "</td>";
            html += "<td>" + value.transactionInfo.amount + "</td>";
            html += "<td>" + value.transactionInfo.commission + "</td>";
            html+="</tr>";
        });

        $("#table").append(html);
    })
</script>
</html>
