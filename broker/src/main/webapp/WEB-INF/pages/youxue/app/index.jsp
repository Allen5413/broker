<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="common/meta.jsp"%>
  <%@ include file="common/taglibs.jsp"%>
</head>
<body>
<section class="section wp-t50">
  <div class="at-wrap">
    <div class="theme-pic">
      <div class="pic"><img src="/youxue/images/banner.jpg"></div>
      <div class="th-tabs">
        <div class="tab" onclick="location.href='${pageContext.request.contextPath}/youxueApp/findTeam/find.html'">
          <p class="num">${teamHeadCount}</p>
          <p>校花</p>
        </div>
        <div class="tab">
          <p class="num">${visitCount}</p>
          <p>关注</p>
        </div>
      </div>
    </div>
    <div class="team-heads">
      <div class="title">人气校花<a class="more" href="${pageContext.request.contextPath}/youxueApp/findTeam/find.html">更多</a></div>
      <div class="th-pics-list">
        <c:forEach var="team" items="${teamList}">
          <a class="th-pic" href="${pageContext.request.contextPath}/youxueApp/findTeam/findInfo.html?teamId=${team.id}"><img src="${team.imgUrl}"></a>
        </c:forEach>
      </div>
    </div>
    <div class="intro-view">
      <div class="intro-text">
        <p>世界那么大，不想去看看？</p>
        <p>去剑桥，去耶鲁，学知识，长经验，最最重要的是收获一份可以吹十年的经历，成为你将来就业、留学的基石。</p>
        <p>跟着校花，去触摸世界；或者你就是校花，快来当团长。</p>
      </div>
      <div class="into-itms">
        <a class="into-2" href="${pageContext.request.contextPath}/youxueApp/applyTeamHead/open.html">
          <div class="icon"></div>
          <p>我要当校花团长</p>
        </a>
        <a class="into-1" href="${pageContext.request.contextPath}/youxueApp/addBroker/open.html">
          <div class="icon"></div>
          <p>我要做游学经纪人</p>
        </a>
      </div>
    </div>
    <div class="item-view">
      <div class="title">项目
        <a class="more" href="items.html">更多</a>
      </div>
      <ul class="list-item">
        <li>
          <a href="${pageContext.request.contextPath}/youxueApp/findProduct/findInfo.html?productId=${bj.id}&teamNum=${bj.num}"><img src="/youxue/images/item-01.jpg"></a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/youxueApp/findProduct/findInfo.html?productId=${xg.id}&teamNum=${xg.num}"><img src="/youxue/images/item-02.jpg"></a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/youxueApp/findProduct/findInfo.html?productId=${jq.id}&teamNum=${jq.num}"><img src="/youxue/images/item-03.jpg"></a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/youxueApp/findProduct/findInfo.html?productId=${yl.id}&teamNum=${yl.num}"><img src="/youxue/images/item-04.jpg"></a>
        </li>
      </ul>
    </div>
    <div class="intro-view">
      <div class="title">关于德行</div>
      <div class="intro-text">
        <p>
          德行教育咨询集团于北京和香港注册成立，主营海内外教育交流项目。 集团基于十年深耕教育行业的积淀，成立之初已得到众多官方机构独家支持，包括北大清华、英国剑桥、美国耶鲁等国际顶尖学府，摩根、瑞银、高盛等世界著名企业，中科院、国务院教育部、地方教育局、留学基金管理委员会等国家行政单位，中国网、教育电视台等教育媒体平台。
        </p>
        <img src="/youxue/images/intro/pic-01.png" />
        <h5>德行团队</h5>
        <p>
          德行团队精英荟萃，有来自清华、北大、剑桥、耶鲁等世界名校，有来自百度、搜狐、腾讯、九鼎、红杉、和君等世界名企，有近10年教育从业经验，有在中国大陆、中国香港、欧洲、美洲、东北亚、东南亚地区丰富的项目执行资源，始终致力于搭建“交流-成长-蜕变”的国际化舞台，已为10000多名学员提供海内外教育交流项目机会。 德行团队成员，拥有国际交流业内唯一中央级官方背景，拥有央网新闻源全媒体传播矩阵、拥有资本圈教育圈全行业智库，为打造国内独家模块化项目和口碑化运营的教育交流服务平台，奠定坚实基础。
        </p>
        <h5>德行历史</h5>
        <p>2006年，在中国北京大学、英国剑桥大学以社团形式注册，形成团队雏形，成为中英国际交流的桥梁。</p>
        <p>2010年，在中国北京大学、英国剑桥大学分别注册成立有限公司，扩充团队，主营大学生中英交流项目。</p>
        <p>2011年，在美国、香港以社团形式设立分支机构，与高校和企业、国内教育局、政府机构、企业建立合作关系，主营业务扩展为大学生及成人国际交流项目。</p>
        <p>2013年，与国内外中小学、培训机构建立合作关系，主营业务扩展为全年龄国际交流项目。</p>
        <p>2015年，与北京大学等国内名校建立官方合作关系，开办全国独家授予大学结业证书的教育项目。</p>
        <p>2017年，在教育局国际交流政策改革的精神指导下，战略资本助力，实现团队重组，制定“政策•教育•商会•媒体”联动战略，在北京成立北京德行教育咨询有限公司，在香港成立香港德行教育咨询有限公司。
        </p>
        <h5>联系方式</h5>
        <p>1、联系电话：400-1009058、010-58031621；<br>
          2、通讯地址：北京丰台区马家堡东路106号 自然新天地 D 座；<br>
          3、公司官网：http://www.dexingedu.com/<br>
          4、联系邮箱：info@dexingedu.com<br>
          5、咨询微信：公众号“dexing_edu”个人号“13485973888”</p>
      </div>
    </div>
    <div class="intro-view">
      <div class="title">德行证书</div>
      <div class="intro-text">
        <ul class="pics-list">
          <li>
            <img src="/youxue/images/certificate_01.jpg">
          </li>
          <li>
            <img src="/youxue/images/certificate_02.jpg">
          </li>
        </ul>
      </div>
    </div>
    <div class="intro-view">
      <div class="title">至善声明</div>
      <div class="intro-text">
        <p>本项目由德行教育咨询集团主办，并承担一切法律责任；至善网协助项目宣传，并监管执行。</p>
        <p>出行用户必须与德行签署书面合同，该合同保护双方的合法权益，超出合同范围的口头承诺均无法律效力，请勿轻信。</p>
        <ul class="pics-code-list">
          <li>
            <img src="/youxue/images/code-zs-android.png">
            <p>至善安卓下载</p>
          </li>
          <li>
            <img src="/youxue/images/code-zs-ios.png">
            <p>至善IOS下载</p>
          </li>
          <li>
            <img src="/youxue/images/code-dx.png">
            <p>德行公众号</p>
          </li>
        </ul>
      </div>
    </div>
  </div>
  <div class="footer">
    <ul class="nav-tabs">
      <li><a class="on" href="javascript:;">首页</a></li>
      <li><a href="${pageContext.request.contextPath}/youxueApp/findProduct/open.html">项目</a></li>
      <li><a href="${pageContext.request.contextPath}/youxueApp/findTeam/find.html">团长</a></li>
      <li><a href="${pageContext.request.contextPath}/youxueApp/findTeam/user.html">我的</a></li>
    </ul>
  </div>
