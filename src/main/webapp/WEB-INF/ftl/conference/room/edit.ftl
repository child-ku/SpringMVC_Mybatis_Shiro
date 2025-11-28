<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8" />
    <title>编辑会议室 — 会议室预订系统</title>
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
            <h2>编辑会议室</h2>
            <hr>
            <form class="col-md-8" method="post" action="${basePath}/conferenceRoom/update.shtml" id="editForm">
                <input type="hidden" name="id" value="${conferenceRoom.id}">
                <div class="form-group">
                    <label>会议室名称</label>
                    <input type="text" class="form-control" name="name" placeholder="请输入会议室名称" value="${conferenceRoom.name}" required>
                </div>
                <div class="form-group">
                    <label>容量</label>
                    <input type="number" class="form-control" name="capacity" placeholder="请输入会议室容量" value="${conferenceRoom.capacity}" required min="1">
                </div>
                <div class="form-group">
                    <label>位置</label>
                    <input type="text" class="form-control" name="location" placeholder="请输入会议室位置" value="${conferenceRoom.location}" required>
                </div>
                <div class="form-group">
                    <label>设备</label>
                    <input type="text" class="form-control" name="equipment" placeholder="请输入会议室设备，如：投影仪、白板等" value="${conferenceRoom.equipment}">
                </div>
                <div class="form-group">
                    <label>状态</label>
                    <select class="form-control" name="status">
                        <option value="1" ${conferenceRoom.status == 1 ? 'selected' : ''}>可用</option>
                        <option value="2" ${conferenceRoom.status == 2 ? 'selected' : ''}>不可用</option>
                    </select>
                </div>
                <div class="form-group">
                    <button class="btn btn-success pull-right" type="submit">保存</button>
                    <button class="btn btn-default pull-right" style="margin-right: 10px;" onclick="history.back()">取消</button>
                </div>
            </form>
        </div>
    </div>
    <#--/row-->
</div>

<script src="http://open.sojson.com/common/jquery/jquery1.8.3.min.js"></script>
<script src="${basePath}/js/common/layer/layer.js"></script>
<script src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${basePath}/js/shiro.demo.js"></script>
<script>
    $("#editForm").submit(function(e) {
        e.preventDefault();
        var formData = $(this).serialize();
        $.post("${basePath}/conferenceRoom/update.shtml", formData, function(result) {
            if (result.status === 200) {
                layer.msg(result.message);
                setTimeout(function() {
                    location.href = "${basePath}/conferenceRoom/list.shtml";
                }, 1500);
            } else {
                layer.msg(result.message);
            }
        });
    });
</script>
</body>
</html>