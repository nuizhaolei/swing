<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>车辆管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#btnExport").click(function () {
                top.$.jBox.confirm("确认要导出数据吗？", "系统提示", function (v, h, f) {
                    if (v == "ok") {
                        $("#searchForm").attr("action", "${ctx}/vehicle/vehicle/vehicle/export");
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
        function static_num() {
            document.getElementById("deleteSubmit").onclick = function () {
                var arr = new Array();
                var items = document.getElementsByName("category");
                for (i = 0; i < items.length; i++) {
                    if (items[i].checked) {
                        arr.push(items[i].value);
                    }
                }
                var url = encodeURIComponent("${ctx}/vehicle/vehicle/vehicle/delete?id=" + arr[0]);
                console.log(decodeURIComponent(url));
                $.get({
                    //请求的媒体类型
                    contentType: "application/json;charset=UTF-8",
                    //请求地址
                    url : url,
                    //数据，json字符串
                    // data : JSON.stringify(arr),
                    //请求成功
                    success : function(result) {
                        console.log(result);
                    },
                    //请求失败，包含具体的错误信息
                    error : function(e){
                        console.log(e.status);
                        console.log(e.responseText);
                    }
                });
            };
        };
    </script>
</head>
<body>
<div id="importBox" class="hide">
    <form id="importForm" action="${ctx}/vehicle/vehicle/vehicle/import" method="post" enctype="multipart/form-data"
          class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
        <input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
        <input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
        <a href="${ctx}/sys/user/import/template">下载模板</a>
    </form>
</div>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/vehicle/vehicle/vehicle/">车辆列表</a></li>
    <li><a href="${ctx}/vehicle/vehicle/vehicle/form">添加</a></li>
</ul>
<form:form id="searchForm" modelAttribute="vehicle" action="${ctx}/vehicle/vehicle/vehicle/" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>姓名：</label>
            <form:input path="name" htmlEscape="false" maxlength="128" class="input-medium"/>
        </li>
        <li><label>车型：</label>
            <form:input path="vehicleType" htmlEscape="false" maxlength="128" cssStyle="width: 90px"/>
        </li>
        <li><label>颜色：</label>
            <form:input path="vehicleColor" htmlEscape="false" maxlength="128" cssStyle="width: 90px"/>
        </li>
        <li><label>车牌号码：</label>
            <form:input path="vehicleCode" htmlEscape="false" maxlength="128" cssStyle="width: 90px"/>
        </li>
        <li class="btns">
            <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
            <%--<input id="deleteSubmit" class="btn btn-primary" type="button" value="删除" onclick="static_num()"/>--%>
            <input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
            <input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>序号</th>
        <%--<th>选择</th>--%>
        <th>姓名</th>
        <th>联系电话</th>
        <%--<th>手机号</th>--%>
        <th>车型</th>
        <th>颜色</th>
        <th>车牌号码</th>
        <th>是否限行</th>
        <th>备注</th>
        <th>单价</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="vehicle" varStatus="status">
        <tr>
            <td>${ status.index + 1}</td>
            <%--<td><input type="checkbox" name="category" value="${vehicle.id}"/></td>--%>
            <td><a href="${ctx}/vehicle/vehicle/vehicle/form?id=${vehicle.id}">
                    ${vehicle.name}
            </a></td>
            <td>
                    ${vehicle.telephone}
            </td>
                <%--<td>
                        ${vehicle.call}
                </td>--%>
            <td>
                    ${vehicle.vehicleType}

            </td>
            <td>
                    ${vehicle.vehicleColor}

            </td>
            <td>
                    ${vehicle.vehicleCode}

            </td>
            <td>
                    ${vehicle.isLimitLine}

            </td>
            <td>
                    ${vehicle.remark}

            </td>
            <td>
                    ${vehicle.price}

            </td>

            <td>
                <a href="${ctx}/vehicle/vehicle/vehicle/form?id=${vehicle.id}">修改</a>
                <a href="${ctx}/vehicle/vehicle/vehicle/delete?id=${vehicle.id}"
                   onclick="return confirmx('确认要删除该信息吗？', this.href)">删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>