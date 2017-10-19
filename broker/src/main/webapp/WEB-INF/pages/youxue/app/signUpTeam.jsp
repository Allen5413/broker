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
      <a href="javascript:;" onclick="history.go(-1)"><i class="i-back"></i></a>
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
            <div class="txt-cel bline"><span class="uname">${teamHead.realname}</span><img class="uhpic" src="${teamHead.icon}"><i class="i-arr"></i></div>
          </li>
          <li>
            <div class="tag-cel">项目名称</div>
            <div class="txt-cel bline">${product.name}<i class="i-arr"></i></div>
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
            <div class="txt-cel bline">${tean.sname}</div>
          </li>
          <li>
            <div class="tag-cel">手 机</div>
            <div class="txt-cel bline">${tean.mobile}</div>
          </li>
        </ul>
      </div>
      <div class="form-item-cell">
        <div class="tit-line">预约咨询</div>
        <ul class="ul-info">
          <li>
            <div class="tag-cel">预约时间</div>
            <div class="txt-cel bline"><input type="text" id="yyDateText" class="inputTxt" placeholder="2017-10-18 00:00"></div>
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
  function sub(){
    if(!$("#cb1").is(":checked") || !$("#cb2").is(":checked")){
      app.alert("请勾选下方2个条款", 1);
      return false;
    }
    $("#yyDate").val($("#yyDateText").val());
    if($("#yyDate").val() == ""){
      app.alert("请输入预约咨询时间", 1);
      return false;
    }
    if($("#teamHeadId").val() == ""){
      app.alert("请选择团长", 1);
      return false;
    }
    if($("#brokerZz").val() == ""){
      app.alert("请选择经纪人", 1);
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
          app.msg("您已报名成功", 0);
        }else{
          app.alert(data.msg, 1);
        }
      }
    });
  }
</script>
