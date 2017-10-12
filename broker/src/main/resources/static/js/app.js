$(document).ready(function(){
    function barnumFixed(id){
        var obj = document.getElementById(id);
        var _getHeight = obj.offsetTop;

        window.onscroll = function(){
            changePos(id,_getHeight);
        }
    }
    function changePos(id,height){
        var obj = document.getElementById(id);
        var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
        if(scrollTop < 1){
            obj.style.position = 'relative';
        }else{
            obj.style.position = 'fixed';
        }
    }
    window.onload = function(){
        barnumFixed('tit-top-fixed');
    }

    //子元素滚动不影响父元素 js
    $.fn.scrollUnique = function() {
        return $(this).each(function() {
            var eventType = 'mousewheel';
            // 火狐是DOMMouseScroll事件
            if (document.mozHidden !== undefined) {
                eventType = 'DOMMouseScroll';
            }
            $(this).on(eventType, function(event) {
                // 一些数据
                var scrollTop = this.scrollTop,
                    scrollHeight = this.scrollHeight,
                    height = this.clientHeight;

                var delta = (event.originalEvent.wheelDelta) ? event.originalEvent.wheelDelta : -(event.originalEvent.detail || 0);

                if ((delta > 0 && scrollTop <= delta) || (delta < 0 && scrollHeight - height - scrollTop <= -1 * delta)) {
                    // IE浏览器下滚动会跨越边界直接影响父级滚动，因此，临界时候手动边界滚动定位
                    this.scrollTop = delta > 0? 0: scrollHeight;
                    // 向上滚 || 向下滚
                    event.preventDefault();
                }
            });
        });
    };
    $(".nav-rt-list").scrollUnique();
    //加载弹出框样式
    layer.config({skin: 'layer-ext-moon', extend:'skin/moon/style.css'});
});

var app = new App();
function App(){
    /*用来存储每个url对应的页面html代码，用去切换tab的时候保存之前请求的结果*/
    this.pageHtmlJSON = {"index":"欢迎使用教材系统"};
}

App.prototype.clickMenu = function(id){
    if($("#resources_"+id).is(":hidden")){
        $("#resources_"+id).show();
    }else{
        $("#resources_"+id).hide();
    }
}

App.prototype.clickResources = function(url, params, obj){
    if("undefined" != typeof (obj) && null != obj) {
        $("[name=resources_a]").attr("class", "");
        $(obj).addClass("on");
    }
    if("undefined" == typeof (params)) {
        params = {};
    }
    $.ajax({
        cache: true,
        type: "POST",
        data: params,
        url: url,
        async: false,
        success: function (data) {
            $("#contentPage").html(data);
        }
    });
}

/**
 * alert
 * @param msg
 * @param flag
 */
App.prototype.alert = function(msg, flag){
    if(flag == 0){
        //成功的提示
        layer.alert(msg, {icon: 6});
    }else{
        //失败的提示
        layer.alert(msg, {icon: 5});
    }
}

/**
 * 消息提示
 * @param msg
 * @param flag
 */
App.prototype.msg = function(msg, flag){
    if(flag == 0){
        //成功的提示
        layer.msg(msg, {icon: 6});
    }else{
        //失败的提示
        layer.alert(msg, {icon: 5});
    }
}

App.prototype.confirm = function(content, fun){
    layer.confirm(content, {icon: 3, title:'提示'}, function(index){
        fun(index);
    });
}

/**
 * 打开一个弹出框，只有关闭按钮
 * @param html
 * @param title
 * @param width
 * @param height
 */
App.prototype.openOneBtnDialog = function(url, title, width, height){
    $.ajax({
        cache: true,
        type: "POST",
        url: url,
        async: false,
        success: function (data) {
            var dialogWidth = app.getWindowWidthSize()*width+'px';
            var dialogHeight = app.getWindowHeightSize()*height+'px';
            if(width > 5){
                dialogWidth = width+'px';
            }
            if(height > 5){
                dialogHeight = height+'px';
            }
            var index = layer.open({
                type: 1,
                title: title,
                area: [dialogWidth, dialogHeight],
                shadeClose: true, //点击遮罩关闭
                content: data,
                shadeClose: false,
                btn: ['关闭'],
                btn2: function(index, layero){
                    layer.close(index);
                }
            });
        }
    });
}

/**
 * 打开一个弹出框
 * @param url
 * @param title
 * @param width
 * @param height
 */
App.prototype.openDialog = function(url, title, width, height, func){
    $.ajax({
        cache: true,
        type: "POST",
        url: url,
        async: false,
        success: function (data) {
            var dialogWidth = app.getWindowWidthSize()*width+'px';
            var dialogHeight = app.getWindowHeightSize()*height+'px';
            if(width > 5){
                dialogWidth = width+'px';
            }
            if(height > 5){
                dialogHeight = height+'px';
            }
            var index = layer.open({
                type: 1,
                title: title,
                area: [dialogWidth, dialogHeight],
                shadeClose: true, //点击遮罩关闭
                content: data,
                shadeClose: false,
                btn: ['提交', '关闭'],
                yes: function(index, layero){
                    func(index);
                },btn2: function(index, layero){
                    layer.close(index);
                }
            });
        }
    });
}

