<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<script charset="utf-8" src="../kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="../kindeditor/lang/zh-CN.js"></script>
<script charset="utf-8" src="../kindeditor/kindeditor-all-min.js"></script>
<script>
    KindEditor.create('#editor_id',{
            uploadJson:"${pageContext.request.contextPath}/kindeditor/uploadImg",   //指定上传图片的路径
            filePostName:"img",   //设置上传图片的名称
            allowFileManager:true, //getAll
            fileManagerJson:"${pageContext.request.contextPath}/kindeditor/getAll", //指定浏览远程图片的路径
            afterBlur:function(){   //在kindeditor失去焦点之后执行的内容
                this.sync();  //将kindeditor中的内容同步到表单中
            }
        }
    );
</script>
<script>
    $(function () {
        $("#atctable").jqGrid(
            {
                url: "${pageContext.request.contextPath}/article/showArt",
                editurl: "${pageContext.request.contextPath}/article/delArit",
                datatype: "json",
                colNames: ["标题", "作者", "描述", "创建时间"],
                colModel: [
                    {name: "title", editable: true, align: "center"},
                    {name: "author", editable: true, align: "center"},
                    {name: "content", editable: true, align: "center"},
                    {name: "create_date"}
                ],
                styleUI: "Bootstrap",
                pager: "#atcpage",
                rowNum: 8,
                height: "60%",
                rowList: [3, 5, 7, 10, 20],
                viewrecords: true,
                autowidth: true,
                multiselect: true
            }).jqGrid("navGrid", "#atcpage", {
                addtext: "添加", deltext: "删除", edittext: "修改", search: false
            },
            {//修改
            },
            {//添加
                closeAfterAdd: true
            },
            {//删除
            });

        //展示文章信息
        $("#btn1").click(function () {
            //只读属性，选择行的id
            var rowId = $("#atctable").jqGrid("getGridParam","selrow");

            //判断是否选中一行
            if(rowId!= null){
                //根据行Id获取行数据
                var row = $("#atctable").jqGrid("getRowData",rowId);

                //给title input设置数据
                $("#title").val(row.title);

                //给 author  input框设置数据
                $("#author").val(row.author);

                //给富文本编辑器设置内容
                KindEditor.html("#editor_id",row.content);

                //展示模态框
                $("#MyModals").modal("show");

                /*设置按钮*/
                $("#MyFooter").html("<button class='btn btn-primary' onclick='updateArticle(\""+rowId+"\")' >提交</button>" +
                    "<button class='btn btn-primary' data-dismiss='modal'>关闭</button>");
            }else{
                alert("请选择查看内容");
            }
        });

        //添加文章  按钮
        $("#btn2").click(function () {

            //清空表单
            $("#MyForm")[0].reset();

            //清空kindEditor
            KindEditor.html("#editor_id","");

            //展示模态框
            $("#MyModals").modal("show");


            /*设置按钮*/
            $("#MyFooter").html("<button class='btn btn-primary' onclick='addArticle()' >提交</button>" +
                "<button class='btn btn-primary' data-dismiss='modal'>关闭</button>");
        })

    });


    //点击添加按钮 添加文章
    function addArticle(){
        $.ajax({
            url:"${pageContext.request.contextPath}/article/addArti",
            type:"post",
            dataType:"json",
            data:$("#MyForm").serialize(),
            success:function(){
                //关闭模态框
                $("#MyModals").modal('hide');
                //刷新页面
                $("#atctable").trigger("reloadGrid");
            }
        });
    }
    function updateArticle(rowId) {
        $.ajax({
            url:"${pageContext.request.contextPath}/article/updateAtri?id="+rowId,
            type:"post",
            dataType:"json",
            data:$("#MyForm").serialize(),
            success:function () {
                //关闭模态框
                $("#MyModals").modal('hide');
                //刷新页面
                $("#atctable").trigger("reloadGrid");
            }
        });
    }
    /*设置按钮*/
   /* $("#btn3").click(function () {
        //只读属性，选择行的id
        var rowId = $("#atctable").jqGrid("getGridParam","selrow");

        //判断是否选中一行
        if(rowId!= null) {
            //根据行Id获取行数据
            $("#MyFooter").html("<button class='btn btn-primary' onclick='delArticle()' >确认删除</button>" +
                "<button class='btn btn-primary' data-dismiss='modal'>取消</button>");
        }else {
            alert("请选择要删除的一行");

        }
    });*/


    function easypoiArticle() {
        location.href="${pageContext.request.contextPath}/article/easypoiArticle";
    }
</script>


<%--初始化面板--%>
<div class="panel panel-danger">
    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>文章信息</h2>
    </div>

    <ul class="nav nav-tabs" >
        <li class="active"><a href="#">文章信息</a></li>
    </ul>

    <div class="panel panel-body">
        <button id="btn1" class="btn btn-danger">文章信息</button>&emsp;
        <button id="btn2" class="btn btn-success">添加文章</button>&emsp;
        <button id="btn3" class="btn btn-success" onclick="easypoiArticle()">导出文章</button>&emsp;
        <%--<button id="btn3" class="btn btn-default">删除文章</button>--%>
    </div>
</div>


<%--初始表单--%>
<table id="atctable" />

<%--分页工具栏--%>
<div id="atcpage" style="height: 50px" />

<%--初始化模态框--%>
<div id="MyModals" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:730px;">
            <%--模态框标题--%>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="gridSystemModalLabel">文章信息</h4>
            </div>
            <%--模态框内容--%>
            <div class="modal-body" align="center">
                <%--放一个表单--%>
                <form id="MyForm" class="form-horizontal">
                    <div class="input-group">
                        <span class="input-group-addon" id="basic-addon1">标题</span>
                        <input id="title" name="title" type="text" class="form-control" aria-describedby="basic-addon1" style="width: 200px">
                    </div></br></br>

                    <div class="input-group">
                        <span class="input-group-addon" id="basic-addon2">作者</span>
                        <input id="author" name="author" type="text" class="form-control" aria-describedby="basic-addon1" style="width: 200px">
                    </div></br></br>
                    <%--kindeditor输入框--%>
                    <div align="center" class="input-group">
                        <%--在需要显示编辑器的位置添加textarea输入框--%>
                        <textarea id="editor_id" name="content" style="width:700px;height:300px;"></textarea>
                    </div>
                </form>
            </div>

            <%--模态框按钮--%>
            <div class="modal-footer" id="MyFooter">
            </div>
        </div>
    </div>
</div>




