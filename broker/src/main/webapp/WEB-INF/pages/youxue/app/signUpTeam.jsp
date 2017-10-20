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
            <div class="txt-cel bline"><span class="uname">${teamHead.nickname}</span><img class="uhpic" src="${teamHead.icon}"></div>
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
            <div class="txt-cel bline">${tean.sname}</div>
          </li>
          <li>
            <div class="tag-cel">手 机</div>
            <div class="txt-cel bline">${tean.mobile}</div>
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
<c:if test="${empty broker.zz}">
  <div id="selectBrokerDiv" class="layer-trans">
    <div class="pop-tips-txt">
      <div class="title">关联经纪人</div>
      <div class="pop-input-view">
        <p class="item-input">
          <input type="text" id="brokerZzText" placeholder="输入经纪人ZZ号">
        </p>
        <div class="pop-ags-tagcell">
          <p>推荐经纪人<a class="f-r" href="javascript:;" onclick="huan()">换一换</a></p>
          <div id="brokerDiv" class="pop-cells">
            <c:forEach var="broker" items="${brokerList}">
              <a href="javascript:;" onclick="selectBroker('${broker.zz}', '${broker.name}')">${broker.name}（${broker.mobile}）</a>
            </c:forEach>
          </div>
        </div>
        <p class="alignCenter"><button class="but-submit" onclick="enterBroker()">确定关联</button></p>
      </div>
    </div>
  </div>
</c:if>
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

  function huan(){
    $.ajax({
      url:"${pageContext.request.contextPath}/recommendBroker/randomBroker.json",
      method : 'POST',
      async:false,
      data:{},
      success:function(data){
        if(data.state == 0){
          $("#brokerDiv").html("");
          var list = data.list;
          if(list.length > 0){
            var html = "";
            for(var i=0; i<list.length; i++){
              var broker = list[i];
              html += "<a href='javascript:;' onclick='selectBroker('"+broker.zz+"', '"+broker.name+"')'>"+broker.name+"（"+broker.mobile+"）</a>";
            }
            $("#brokerDiv").html(html);
          }
        }else {
          app.alert(data.msg, 1);
        }
      }
    });
  }

  function selectBroker(zz, name){
    $("#brokerZz").val(zz);
    $("#brokerNameDiv").html(name);
    $("#selectBrokerDiv").hide();
  }

  function enterBroker(){
    var zz = $("#brokerZzText").val();
    if(zz == ""){
      app.alert("请输入经纪人ZZ号", 1);
      return false;
    }
    $.ajax({
      url:"${pageContext.request.contextPath}/findBrokerByZz/findForJSON.json",
      method : 'POST',
      async:false,
      data:{"zz":zz},
      success:function(data){
        if(data.state == 0){
          if(null == data.broker){
            app.alert("您输入的ZZ号目前还不是经纪人", 1);
            return false;
          }
          $("#brokerZz").val(data.broker.zz);
          $("#brokerNameDiv").html(data.broker.realname);
          $("#selectBrokerDiv").hide();
        }else {
          app.alert(data.msg, 1);
        }
      }
    });
  }
</script>
