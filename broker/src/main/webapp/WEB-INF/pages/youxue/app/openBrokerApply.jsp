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
    <div class="tit">我要做游学经纪人</div>
  </div>
</header>
<section class="section wp-t44">
  <div class="at-wrap">
    <div class="form-apply-view">
      <div class="form-item-cell">
        <ul class="ul-input-view">
          <li class="item-cell">
            <div class="tag-cel">姓名</div>
            <div class="txt-cel">${broker.realname}</div>
          </li>
          <li class="item-cell">
            <div class="tag-cel">学校</div>
            <div class="txt-cel">${broker.sname}</div>
          </li>
          <li class="item-cell">
            <div class="tag-cel">手机号码</div>
            <div class="txt-cel">${broker.mobile}</div>
          </li>
          <li class="item-cell">
            <div class="tag-cel">QQ号码</div>
            <div class="txt-cel">${broker.qq}</div>
          </li>
          <li class="item-cell">
            <div class="tag-cel">身份证号</div>
            <div class="txt-cel">${broker.credit}</div>
          </li>
        </ul>
        <div class="agree-view">
          <div class="title">合作协议</div>
          <div class="txt-bord">
            <p>传播游学理念，帮更多同学实现人生的飞跃<br>展现魅力，锻炼能力，体验乐趣，回报丰厚<br>
              Q群、微信、直播，团队协作，营销专家实时指导<br>移动平台支持，数字化管理，客户自动推送，让一切变得更简单<br>
              每校限额，项目直管，去除中间层压榨，避免恶性竞争，确保经纪人利益<br>
              本项目业绩被 职多星logo 收录，人社系统推荐，数百万企业认可</p>
          </div>
        </div>
        <div class="submit-btn">
          <a class="btnCom" href="javascript:;" onclick="apply()">立即申请</a>
          <p><input id="isRead" type="checkbox" class="checkbox-1">我已阅读并同意上述全部条款</p>
        </div>
      </div>
    </div>
  </div>
</section>
</body>
</html>
<script>
  function apply(){
    if($("#isRead").is(":checked")){
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/youxueApp/addBroker/apply.json",
        data:{},
        async: false,
        success: function(data) {
          if(data.state == 0){
            app.msg("恭喜，您已经是至善网的项目经纪人", 1);
          }else{
            app.alert(data.msg, 1);
          }
        }
      });
    }else{
      app.alert("请勾选下面的我已阅读并同意上述全部条款", 1);
    }
  }
</script>