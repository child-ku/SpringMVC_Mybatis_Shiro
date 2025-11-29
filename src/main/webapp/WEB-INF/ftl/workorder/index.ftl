<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8" />
    <title>工单管理系统</title>
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
                <h2>工单管理</h2>
                <hr>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">我的工单</h3>
                    </div>
                    <div class="panel-body">
                        <table class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>工单编号</th>
                                    <th>标题</th>
                                    <th>状态</th>
                                    <th>创建时间</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <#if workOrders?? && workOrders?size > 0>
                                    <#list workOrders as workOrder>
                                        <tr>
                                            <td>${workOrder.id}</td>
                                            <td>${workOrder.title}</td>
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
                                            <td>${workOrder.createTime?string('yyyy-MM-dd HH:mm')}</td>
                                            <td>
                                                <a href="${basePath}/workorder/detail?id=${workOrder.id}" class="btn btn-xs btn-primary">查看详情</a>
                                                <a href="${basePath}/workorder/edit?id=${workOrder.id}" class="btn btn-xs btn-warning">编辑</a>
                                                <a href="javascript:void(0);" onclick="deleteWorkOrder(${workOrder.id})" class="btn btn-xs btn-danger">删除</a>
                                            </td>
                                        </tr>
                                    </#list>
                                <#else>
                                    <tr>
                                        <td colspan="5" class="text-center">暂无工单</td>
                                    </tr>
                                </#if>
                            </tbody>
                        </table>
                        <div class="text-right">
                            <a href="${basePath}/workorder/add" class="btn btn-success">创建新工单</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        function deleteWorkOrder(id) {
            layer.confirm('确定要删除该工单吗？', {
                btn: ['确定','取消']
            }, function(){
                window.location.href = '${basePath}/workorder/delete?id=' + id;
            }, function(){
                layer.close();
            });
        }
    </script>
</body>
</html>