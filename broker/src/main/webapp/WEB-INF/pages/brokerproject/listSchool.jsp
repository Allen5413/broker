<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">经纪人查询</div>
  <ul class="search-view">
    <form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findBrokerProjectForSchool/find.html">
      <input type="hidden" name="method" value="search"/>
      <li>
        <span class="itg">项目：</span>
        <span class="inline-select">
          <select id="projectId" name="projectId" class="select-140">
            <c:forEach var="project" items="${projectList}">
              <option value="${project.id}" <c:if test="${param.projectId == project.id}">selected="selected" </c:if> >${project.name}</option>
            </c:forEach>
          </select>
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="itg">姓名：</span>
        <span class="inline-select">
          <input type="text" class="input-txt-200" name="name" value="${param.name}" />
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="inline-input"><a id="searchBtn" class="btn-1" href="#" onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'))">查 询</a></span>
      </li>
    </form>
  </ul>
</div>
<c:if test="${'search' eq param.method}">
  <div class="container-view">
    <div class="mod-com-view">
      <div class="title-tabs">
        <a href="#">学校列表</a>
      </div>
      <div class="mod-content">
        <div class="data-table-list">
          <table width="100%">
            <tr>
              <th>序号</th>
              <th>学校</th>
              <th>在校生人数</th>
              <th>最大人数</th>
              <th>实际人数</th>
              <th>操作</th>
            </tr>
            <c:if test="${empty map}">
              <tr>
                <td colspan="999" style="text-align: center; color: red">没有找到相关数据</td>
              </tr>
            </c:if>
            <c:if test="${!empty map}">
              <c:forEach var="school" items="${map}" varStatus="status">
                <tr>
                  <td>${status.index+1}</td>
                  <td>${school.value.name}</td>
                  <td>${school.value.num}</td>
                  <td>${school.value.maxNum}</td>
                  <td>${school.value.manNum}</td>
                  <td>
                    <a class="btn-opr" href="#" onclick="detail('${school.value.code}', '${school.value.name}');">查看</a>
                  </td>
                </tr>
              </c:forEach>
            </c:if>
          </table>
        </div>
      </div>
    </div>
  </div>
  <form id="detailForm" name="detailForm" action="${pageContext.request.contextPath}/findBrokerProjectBySchoolCode/find.html" method="post">
    <input type="hidden" id="projectId2" name="projectId" />
    <input type="hidden" id="schoolCode" name="schoolCode" />
    <input type="hidden" id="schoolName" name="schoolName" />
  </form>
</c:if>
<script>
  function detail(schoolCode, schoolName){
    $("#projectId2").val($("#projectId").val());
    $("#schoolCode").val(schoolCode);
    $("#schoolName").val(schoolName);
    app.searchFormPage($('#detailForm'), $('#detailForm').attr('action'))
  }
</script>