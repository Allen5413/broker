<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="common/meta.jsp"%>
  <%@ include file="common/taglibs.jsp"%>
</head>
<body>
<header>
  <div class="header">
    <div class="ct-lf">
      <a href="#" onclick="history.go(-1);"><i class="i-back"></i></a>
    </div>
    <div class="tit">${product.name}</div>
  </div>
</header>
<section class="section wp-t44">
  <div class="at-wrap">
    <div class="theme-pic">
      <div class="pic"><img src="/youxue/images/item/item-01.png">
        <div class="item-tit">
          <div class="tit">${product.name}</div>
          <p class="sum-bar">
            团队 ${teamNum}
          </p>
        </div>
      </div>
      <div class="sign-up-bar">
        <div class="price-cel"><span class="num"><i class="small">￥</i>${product.money/100}</span> / ${product.unit}</div>
        <div class="sign-cel"><a class="btn-cc" href="${pageContext.request.contextPath}/youxueApp/signUpTeam/open.html?productId=${product.id}">我要报名</a></div>
      </div>
    </div>
    <div class="intro-view">
      <div class="title">校花团队（<span class="blue">${teamHeadNum}</span>）<a class="more" href="#">更多</a></div>
      <div class="members-list">
        <c:forEach var="team" items="${pageInfo.pageResults}" begin="0" end="4">
          <a class="mem-cell" href="#">
            <div class="uhpic"><img src="${team.icon}"></div>
            <p>${team.name}</p>
          </a>
        </c:forEach>
        <a class="mem-cell" href="${pageContext.request.contextPath}/youxueApp/applyTeamHead/open.html">
          <div class="add-plus"><img src="/youxue/images/button-cr.png"></div>
          <p>&nbsp </p>
        </a>
      </div>
    </div>
    <c:if test="${product.id eq '1'}"><%@ include file="product/1.jsp"%></c:if>
    <c:if test="${product.id eq '2'}"><%@ include file="product/2.jsp"%></c:if>
    <c:if test="${product.id eq '3'}"><%@ include file="product/3.jsp"%></c:if>
    <c:if test="${product.id eq '4'}"><%@ include file="product/4.jsp"%></c:if>
  </div>
</section>
</body>
</html>