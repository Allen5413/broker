<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">团长风采</div>
  <ul class="search-view">
    <form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/youxue/pageTeam/page.html">
      <input type="hidden" id="rows" name="rows" />
      <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
      <li>
        <span class="itg">ZZ：</span>
        <span class="inline-select">${team.zz}</span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="itg">昵称：</span>
        <span class="inline-select">${team.nickname}</span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="itg">手机号：</span>
        <span class="inline-select">${team.mobile}</span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="itg">QQ：</span>
        <span class="inline-select">${team.qq}</span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="itg">学校：</span>
        <span class="inline-select">${team.sname}</span>&nbsp;&nbsp;&nbsp;&nbsp;
      </li>
    </form>
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
          <c:if test="${empty imgMap}">
            <tr>
              <td colspan="999" style="text-align: center; color: red">没有上传照片</td>
            </tr>
          </c:if>
          <c:if test="${!empty imgMap}">
            <div class="pics-view">
              <c:forEach var="imgUrl" items="${imgMap}" >
                <c:forEach var="url" items="${imgUrl.value}">
                  <div class="pic-cel"><img src="${url[0]}"><a class="delete" href="#">删除</a></div>
                </c:forEach>
              </c:forEach>
            </div>
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