<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<script>
    $(function () {
        $("#testlist").jqGrid(
            {
                url: "${pageContext.request.contextPath}/user/showUser",
                editurl:"${pageContext.request.contextPath}/user/edit",
                datatype: "json",
                styleUI: "Bootstrap",
                pager: "#bannerPager",
                closeAfterAdd: true,
                rowNum: 6,
                height: "60%",
                rowList: [3, 5, 7, 10, 20],
                viewrecords: true,
                autowidth: true,
                multiselect: true,
                colNames: [ "创建时间","法号","地址","语录","电话号码","状态","密码","性别"],
                colModel: [
                    {name: "create_date"},
                    {name: "dname", editable: true, align: "center"},
                    {name: "address", editable: true, align: "center"},
                    {name: "sign", editable: true, align: "center"},
                    {name: "phone_num", editable: true, align: "center"},
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
                    },
                    {name: "password", editable: true, align: "center",type:"password"},
                    {name: "sex",editable: true,edittype:"select", editoptions:{value:{men:'men',women:'women'}}
                        }
                    ]
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
                url: "${pageContext.request.contextPath}/user/updateStatus",
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
                url: "${pageContext.request.contextPath}/user/updateStatus",
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
    function outUserMsg() {
       location.href="${pageContext.request.contextPath}/user/easyPoiExport";

       //也可以通过ajax进行导出
    }

</script>
<nav class="navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-2" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
            </button>
            <a class="navbar-brand" href="#">用户管理</a>
        </div>
    </div>
</nav>
<ul class="nav nav-tabs">
    <li role="presentation" class="active"><a href="#">用户信息</a></li>
    <li><a href="#" onclick="outUserMsg()">导出用户信息</a></li>
</ul>




<table id="testlist"></table>
<div id="bannerPager" style="height: 50px"></div>







