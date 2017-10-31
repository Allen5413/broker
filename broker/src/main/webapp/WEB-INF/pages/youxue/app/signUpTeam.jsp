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
            <div class="txt-cel bline"><input type="text" id="yyDateText" class="inputTxt"></div>
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
</body>
</html>
<script>
  setTimeout(function(){hideButtom();}, 500);
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
</script>
