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
      <a href="#" onclick="history.go(-1);"><i class="i-back"></i></a>
    </div>
    <div class="tit">团队</div>
  </div>
</header>
<section class="section wp-t44">
  <div class="at-wrap">
    <div class="team-theme-view">
      <img src="/youxue/images/item/item-0${product.id}.png">
      <div class="team-cel">
        <div class="uh-pic"><img src="${team.icon}"></div>
        <div class="tm-info">
          <p class="tit">${team.realname} <span class="fxx">(${team.sname})</span></p>
          <p class="from">
            ${product.name}
          </p>
          <p class="from">咨询QQ群：234562345<a class="btn-go f-r" href="${pageContext.request.contextPath}/youxueApp/signUpTeam/open.html?productId=${product.id}&teamHeadId=${team.id}">去报名</a></p>
        </div>
      </div>
    </div>
    <ul class="tabs-title">
      <li class="tab on"><a href="javascript:;">行程</a></li>
      <li class="tab"><a href="javascript:;">团长风采</a></li>
      <li class="tab"><a href="javascript:;">团员</a></li>
    </ul>
    <div id="bdmod">
      <div class="bd-mod-view" style="display:block;">
        <div class="trip-team-cell">
          <div class="title">行程时间</div>
          <div class="txt-cell">
            <p>${productDate.date}</p>
          </div>
          <div class="title">行程安排</div>
          <div class="trip-list">
            ${product.trip}
          </div>
        </div>
      </div>
      <div class="bd-mod-view">
        <div class="pics-team-cell">
          <div class="intro">
            <p>${team.label}</p>
          </div>
          <div class="photos-list">
            <a class="pic" href="#"><img src="/youxue/images/xiupic1.jpg"></a>
            <a class="pic" href="#"><img src="/youxue/images/xiupic2.jpg"></a>
            <a class="pic" href="#"><img src="/youxue/images/xiupic3.jpg"></a>
            <a class="pic" href="#"><img src="/youxue/images/xiupic2.jpg"></a>
            <a class="pic" href="#"><img src="/youxue/images/xiupic1.jpg"></a>
            <a class="pic" href="#"><img src="/youxue/images/xiupic3.jpg"></a>
          </div>
        </div>
      </div>
      <div class="bd-mod-view">
        <div class="member-team-cell">
          <c:forEach var="team" items="${teamList}">
            <div class="member-cell">
              <div class="upic"><img src="${team.icon}"></div>
              <div class="uinfo">
                <p class="tit">${team.name}</p>
                <p>${team.sName}</p>
              </div>
            </div>
          </c:forEach>
        </div>
      </div>
    </div>
  </div>
</section>
<script>
  $(".tabs-title").on("click","li",function(){
    // 设index为当前点击
    var index = $(this).index();
    // 点击添加样式利用siblings清除其他兄弟节点样式
    $(this).addClass("on").siblings().removeClass("on");
    // 同理显示与隐藏
    $("#bdmod").find(".bd-mod-view").eq(index).show().siblings().hide();
  })
</script>
</body>
</html>
