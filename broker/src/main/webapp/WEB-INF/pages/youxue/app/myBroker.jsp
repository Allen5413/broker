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
    <div class="tit">我的游学经纪人</div>
  </div>
</header>
<section class="section wp-t50">
  <div class="at-wrap">
    <div class="w-info-view">
      <div class="uh-cel">
        <img src="${broker.icon}">
      </div>
      <div class="uinfo-cel">
        <p class="tit">${broker.realname}</p>
        <p>${broker.sname}</p>
      </div>
    </div>
    <div class="w-ico-item">
      <a class="li-cell" href="#">
        <div class="tag-cel">联系电话</div>
        <div class="txt-cel">${broker.mobile}</div>
      </a>
      <a class="li-cell" href="#">
        <div class="tag-cel">联系QQ</div>
        <div class="txt-cel">${broker.qq}</div>
      </a>
    </div>
  </div>
</section>
<c:if test="${empty broker}">
  <div id="selectBrokerDiv" class="layer-trans" >
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
  setTimeout(function(){hideButtom();}, 500);
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

  function selectBroker(zz){
    $.ajax({
      url:"${pageContext.request.contextPath}/youxueApp/addMyBroker/add.json",
      method : 'POST',
      async:false,
      data:{"brokerZz":zz},
      success:function(data){
        if(data.state == 0){
          location.href = "${pageContext.request.contextPath}/youxueApp/findMyBroker/open.html";
        }else {
          app.alert(data.msg, 1);
        }
      }
    });
  }

  function enterBroker(){
    var zz = $("#brokerZzText").val();
    if(zz == ""){
      app.alert("请输入经纪人ZZ号", 1);
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
          app.alert(data.msg, 1);
        }
      }
    });
  }
</script>