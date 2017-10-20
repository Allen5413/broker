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
      <a href="javascript:;" onclick="history.go(-1);"><i class="i-back"></i></a>
    </div>
    <div class="tit">我的经纪人信息</div>
  </div>
</header>
<section class="section wp-t50">
  <div class="at-wrap">
    <div class="w-info-view">
      <div class="uh-cel">
        <img src="${broker.icon}">
      </div>
      <div class="uinfo-cel">
        <p class="tit">${broker.realname}</p>
        <p>${broker.sname}</p>
      </div>
    </div>
    <div class="w-ico-item">
      <a class="li-cell" href="#">
        <div class="tag-cel">联系电话</div>
        <div class="txt-cel">${broker.mobile}</div>
      </a>
      <a class="li-cell" href="#">
        <div class="tag-cel">联系QQ</div>
        <div class="txt-cel">${broker.qq}</div>
      </a>
    </div>
  </div>
</section>
</body>
</html>