<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">${school.name}</div>
  <ul class="search-view"></ul>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-com-view">
      <div class="title-tabs">
        <a class="on" href="#">基本情况</a>
        <a href="#">总监收益</a>
        <a href="#">推荐人收益</a>
        <a href="#" onclick="searchBroker('${school.no}')">经纪人</a>
      </div>
      <div class="mod-content">
        <table class="set-table-info">
          <tr>
            <td class="tag-b">核定人数：</td>
            <td>${school.num}</td>
          </tr>
          <tr>
            <td class="tag-b">本月活跃人数：</td>
            <td>${school.activeNum} （${date}）</td>
          </tr>
          <tr>
            <td class="tag-b">本月活跃度：</td>
            <td>${activePercent}%</td>
          </tr>
          <tr>
            <td class="tag-b" style="font-size: 16px; color: #4AA6FC">校园总监</td>
          </tr>
          <tr>
            <td class="tag-b">姓名：</td>
            <td>${cName}</td>
          </tr>
          <tr>
            <td class="tag-b">电话：</td>
            <td>${cMobile}</td>
          </tr>
          <tr>
            <td class="tag-b">任命日期：</td>
            <td>${createTime}</td>
          </tr>
          <tr>
            <td class="tag-b" style="font-size: 16px; color: #4AA6FC">推荐人</td>
          </tr>
          <tr>
            <td class="tag-b">姓名：</td>
            <td>${rName}</td>
          </tr>
          <tr>
            <td class="tag-b">电话：</td>
            <td>${rMobile}</td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</div>
<script>
  function searchBroker(no){
    app.clickResources("${pageContext.request.contextPath}/findBrokerProjectBySchoolCode/findGroupByProject.html", {"no":no});
  }
</script>