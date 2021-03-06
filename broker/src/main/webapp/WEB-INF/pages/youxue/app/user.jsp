<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="common/meta.jsp"%>
  <%@ include file="common/taglibs.jsp"%>
</head>
<body>
<section class="section wp-t50">
  <div class="at-wrap">
    <div class="w-info-view">
      <div class="uh-cel">
        <img src="${user.icon}">
      </div>
      <div class="uinfo-cel">
        <p class="tit">${user.nickname}</p>
        <p>${user.sname}</p>
      </div>
    </div>
    <div class="w-ico-item">
      <c:if test="${isHead}">
        <a class="li-cell" href="${pageContext.request.contextPath}/youxueApp/uploadMyImg/openCover.html">
          <div class="tag-cel"><i class="i-w6"></i>我的形象照</div>
          <div class="txt-cel">
            <c:if test="${!empty coverImgUrl}">
              <img src="${coverImgUrl}">
            </c:if>
            <i class="i-arr"></i>
          </div>
        </a>
        <a class="li-cell" href="${pageContext.request.contextPath}/youxueApp/uploadMyImg/open.html">
          <div class="tag-cel"><i class="i-w2"></i>我的相册</div>
          <div class="txt-cel">
            <c:if test="${!empty imgUrl}">
              <img src="${imgUrl}">
            </c:if>
            <i class="i-arr"></i>
          </div>
        </a>
        <a class="li-cell" href="${pageContext.request.contextPath}/youxueApp/editTeamLabel/open.html">
          <div class="tag-cel"><i class="i-w3"></i>自我介绍</div>
          <div class="txt-cel"><i class="i-arr"></i></div>
        </a>
      </c:if>
      <a class="li-cell" href="${pageContext.request.contextPath}/youxueApp/findMyBroker/open.html">
        <div class="tag-cel"><i class="i-w4"></i>我的游学经纪人</div>
        <div class="txt-cel">
          <c:if test="${!empty broker.icon}">
            <img src="${broker.icon}">
          </c:if>
          <i class="i-arr"></i>
        </div>
      </a>
    </div>
    <div class="w-ico-item">
      <a class="li-cell" href="javascript:;" onclick="$('#fenxiangDiv').show();">
        <div class="tag-cel"><i class="i-w5"></i>分享</div>
        <div class="txt-cel"><i class="i-arr"></i></div>
      </a>
    </div>
    <c:forEach var="team" items="${teamList}">
      <div class="w-ico-item">
        <a class="li-cell" href="javascript:;" onclick="location.href='${pageContext.request.contextPath}/youxueApp/findTeam/findInfo.html?teamId=${team.id}'">
          <div class="tag-cel"><i class="i-w1"></i>${team.pName}</div>
          <div class="txt-cel">
            <c:if test="${team.isHead == 1}">校花团长<i class="i-arr"></i></c:if>
            <c:if test="${team.isHead == 0}">
              <c:if test="${team.state == 0}">已报名</c:if>
              <c:if test="${team.state == 1}">资料待审核</c:if>
              <c:if test="${team.state == 2}">资料审核通过</c:if>
              <c:if test="${team.state == 3}">资料审核不通过</c:if>
              <c:if test="${team.state == 4}">缴费报名成功</c:if>
            </c:if>
          </div>
        </a>
      </div>
    </c:forEach>
  </div>
  <div class="footer">
    <ul class="nav-tabs">
      <li><a href="${pageContext.request.contextPath}/youxueApp/index/open.html?zz=${sessionScope.loginName}&projectId=${sessionScope.projectId}&notCount=0">首页</a></li>
      <li><a href="${pageContext.request.contextPath}/youxueApp/findProduct/open.html">项目</a></li>
      <li><a href="${pageContext.request.contextPath}/youxueApp/findTeam/find.html">团长</a></li>
      <li><a class="on" href="javascript:;">我的</a></li>
    </ul>
  </div>
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
</section>
<div id="fenxiangDiv" class="layer-trans" style="display: none;">
  <div class="pop-tips-txt">
    <div class="close-x" onclick="$('#fenxiangDiv').hide()">×</div>
    <div class="title">复制以下链接，快发朋友圈分享吧</div>
    <div class="pop-input-view">
      <p class="item-input"></p>
      <div class="pop-ags-tagcell">
        <p>
          <div class="text-area bline">
            <textarea id="fenxiangUrl" readonly="readonly">http://localhost:8080/youxueApp/fenXiangYx/fenxiang.html?brokerZz=${sessionScope.loginName}</textarea>
          </div>
        </p>
        <div class="pop-cells"></div>
      </div>
      <%--<p class="alignCenter"><button class="but-submit" onclick="copyUrl('http://localhost:8080/youxueApp/fenXiangYx/fenxiang.html?brokerZz=${sessionScope.loginName}')">点此复制</button></p>--%>
    </div>
  </div>
</div>
</body>
</html>
<script>
  setTimeout(function(){hideButtom();}, 500);

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

  function copyUrl(){
    var url = document.getElementById("fenxiangUrl");
    url.select(); // 选择对象
    document.execCommand("Copy"); // 执行浏览器复制命令
    layer.msg("复制成功!", {icon: 6});
  }
</script>
