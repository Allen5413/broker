<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tit-top-fixed" class="pos-rev-cell">
  <div class="title">添加团长</div>
</div>
<div class="container-view">
  <div class="mod-com-view">
    <div class="mod-content">
      <form id="form" name="form" action="${pageContext.request.contextPath}/youxue/addTeam/addHead.json">
        <table class="set-table-info">
          <tr>
            <td class="tag-b">团长ZZ：</td>
            <td><input type="text" id="zz" name="zz" class="input-txt-220" /></td>
          </tr>
          <tr>
            <td class="tag-b">产品：</td>
            <td>
              <select id="productId" onchange="selectProduct()">
                <option value="">--请选择--</option>
                <c:forEach var="product" items="${productList}">
                  <option value="${product.id}">${product.name}</option>
                </c:forEach>
              </select>&nbsp;&nbsp;&nbsp;&nbsp;
              <select id="productDateId" style="display: none;"></select>
            </td>
          </tr>
          <tr>
            <td class="tag-b">个性说明：</td>
            <td><textarea id="label" name="label" class="textarea-intro"></textarea></td>
          </tr>
          <tr>
            <td class="tag-b"></td>
            <td>
              <a class="btn-com" href="#" onclick="addTeamHead();">保存提交</a>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</div>
<script>
  function selectProduct(){
    var productId = $("#productId").val();
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/youxue/findProductDateByProductId.json",
      data:{"productId":productId},
      async: false,
      success: function(data) {
        if(data.state == 0){
          var productDateList = data.productDateList;
          if(0 < productDateList.length){
            $("#productDateId").empty();
            $("#productDateId").show();
            for(var i=0; i<productDateList.length; i++) {
              var productDate = productDateList[i];
              $("#productDateId").append("<option value='" + productDate.id + "'>" + productDate.date + "</option>");
            }
          }
        }else{
          app.msg(data.msg, 1);
        }
      }
    });
  }

  function addTeamHead(){
    if($("#zz").val() == ""){
      app.alert("请输入ZZ！", 1);
      return false;
    }
    if($("#productDateId").val() == "" || $("#productDateId").val() == null){
      app.alert("请选择产品行程时间！", 1);
      return false;
    }
    app.add("${pageContext.request.contextPath}/youxue/addTeam/addHead.json", $("#form").serialize(), "${pageContext.request.contextPath}/pageProject/page.html", "");
  }
</script>