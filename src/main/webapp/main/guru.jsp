<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<script>
    $(function () {
        $("#testlist").jqGrid(
            {
                url: "${pageContext.request.contextPath}/guru/show",
                datatype: "json",
                styleUI: "Bootstrap",
                pager: "#bannerPager",
                rowNum: 3,
                height: "60%",
                rowList: [3, 5, 7, 10, 20],
                viewrecords: true,
                autowidth: true,
                multiselect: true,
                colNames: [ "创建时间","法号","头像","状态"],
                colModel: [
                    {name: "create_date"},
                    {name: "dharma", editable: true, align: "center"},
                    {name: "head_pic", editable: true, edittype: "file", align: "center",
                        formatter: function (cellvalue) {
                            return "<img style='width:60px;' src='${pageContext.request.contextPath}/upload/img/" + cellvalue + "'>"
                        }
                    },
                    {name: "status", align: "center",
                        formatter: function (cellvalue, options, row) {
                            if (cellvalue == 1) {
                                //展示
                                return "<button type='button' class='button btn-success' onclick='updateStatus(\"" + row.id + "\",\"" + cellvalue + "\")'>展示 </button>";
                            } else {
                                //不展示
                                return "<button type='button' class='button btn-danger' onclick='updateStatus(\"" + row.id + "\",\"" + cellvalue + "\")'>不展示</button>";
                            }
                        }
                    }]
            }).jqGrid("navGrid", "#bannerPager", {
                addtext: "添加", deltext: "删除", edittext: "修改", search: false
            },
            {//修改

            },
            {//添加

            },
            {//删除

            }
        );
    });
    //修改状态
    function updateStatus(id, status) {
        if (status == 1) {
            //修改状态为2
            $.ajax({
                url: "${pageContext.request.contextPath}/guru/updateGuru",
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
                url: "${pageContext.request.contextPath}/guru/updateGuru",
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
</script>
<nav class="navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-2" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
            </button>
            <a class="navbar-brand" href="#">上师管理</a>
        </div>
    </div>
</nav>
<ul class="nav nav-tabs">
    <li role="presentation" class="active"><a href="#">上师详情信息</a></li>
</ul>

<table id="testlist"></table>
<div id="bannerPager" style="height: 50px"></div>



