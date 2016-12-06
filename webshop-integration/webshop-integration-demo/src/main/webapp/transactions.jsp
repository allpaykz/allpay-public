<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>

    <!-- Bootstrap core CSS -->
    <%--<link href="css/bootstrap.min.css" rel="stylesheet">--%>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <!-- Material Design fonts -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:300,400,500,700" type="text/css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <!-- Bootstrap Material Design -->
    <%--<link href="css/bootstrap-material-design.min.css" rel="stylesheet">--%>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-material-design/0.5.7/css/bootstrap-material-design.min.css"
          rel="stylesheet">
    <%--<link href="css/ripples.min.css" rel="stylesheet">--%>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-material-design/0.5.7/css/ripples.min.css"
          rel="stylesheet">
</head>
<body>
<div class="form-group col-xs-12 col-md-12 text-center">
    <a href="${pageContext.request.contextPath}/" class="btn btn-raised btn-info">
        Сделать ещё покупку
    </a>
</div>
<div class="container col-xs-12 col-md-6 col-md-offset-3">
    <div class="panel panel-default">
        <div class="panel-heading text-center">Проведённые транзакции</div>

        <table class="table" id="table">
            <thead>
            <tr>
                <th>Номер транзакции</th>
                <th>Статус транзакции</th>
            </tr>
            </thead>
        </table>
    </div>
</div>



<%--<script src="js/jquery-1.11.3.min.js"></script>--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="js/vendor/jquery.min.js"><\/script>')</script>

<script>
    $.ajax({
        type: "GET",
        url: "${pageContext.request.contextPath}/webresources/test/getTransactions"

    }).done(function (data) {
        var html = "";

        $.each(data, function (index, value) {
            html += "<tr><td>" + index + "</td><td>" + value + "</td></tr>";
        })
        console.log(html)

        $("#table").append(html);
    })
</script>

<%--<script src="js/bootstrap.min.js"></script>--%>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
        integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
        crossorigin="anonymous"></script>
</body>
</html>
