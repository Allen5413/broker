<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">校园总监查询</div>
  <ul class="search-view">
    <form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/pageChief/page.html">
      <input type="hidden" id="rows" name="rows" />
      <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
      <li>
        <span class="itg">学校类型：</span>
        <span class="inline-select">
          <select name="type" class="select-140">
            <option value="">全部</option>
            <option value="高校" <c:if test="${param.type eq '高校'}">selected="selected" </c:if> >高校</option>
            <option value="中职" <c:if test="${param.type eq '中职'}">selected="selected" </c:if> >中职</option>
          </select>
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="itg">学校名称：</span>
        <span class="inline-select">
          <input type="text" class="input-txt-200" name="name" value="${param.name}" />
        </span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="inline-input"><a id="searchBtn" class="btn-1" href="#" onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'))">查 询</a></span>
      </li>
    </form>
  </ul>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="title-tabs">
      <a href="#">校园总监列表</a>
    </div>
    <div class="mod-content">
      <div class="data-table-list">
        <table width="100%">
          <tr>
            <th>序号</th>
            <th>学校名称</th>
            <th>总监</th>
            <th>推荐人</th>
            <th>操作</th>
          </tr>
          <c:if test="${empty pageInfo.pageResults}">
            <tr>
              <td colspan="999" style="text-align: center; color: red">没有找到相关数据</td>
            </tr>
          </c:if>
          <c:if test="${!empty pageInfo.pageResults}">
            <c:forEach var="chief" items="${pageInfo.pageResults}" varStatus="status">
              <tr>
                <td>${status.index+1}</td>
                <td>${chief.name}</td>
                <td>
                  <c:if test="${empty chief.cName}">
                    <a class="btn-opr" href="#" onclick="addChief('${chief.no}');">添加</a>
                  </c:if>
                  <c:if test="${!empty chief.cName}">
                    ${chief.cName}
                  </c:if>
                </td>
                <td>
                  <c:if test="${empty chief.rName}">
                    <a class="btn-opr" href="#" onclick="addRecommendMan('${chief.no}');">添加</a>
                  </c:if>
                  <c:if test="${!empty chief.rName}">
                    ${chief.rName}
                  </c:if>
                </td>
                <td>
                  <a class="btn-opr" href="#" onclick="detail('${chief.no}')">查看详情</a>
                  <c:if test="${!empty chief.cName}">
                    <a class="btn-opr" href="#" onclick="removeChief('${chief.no}');">撤销总监</a>
                  </c:if>
                  <c:if test="${!empty chief.rName}">
                    <a class="btn-opr" href="#" onclick="removeRecommendMan('${chief.no}');">撤销推荐人</a>
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
  function addChief(no){
    app.openDialog("${pageContext.request.contextPath}/addChief/open.html", "添加校园总监", "300", "200", function(index){
      var zz = $("#zz").val().trim();
      if(zz == "") {
        app.msg("请输入ZZ", 1);
        return false;
      }
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/addChief/add.json",
        data:{"no":no, "zz":zz},
        async: false,
        success: function(data) {
          if(data.state == 0){
            app.msg("操作成功！", 0);
            layer.close(index);
            $("#searchBtn").click();
          }else{
            app.msg(data.msg, 1);
          }
        }
      });
    });
  }

  function addRecommendMan(no){
    app.openDialog("${pageContext.request.contextPath}/addRecommendMan/open.html", "添加推荐人", "300", "200", function(index){
      var zz = $("#zz").val().trim();
      if(zz == "") {
        app.msg("请输入ZZ", 1);
        return false;
      }
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/addRecommendMan/add.json",
        data:{"no":no, "zz":zz},
        async: false,
        success: function(data) {
          if(data.state == 0){
            app.msg("操作成功！", 0);
            layer.close(index);
            $("#searchBtn").click();
          }else{
            app.msg(data.msg, 1);
          }
        }
      });
    });
  }

  function removeChief(no){
    app.del("您确定要撤销该总监", "${pageContext.request.contextPath}/delChief/del.json", {"no":no}, function(){$("#searchBtn").click();});
  }

  function removeRecommendMan(no){
    app.del("您确定要撤销该推荐人", "${pageContext.request.contextPath}/delRecommendMan/del.json", {"no":no}, function(){$("#searchBtn").click();});
  }

  function detail(no){
    app.clickResources("${pageContext.request.contextPath}/findChief/info.html", {"no":no});
  }

</script>