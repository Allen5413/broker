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
    <div class="tit">添加经纪人</div>
  </div>
</header>
<section class="section wp-t50">
  <div class="at-wrap">
    <div class="w-info-view tips-bod">
      <p>您未关联经纪人，请输入经纪人ZZ关联</p>
      <p><input type="text" class="inputTxt" id="brokerZzText" placeholder="输入ZZ"> <button class="btn-inline" onclick="enterBroker()">关 联</button></p>
    </div>
    <div id="brokerDiv" class="w-items agents-view">
      <div class="title bline">推荐经纪人<c:if test="${brokerNum > 10}"><a class="f-r" href="javascript:;" onclick="huan()">换一换</a></c:if></div>
    </div>
  </div>
</section>
<div class="layer-trans" id="subBrokerDiv" style="display: none;">
  <div class="pop-tips-txt pop-uinfo">
    <div class="close-x" onclick="$('#subBrokerDiv').hide()">×</div>
    <div class="title">确定经纪人</div>
    <div class="pop-input-view">
      <div class="pop-uh-text">
        <div class="uhead"><img id="brokerPic" src="images/pic3.png"></div>
        <div class="uinfo">
          <p id="brokerName">张三丰</p>
          <p id="brokerSName">重庆工商大学</p>
          <p id="brokerCustomerNum"><i class="i-sum"></i>123</p>
        </div>
      </div>
    </div>
    <input type="hidden" id="brokerZz" />
    <div><a class="btnCom" href="javascript:;" onclick="sub()">确定关联</a></div>
  </div>
</div>
</body>
</html>
<script>
  setTimeout(function(){hideButtom();}, 500);
  var brokerDivHtml = $("#brokerDiv").html();
  $(function(){
    　huan();
  });

  /**
  *随机取10个经纪人显示
   */
  function huan(){
    $("#brokerDiv").html(brokerDivHtml);
    var brokerNum = "${brokerNum}";
    var brokerIndexs = "";
    if(brokerNum > 10){
      for(var i = 0; i<10; i++){
        var random = Math.ceil(Math.random()*brokerNum);
        if(-1 < brokerIndexs.indexOf(random+",")){
          i--;
        }else{
          brokerIndexs += random+",";
        }
      }
    }else{
      for(var i = 1; i<=brokerNum; i++){
        brokerIndexs += i+",";
      }
    }
    if("" != brokerIndexs) {
      brokerIndexs = brokerIndexs.substring(0, brokerIndexs.length - 1);
      $.ajax({
        url:"${pageContext.request.contextPath}/youxueApp/findMyBroker/findRndomBroker.json",
        method : 'POST',
        async:false,
        data:{"indexs":brokerIndexs},
        success:function(data){
          if(data.state == 0 && 0 < data.list.length){
            for(var i=0; i<data.list.length; i++){
              var broker = data.list[i];
              var html = "<div class=\"w-item bline\" onclick=\"selectBroker('"+broker.zz+"', '"+broker.icon+"', '"+broker.realname+"', '"+broker.sname+"', "+broker.customerNum+")\">";
              html += "<div class=\"info-cel\">";
              html += "<img class=\"u-hd\" src=\""+broker.icon+"\">";
              html += "<div class=\"u-nam\">";
              if(broker.sname != "") {
                html += "<p class=\"tit\">" + broker.realname + "<span class=\"fs\">"+broker.sname+"</span></p>";
              }else{
                html += "<p class=\"tit\">" + broker.realname + "<span class=\"fs\"></span></p>";
              }
              html += "<p>";
              html += "<i class=\"i-sum\"></i>"+broker.customerNum;
              html += "</p>";
              html += "</div>";
              html += "</div>";
              html += "</div>";
              $("#brokerDiv").append(html);
            }
          }else {
            layer.alert(data.msg, {icon: 5});
          }
        }
      });
    }
  }

  function selectBroker(zz, pic, name, sname, num){
    $("#brokerPic").attr("src", pic);
    $("#brokerName").html(name);
    $("#brokerSName").html(sname);
    $("#brokerCustomerNum").html("<i class='i-sum'></i>"+num);
    $("#brokerZz").val(zz);
    $("#subBrokerDiv").show();
  }

  function enterBroker(){
    var zz = $("#brokerZzText").val();
    if(zz == ""){
      layer.alert("请输入经纪人ZZ号", {icon: 5});
      return false;
    }
    $.ajax({
      url:"${pageContext.request.contextPath}/findBrokerByZz/findForJSON.json",
      method : 'POST',
      async:false,
      data:{"zz":zz},
      success:function(data){
        if(data.state == 0){
          var broker = data.broker;
          if(null != broker) {
            $("#brokerPic").attr("src", broker.icon);
            $("#brokerName").html(broker.realname);
            $("#brokerSName").html(broker.sname);
            $("#brokerCustomerNum").html("<i class='i-sum'></i>" + broker.customerNum);
            $("#brokerZz").val(broker.zz);
            $("#subBrokerDiv").show();
          }else{
            layer.alert("你输入的zz号没有找到经纪人", {icon: 5});
          }
        }else {
          layer.alert(data.msg, {icon: 5});
        }
      }
    });
  }

  function sub(){
    var zz = $("#brokerZz").val();
    if("" == zz){
      layer.alert("请选择经纪人", {icon: 5});
      return false;
    }
    $.ajax({
      url:"${pageContext.request.contextPath}/youxueApp/addMyBroker/add.json",
      method : 'POST',
      async:false,
      data:{"brokerZz":zz},
      success:function(data){
        if(data.state == 0){
          location.href = "${pageContext.request.contextPath}/youxueApp/findMyBroker/open.html";
        }else {
          layer.alert(data.msg, {icon: 5});
        }
      }
    });
  }
</script>