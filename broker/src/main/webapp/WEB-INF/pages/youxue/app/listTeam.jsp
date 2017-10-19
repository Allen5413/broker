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
    <div class="teams-itm-view">
      <div class="sift-cell" >
        <a id="sift-ar" href="#">${empty param.productName ? "全部" : param.productName} <i class="i-arr"></i></a>
      </div>
      <ul class="list-item">
        <c:forEach var="team" items="${list}">
          <li>
            <a class="team-cel" href="team/team-detail-01.html">
              <div class="uh-pic"><img src="${team.icon}"></div>
              <div class="tm-info">
                <p class="tit">${team.name} <span class="fxx">(${team.sName})</span></p>
                <p class="from">
                  ${team.pName}
                  <span class="num">${team.teamNum+1} 人</span>
                </p>
              </div>
            </a>
          </li>
        </c:forEach>
      </ul>
    </div>
  </div>
  <div class="footer">
    <ul class="nav-tabs">
      <li><a href="${pageContext.request.contextPath}/youxueApp/index/open.html?zz=${sessionScope.loginName}">首页</a></li>
      <li><a href="${pageContext.request.contextPath}/youxueApp/findProduct/open.html">项目</a></li>
      <li><a class="on" href="javascript:;">团队</a></li>
      <li><a href="w-user.html">我的</a></li>
    </ul>
  </div>
</section>
<div id="show-select" class="layer-trans" style="display:none;">
  <div class="pop-txt-view">
    <ul class="select-item">
      <li onclick="changeProduct('全部', '')">全部<i class="ico-selected"></i></li>
      <c:forEach var="product" items="${produceList}">
        <li onclick="changeProduct('${product.name}', ${product.id})">${product.name}<i class="ico-selected"></i></li>
      </c:forEach>
    </ul>
  </div>
</div>
<form id="form" name="form" action="${pageContext.request.contextPath}/youxueApp/findTeam/find.html" method="post">
  <input type="hidden" id="productId" name="productId" />
  <input type="hidden" id="productName" name="productName" />
</form>
</body>
</html>
<script>
  var modal = document.getElementById("show-select");
  var btn = document.getElementById("sift-ar");
  btn.onclick = function(){
    modal.style.display = "block";
  }
  modal.onclick = function(){
    modal.style.display = "none";
  }

  function changeProduct(name, id){
    $("#productId").val(id);
    $("#productName").val(name);
    $("#form").submit();
  }
</script>