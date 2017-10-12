<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">编辑项目</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="form" name="form" action="${pageContext.request.contextPath}/editProject/editor.json">
        <input type="hidden" name="id" value="${project.id}" />
        <table class="set-table-info">
          <tr>
            <td class="tag-b">项目名称：</td>
            <td><input type="text" id="name" name="name" value="${project.name}" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">人数比例：</td>
            <td><input type="text" id="ratio" name="ratio" value="${project.ratio}" class="input-txt-220" />%</td>
          </tr>
          <tr>
            <td class="tag-b">访问频次：</td>
            <td><input type="text" id="frequency" name="frequency" value="${project.frequency}" class="input-txt-220" />天</td>
          </tr>
          <tr>
            <td class="tag-b">审批模式：</td>
            <td>
              <label><input type="radio" name="auditType" value="0" <c:if test="${project.auditType == 0}">checked="checked"</c:if> > 自动 </label>
              <label><input type="radio" name="auditType" value="1" <c:if test="${project.auditType == 1}">checked="checked"</c:if> > 手动 </label>
            </td>
          </tr>
          <tr>
            <td class="tag-b"></td>
            <td>
              <a class="btn-com" href="#" onclick="addProject();">保存提交</a>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</div>
<script>
  function addProject(){
    if($("#name").val() == ""){
      app.alert("请输入名称！", 1);
      return false;
    }
    app.edit("${pageContext.request.contextPath}/editProject/editor.json", $("#form").serialize(), "${pageContext.request.contextPath}/pageProject/page.html", "");
  }
</script>