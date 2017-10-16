<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container-view">
    <div class="mod-com-view">
        <div class="mod-content">
            <table class="set-table-info">
                <tr>
                    <td class="tag-b" rowspan="3"><img src="${broker.icon}"> </td>
                    <td class="tag-b" style="width: 70px;">姓名：</td>
                    <td>${broker.realname}</td>
                    <td class="tag-b" style="width: 70px;">手机：</td>
                    <td>${broker.mobile}</td>
                </tr>
                <tr>
                    <td class="tag-b" style="width: 70px;">学校：</td>
                    <td>${broker.sname}</td>
                    <td class="tag-b" style="width: 70px;">邮箱：</td>
                    <td>${broker.email}</td>
                </tr>
                <tr>
                    <td class="tag-b" style="width: 70px;">QQ：</td>
                    <td>${broker.qq}</td>
                    <td class="tag-b" style="width: 70px;">客户数：</td>
                    <td>${broker.customerNum}</td>
                </tr>
            </table>
        </div>
    </div>
</div>
<div class="container-view">
    <div class="mod-com-view">
        <div class="mod-content">
            <table class="set-table-info">
                <tr>
                    <td>
                        <select id="projectId" onchange="selectProject()">
                            <c:forEach var="project" items="${projectList}">
                                <option value="${project.id}">${project.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </table>
        </div>
        <div class="data-table-list">
            <table  width="100%">
                <tr>
                    <th>序号</th>
                    <th>客户姓名</th>
                    <th>最后浏览时间</th>
                    <th>浏览次数</th>
                </tr>
                <c:forEach var="customer" items="${customerList}" varStatus="status">
                    <tr>
                        <td>${status.index+1}</td>
                        <td>${customer.name}</td>
                        <td>${customer.endLoginTime}</td>
                        <td>${customer.loginCount}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<script>
    function selectProject(){
        var projectId = $("#projectId").val();
    }
</script>