/**
 * 打开一个弹出框, 带下一步按钮
 * @param html
 * @param title
 * @param width
 * @param height
 */
App.prototype.openNextBtnDialog = function(url, title, width, height, func){
    $.ajax({
        cache: true,
        type: "POST",
        url: url,
        async: false,
        success: function (data) {
            var dialogWidth = app.getWindowWidthSize()*width+'px';
            var dialogHeight = app.getWindowHeightSize()*height+'px';
            if(width > 5){
                dialogWidth = width+'px';
            }
            if(height > 5){
                dialogHeight = height+'px';
            }
            var index = layer.open({
                type: 1,
                title: title,
                area: [dialogWidth, dialogHeight],
                shadeClose: true, //点击遮罩关闭
                content: data,
                shadeClose: false,
                btn: ['下一步', '关闭'],
                yes: function(index, layero){
                    func(index);
                },btn2: function(index, layero){
                    layer.close(index);
                }
            });
        }
    });
}

/**
 * 打开一个弹出框, 带审核按钮
 * @param html
 * @param title
 * @param width
 * @param height
 */
App.prototype.openAuditBtnDialog = function(url, title, width, height, func){
    $.ajax({
        cache: true,
        type: "POST",
        url: url,
        async: false,
        success: function (data) {
            var dialogWidth = app.getWindowWidthSize() * width + 'px';
            var dialogHeight = app.getWindowHeightSize() * height + 'px';
            if (width > 5) {
                dialogWidth = width + 'px';
            }
            if (height > 5) {
                dialogHeight = height + 'px';
            }
            var index = layer.open({
                type: 1,
                title: title,
                area: [dialogWidth, dialogHeight],
                shadeClose: true, //点击遮罩关闭
                content: data,
                shadeClose: false,
                btn: ['审核通过', '不通过', '关闭'],
                yes: function (index, layero) {
                    func(index, 0);
                }, btn2: function (index, layero) {
                    func(index, 1);
                }, btn3: function (index, layero) {
                    layer.close(index);
                }
            });
        }
    });
}

/**
 * 复选框全选
 */
App.prototype.checkAll = function(name){
	var cheArr = document.getElementsByName(name);
    var checkValues="";
    for(var i = 0;i < cheArr.length;i ++){
        cheArr[i].checked = true;
        checkValues += $(cheArr[i]).val()+",";
    }
}

/**
 * 复选框反选
 */
App.prototype.checkNAll = function(name){
	var cheArr = document.getElementsByName(name);
    var checkValues="";
    for(var i = 0;i < cheArr.length;i ++){
        if( cheArr[i].checked){
            cheArr[i].checked = false;
        }else{
            cheArr[i].checked = true;
        }
    }
}

/**
 * 树
 */
