<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br />
<table style="width: 100%">
  <tr style="height: 50px;">
    <td style="text-align: center">
      <a class="btn-opr" href="#" onclick="cancel();">取消</a>
    </td>
  </tr>
  <tr style="height: 50px;">
    <td style="text-align: center">
      <a class="btn-opr" href="#" onclick="six();">拉黑6个月</a>
    </td>
  </tr>
  <tr style="height: 50px;">
    <td style="text-align: center">
      <a class="btn-opr" href="#" onclick="forever();">永久拉黑</a>
    </td>
  </tr>
</table>
<script>
  function cancel(){
    app.confirm("您确定要取消该经纪人的项目？", function(){
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/blackBrokerProject/cancel.json",
        data:{"zz":"${param.zz}", "projectId":"${param.projectId}"},
        async: false,
        success: function(data) {
          if(data.state == 0){
            app.msg("操作成功！", 0);
            layer.closeAll();
          }else{
            app.msg(data.msg, 1);
          }
        }
      });
    });
  }

  function six(){
    app.confirm("您确定要拉黑6个月该经纪人的项目？", function(){
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/blackBrokerProject/sixMonth.json",
        data:{"zz":"${param.zz}", "projectId":"${param.projectId}"},
        async: false,
        success: function(data) {
          if(data.state == 0){
            app.msg("操作成功！", 0);
            layer.closeAll();
          }else{
            app.msg(data.msg, 1);
          }
        }
      });
    });
  }

  function forever(){
    app.openDialog("${pageContext.request.contextPath}/blackBrokerProject/openReason.html?zz=${param.zz}", "永久拉黑", "400", "300", function(index){
      var reason = $("#reason").val().trim();
      if(reason == "" || reason.length < 20) {
        app.msg("请输入不低于20个字的拉黑原因", 1);
        return false;
      }
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/blackBrokerProject/forever.json",
        data:{"zz":"${param.zz}", "projectId":"${param.projectId}", "reason":reason},
        async: false,
        success: function(data) {
          if(data.state == 0){
            layer.closeAll();
            app.msg("操作成功！", 0);
          }else{
            app.msg(data.msg, 1);
          }
        }
      });
    });
  }
</script>
