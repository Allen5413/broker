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
    <div class="tit">我要做游学经纪人</div>
  </div>
</header>
<section class="section wp-t44">
  <div class="at-wrap">
    <div class="apply-content ap-agent">
      <div class="banner-pic"><img src="/youxue/images/banner-j1.png"></div>
      <div class="text-view">
        <div class="theme-ft"></div>
        <p>传播游学理念，帮更多同学实现人生的飞跃</p>
        <p>展现魅力，锻炼能力，体验乐趣，回报丰厚</p>
        <p>Q群、微信、直播，团队协作，营销专家实时指导</p>
        <p>移动平台支持，数字化管理，客户自动推送，让一切变得更简单</p>
        <p>每校限额，项目直管，去除中间层压榨，避免恶性竞争，确保经纪人利益</p>
        <p>本项目业绩被 <span class="blue">职多星</span> 收录，人社系统推荐，数百万企业认可</p>
        <div class="title">申请流程步骤</div>
        <p>登录至善网，进入发现频道，点击“经纪人”去申请成为经纪人</p>
        <%--<div class="opr-bar">机会始于行动 <a class="btn-inline" href="${pageContext.request.contextPath}/youxueApp/addBroker/openApply.html">我要申请</a></div>--%>
      </div>
    </div>
  </div>
</section>
</body>
</html>
<script>
  setTimeout(function(){hideButtom();}, 500);
  if("${msg}" != ""){
    app.alert("${msg}", 1, function(){
      history.go(-1);
    });
  }
</script>