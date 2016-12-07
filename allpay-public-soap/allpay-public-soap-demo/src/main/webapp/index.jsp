<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<t:genericpage>
    <jsp:attribute name="head">
    </jsp:attribute>
    <jsp:attribute name="header">
        <t:navbar active="main"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <div class="panel panel-info col-xs-12 col-md-6 col-md-offset-3" style="margin-top:142px;">
            <div class="panel-heading">
                <h2 class="panel-title text-center h2">Снять или пополнить allpay</h2>
            </div>
            <div class="panel-body col-xs-12">
                <div class="text-center">
                        <%--Кнопка снятие--%>
                    <div class="col-xs-6">
                        <a class="btn btn-warning text-uppercase" href="cashout.jsp">
                            <span class="glyphicon glyphicon-arrow-up" style="color: #fff"></span>
                            Снятие
                        </a>
                    </div>

                        <%--Кнопка пополнение--%>
                    <div class="col-xs-6">
                        <a class="btn btn-info text-uppercase" href="cashin.jsp">
                            <span class="glyphicon glyphicon-arrow-down" style="color: #fff"></span>
                            Пополнение
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:genericpage>
