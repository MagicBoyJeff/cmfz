<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"  %>

<script>
    $(function () {
        $("#testlist").jqGrid(
            {
                url: "${pageContext.request.contextPath}/banner/showAllBanner",
                editurl: "${pageContext.request.contextPath}/banner/edit",
                datatype: "json",
                colNames: ["标题", "是否展示", "头像", "描述", "创建时间"],
                colModel: [
                    {name: "title", editable: true, align: "center"},
                    {
                        name: "status", align: "center",
                        formatter: function (cellvalue, options, row) {
                            if (cellvalue == 1) {
                                //展示
                                return "<button type='button' class='button btn-success' onclick='updateStatus(\"" + row.id + "\",\"" + cellvalue + "\")'>展示 </button>";
                            } else {
                                //不展示
                                return "<button type='button' class='button btn-danger' onclick='updateStatus(\"" + row.id + "\",\"" + cellvalue + "\")'>不展示</button>";
                            }
                        }
                    },
                    {
                        name: "img_pic", editable: true, edittype: "file", align: "center",
                        formatter: function (cellvalue) {
                            return "<img style='width:60px;' src='${pageContext.request.contextPath}/upload/img/" + cellvalue + "'>"
                        }
                    },
                    {name: "description", editable: true, align: "center"},
                    {name: "create_date"}
                ],
                styleUI: "Bootstrap",
                pager: "#bannerPager",
                rowNum: 3,
                height: "60%",
                rowList: [3, 5, 7, 10, 20],
                viewrecords: true,
                autowidth: true,
                multiselect: true
            }).jqGrid("navGrid", "#bannerPager", {
                addtext: "添加", deltext: "删除", edittext: "修改", search: false
            },
            {//修改
                closeAfterEdit: true,
                afterSubmit: function (response) {
                    if (response.responseJSON.id != '') {
                        var bannerId = response.responseJSON.id;
                        var msg = response.responseJSON.msg;
                        $.ajaxFileUpload({
                            url: "${pageContext.request.contextPath}/banner/upload",
                            type: "post",
                            fileElementId: "img_pic",
                            data: {bannerId: bannerId},
                            success: function () {
                                $("#testlist").trigger("reloadGrid");
                                $("#bannerMsgId").show().html(msg);
                                setTimeout(function () {
                                    $("#bannerMsgId").hide()
                                }, 5000);
                            }
                        });
                    }
                    return response;
                }
            },
            {//添加
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var bannerId = response.responseJSON.bannerId;
                    var msg = response.responseJSON.msg;
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/banner/upload",
                        type: "post",
                        fileElementId: "img_pic",
                        data: {bannerId: bannerId},
                        success: function () {
                            $("#testlist").trigger("reloadGrid");
                            $("#bannerMsgId").show().html(msg);
                            setTimeout(function () {
                                $("#bannerMsgId").hide()
                            }, 5000);
                        }
                    });
                    return response;
                }
            },
            {//删除
                afterComplete: function (response) {
                    var msg = response.responseJSON.msg;
                    $("#bannerMsgId").show().html(msg);
                    setTimeout(function () {
                        $("#bannerMsgId").hide()
                    }, 5000);
                }
            }
        );
    });

    //修改状态
    function updateStatus(id, status) {
        if (status == 1) {
            //修改状态为2
            $.ajax({
                url: "${pageContext.request.contextPath}/banner/updateStatus",
                type: "post",
                dataType: "json",
                data: {"id": id, "status": "2"},
                success: function () {
                    //刷新页面
                    $("#testlist").trigger("reloadGrid");
                }
            })
        } else {
            //修改状态为1
            $.ajax({
                url: "${pageContext.request.contextPath}/banner/updateStatus",
                type: "post",
                dataType: "json",
                data: {"id": id, "status": "1"},
                success: function () {
                    //刷新页面
                    $("#testlist").trigger("reloadGrid");
                }
            })
        }
    }


    function outBannerMsg() {
        location.href="${pageContext.request.contextPath}/banner/easyPoiExport";
    }
</script>
<nav class="navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-2" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
            </button>
            <a class="navbar-brand" href="#">轮播图管理</a>
        </div>
    </div>
</nav>
<ul class="nav nav-tabs">
    <li role="presentation" class="active"><a href="#">轮播图信息</a></li>
    <li ><a href="#" onclick="outBannerMsg()">导出轮播图信息</a></li>
</ul>

<table id="testlist"></table>
<div id="bannerPager" style="height: 50px"></div>
<div id="bannerMsgId" class="alert alert-warning alert-dismissible" role="alert" style="display:none">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span>
    </button>
    <strong>Warning!</strong>
</div>


