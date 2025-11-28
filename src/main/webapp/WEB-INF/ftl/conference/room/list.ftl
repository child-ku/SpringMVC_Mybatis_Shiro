<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8" />
    <title>会议室列表 — 会议室预订系统</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
    <link rel="icon" href="https://open.sojson.com/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="https://open.sojson.com/favicon.ico" />
    <link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
</head>
<body data-target="#one" data-spy="scroll">

<@_top.top 1/>
<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
    <#--row-->
    <div class="row">
        <@_left.user 2/>
        <div class="col-md-10">
            <h2>会议室列表</h2>
            <hr>
            <button class="btn btn-success" onclick="addRoom()">添加会议室</button>
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>名称</th>
                    <th>容量</th>
                    <th>位置</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="roomList">
                </tbody>
            </table>
        </div>
    </div>
    <#--/row-->
</div>

<script src="http://open.sojson.com/common/jquery/jquery1.8.3.min.js"></script>
<script src="${basePath}/js/common/layer/layer.js"></script>
<script src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${basePath}/js/shiro.demo.js"></script>
<script>
    function loadRoomList() {
        $.post("${basePath}/conferenceRoom/dataList.shtml", {}, function(result) {
            if (result.status === 200) {
                var html = "";
                $.each(result.list, function(index, room) {
                    var statusText = room.status === 1 ? "可用" : "不可用";
                    var statusClass = room.status === 1 ? "label-success" : "label-danger";
                    html += "<tr>";
                    html += "<td>" + room.id + "</td>";
                    html += "<td>" + room.name + "</td>";
                    html += "<td>" + room.capacity + "</td>";
                    html += "<td>" + room.location + "</td>";
                    html += "<td><label class='label " + statusClass + "'>" + statusText + "</label></td>";
                    html += "<td>";
                    html += "<button class='btn btn-primary btn-sm' onclick='editRoom(" + room.id + ")'>编辑</button> ";
                    html += "<button class='btn btn-danger btn-sm' onclick='deleteRoom(" + room.id + ")'>删除</button>";
                    html += "</td>";
                    html += "</tr>";
                });
                $("#roomList").html(html);
            }
        });
    }

    function addRoom() {
        location.href = "${basePath}/conferenceRoom/add.shtml";
    }

    function editRoom(id) {
        location.href = "${basePath}/conferenceRoom/edit.shtml?id=" + id;
    }

    function deleteRoom(id) {
        if (confirm("确定要删除这个会议室吗？")) {
            $.post("${basePath}/conferenceRoom/delete.shtml", {id: id}, function(result) {
                if (result.status === 200) {
                    layer.msg(result.message);
                    loadRoomList();
                } else {
                    layer.msg(result.message);
                }
            });
        }
    }

    $(function() {
        loadRoomList();
    });
</script>
</body>
</html>