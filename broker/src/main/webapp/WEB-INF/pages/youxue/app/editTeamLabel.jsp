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
      <a href="javascript:;" onclick="history.go(-1);"><i class="i-back"></i></a>
    </div>
    <div class="tit">自我介绍</div>
  </div>
</header>
<section class="section wp-t44">
  <div class="at-wrap">
    <div class="we-info-f">
      <div class="text-area bline">
        <textarea id="label" name="label">${team.label}</textarea>
      </div>
      <div class="opr-bar"><a class="btn-1" href="javascript:;" onclick="sub();">保存</a></div>
    </div>
  </div>
</section>
</body>
</html>
<script>
  function sub(){
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/youxueApp/editTeamLabel/editLabel.json",
      data:{"label":$("#label").val()},
      async: false,
      success: function(data) {
        if(data.state == 0){
          app.msg("保存成功", 0);
        }else{
          app.alert(data.msg, 1);
        }
      }
    });
  }
</script>