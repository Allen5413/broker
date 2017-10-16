<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">黑名单</div>
  <ul class="search-view">
  </ul>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="title-tabs">
      <a href="#">经纪人列表</a>
    </div>
    <div class="mod-content">
      <div class="data-table-list">
        <table width="100%">
          <tr>
            <th>序号</th>
            <th>姓名</th>
            <th>拉黑时间</th>
            <th>解除时间</th>
            <th>操作</th>
          </tr>
          <c:if test="${empty pageInfo.pageResults}">
            <tr>
              <td colspan="999" style="text-align: center; color: red">没有找到相关数据</td>
            </tr>
          </c:if>
          <c:if test="${!empty pageInfo.pageResults}">
            <c:forEach var="broker" items="${pageInfo.pageResults}" varStatus="status">
              <tr>
                <td>${status.index+1}</td>
                <td>${broker.name}</td>
                <td>${broker.blackTime}</td>
                <td>${broker.removeTime}</td>
                <td>
                  <a class="btn-opr" href="#" onclick="removeBlack(${broker.id});">解除</a>
                </td>
              </tr>
            </c:forEach>
            <%@ include file="../common/page.jsp"%>
          </c:if>
        </table>
      </div>
    </div>
  </div>
</div>
<script>
  function removeBlack(id){
    app.edit("${pageContext.request.contextPath}/removeBlackBroker/removeBlack.json", {"id":id}, "${pageContext.request.contextPath}/pageBroker/pageBlack.html", "");
  }

</script>