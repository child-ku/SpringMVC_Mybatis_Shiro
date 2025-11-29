<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8" />
    <title>编辑工单</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
    <link rel="icon" href="https://open.sojson.com/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="https://open.sojson.com/favicon.ico" />
    <link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
    <script src="http://open.sojson.com/common/jquery/jquery1.8.3.min.js"></script>
    <script src="${basePath}/js/common/layer/layer.js"></script>
    <script src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body data-target="#one" data-spy="scroll">
    <@_top.top 1/>
    <div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
        <div class="row">
            <@_left.user 1/>
            <div class="col-md-10">
                <h2>编辑工单</h2>
                <hr>
                <form id="workOrderForm" method="post" action="${basePath}/workorder/update">
                    <input type="hidden" name="id" value="${workOrder.id}" />
                    <div class="form-group">
                        <label for="title">工单标题</label>
                        <input type="text" class="form-control" id="title" name="title" value="${workOrder.title}" placeholder="请输入工单标题" required>
                    </div>
                    <div class="form-group">
                        <label for="content">工单内容</label>
                        <textarea class="form-control" id="content" name="content" rows="5" placeholder="请输入工单内容" required>${workOrder.content}</textarea>
                    </div>
                    <div class="form-group">
                        <label for="statusId">工单状态</label>
                        <select class="form-control" id="statusId" name="statusId" required>
                            <option value="1" <#if workOrder.statusId == 1>selected</#if>>待处理</option>
                            <option value="2" <#if workOrder.statusId == 2>selected</#if>>处理中</option>
                            <option value="3" <#if workOrder.statusId == 3>selected</#if>>已完成</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">更新工单</button>
                    <a href="${basePath}/workorder/index" class="btn btn-default">返回工单列表</a>
                </form>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        $(function() {
            $('#workOrderForm').submit(function(e) {
                e.preventDefault();
                var form = $(this);
                $.ajax({
                    url: form.attr('action'),
                    type: form.attr('method'),
                    data: form.serialize(),
                    success: function(result) {
                        if (result.status == 200) {
                            layer.msg('工单更新成功', {icon: 1});
                            setTimeout(function() {
                                window.location.href = '${basePath}/workorder/index';
                            }, 1500);
                        } else {
                            layer.msg('工单更新失败：' + result.message, {icon: 2});
                        }
                    },
                    error: function() {
                        layer.msg('工单更新失败，请稍后重试', {icon: 2});
                    }
                });
            });
        });
    </script>
</body>
</html>