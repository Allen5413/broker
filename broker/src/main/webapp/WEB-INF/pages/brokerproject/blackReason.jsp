<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="set-table-info">
  <tr>
    <td colspan="2" class="tag-b">您正在对经纪人${name}，进行永久拉黑</td>
  </tr>
  <tr>
    <td class="tag-b">拉黑原因：</td>
    <td><textarea id="reason" name="reason" class="textarea-intro" rows="3" style="width: 240px;"></textarea>不能少于20个字</td>
  </tr>
</table>
