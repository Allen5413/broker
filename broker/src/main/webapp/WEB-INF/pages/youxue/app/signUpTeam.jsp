<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="common/meta.jsp"%>
  <%@ include file="common/taglibs.jsp"%>
</head>
<body>
<header>
  <div class="header">
    <div class="ct-lf">
      <a href="javascript:;" onclick="history.go(-1);return false;"><i class="i-back"></i></a>
    </div>
    <div class="tit">跟着校花去游学</div>
  </div>
</header>
<section class="section wp-t44">
  <div class="at-wrap">
    <div class="form-apply-view">
      <div class="form-item-cell">
        <div class="tit-line">团队信息</div>
        <ul class="ul-info">
          <li>
            <div class="tag-cel">校花团长</div>
            <div class="txt-cel bline"><span class="uname">${teamHead.realname}</span><img class="uhpic" src="${teamHead.icon}"></div>
          </li>
          <li>
            <div class="tag-cel">项目名称</div>
            <div class="txt-cel bline">${product.name}</div>
          </li>
          <li>
            <div class="tag-cel">报名人数</div>
            <div class="txt-cel bline">${teamNum} 人</div>
          </li>
        </ul>
      </div>
      <div class="form-item-cell">
        <div class="tit-line">个人信息</div>
        <ul class="ul-info">
          <li>
            <div class="tag-cel">姓 名</div>
            <div class="txt-cel bline">${team.realname}</div>
          </li>
          <li>
            <div class="tag-cel">学 校</div>
            <div class="txt-cel bline">${team.sname}</div>
          </li>
          <li>
            <div class="tag-cel">手 机</div>
            <div class="txt-cel bline">${team.mobile}</div>
          </li>
          <li>
            <div class="tag-cel">经纪人</div>
            <div id="brokerNameDiv" class="txt-cel bline">${empty broker.realname ? "未关联" : broker.realname}</div>
          </li>
        </ul>
      </div>
      <div class="form-item-cell">
        <div class="tit-line">预约咨询</div>
        <ul class="ul-info">
          <li>
            <div class="tag-cel">预约时间</div>
            <div class="txt-cel bline" onclick="changeDate()">
              <input type="text" name="yyDateText" id="yyDateText" readonly class="inputTxt" placeholder="请选择预约时间" />
            </div>
          </li>
        </ul>
        <div class="submit-btn">
          <a class="btnCom" href="javascript:;" onclick="sub()">提 交</a>
          <p><input id="cb1" type="checkbox" class="checkbox-1">我已阅读并同意上述全部条款</p>
          <p><input id="cb2" type="checkbox" class="checkbox-1">团队人数不够，同意与其他团合并</p>
        </div>
      </div>
    </div>
  </div>
</section>
<form id="form" name="form" action="${pageContext.request.contextPath}/youxueApp/signUpTeam/signUp.json" method="post">
  <input type="hidden" id="yyDate" name="yyDate" />
  <input type="hidden" id="teamHeadId" name="teamHeadId" value="${teamHead.id}" />
  <input type="hidden" id="brokerZz" name="brokerZz" value="${broker.zz}" />
</form>
<div id="loginDiv" class="layer-trans" style="display: ${isLogin ? 'block' : 'none'}">
  <div class="pop-tips-txt">
    <div class="title">您未登录，请登录至善账号</div>
    <div class="pop-input-view">
      <p class="item-input">
        <span class="i-tg">用户名：</span>
        <input type="text" id="loginName">
      </p>
      <p class="item-input">
        <span class="i-tg">密码：</span>
        <input type="password" id="loginPwd">
      </p>
      <p><button class="but-submit" onclick="login()">登 录</button></p>
    </div>
  </div>
</div>
</body>
</html>
<script>
  setTimeout(function(){hideButtom();}, 500);

  $(function() {
    $(function() {
      var currYear = (new Date()).getFullYear();
      var opt = {};
      opt.date = {
        preset: 'date'
      };
      opt.datetime = {
        preset: 'datetime'
      };
      opt.time = {
        preset: 'time'
      };
      opt.default = {
        theme: 'android-ics light', //皮肤样式
        display: 'modal', //显示方式
        mode: 'scroller', //日期选择模式
        lang: 'zh',
        startYear: currYear - 10, //开始年份
        endYear: currYear + 10, //结束年份
        minDate: new Date(),
        onSelect:function(valueText,inst){
          //$("#year").val(valueText);
        }
      };
      $("#appDate").val('').scroller('destroy').scroller($.extend(opt['date'], opt['default']));
      var optDateTime = $.extend(opt['datetime'], opt['default']);
      $("#yyDateText").mobiscroll(optDateTime).datetime(optDateTime);
    });
  });

  function sub(){
    if(!$("#cb1").is(":checked") || !$("#cb2").is(":checked")){
      layer.alert("请勾选下方2个条款", {icon: 5});
      return false;
    }
    $("#yyDate").val($("#yyDateText").val());
    if($("#yyDate").val() == ""){
      layer.alert("请输入预约咨询时间", {icon: 5});
      return false;
    }
    if($("#teamHeadId").val() == ""){
      layer.alert("请选择团长", {icon: 5});
      return false;
    }
    if($("#brokerZz").val() == ""){
      layer.alert("请到 我的-我的经纪人 去选择经纪人", {icon: 5});
      return false;
    }
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/youxueApp/signUpTeam/signUp.json",
      data:$("#form").serialize(),
      async: false,
      success: function(data) {
        if(data.state == 0){
          layer.msg("您已报名成功", {icon: 6});
        }else{
          layer.alert(data.msg, {icon: 5});
        }
      }
    });
  }

  function changeDate(){
    $("#yyDateText").click();
  }

  function login(){
    var loginName = $("#loginName").val().trim();
    var pwd = $("#loginPwd").val().trim();
    if(loginName == ""){
      layer.alert("请输入用户名", {icon: 5});
      return false;
    }
    if(pwd == ""){
      layer.alert("请输入密码", {icon: 5});
      return false;
    }
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/youxueApp/loginApp.json",
      data:{"loginName":loginName, "pwd":pwd},
      async: false,
      success: function(data) {
        if(data.state == 0){
          history.go(0);
          return false;
        }else{
          layer.alert(data.msg, {icon: 5});
        }
      }
    });
  }
</script>
