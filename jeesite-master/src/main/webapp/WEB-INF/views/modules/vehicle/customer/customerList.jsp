<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>客户管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#btnExport").click(function () {
                top.$.jBox.confirm("确认要导出数据吗？", "系统提示", function (v, h, f) {
                    if (v == "ok") {
                        $("#searchForm").attr("action", "${ctx}/vehicle/customer/customer/export");
                        $("#searchForm").submit();
                    }
                }, {buttonsFocus: 1});
                top.$('.jbox-body .jbox-icon').css('top', '55px');
            });
            $("#btnImport").click(function () {
                $.jBox($("#importBox").html(), {
                    title: "导入数据", buttons: {"关闭": true},
                    bottomText: "导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"
                });
            });
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
    <li class="active"><a href="${ctx}/vehicle/customer/customer/">客户列表</a></li>
    <li><a href="${ctx}/vehicle/customer/customer/form">客户添加</a></li>
</ul>
<form:form id="searchForm" modelAttribute="customer" action="${ctx}/vehicle/customer/customer/" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>姓名：</label>
            <form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
        </li>
        <li class="btns">
            <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
            <input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
            <input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
        </li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>序号</th>
        <th>姓名</th>
        <th>婚期</th>
        <th>电话</th>
        <th>车队组合</th>
        <th>数量</th>
        <th>路线</th>
        <th>公里计算</th>
        <th>总价</th>
        <th>创建者</th>
        <th>备注</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="customer" varStatus="status">
        <tr>
            <td>${ status.index + 1}</td>
            <td><a href="${ctx}/vehicle/customer/customer/form?id=${customer.id}">
                    ${customer.name}
            </a></td>
            <td>
                <fmt:formatDate value="${customer.marriageDate}" type="DATE" pattern="yyyy-MM-dd HH:ss:mm"/>
            </td>
            <td>
                    ${customer.telephone}
            </td>
            <td><a href="${ctx}/vehicle/customer/customer/query?id=${customer.id}">
                    ${customer.carList}</a>
            </td>
            <td>
                    ${customer.count}
            </td>
            <td>
                    ${customer.line}
            </td>
            <td>
                    ${customer.distance}
            </td>
            <td>
                    ${customer.totalPrice}
            </td>
            <td>
                    ${customer.user.name}
            </td>
            <td>
                    ${customer.remark}
            </td>
            </td>
            <td>
                <a href="${ctx}/vehicle/customer/customer/form?id=${customer.id}">修改</a>
                <a href="${ctx}/vehicle/customer/customer/delete?id=${customer.id}"
                   onclick="return confirmx('确认要删除该customer吗？', this.href)">删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
<div id="importBox" class="hide">
    <form id="importForm" action="${ctx}/vehicle/customer/customer/import" method="post" enctype="multipart/form-data"
          class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
        <input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
        <input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
        <a href="${ctx}/sys/user/import/template">下载模板</a>
    </form>
</div>
</body>
</html>