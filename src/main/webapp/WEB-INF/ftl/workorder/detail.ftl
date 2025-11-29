<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8" />
    <title>工单详情</title>
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
                <h2>工单详情</h2>
                <hr>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">${workOrder.title}</h3>
                    </div>
                    <div class="panel-body">
                        <table class="table table-bordered">
                            <tr>
                                <th>工单编号</th>
                                <td>${workOrder.id}</td>
                            </tr>
                            <tr>
                                <th>工单状态</th>
                                <td>
                                    <#if workOrder.statusId == 1>
                                        <span class="label label-info">待处理</span>
                                    <#elseif workOrder.statusId == 2>
                                        <span class="label label-primary">处理中</span>
                                    <#elseif workOrder.statusId == 3>
                                        <span class="label label-success">已完成</span>
                                    <#else>
                                        <span class="label label-default">未知状态</span>
                                    </#if>
                                </td>
                            </tr>
                            <tr>
                                <th>创建时间</th>
                                <td>${workOrder.createTime?string('yyyy-MM-dd HH:mm')}</td>
                            </tr>
                            <tr>
                                <th>更新时间</th>
                                <td>${workOrder.updateTime?string('yyyy-MM-dd HH:mm')}</td>
                            </tr>
                            <tr>
                                <th>工单内容</th>
                                <td>${workOrder_content}</td>
                            </tr>
                        </table>
                        <div class="text-right">
                            <a href="${basePath}/workorder/edit?id=${workOrder.id}" class="btn btn-warning">编辑工单</a>
                            <a href="${basePath}/workorder/index" class="btn btn-default">返回工单列表</a>
                        </div>
                    </div>
                </div>
                
                <!-- 工单评论 -->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">工单评论</h3>
                    </div>
                    <div class="panel-body">
                        <form id="commentForm" method="post" action="${basePath}/workorder/addComment">
                            <input type="hidden" name="workOrderId" value="${workOrder.id}" />
                            <div class="form-group">
                                <label for="commentContent">评论内容</label>
                                <textarea class="form-control" id="commentContent" name="commentContent" rows="3" required></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary">提交评论</button>
                        </form>
                        
                        <hr>
                        
                        <div id="commentsList">
                            <#if comments?? && comments?size > 0>
                                <#list comments as comment>
                                    <div class="media">
                                        <div class="media-body">
                                            <h4 class="media-heading">${comment.userNickname} <small>${comment.commentTime?string('yyyy-MM-dd HH:mm')}</small></h4>
                                            ${comment.commentContent}
                                        </div>
                                    </div>
                                    <hr>
                                </#list>
                            <#else>
                                <p class="text-center">暂无评论</p>
                            </#if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        $(function() {
            $('#commentForm').submit(function(e) {
                e.preventDefault();
                var form = $(this);
                $.ajax({
                    url: form.attr('action'),
                    type: form.attr('method'),
                    data: form.serialize(),
                    success: function(result) {
                        if (result.status == 200) {
                            layer.msg('评论提交成功', {icon: 1});
                            // 刷新评论列表
                            setTimeout(function() {
                                window.location.reload();
                            }, 1500);
                        } else {
                            layer.msg('评论提交失败：' + result.message, {icon: 2});
                        }
                    },
                    error: function() {
                        layer.msg('评论提交失败，请稍后重试', {icon: 2});
                    }
                });
            });
        });
    </script>
</body>
</html>