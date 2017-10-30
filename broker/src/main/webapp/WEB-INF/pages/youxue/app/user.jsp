<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="common/meta.jsp"%>
  <%@ include file="common/taglibs.jsp"%>
</head>
<body>
<section class="section wp-t50">
  <div class="at-wrap">
    <div class="w-info-view">
      <div class="uh-cel">
        <img src="${user.icon}">
      </div>
      <div class="uinfo-cel">
        <p class="tit">${user.nickname}</p>
        <p>${user.sname}</p>
      </div>
    </div>
    <div class="w-ico-item">
      <c:if test="${isHead}">
        <a class="li-cell" href="${pageContext.request.contextPath}/youxueApp/uploadMyImg/open.html">
          <div class="tag-cel"><i class="i-w2"></i>我的相册</div>
          <div class="txt-cel">
            <c:if test="${!empty imgUrl}">
              <img src="${imgUrl}">
            </c:if>
            <i class="i-arr"></i>
          </div>
        </a>
        <a class="li-cell" href="${pageContext.request.contextPath}/youxueApp/editTeamLabel/open.html">
          <div class="tag-cel"><i class="i-w3"></i>自我介绍</div>
          <div class="txt-cel"><i class="i-arr"></i></div>
        </a>
      </c:if>
      <a class="li-cell" href="${pageContext.request.contextPath}/youxueApp/findMyBroker/open.html">
        <div class="tag-cel"><i class="i-w4"></i>我的经纪人</div>
        <div class="txt-cel">
          <c:if test="${!empty broker.icon}">
            <img src="${broker.icon}">
          </c:if>
          <i class="i-arr"></i>
        </div>
      </a>
    </div>
    <c:forEach var="team" items="${teamList}">
      <div class="w-ico-item">
        <a class="li-cell" href="javascript:;" onclick="location.href='${pageContext.request.contextPath}/youxueApp/findTeam/findInfo.html?teamId=${team.id}'">
          <div class="tag-cel"><i class="i-w1"></i>${team.pName}</div>
          <div class="txt-cel">
            <c:if test="${team.isHead == 1}">校花团长<i class="i-arr"></i></c:if>
            <c:if test="${team.isHead == 0}">
              <c:if test="${team.state == 0}">已报名</c:if>
              <c:if test="${team.state == 1}">资料待审核</c:if>
              <c:if test="${team.state == 2}">资料审核通过</c:if>
              <c:if test="${team.state == 3}">资料审核不通过</c:if>
              <c:if test="${team.state == 4}">缴费报名成功</c:if>
            </c:if>
          </div>
        </a>
      </div>
    </c:forEach>
  </div>
  <div class="footer">
    <ul class="nav-tabs">
      <li><a href="${pageContext.request.contextPath}/youxueApp/index/open.html?zz=${sessionScope.loginName}&projectId=${sessionScope.projectId}&notCount=0">首页</a></li>
      <li><a href="${pageContext.request.contextPath}/youxueApp/findProduct/open.html">项目</a></li>
      <li><a href="${pageContext.request.contextPath}/youxueApp/findTeam/find.html">团队</a></li>
      <li><a class="on" href="javascript:;">我的</a></li>
    </ul>
  </div>
</section>
</body>
</html>
<script>
  setTimeout(function(){hideButtom();}, 500);
</script>
