<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">团长风采</div>
  <ul class="search-view">
    <table class="set-table-info">
      <tr>
        <td class="tag-b">ZZ：</td>
        <td>${team.zz}</td>
      </tr>
      <tr>
        <td class="tag-b">昵称：</td>
        <td>${team.nickname}</td>
      </tr>
      <tr>
        <td class="tag-b">手机：</td>
        <td>${team.mobile}</td>
      </tr>
      <tr>
        <td class="tag-b">QQ：</td>
        <td>${team.qq}</td>
      </tr>
      <tr>
        <td class="tag-b">学校：</td>
        <td>${team.sname}</td>
      </tr>
    </table>
  </ul>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="title-tabs">
      <a href="#">照片列表</a>
    </div>
    <div class="mod-content">
      <div class="data-table-list">
        <table width="100%">
          <tr>
            <th>序号</th>
            <th>姓名</th>
            <th>所在学校</th>
            <th>手机号码</th>
            <th>产品名称</th>
            <th>是否团长</th>
            <th>经纪人</th>
            <th>状态</th>
            <th>预约时间</th>
            <th>备注</th>
            <th>操作</th>
          </tr>
          <c:if test="${empty pageInfo.pageResults}">
            <tr>
              <td colspan="999" style="text-align: center; color: red">没有找到相关数据</td>
            </tr>
          </c:if>
          <c:if test="${!empty pageInfo.pageResults}">
            <c:forEach var="team" items="${pageInfo.pageResults}" varStatus="status">
              <tr>
                <td>${status.index+1}</td>
                <td>${team.name}</td>
                <td>${team.sName}</td>
                <td>${team.mobile}</td>
                <td>${team.pName}</td>
                <td>${team.isHead == 0 ? "否":"是"}</td>
                <td>${team.bName}</td>
                <td>
                  <c:if test="${team.state eq '0'}">已报名</c:if>
                  <c:if test="${team.state eq '1'}">待审核</c:if>
                  <c:if test="${team.state eq '2'}">审核通过</c:if>
                  <c:if test="${team.state eq '3'}">审核未通过</c:if>
                  <c:if test="${team.state eq '4'}">已缴费</c:if>
                </td>
                <td>${team.yyDate}</td>
                <td>${team.remark}</td>
                <td>
                  <c:if test="${team.isHead == 0}">
                    <a class="btn-opr" href="#" onclick="editState(${team.id})">编辑状态</a>
                  </c:if>
                  <c:if test="${team.isHead == 1}">
                    <a class="btn-opr" href="#" onclick="searchPic('${team.zz}')">团长风采</a>
                    <a class="btn-opr" href="#" onclick="editQq(${team.id})">编辑QQ</a>
                  </c:if>
                  <c:if test="${(team.isHead == 0 && team.state ne '4') || team.isHead == 1}">
                    <a class="btn-opr" href="#" onclick="del(${team.id})">删除</a>
                  </c:if>
                </td>
              </tr>
            </c:forEach>
            <%@ include file="../../common/page.jsp"%>
          </c:if>
        </table>
      </div>
    </div>
  </div>
</div>
<script>
  function del(id){
    app.del("您确定要删除该人员？", "${pageContext.request.contextPath}/youxue/delYxTeam/del.json", {"id":id}, "${pageContext.request.contextPath}/youxue/pageTeam/page.html", "${reqParams}");
  }
</script>