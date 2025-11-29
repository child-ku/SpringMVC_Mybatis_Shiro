<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="utf-8" />
		<title>文档管理系统</title>
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
		<link   rel="icon" href="https://open.sojson.com/favicon.ico" type="image/x-icon" />
		<link   rel="shortcut icon" href="https://open.sojson.com/favicon.ico" />
		<link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
		<link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
		<script  src="http://open.sojson.com/common/jquery/jquery1.8.3.min.js"></script>
		<script  src="${basePath}/js/common/layer/layer.js"></script>
		<script  src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</head>
	<body data-target="#one" data-spy="scroll">
		<@_top.top 1/>
		<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
			<div class="row">
				<@_left.user 1/>
				<div class="col-md-10">
					<h2>文档管理</h2>
					<hr>
					<div class="row">
						<div class="col-md-4">
							<div class="panel panel-default">
								<div class="panel-heading">目录结构</div>
								<div class="panel-body">
									<div id="directory-tree"></div>
								</div>
							</div>
						</div>
						<div class="col-md-8">
							<div class="panel panel-default">
								<div class="panel-heading">文档列表</div>
								<div class="panel-body">
									<div id="document-list"></div>
								</div>
							</div>
						</div>
					</div>
					<#--/row-->
				</div>
			</div>
			<#--/row-->
		</div>
		
	</body>
</html>
