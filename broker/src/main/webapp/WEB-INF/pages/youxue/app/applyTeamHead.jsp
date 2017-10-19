<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
      <a href="#" onclick="history.go(-1);"><i class="i-back"></i></a>
    </div>
    <div class="tit">我要当校花团长</div>
  </div>
</header>
<section class="section wp-t44">
  <div class="at-wrap">
    <div class="apply-content ap-captain">
      <div class="banner-pic"><img src="/youxue/images/banner-j2.png"></div>
      <div class="text-view">
        <p>选择一个远方的目标</p>
        <p>引领数十个团友</p>
        <p>挥一挥手，全世界，跟你走……<br></p>
        <p>有自信，就亮出你的旗号</p>
        <p>经纪人帮你招募团友</p>
        <p>达到20人即组团成功</p>
        <p>德行免费送你游</p>
        <div class="team-select-view">
          <div class="title">请选择一个经纪人，联系他</div>
          <ul id="brokerInfoUl" class="agents-item-list">
            <c:forEach var="broker" items="${brokerList}">
              <li class="item-cell">
                <div class="uh"><img src="${broker.icon}"></div>
                <div class="uinfo">
                  <p class="p-tit">${broker.name}<span class="f-14">（${broker.sName}）</span></p>
                  <p><i class="i-qq"></i>${broker.qq} <i class="i-tel"></i>${broker.mobile}</p>
                </div>
              </li>
            </c:forEach>
          </ul>
          <div class="opr-change"><a href="#" onclick="randomBroker()">换一批</a></div>
        </div>
      </div>
    </div>
  </div>
</section>
</body>
</html>
<script>
  function randomBroker(){
    $.ajax({
      url:"${pageContext.request.contextPath}/recommendBroker/randomBroker.json",
      method : 'POST',
      async:false,
      data:{},
      success:function(data){
        if(data.state == 0){
          $("#brokerInfoUl").html("");
          var list = data.list;
          if(list.length > 0){
            var html = "";
            for(var i=0; i<list.length; i++){
              var broker = list[i];
              html += "<li class='item-cell'>";
              html += "<div class='uh'><img src='"+broker.icon+"'></div>";
              html += "<div class='uinfo'>";
              html += "<p class='p-tit'>"+broker.name+"<span class='f-14'>（"+broker.sName+"）</span></p>";
              html += "<p><i class='i-qq'></i>"+broker.qq+"<i class='i-tel'></i>"+broker.mobile+"</p>";
              html += "</div>";
              html += "</li>";
            }
            $("#brokerInfoUl").html(html);
          }
        }else {
          app.msg(data.msg, 1);
        }
      }
    });
  }
</script>