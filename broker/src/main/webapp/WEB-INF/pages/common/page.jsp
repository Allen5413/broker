<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tr>
  <td class="td-foot" colspan="99">
    <div class="com-pages">
      <span id="page"></span>
      <span>共${pageInfo.totalPage}页 ${pageInfo.totalCount}条</span>
    </div>
  </td>
</tr>
<script>
  var obj = $("#page");
  var currentPage = Number("${pageInfo.currentPage}");
  var totalPage = Number("${pageInfo.totalPage}");
  $(function(){
    var liPrev;
    var liNext;
    if(currentPage > 1){
      liPrev = $("<a class=\"prev\" href=\"#\" onclick=\"pageSub('up')\">上一页</a>");
    }
    obj.append(liPrev);
    if(totalPage <= 5){
      for(var i=1; i<=totalPage; i++){
        var li;
        if(currentPage == i){
          li = $("<a href=\"#\" class=\"on\" onclick=\"pageSub('change', this)\">"+i+"</a>");
        }else{
          li = $("<a href=\"#\" onclick=\"pageSub('change', this)\">"+i+"</a>");
        }
        obj.append(li);
      }
    }else{
      var baseNum = parseInt((currentPage-1)/5)*5;
      for(var i=1+baseNum; i<=(5+baseNum < totalPage ? 5+baseNum : totalPage); i++){
        var li;
        if(currentPage == i){
          li = $("<a href=\"#\" class=\"on\" onclick=\"pageSub('change', this)\">"+i+"</a>");
        }else{
          li = $("<a href=\"#\" onclick=\"pageSub('change', this)\">"+i+"</a>");
        }
        obj.append(li);
      }
    }

    if(currentPage != totalPage){
      liNext = $("<a class=\"next\" href=\"#\" onclick=\"pageSub('down')\">下一页</a>");
    }
    obj.append(liNext);
  });

  function pageSub(flag, obj){
    var isSub = true;
    if(flag == "start"){
      $("#currentPage").val(1);
    }
    if(flag == "up"){
      if(currentPage > 1){
        $("#currentPage").val(currentPage-1);
      }else{
        isSub = false;
        alert("已经是第一页");
      }
    }
    if(flag == "down"){
      if(currentPage < totalPage){
        currentPage = parseInt(currentPage);
        $("#currentPage").val(currentPage+1);
      }else{
        isSub = false;
        alert("已经是最后一页");
      }
    }
    if(flag == "end"){
      $("#currentPage").val(totalPage);
    }
    if(flag == "change"){
      if(typeof (obj) == "undefined"){
        if($("#changePage").val() > 0 && $("#changePage").val() <= totalPage) {
          $("#currentPage").val($("#changePage").val());
        }else{
          isSub = false;
          alert("跳转的页数不正确");
        }
      }else{
        $("#currentPage").val($(obj).html());
      }
    }
    if(isSub){
      $("#rows").val($("#rows_txt").val());
      var url = $("#pageForm").attr("action");
      app.searchFormPage($('#pageForm'), url);
    }
  }
</script>