</section>
<div id="brokerRecommend" class="layer-trans" style="display: none">
  <div class="pop-tips-txt">
    <div class="close-x" onclick="$('#brokerRecommend').hide()">×</div>
    <div class="title">关联经纪人</div>
    <div class="pop-input-view">
      <div class="agent-uhs">
        <c:forEach var="broker" items="${brokerList}">
          <a name="selectBroker" class="u-pic" href="#" onclick="selectBroker('${broker.zz}', this)"><img src="${broker.icon}"><span class="g-ed"></span>
            <div class="info">
              <p>${broker.name}</p>
              <p>TEL:${broker.mobile}</p>
              <p>QQ:${broker.qq}</p>
            </div>
          </a>
        </c:forEach>
      </div>
      <label>有认识的经纪人，请输入他的ID</label>
      <p class="item-input">
        <span class="i-tg">ZZ：</span>
        <input type="text" id="zz">
      </p>
      <input type="hidden" id="selectBrokerZz" />
      <p><button class="but-submit" onclick="subBroker()">确定关联</button></p>
    </div>
  </div>
</div>
</body>
</html>
<script>
  setTimeout(function(){hideButtom();}, 500);
  <c:if test="${!isHaveBroker && !empty sessionScope.loginName}">
    setTimeout(function(){
      //获取当前时间
      var date = new Date();
      var year = date.getFullYear();
      var month = date.getMonth()+1;
      var day = date.getDate();
      var nowDate = year+"-"+month+"-"+day;
      if(app.getCookie("${param.zz}") == ""){
        app.setCookie("${param.zz}", nowDate, 1);
        $("#brokerRecommend").show();
      }
    }, 3000);
  </c:if>

  function selectBroker(zz, obj){
    $("[name=selectBroker]").each(function(){
      $(this).removeClass("u-pic selected");
      $(this).addClass("u-pic");
    });
    $(obj).addClass("u-pic selected");
    $("#selectBrokerZz").val(zz);
  }

  function subBroker(){
    var zz = "";
    if($("#zz").val() == ""){
      zz = $("#selectBrokerZz").val();
    }else{
      zz = $("#zz").val();
    }
    if(zz == ""){
      app.msg("请选择一个经纪人或者输入经纪人ZZ", 1);
      return false;
    }
    $.ajax({
      url:"${pageContext.request.contextPath}/addCustomer/addForYx.json",
      method : 'POST',
      async:false,
      data:{"brokerZz":zz},
      success:function(data){
        if(data.state == 0){
          app.msg("关联成功！", 0);
          $("#brokerRecommend").hide();
        }else {
          app.msg(data.msg, 1);
        }
      }
    });
  }
</script>