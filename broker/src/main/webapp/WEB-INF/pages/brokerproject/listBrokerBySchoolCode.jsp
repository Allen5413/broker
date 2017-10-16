<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">${param.schoolName}</div>
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
            <th>有效客户人数</th>
            <th>操作</th>
          </tr>
          <c:if test="${empty list}">
            <tr>
              <td colspan="999" style="text-align: center; color: red">没有找到相关数据</td>
            </tr>
          </c:if>
          <c:if test="${!empty list}">
            <c:forEach var="broker" items="${list}" varStatus="status">
              <tr>
                <td>${status.index+1}</td>
                <td>${broker.name}</td>
                <td></td>
                <td>
                  <a class="btn-opr" href="#" onclick="detail('${broker.zz}');">查看</a>
                  <a class="btn-opr" href="#" onclick="black('${broker.zz}');">黑名单</a>
                </td>
              </tr>
            </c:forEach>
          </c:if>
        </table>
      </div>
    </div>
  </div>
</div>
<script>
  function detail(zz){
    app.openDialog("${pageContext.request.contextPath}/findBrokerByZz/find.html?zz="+zz, "经纪人详情", "800", "700", function(index){
    });
  }

  function black(zz){
    app.openOneBtnDialog("${pageContext.request.contextPath}/blackBrokerProject/open.html?zz="+zz+"&projectId=${param.projectId}", "拉黑经纪人", "200", "288", function(index){
    });
  }
</script>