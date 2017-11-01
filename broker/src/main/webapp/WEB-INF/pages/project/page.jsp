<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">项目查询</div>
  <ul class="search-view">
    <form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/pageProject/page.html">
      <input type="hidden" id="rows" name="rows" />
      <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
    </form>
  </ul>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="title-tabs">
      <a href="#">项目列表</a>
    </div>
    <div class="mod-content">
      <div class="data-table-list">
        <table width="100%">
          <tr>
            <th>序号</th>
            <th>名称</th>
            <th>在校生比例</th>
            <th>访问频次</th>
            <th>审批模式</th>
            <th>经纪人数量</th>
            <th>学校数量</th>
            <th width="140">操作</th>
          </tr>
          <c:if test="${empty pageInfo.pageResults}">
            <tr>
              <td colspan="999" style="text-align: center; color: red">没有找到相关数据</td>
            </tr>
          </c:if>
          <c:if test="${!empty pageInfo.pageResults}">
            <c:forEach var="project" items="${pageInfo.pageResults}" varStatus="status">
              <tr>
                <td>${status.index+1}</td>
                <td>${project.name}</td>
                <td>${project.ratio}%</td>
                <td>${project.frequency}天</td>
                <td>${project.auditTypeStr}</td>
                <td>${project.brokerCount}</td>
                <td>${project.schoolCount}</td>
                <td>
                  <a class="btn-opr" href="#" onclick="app.clickResources('${pageContext.request.contextPath}/editProject/open.html?id=${project.id}&reqParams=${reqParams}');">编辑</a>
                  <c:if test="${sessionScope.type eq '0'}">
                    <a class="btn-opr" href="#" onclick="del(${project.id})">删除</a>
                  </c:if>
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
  function del(id){
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/delProject/del.json",
      data:{"id":id},
      async: false,
      success: function(data) {
        if(data.state == 0){
          app.msg("操作成功！", 0);
          app.clickResources("${pageContext.request.contextPath}/pageProject/page.html", "");
        }else{
          app.msg(data.msg, 1);
        }
      }
    });
  }
</script>