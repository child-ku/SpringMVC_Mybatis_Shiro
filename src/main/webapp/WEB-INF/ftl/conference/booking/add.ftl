<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8" />
    <title>添加预订 — 会议室预订系统</title>
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
            <h2>添加预订</h2>
            <hr>
            <form class="col-md-8" method="post" action="${basePath}/booking/save.shtml" id="addForm">
                <div class="form-group">
                    <label>会议室</label>
                    <select class="form-control" name="conferenceRoomId" id="conferenceRoomId" required>
                        <option value="">请选择会议室</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>开始时间</label>
                    <input type="datetime-local" class="form-control" name="startTime" id="startTime" required>
                </div>
                <div class="form-group">
                    <label>结束时间</label>
                    <input type="datetime-local" class="form-control" name="endTime" id="endTime" required>
                </div>
                <div class="form-group">
                    <label>会议目的</label>
                    <textarea class="form-control" name="purpose" placeholder="请输入会议目的" required></textarea>
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
    function loadConferenceRooms() {
        $.post("${basePath}/conferenceRoom/dataList.shtml", {}, function(result) {
            if (result.status === 200) {
                var html = "<option value=''>请选择会议室</option>";
                $.each(result.list, function(index, room) {
                    if (room.status === 1) {
                        html += "<option value='" + room.id + "'>" + room.name + "（" + room.capacity + "人）</option>";
                    }
                });
                $("#conferenceRoomId").html(html);
            }
        });
    }

    function checkConflict() {
        var roomId = $("#conferenceRoomId").val();
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();

        if (roomId && startTime && endTime) {
            $.post("${basePath}/booking/checkConflict.shtml", {
                conferenceRoomId: roomId,
                startTime: startTime,
                endTime: endTime
            }, function(result) {
                if (result.status === 200) {
                    if (result.data) {
                        layer.msg("该会议室在选定时间已被预订，请重新选择");
                        return false;
                    }
                } else {
                    layer.msg(result.message);
                    return false;
                }
            });
        }
        return true;
    }

    $("#addForm").submit(function(e) {
        e.preventDefault();
        if (!checkConflict()) {
            return;
        }
        var formData = $(this).serialize();
        $.post("${basePath}/booking/save.shtml", formData, function(result) {
            if (result.status === 200) {
                layer.msg(result.message);
                setTimeout(function() {
                    location.href = "${basePath}/booking/list.shtml";
                }, 1500);
            } else {
                layer.msg(result.message);
            }
        });
    });

    $(function() {
        loadConferenceRooms();
        $("#startTime, #endTime").change(function() {
            checkConflict();
        });
    });
</script>
</body>
</html>