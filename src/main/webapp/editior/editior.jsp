<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<script charset="utf-8" src="../kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="../kindeditor/lang/zh-CN.js"></script>
<script charset="utf-8" src="../kindeditor/kindeditor-all-min.js"></script>
<script>
    KindEditor.ready(function (K) {
        K.create('#editor_id',{
        uploadJson : "${pageContext.request.contextPath}/kindeditor/uploadImg",
            fileManagerJson : "${pageContext.request.contextPath}/kindeditor/getAll",
            allowFileManager : true,
            filePostName: "img"
        });
    });

</script>

<div align="center">
        <form action="${pageContext.request.contextPath}/kindeditor/addKindeditor" method="post">
        <textarea id="editor_id" name="content" style="width:700px;height:300px;">
                请输入文章内容
        </textarea>
                <input type="submit" value="按钮"/>
        </form>

</div>

