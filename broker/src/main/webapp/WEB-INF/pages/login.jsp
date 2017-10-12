<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="common/meta.jsp"%>
<%@ include file="common/taglibs.jsp"%>
</head>
<script type="text/javascript">
  $(function(){
    //回车事件
    document.onkeydown = function(e){
      var ev = document.all ? window.event : e;
      if(ev.keyCode==13) {
        sub();
      }
    }
  });

  function sub(){
    var loginName = $.trim($("#loginName").val());
    var pwd = $.trim($("#pwd").val());
    if(loginName == ""){
      $("#msg").html("请输入用户名");
    }
    else if(pwd == ""){
      $("#msg").html("请输入密码");
    }else{
      var params = {
        "loginName":loginName,
        "pwd":pwd
      };
      $.ajax({
        url:"${pageContext.request.contextPath}/login.json",
        method : 'POST',
        async:false,
        data:params,
        success:function(data){
          if(data.state == 0){
            location.href = "${pageContext.request.contextPath}/openIndex.html";
          }else {
            $("#msg").html(data.msg);
          }
        }
      });
    }
  }
</script>
<body>
<div class="header-view">
  <div class="title">成人继续教育管理平台</div>
</div>
<div id="main_page" class="section-view">
  <div class="logon-view">
    <div class="title">后台管理员登录</div>
    <ul class="input-item-list">
      <li>
        <label class="i-tg">用户名：</label>
        <input type="text" class="reg-input-unam" id="loginName" />
      </li>
      <li>
        <label class="i-tg">密 码：</label>
        <input type="password" class="reg-input-pwd" id="pwd" />
      </li>
      <li><label class="i-tg"></label><span id="msg" style="color: red"></span></li>
      <li class="bar-but">
        <a class="reg-btn-1" href="#" onclick="sub()">登 录</a>
      </li>
    </ul>
  </div>
</div>
</body>
</html>