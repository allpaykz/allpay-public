<%@tag description="Card template" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@attribute name="active" required="true" type="java.lang.String" %>

<c:if test="${active.equals('main')}">
    <c:set var="classMain" value="active"/>
</c:if>
<c:if test="${active.equals('transactions')}">
    <c:set var="classTx" value="active"/>
</c:if>
<c:if test="${active.equals('cashin')}">
    <c:set var="classCashin" value="active"/>
</c:if>
<c:if test="${active.equals('cashout')}">
    <c:set var="classCashout" value="active"/>
</c:if>
<c:if test="${active.equals('complete')}">
    <c:set var="classComplete" value="active"/>
</c:if>
<c:if test="${active.equals('decline')}">
    <c:set var="classDecline" value="active"/>
</c:if>
<c:if test="${active.equals('userInfo')}">
    <c:set var="classUser" value="active"/>
</c:if>
<nav class="navbar navbar-default navbar-fixed-top col-md-8 col-md-offset-2 text-center">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-default-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
            <div class="navbar-collapse collapse navbar-default-collapse">
            <ul class="nav navbar-nav" style="font-size: medium">
                <li class="${classMain}"><a href="${pageContext.request.contextPath}/">Домой</a></li>
                <li class="${classTx}"><a href="${pageContext.request.contextPath}/transactions.jsp">Транзакции</a></li>
                <li class="${classCashin}"><a href="${pageContext.request.contextPath}/cashin.jsp">Пополнить</a></li>
                <li class="${classCashout}"><a href="${pageContext.request.contextPath}/cashout.jsp">Снять</a></li>
                <li class="${classComplete}"><a href="${pageContext.request.contextPath}/complete.jsp">Подтвердить</a></li>
                <li class="${classDecline}"><a href="${pageContext.request.contextPath}/decline.jsp">Отменить</a></li>
                <li class="${classUser}"><a href="${pageContext.request.contextPath}/userinfo.jsp">Юзер</a></li>
            </ul>
        </div>
    </div>
</nav>
