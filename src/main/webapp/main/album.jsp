<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<script>
    $(function () {
        pageInit();
    });

    function pageInit() {
        $("#albumlist").jqGrid(
            {
                url: "${pageContext.request.contextPath}/album/showAlbum",
                editurl: "${pageContext.request.contextPath}/album/edit",
                datatype: "json",
                multiselect: true,
                rowNum: 8,
                rowList: [8, 10, 20, 30],
                pager: '#albumPager',
                styleUI: "Bootstrap",
                viewrecords: true,
                autowidth: true,
                height: "60%",
                subGrid: true,//开启子表
                subGridRowExpanded: function (subGirdId, albumId) {
                    addSubGrid(subGirdId, albumId);
                },
                colNames: ['title', '评分', '作者', '章节数量', '播音', '内容简介', '发布日期', '封面'],
                colModel: [
                    {name: 'title', width: 90, editable: true},
                    {name: 'score', width: 100, editable: true},
                    {name: 'author', width: 80, align: "right", editable: true},
                    {name: 'count', width: 100},
                    {name: 'broadcast', width: 100, editable: true},
                    {name: 'brief', width: 100, editable: true},
                    {name: 'create_date', width: 100},
                    {
                        name: 'cover_pic', width: 100, edittype: "file", align: "center", editable: true,
                        formatter: function (cellvalue) {
                            return "<img style='width:60px;' src='${pageContext.request.contextPath}/upload/img/" + cellvalue + "'>"
                        }
                    }
                ]
            }).jqGrid("navGrid", "#albumPager", {
                addtext: "添加", deltext: "删除", edittext: "修改", search: false
            }, {//修改
                closeAfterEdit: true,
                afterSubmit: function (response) {
                    if (response.responseJSON.id != '') {
                        var albumId = response.responseJSON.id;
                        $.ajaxFileUpload({
                            url: "${pageContext.request.contextPath}/album/upload",
                            type: "post",
                            fileElementId: "cover_pic",
                            data: {albumId: albumId},
                            success: function () {
                                $("#albumlist").trigger("reloadGrid");
                            }
                        });
                    }
                    return response;
                }
            },
            {//添加
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var albumId = response.responseJSON.albumId;
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/album/upload",
                        type: "post",
                        fileElementId: "cover_pic",
                        data: {albumId: albumId},
                        success: function () {
                            $("#albumlist").trigger("reloadGrid");
                        }
                    });
                    return response;
                }
            });
    }

    function addSubGrid(subGirdId, albumId) {
        var subGridTableId = subGirdId + "table";
        var subGridPagerId = subGirdId + "pager";
        $("#" + subGirdId).html(
            "<table id= '" + subGridTableId + "' class=\"table table-striped\" ></table>" +
            "<div id= '" + subGridPagerId + "' style='height: 50px'></div>"
        );
        $("#" + subGridTableId).jqGrid(
            {
                url: "${pageContext.request.contextPath}/chapter/showChapter?albumId=" + albumId,
                editurl: "${pageContext.request.contextPath}/chapter/edit?albumId="+albumId,
                datatype: "json",
                height: 190,
                rowNum: 8,
                rowList: [8, 10, 20, 30],
                pager: "#" + subGridPagerId,
                styleUI: "Bootstrap",
                viewrecords: true,
                autowidth: true,
                multiselect: true,
                colNames: ["id", "albumId", "标题", "大小", "时长", "操作"],
                colModel: [
                    {name: 'id', width: 90},
                    {name: 'albumId', width: 100},
                    {name: 'title', width: 80, align: "right", editable: true},
                    {name: 'size', width: 100},
                    {name: 'duration', width: 100},
                    {
                        name: 'url', editable: true,edittype: "file",
                        formatter: function (cellValue, options, value) {
                            return "<a href='#' onclick=\"playAudio('" + cellValue + "')\"><span class='glyphicon glyphicon-play-circle' style='font-size: 40px'></span></a>&nbsp;&nbsp;&nbsp;" +
                                "<a href='#' onclick=\"downLoadAudio('" + cellValue + "')\"><span class='glyphicon glyphicon-download' style='font-size: 40px'></span></a>,"
                        }
                    }
                ]
            }).jqGrid("navGrid", "#" + subGridPagerId, {
            addtext: "添加", deltext: "删除", edittext: "修改", search: false
        }, {},
            {
                //添加
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var chapterId = response.responseJSON.chapterId;
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/chapter/upload",
                        type: "post",
                        fileElementId: "url",
                        data: {chapterId: chapterId},
                        success: function () {
                            $("#albumlist").trigger("reloadGrid");
                        }
                    });
                    return response;
                }
            },
            {
                //删除
            }
        );
    }

    function playAudio(cellValue) {
        $("#playAudio").modal("show");
        $("#audioId").attr("src", "${pageContext.request.contextPath}/upload/audio/" + cellValue);
    }

    function downLoadAudio(cellValue) {
        location.href = "${pageContext.request.contextPath}/chapter/download?audioName=" + cellValue;
    }


    function easyPoiAlbum() {
        location.href="${pageContext.request.contextPath}/album/easyPoiExportAlbum";
    }
</script>
<nav class="navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-2" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
            </button>
            <a class="navbar-brand" href="#">专辑与章节管理</a>
        </div>
    </div>
</nav>
<ul class="nav nav-tabs">
    <li role="presentation" class="active"><a href="#">专辑与章节信息</a></li>
    <li><a href="#" onclick="easyPoiAlbum()">导出专辑信息</a></li>
</ul>

<table id="albumlist"></table>
<div id="albumPager" style="height: 50px"></div>
<div id="playAudio" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <audio id="audioId" src="" controls></audio>
    </div>
</div>



