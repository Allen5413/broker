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
    <div class="tit">我的相册</div>
  </div>
</header>
<section class="section wp-t44">
  <div class="at-wrap">
    <div class="upload-photo-view">
      <div class="photo-btn-cell">
        <div id="tempImg" class="pics-upload bline">
          <form id="form" name="form" enctype="multipart/form-data" method="post">
            <a class="btn-upload" href="#"><i class="i-camera"></i><input type="file" name="file" class="uploadFile" onchange="uploadFile()"></a>
          </form>
        </div>
        <input type="hidden" id="fileNames" name="fileNames" />
        <div class="opr-bar"><a class="btn-inline" href="javascript:;" onclick="sub()">发 布</a></div>
      </div>
      <div class="album-cells-list">
        <c:forEach var="img" items="${imgMap}">
          <div class="tim-date">${img.key}</div>
          <div class="album-pics">
            <c:forEach var="url" items="${img.value}">
              <a class="pic" href="#"><img src="${url[0]}"></a>
            </c:forEach>
          </div>
        </c:forEach>
      </div>
    </div>
  </div>
</section>
</body>
</html>
<script>
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
          var html = $("#tempImg").html();
          html = "<a class=\"pic\" href=\"javascript:;\"><img src='"+data.path+"'><span class=\"x-del\" onclick=\"delImg('"+data.path+"', '"+data.fileName+"', this)\">×</span></a>" + html;
          $("#tempImg").html(html);
          $("#fileNames").val($("#fileNames").val()+data.fileName+",");
        }else{
          app.alert(data.msg, 1);
        }
      }
    });
  }

  function delImg(path, fileName, obj){
    $.ajax({
      url:"${pageContext.request.contextPath}/youxueApp/uploadMyImg/del.json",
      method : 'POST',
      async:false,
      data:{"path":path},
      success:function(data){
        if(data.state == 0){
          $(obj).parent().remove();
          var fileNames = $("#fileNames").val();
          fileNames = fileNames.replace(fileName+",", "");
          $("#fileNames").val(fileNames);
        }else {
          app.msg(data.msg, 1);
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
      url:"${pageContext.request.contextPath}/youxueApp/uploadMyImg/addImg.json",
      method : 'POST',
      async:false,
      data:{"fileNames":fileNames, "domain":"http://localhost:8080"},
      success:function(data){
        if(data.state == 0){
          location.href = "${pageContext.request.contextPath}/youxueApp/uploadMyImg/open.html";
        }else {
          app.msg(data.msg, 1);
        }
      }
    });
  }
</script>
