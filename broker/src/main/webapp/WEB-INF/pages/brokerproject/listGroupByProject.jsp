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
        <a href="#" onclick="searchDetail('${school.no}')">基本情况</a>
        <a href="#">总监收益</a>
        <a href="#">推荐人收益</a>
        <a class="on" href="#">经纪人</a>
      </div>
      <div class="mod-content">
        <div class="data-table-list">
          <table width="100%">
            <tr>
              <th>项目</th>
              <th>经纪人</th>
              <th>应有经纪人</th>
              <th>完成比</th>
            </tr>
            <c:if test="${empty list}">
              <tr>
                <td colspan="999" style="text-align: center; color: red">没有找到相关数据</td>
              </tr>
            </c:if>
            <c:if test="${!empty list}">
              <c:forEach var="json" items="${list}" varStatus="status">
                <tr>
                  <td>${json.name}</td>
                  <td>${json.num}</td>
                  <td>${json.maxNum}</td>
                  <td>${json.bl}%</td>
                </tr>
              </c:forEach>
            </c:if>
          </table>
        </div>
      </div>
    </div>
  </div>
</div>
<script>
  function searchDetail(no){
    app.clickResources("${pageContext.request.contextPath}/findChief/info.html", {"no":no});
  }
</script>