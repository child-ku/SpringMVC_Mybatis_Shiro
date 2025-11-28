<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8" />
    <title>预订审批 — 会议室预订系统</title>
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
            <h2>预订审批</h2>
            <hr>
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>会议室名称</th>
                    <th>预订人</th>
                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>会议目的</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="approvalList">
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
    function loadApprovalList() {
        $.post("${basePath}/bookingApproval/dataList.shtml", {}, function(result) {
            if (result.status === 200) {
                var html = "";
                $.each(result.list, function(index, booking) {
                    var statusText = '';
                    var statusClass = '';
                    switch(booking.status) {
                        case 1:
                            statusText = "待审批";
                            statusClass = "label-warning";
                            break;
                        case 2:
                            statusText = "已通过";
                            statusClass = "label-success";
                            break;
                        case 3:
                            statusText = "已拒绝";
                            statusClass = "label-danger";
                            break;
                        case 4:
                            statusText = "已取消";
                            statusClass = "label-default";
                            break;
                    }
                    html += "<tr>";
                    html += "<td>" + booking.id + "</td>";
                    html += "<td>" + booking.conferenceRoomName + "</td>";
                    html += "<td>" + booking.userName + "</td>";
                    html += "<td>" + booking.startTime + "</td>";
                    html += "<td>" + booking.endTime + "</td>";
                    html += "<td>" + booking.purpose + "</td>";
                    html += "<td><label class='label " + statusClass + "'>" + statusText + "</label></td>";
                    html += "<td>";
                    if (booking.status === 1) {
                        html += "<button class='btn btn-success btn-sm' onclick='approveBooking(" + booking.id + ", 2)'>通过</button> ";
                        html += "<button class='btn btn-danger btn-sm' onclick='approveBooking(" + booking.id + ", 3)'>拒绝</button>";
                    }
                    html += "</td>";
                    html += "</tr>";
                });
                $("#approvalList").html(html);
            }
        });
    }

    function approveBooking(id, status) {
        var actionText = status === 2 ? "通过" : "拒绝";
        if (confirm("确定要" + actionText + "这个预订吗？")) {
            $.post("${basePath}/bookingApproval/approve.shtml", {id: id, status: status}, function(result) {
                if (result.status === 200) {
                    layer.msg(result.message);
                    loadApprovalList();
                } else {
                    layer.msg(result.message);
                }
            });
        }
    }

    $(function() {
        loadApprovalList();
    });
</script>
</body>
</html>