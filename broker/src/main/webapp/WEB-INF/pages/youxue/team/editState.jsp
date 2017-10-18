<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="editForm" name="editForm" action="${pageContext.request.contextPath}/youxue/editTeam/editState.json">
  <input type="hidden" name="id" value="${team.id}" />
  <table class="set-table-info">
    <tr>
      <td class="tag-b" style="width: 50px;">状态：</td>
      <td>
        <select id="state" name="state">
          <option value="0" <c:if test="${team.state eq '0'}">selected="selected"</c:if> >已报名</option>
          <option value="1" <c:if test="${team.state eq '1'}">selected="selected"</c:if> >待审核</option>
          <option value="2" <c:if test="${team.state eq '2'}">selected="selected"</c:if> >审核通过</option>
          <option value="3" <c:if test="${team.state eq '3'}">selected="selected"</c:if> >审核未通过</option>
          <option value="4" <c:if test="${team.state eq '4'}">selected="selected"</c:if> >已缴费</option>
        </select>
      </td>
    </tr>
    <tr>
      <td class="tag-b" style="width: 50px;">备注：</td>
      <td><textarea id="remark" name="remark" class="textarea-intro" rows="3" style="width: 240px;"></textarea></td>
    </tr>
  </table>
</form>
