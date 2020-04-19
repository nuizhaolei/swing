<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>商家管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#btnExport").click(function () {
                top.$.jBox.confirm("确认要导出数据吗？", "系统提示", function (v, h, f) {
                    if (v == "ok") {
                        $("#searchForm").attr("action", "${ctx}/vehicle/merchant/merchant/export");
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
    <li class="active"><a href="${ctx}/vehicle/merchant/merchant/">商家列表</a></li>
    <li class="clearfix"></li>
    <li><a href="${ctx}/vehicle/merchant/merchant/form">商家添加</a></li>
</ul>
<form:form id="searchForm" modelAttribute="merchant" action="${ctx}/vehicle/merchant/merchant/" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>商家名称：</label>
            <form:input path="name" htmlEscape="false" maxlength="256" class="input-medium"/>
        </li>
        <li class="btns">
            <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
            <input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
            <input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>序列</th>
        <th>商家名称</th>
        <th>地址</th>
        <th>联系人</th>
        <th>联系方式</th>
        <th>备注</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="merchant" varStatus="status">
        <tr>
            <td>${ status.index + 1}</td>
            <td><a href="${ctx}/vehicle/merchant/merchant/form?id=${merchant.id}">
                    ${merchant.name}
            </a></td>
            <td>
                    ${merchant.address}
            </td>
            <td>
                    ${merchant.linkName}
            </td>
            <td>
                    ${merchant.telephone}
            </td>
            <td>
                    ${merchant.remark}
            </td>
            <td>
                <a href="${ctx}/vehicle/merchant/merchant/form?id=${merchant.id}">修改</a>
                <a href="${ctx}/vehicle/merchant/merchant/delete?id=${merchant.id}"
                   onclick="return confirmx('确认要删除该商家吗？', this.href)">删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
<div id="importBox" class="hide">
    <form id="importForm" action="${ctx}/vehicle/merchant/merchant/import" method="post" enctype="multipart/form-data"
          class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
        <input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
        <input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
        <a href="${ctx}/sys/user/import/template">下载模板</a>
    </form>
</div>
</body>
</html>