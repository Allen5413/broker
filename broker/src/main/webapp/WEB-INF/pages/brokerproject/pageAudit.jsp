<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">经纪人审批</div>
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
            <th>ZZ号</th>
            <th>姓名</th>
            <th>项目</th>
            <th>申请时间</th>
            <th>操作内容</th>
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
                <td>${broker.creator}</td>
                <td></td>
                <td>${broker.pName}</td>
                <td>${broker.createTime}</td>
                <td>
                  <a class="btn-opr" href="#" onclick="pass(${broker.id});">通过</a>
                  <a class="btn-opr" href="#" onclick="not(${broker.id});">不通过</a>
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
  function pass(id){
    app.edit("${pageContext.request.contextPath}/auditBrokerProject/pass.json", {"id":id}, "${pageContext.request.contextPath}/pageBrokerProject/pageAudit.html", "");
  }

  function not(id){
    app.openDialog("${pageContext.request.contextPath}/auditBrokerProject/openNot.html", "审批不通过", "400", "200", function(index){
      var reason = $("#reason").val().trim();
      if(reason == "") {
        app.msg("请输入不通过原因", 1);
        return false;
      }
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/auditBrokerProject/not.json",
        data:{"id":id, "reason":reason},
        async: false,
        success: function(data) {
          if(data.state == 0){
            app.msg("操作成功！", 0);
            layer.close(index);
            app.clickResources("${pageContext.request.contextPath}/pageBrokerProject/pageAudit.html", "");
          }else{
            app.msg(data.msg, 1);
          }
        }
      });
    });
  }
</script>