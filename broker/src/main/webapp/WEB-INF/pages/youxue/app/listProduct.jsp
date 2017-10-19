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
    <div class="item-view">
      <ul class="list-item">
        <li>
          <a class="item" href="${pageContext.request.contextPath}/youxueApp/findProduct/findInfo.html?productId=${bj.id}&teamNum=${bj.num}"><img src="/youxue/images/item/item-01.png">
            <div class="info">
              <p class="tit">${bj.name}</p>
              <p class="sum-bar">
                团队 <span class="num">${bj.num}</span>
              </p>
            </div>
          </a>
        </li>
        <li>
          <a class="item" href="${pageContext.request.contextPath}/youxueApp/findProduct/findInfo.html?productId=${xg.id}&teamNum=${xg.num}"><img src="/youxue/images/item/item-02.png">
            <div class="info">
              <p class="tit">${xg.name}</p>
              <p class="sum-bar">
                团队 <span class="num">${xg.num}</span>
              </p>
            </div>
          </a>
        </li>
        <li>
          <a class="item" href="${pageContext.request.contextPath}/youxueApp/findProduct/findInfo.html?productId=${jq.id}&teamNum=${jq.num}"><img src="/youxue/images/item/item-03.png">
            <div class="info">
              <p class="tit">${jq.name}</p>
              <p class="sum-bar">
                团队 <span class="num">${jq.num}</span>
              </p>
            </div>
          </a>
        </li>
        <li>
          <a class="item" href="${pageContext.request.contextPath}/youxueApp/findProduct/findInfo.html?productId=${yl.id}&teamNum=${yl.num}"><img src="/youxue/images/item/item-04.png">
            <div class="info">
              <p class="tit">${yl.name}</p>
              <p class="sum-bar">
                团队 <span class="num">${yl.num}</span>
              </p>
            </div>
          </a>
        </li>
      </ul>
    </div>
  </div>
  <div class="footer">
    <ul class="nav-tabs">
      <li><a href="${pageContext.request.contextPath}/youxueApp/index/open.html?zz=${sessionScope.loginName}">首页</a></li>
      <li><a class="on" href="javascript:;">项目</a></li>
      <li><a href="${pageContext.request.contextPath}/youxueApp/findTeam/find.html">团队</a></li>
      <li><a href="w-user.html">我的</a></li>
    </ul>
  </div>
</section>
</body>
</html>