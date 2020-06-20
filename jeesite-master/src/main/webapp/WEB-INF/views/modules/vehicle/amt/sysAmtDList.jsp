<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>账目明细管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

        });

        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/vehicle/amt/sysAmtD/">账目列表</a></li>
    <li><a href="${ctx}/vehicle/amt/sysAmtD/form">账目添加</a></li>
</ul>
<form:form id="searchForm" modelAttribute="sysAmtD" action="${ctx}/vehicle/amt/sysAmtD/" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>日期：</label>
            <input name="amtDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${sysAmtD.amtDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </li>
        <li><label>摘要(说明)：</label>
            <form:input path="amtName" htmlEscape="false" maxlength="512" class="input-medium"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>序号</th>
        <th>日期</th>
        <th>摘要(说明)</th>
        <th>出项</th>
        <th>进项</th>
        <th>余额</th>
        <th>备注</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="sysAmtD" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td><fmt:formatDate value="${sysAmtD.amtDate}" type="DATE" pattern="yyyy-MM-dd"/></td>
            <td>${sysAmtD.amtName}</td>
            <td>${sysAmtD.amtInput}</td>
            <td>${sysAmtD.amtOutput}</td>
            <td>${sysAmtD.amtBalance}</td>
            <td>${sysAmtD.remark}</td>
            <td>
                <a href="${ctx}/vehicle/amt/sysAmtD/form?id=${sysAmtD.id}">修改</a>
                <a href="${ctx}/vehicle/amt/sysAmtD/delete?id=${sysAmtD.id}"
                   onclick="return confirmx('确认要删除该账目明细吗？', this.href)">删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>