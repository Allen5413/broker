<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="editForm" name="editForm" action="${pageContext.request.contextPath}/youxue/editTeam/editQq.json">
  <input type="hidden" name="id" value="${team.id}" />
  <table class="set-table-info">
    <tr>
      <td class="tag-b" style="width: 100px;">咨询QQ群：</td>
      <td>
        <input type="text" name="qq" value="${team.qq}" />
      </td>
    </tr>
  </table>
</form>
