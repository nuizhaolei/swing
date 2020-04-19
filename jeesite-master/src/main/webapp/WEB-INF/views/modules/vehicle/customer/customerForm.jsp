<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>客户管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });

        function viewLogistics(event, logisticNO) {
            var os = getOs();
            var y = getY(event);
            if (os == 'MSIE') {
                y = window.event.y + 405;
            }
            $(".logisticscenter_xq").hide();
            $("#" + logisticNO).show();
            $("#" + logisticNO).css("top", y + 15);
        }

        // 计算对象居中需要设置的left和top值
        // 参数：
        // _w - 对象的宽度
        // _h - 对象的高度
        function getLT(_w, _h) {
            var de = document.documentElement;
            // 获取当前浏览器窗口的宽度和高度
            // 兼容写法，可兼容ie,ff
            var w = self.innerWidth || (de && de.clientWidth) || document.body.clientWidth;
            var h = (de && de.clientHeight) || document.body.clientHeight;
            // 获取当前滚动条的位置
            // 兼容写法，可兼容ie,ff
            var st = (de && de.scrollTop) || document.body.scrollTop;
            var topp = 0;
            if (h > _h)
                topp = (st + (h - _h) / 2);
            else
                topp = st;
            var leftp = 0;
            if (w > _w)
                leftp = ((w - _w) / 2);
            // 左侧距，顶部距
            return [leftp, topp];
        }

        //获取鼠标位置GetPostion
        function GetPostion(e) {
            var x = getX(e);
            var y = getY(e);
        }

        function getX(e) {
            e = e || window.event;
            return e.pageX || e.clientX + document.body.scrollLeft - document.body.clientLeft
        }

        function getY(e) {
            e = e || window.event;
            return e.pageY || e.clientY + document.body.scrollTop - document.body.clientTop
        }

        //判断浏览器类型
        function getOs() {
            var OsObject = "";
            if (navigator.userAgent.indexOf("MSIE") > 0) {
                return "MSIE";
            }
            if (isFirefox = navigator.userAgent.indexOf("Firefox") > 0) {
                return "Firefox";
            }
            if (isSafari = navigator.userAgent.indexOf("Safari") > 0) {
                return "Safari";
            }
            if (isCamino = navigator.userAgent.indexOf("Camino") > 0) {
                return "Camino";
            }
            if (isMozilla = navigator.userAgent.indexOf("Gecko/") > 0) {
                return "Gecko";
            }
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/vehicle/customer/customer/">客户列表</a></li>
    <li class="active"><a
            href="${ctx}/vehicle/customer/customer/form?id=${customer.id}">客户${not empty customer.id?'修改':'添加'}</a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="customer" action="${ctx}/vehicle/customer/customer/save" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">姓名：</label>
        <div class="controls">
            <form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">婚期：</label>
        <div class="controls">
            <input name="marriageDate" type="text" readonly="readonly" maxlength="20"
                   class="input-medium Wdate required"
                   value="<fmt:formatDate value="${customer.marriageDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">电话：</label>
        <div class="controls">
            <form:input path="telephone" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">数量：</label>
        <div class="controls">
            <form:input path="count" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">车队组合：</label>
        <div class="controls">
            <form:textarea path="carList" htmlEscape="false" rows="4" maxlength="256" class="input-xxlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">选择车辆：</label>
        <div class="controls">
            <a class="view_button" onclick="viewLogistics(event,'page.html')"
               href="####">选择</a>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">总价：</label>
        <div class="controls">
            <form:input path="totalPrice" htmlEscape="false" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">路线：</label>
        <div class="controls">
            <form:input path="line" htmlEscape="false" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">公里测算：</label>
        <div class="controls">
            <form:input path="distance" htmlEscape="false" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">备注：</label>
        <div class="controls">
            <form:textarea path="remark" htmlEscape="false" rows="4" maxlength="256" class="input-xxlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">创建时间：</label>
        <div class="controls">
            <input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
                   value="<fmt:formatDate value="${customer.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="form-actions">
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>