App.prototype.tree = function(obj, zNodes){
	var setting = {
		check: {
			enable: true,
			nocheckInherit: true,
            chkboxType : { "Y" : "ps", "N" : "ps" }
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};


	function nocheckNode(e) {
		var zTree = $.fn.zTree.getZTreeObj("myTree"),
		nocheck = e.data.nocheck,
		nodes = zTree.getSelectedNodes();
		if (nodes.length == 0) {
			alert("请先选择一个节点");
		}

		for (var i=0, l=nodes.length; i<l; i++) {
			nodes[i].nocheck = nocheck;
			zTree.updateNode(nodes[i]);
		}
	}

	$(document).ready(function(){
		$.fn.zTree.init(obj, setting, zNodes);
		$("#nocheckTrue").bind("click", {nocheck: true}, nocheckNode);
		$("#nocheckFalse").bind("click", {nocheck: false}, nocheckNode);
	});
}


/**
 * 得到浏览器的宽
 * @returns {{x: number, y: number}}
 */
App.prototype.getWindowWidthSize = function() {
    return app.getWindowSize().x;
}
/**
 * 得到浏览器的高
 * @returns {{x: number, y: number}}
 */
App.prototype.getWindowHeightSize = function() {
    return app.getWindowSize().y;
}
/**
 * 得到浏览器的高和宽
 * @returns {{x: number, y: number}}
 */
App.prototype.getWindowSize = function() {
    var client = {
        x:0,
        y:0
    };

    if(typeof document.compatMode != 'undefined' && document.compatMode == 'CSS1Compat') {
        client.x = document.documentElement.clientWidth;
        client.y = document.documentElement.clientHeight;
    } else if(typeof document.body != 'undefined' && (document.body.scrollLeft || document.body.scrollTop)) {
        client.x = document.body.clientWidth;
        client.y = document.body.clientHeight;
    }
    return client;
}

/**
 * 点击查询按钮  btnObj为undefined，说明是从点击页码跳转来的，否则是点击查询按钮来的
 * @param obj
 * @param url
 */
App.prototype.searchFormPage = function(obj, url){
    //$("#rows").val($("#rows_txt").val());
    if(url != "") {
        var params = {};
        if(null != obj){
            params = obj.serialize();
        }
        $.ajax({
            cache: true,
            type: "POST",
            url: url,
            async: false,
            data: params,
            success: function (data) {
                $("#contentPage").html(data);
            }
        });
    }
}

/**
 * 常用的新增数据的操作
 * @param openUrl
 * @param doUrl
 * @param params
 * @param title
 * @param width
 * @param height
 */
App.prototype.add = function(url, params, url2, params2){
    $.ajax({
        cache: true,
        type: "POST",
        url:url,
        data:params,
        async: false,
        success: function(data) {
            if(data.state == 0){
                app.clickResources(url2, params2);
            }else{
                app.msg(data.msg, 1);
            }
        }
    });
}

/**
 * 常用的新增数据的操作
 * @param url
 * @param params
 * @param btnId
 * @param callBack
 */
App.prototype.addAjax = function(url,params,btnId,callBack){
    $("#"+btnId).button('loading');
    $.ajax({
        cache: true,
        type: "POST",
        url:url,
        data:params,
        async: false,
        success: function(data) {
            $("#"+btnId).button('reset');
            if(data.state == 0){
                app.msg('提交成功', 0);
                if(callBack){
                    callBack(data.data);
                }
            }else{
                app.msg(data.msg, 1);
            }
        },
        error:function(data){
            $("#"+btnId).button('reset');
            app.msg('服务器异常', 0);
        }
    });
}
/**
 * 常用的新增数据的操作
 * @param url
 * @param params
 * @param isShow
 * @param callBack
 */
App.prototype.getAjaxData = function(url,params,isShow,callBack){
    $.ajax({
        cache: true,
        type: "POST",
        url:url,
        data:params,
        async: false,
        success: function(data) {
            if(data.state == 0){
                if(callBack){
                    callBack(data.data);
                }
            }else{
                app.msg(data.msg, 1);
            }
        },
        error:function(data){
            app.msg('服务器异常', 0);
        }
    });
}
/**
 * 新增有文件上传的数据操作
 * @param url
 * @param formId
 * @param index
 */
App.prototype.addForFile = function(url, formId, index){
    $("#"+formId).ajaxSubmit({
        url : url,
        dataType : 'json',
        success : function(result, statusText){
            if(0 == result.state) {
                if(typeof (result.str) != "undefined") {
                    if ("" == result.str) {
                        app.msg("提交成功！", 0);
                        layer.close(index);
                        $("#searchBtn").click();
                        return 0;
                    } else {
                        app.msg(result.str, 1);
                        return 1;
                    }
                }else{
                    app.msg("提交成功！", 0);
                    layer.close(index);
                    $("#searchBtn").click();
                    return 0;
                }
            }
            if(1 == result.state) {
                app.msg(result.msg, 1);
                return 1;
            }
        }
    });
}

/**
 * 常用的修改数据的操作
 * @param url
 * @param params
 */
App.prototype.edit = function(url, params, url2, params2){
    $.ajax({
        cache: true,
        type: "POST",
        url:url,
        data:params,
        async: false,
        success: function(data) {
            if(data.state == 0){
                if("undefined" != typeof (data.msg) && "" != data.msg){
                    app.msg(data.msg, 0);
                }else{
                    app.msg("操作成功！", 0);
                }
                app.clickResources(url2, params2);
            }else{
                app.msg(data.msg, 1);
            }
        }
    });
}

/**
 * 常用的删除数据的操作
 * @param confirmStr
 * @param url
 * @param btnObj
 */
App.prototype.del = function(confirmStr, url, params, url2, params2){
    app.confirm(confirmStr, function(index){
        $.ajax({
            url: url,
            method: 'POST',
            async: false,
            data: params,
            success: function (data) {
                if (data.state == 0) {
                    layer.close(index);
                    app.clickResources(url2, params2);
                } else {
                    app.msg(data.msg, 1);
                }
            }
        });
    });
}

/**
 * 常用的操作数据的操作
 * @param confirmStr
 * @param url
 * @param btnObj
 */
App.prototype.operator = function(confirmStr, url, params){
    app.confirm(confirmStr, function(){
        $.ajax({
            url:url,
            method : 'POST',
            async:false,
            data:params,
            success:function(data){
                if(data.state == 0){
                    app.msg("操作成功！", 0);
                }else {
                    app.msg(data.msg, 1);
                }
            }
        });
    });
}