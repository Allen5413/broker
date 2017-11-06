<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">团队查询</div>
  <ul class="search-view">
    <form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/youxue/pageTeam/page.html">
      <input type="hidden" id="rows" name="rows" />
      <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
      <li>
        <span class="itg">团长：</span>
        <span class="inline-select">
          <select name="teamId" class="select-140">
            <option value="">全部</option>
            <c:forEach var="team" items="${teamHeadList}">
              <option value="${team.id}" <c:if test="${param.teamId == team.id}">selected="selected" </c:if> >${team.name}</option>
            </c:forEach>
          </select>
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="itg">经纪人：</span>
        <span class="inline-select">
          <select name="brokerId" class="select-140">
            <option value="">全部</option>
            <c:forEach var="broker" items="${brokerList}">
              <option value="${broker.id}" <c:if test="${param.brokerId == broker.id}">selected="selected" </c:if> >${broker.name}</option>
            </c:forEach>
          </select>
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="itg">产品：</span>
        <span class="inline-select">
          <select name="productId" class="select-140">
            <option value="">全部</option>
            <c:forEach var="product" items="${produceList}">
              <option value="${product.id}" <c:if test="${param.productId == product.id}">selected="selected" </c:if> >${product.name}</option>
            </c:forEach>
          </select>
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="itg">是否团长：</span>
        <span class="inline-select">
          <select name="isHead" class="select-140">
            <option value="">全部</option>
            <option value="0" <c:if test="${param.isHead eq '0'}">selected="selected" </c:if>>否</option>
            <option value="1" <c:if test="${param.isHead eq '1'}">selected="selected" </c:if>>是</option>
          </select>
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="itg">状态：</span>
        <span class="inline-select">
          <select name="state" class="select-140">
            <option value="">全部</option>
            <option value="0" <c:if test="${param.state eq '0'}">selected="selected" </c:if>>已报名</option>
            <option value="1" <c:if test="${param.state eq '1'}">selected="selected" </c:if>>待审核</option>
            <option value="2" <c:if test="${param.state eq '2'}">selected="selected" </c:if>>审核通过</option>
            <option value="3" <c:if test="${param.state eq '3'}">selected="selected" </c:if>>审核未通过</option>
            <option value="4" <c:if test="${param.state eq '4'}">selected="selected" </c:if>>已缴费</option>
          </select>
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="inline-input"><a id="searchBtn" class="btn-1" href="#" onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'))">查 询</a></span>
      </li>
    </form>
  </ul>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="title-tabs">
      <a href="#">团员列表</a>
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
                    <a class="btn-opr" href="#" onclick="editQq(${team.id})">编辑QQ</a>
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
  function editState(id){
    app.openDialog("${pageContext.request.contextPath}/youxue/editTeam/open.html?id="+id, "编辑状态", "400", "300", function(index){
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/youxue/editTeam/editState.json",
        data:$("#editForm").serialize(),
        async: false,
        success: function(data) {
          if(data.state == 0){
            app.msg("操作成功！", 0);
            layer.close(index);
            app.clickResources("${pageContext.request.contextPath}/youxue/pageTeam/page.html", "${reqParams}");
          }else{
            app.msg(data.msg, 1);
          }
        }
      });
    });
  }

  function editQq(id){
    app.openDialog("${pageContext.request.contextPath}/youxue/editTeam/openEditQq.html?id="+id, "编辑咨询QQ群", "400", "180", function(index){
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/youxue/editTeam/editQq.json",
        data:$("#editForm").serialize(),
        async: false,
        success: function(data) {
          if(data.state == 0){
            app.msg("操作成功！", 0);
            layer.close(index);
            app.clickResources("${pageContext.request.contextPath}/youxue/pageTeam/page.html", "${reqParams}");
          }else{
            app.msg(data.msg, 1);
          }
        }
      });
    });
  }
</script>