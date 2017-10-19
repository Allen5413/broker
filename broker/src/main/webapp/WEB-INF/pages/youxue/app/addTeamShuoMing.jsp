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
    <div class="tit">跟着校花去游学</div>
  </div>
</header>
<section class="section wp-t44">
  <div class="at-wrap">
    <div class="form-apply-view">
      <div class="welcom-txt">欢迎词</div>
      <div class="form-item-cell ul-pdl">
        <div class="title">游学出行流程</div>
        <div class="steps-list">
          <div class="step-text">
            <div class="step">1</div>
            <div class="text">
              <p>在线申请</p>
              <p>提交个人信息，选择团队</p>
            </div>
          </div>
          <div class="step-text">
            <div class="step">2</div>
            <div class="text">
              <p>电话测试</p>
              <p>德行按预约时间电话沟通，考察申请者状态，确保符合出行条件</p>
            </div>
          </div>
          <div class="step-text">
            <div class="step">3</div>
            <div class="text">
              <p>录取通知</p>
              <p>电话测试后两个工作日，以邮件或电话的形式通知是否录取</p>
            </div>
          </div>
          <div class="step-text">
            <div class="step">4</div>
            <div class="text">
              <p>缴费报名</p>
              <p>学员在收到录取通知后要按照录取通知书上的时间进行缴费</p>
            </div>
          </div>
          <div class="step-text">
            <div class="step">5</div>
            <div class="text">
              <p>签订协议</p>
              <p>缴费后公司将给每位学员邮寄收款凭证、项目协议、酒店订单、保险单、和旅行服务包等</p>
            </div>
          </div>
          <div class="step-text">
            <div class="step">6</div>
            <div class="text">
              <p>整装出发</p>
              <p>出发前将发送给学员“行前指南”提醒学员行前需准备的相关事宜</p>
            </div>
          </div>
        </div>
        <c:if test="${!empty param.teamHeadId}">
          <div class="submit-btn">
            <a class="btnCom" href="${pageContext.request.contextPath}/youxueApp/signUpTeam/openSignUp.html?productId=${product.id}&teamHeadId=${param.teamHeadId}">下一步</a>
          </div>
        </c:if>
        <c:if test="${empty param.teamHeadId}">
          <div class="submit-btn">
            <a class="btnCom" href="javascript:;" onclick="selectTeam()">去选择团队</a>
          </div>
        </c:if>
      </div>
    </div>
  </div>
</section>
<form id="form" name="form" action="${pageContext.request.contextPath}/youxueApp/findTeam/find.html" method="post">
  <input type="hidden" id="productId" name="productId" value="${product.id}" />
  <input type="hidden" id="productName" name="productName" value="${product.name}" />
</form>
</body>
</html>
<script>
  function selectTeam(){
    $("#form").submit();
  }
</script>