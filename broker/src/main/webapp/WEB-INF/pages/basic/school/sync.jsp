<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">学校同步</div>
  <ul class="search-view">
  </ul>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="title-tabs">
      <a href="#">学校同步</a>
    </div>
    <div class="mod-content">
      <div class="data-table-list">
        <a class="btn-com" href="#" onclick="syncSchool();">同步</a>
      </div>
    </div>
  </div>
</div>
<script>
  function syncSchool(){
    app.operator("您确定要同步？", "${pageContext.request.contextPath}/syncSchool/sync.json", "");
  }
</script>