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
    <div class="tit">我的形象照</div>
  </div>
</header>
<section class="section wp-t44">
  <div class="at-wrap">
    <div class="upload-photo-view">
      <div class="photo-btn-cell">
        <div id="tempImg" class="pics-upload bline">
          <form id="form" name="form" enctype="multipart/form-data" method="post" target="_blank">
            <a class="btn-upload" href="#"><i class="i-camera"></i><input type="file" name="file" class="uploadFile" onchange="uploadFile()"></a>
          </form>
        </div>
        <input type="hidden" id="fileNames" name="fileNames" />
        <input type="hidden" id="path" />
        <input type="hidden" id="fileName" />
        <div class="opr-bar"><a class="btn-inline" href="javascript:;" onclick="sub()">发 布</a></div>
      </div>
      <div class="album-cells-list">
        <div id="demo-test-gallery" class="demo-gallery">
            <div class="album-pics">
              <c:if test="${!empty imgUrl}">
                <a class="pic" href="javascript:;">
                  <img src="${imgUrl}" onclick="picBig('${imgUrl}')" />
                  <span class="x-del" onclick="delImg2('${imgUrl}')">×</span>
                </a>
              </c:if>
            </div>
        </div>
      </div>
    </div>
  </div>
</section>
<div id="showBig_pic" class="show-bigPic">
  <a class="close" href="javascript:;" onclick="picClose();">×</a>
  <div class="picArea" onclick="picClose();">
    <img id="imgBig">
  </div>
</div>
</body>
</html>
<script>
  setTimeout(function(){hideButtom();}, 500);
  function uploadFile(){
    $.ajax({
      type: "POST",
      enctype: 'multipart/form-data',
      url: "${pageContext.request.contextPath}/youxueApp/uploadMyImg/upload.json",
      data: new FormData($("#form")[0]),
      processData: false,
      contentType: false,
      cache: false,
      timeout: 600000,
      success: function (data) {
        if(data.state == 0) {
          //只能选择一张照片上传，，如果之前传了就删除之前传的，保留最新传的
          var path = $("#path").val();
          var fileName = $("#fileName").val();
          if(path != "" && fileName != ""){
            delImg(path, fileName)
          }
          var html = $("#tempImg").html();
          html = "<a class=\"pic\" href=\"javascript:;\"><img src='"+data.path+"'><span id=\"delIcon\" class=\"x-del\" onclick=\"delImg('"+data.path+"', '"+data.fileName+"')\">×</span></a>" + html;
          $("#tempImg").html(html);
          $("#fileNames").val(data.fileName+",");
          $("#path").val(data.path);
          $("#fileName").val(data.fileName);
        }else{
          layer.alert(data.msg, {icon: 5});
        }
      }
    });
  }

  function delImg(path, fileName){
    $.ajax({
      url:"${pageContext.request.contextPath}/youxueApp/uploadMyImg/del.json",
      method : 'POST',
      async:false,
      data:{"path":path},
      success:function(data){
        if(data.state == 0){
          $("#delIcon").parent().remove();
          var fileNames = $("#fileNames").val();
          fileNames = fileNames.replace(fileName+",", "");
          $("#fileNames").val(fileNames);
        }else {
          layer.alert(data.msg, {icon: 5});
        }
      }
    });
  }

  function sub(){
    var fileNames = $("#fileNames").val();
    if(fileNames == ""){
      app.alert("请先上传照片", 1);
      return false;
    }
    fileNames = fileNames.substring(0, fileNames.length - 1);
    $.ajax({
      url:"${pageContext.request.contextPath}/youxueApp/uploadMyImg/addCoverImg.json",
      method : 'POST',
      async:false,
      data:{"fileNames":fileNames, "domain":"http://localhost:8080"},
      success:function(data){
        if(data.state == 0){
          location.href = "${pageContext.request.contextPath}/youxueApp/uploadMyImg/openCover.html";
        }else {
          layer.alert(data.msg, {icon: 5});
        }
      }
    });
  }

  function delImg2(path){
    $.ajax({
      url:"${pageContext.request.contextPath}/youxueApp/delYxTeamImg/del.json",
      method : 'POST',
      async:false,
      data:{"path":path},
      success:function(data){
        if(data.state == 0){
          history.go(0);
          return false;
        }else {
          layer.alert(data.msg, {icon: 5});
        }
      }
    });
  }
</script>
