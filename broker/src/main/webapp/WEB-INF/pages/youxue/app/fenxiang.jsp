<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="common/meta.jsp"%>
  <%@ include file="common/taglibs.jsp"%>
</head>
<body>
<section class="section" style="background: #4abffa;">
  <div class="share-wrap">
    <div class="pic"><img src="/youxue/images/bg-sc_01.png"></div>
    <div class="text">
      <p>我有一个梦想！梦想有一天去看看世界不一样的风景，去感受不一样的大学文化，去体会不一样的风土人情！</p>
      <p>和校花一起去游学，一起去感受吧！</p>
    </div>
    <div class="ag-uhd">
      <div class="uh-cel"><img src="${broker.icon}"></div>
      <div class="uinfo">
        <p class="tit">${broker.realname}</p>
        <p>${broker.sname}</p>
      </div>
    </div>
    <p class="go-into"><a href="http://localhost:8080/youxueApp/index/open.html?projectId=1&brokerZz=${broker.zz}">快来加入吧</a></p>
  </div>
</section>
</body>
</html>
<script>
  setTimeout(function(){hideButtom();}, 500);
</script>