<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="../common/meta.jsp"%>
  <%@ include file="../common/taglibs.jsp"%>
</head>
<body>
<div class="header-view">
  <div class="title">跟着校花去游学项目管理平台</div>
  <div class="user-navs">
    <ul>
      <li> </li>
      <li class="uh-name">
        <span class="uhead"><img src="../img/zs_uhead.jpg" /></span>
        <a href="#">您好！${sessionScope.name}</a>
        <div class="navs-drop">
          <ul>
            <li><a href="${pageContext.request.contextPath}/youxue/logout.html">退出登录</a></li>
          </ul>
        </div>
      </li>
    </ul>
  </div>
</div>
<div id="main_page" class="section-view">
  <div class="nav-view">
    <div class="title">目录</div>
    <ul id="nav_1" class="nav-rt-list">
      <c:forEach var="menu" items="${sessionScope.menuMap}" varStatus="status">
        <li>
          <a class="n-icon" style="background-image:url(../img/menu/${fn:substring(menu.key, fn:indexOf(menu.key, '_')+1, fn:length(menu.key))});" href="#" onclick="app.clickMenu(${status.index})">${fn:substring(menu.key, 0, fn:indexOf(menu.key, '_'))}</a>
          <ul id="resources_${status.index}" class="eve-two-list">
            <c:forEach var="resource" items="${menu.value}" varStatus="status2">
              <li><a href="#" name="resources_a" onclick="app.clickResources('${resource.url}', {}, this)">${resource.name}</a></li>
            </c:forEach>
          </ul>
        </li>
      </c:forEach>
    </ul>
  </div>
  <div id="contentPage" class="main-view">
    <div id="tit-top-fixed" class="pos-rev-cell">
      <div class="title">首页</div>
    </div>
    <div class="container-view">
      <div class="mod-com-view">
        <div class="mod-content">
          <table class="set-table-info">
            <tr>
              <td class="tag-b">欢迎使用本系统</td>
            </tr>
          </table>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
<script>
  var oNav = document.getElementById('nav_1');
  oNav.style.height = document.documentElement.clientHeight - 100 + 'px';
</script>
