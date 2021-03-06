<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/resource/common_html_meat.jsp"%>
<%@ include file="/manage/system/common.jsp"%>
<%@ include file="/resource/common_html_validator.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resource/kindeditor-4.1.7/themes/default/default.css" />
</head>

<body>
	<s:form action="indexImg" namespace="/manage" theme="simple" enctype="multipart/form-data">
		<span id="pifeSpan" class="input-group-addon" style="display:none"><%=SystemManager.systemSetting.getImageRootPath()%></span>
		<table class="table table-bordered">
			<tr style="background-color: #dff0d8">
				<td colspan="2" style="background-color: #dff0d8;text-align: center;">
					<strong> 门 户 图 片 编 辑 </strong>
				</td>
			</tr>
			<tr style="display: none;">
				<th>id</th>
				<td><s:hidden name="e.id" label="id" id="idd"/></td>
			</tr>
			<tr>
				<th class="right">标题</th>
				<td style="text-align: left;"><s:textfield name="e.title" data-rule="标题:required;title;length[1~45];" 
						id="title" /></td>
			</tr>
			<tr>
				<th>图片地址</th>
				<td style="text-align: left;" colspan="3">
					<input type="button" name="filemanager" value="浏览图片" class="btn btn-warning"/>
					<s:textfield name="e.picture" id="picture" ccc="imagesInput" style="width: 600px;" data-rule="图片地址:required;picture;" />
					<s:if test="e.picture!=null">
						<a target="_blank" href="<%=SystemManager.systemSetting.getImageRootPath()%>/<s:property value="e.picture" />">
							<img style="max-width: 50px;max-height: 50px;" alt="" src="<%=SystemManager.systemSetting.getImageRootPath()%>/<s:property value="e.picture" />">
						</a>
					</s:if>
				</td>
			</tr>
			<tr>
				<th>广告链接</th>
				<td style="text-align: left;">
					<s:textfield name="e.link" id="link" />
				</td>
			</tr>
			<tr>
				<th>类型</th>
				<td style="text-align: left;">
					<s:select name="e.type" id="type" list="#{'0':'首页','1':'道具商城','2':'团购','3':'今日推荐'}"></s:select>
				</td>
			</tr>
			<tr>
				<th>排序</th>
				<td style="text-align: left;"><s:textfield name="e.sort" data-rule="排序:integer;sort;length[1~5];" 
						id="sort" /></td>
			</tr>
			<tr>
				<th>描述</th>
				<td style="text-align: left;"><s:textfield name="e.description" data-rule="排序:description;length[1~100];"  
						id="description" /></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
					<s:if test="e.id=='' or e.id==null">
<%-- 						<s:submit method="insert" value="新增" cssClass="btn btn-primary"/> --%>
<%-- 						<s:a method="insert" cssClass="btn btn-success"> --%>
<!-- 							<i class="icon-plus-sign icon-white"></i> 新增 -->
<%-- 						</s:a> --%>
						<button method="indexImg!insert.action" class="btn btn-success">
							<i class="icon-ok icon-white"></i> 新增
						</button>
					</s:if> 
					<s:else>
<%-- 						<s:submit method="update" value="保存" cssClass="btn btn-primary"/> --%>
<%-- 						<s:a method="update" cssClass="btn btn-success"> --%>
<!-- 							<i class="icon-ok icon-white"></i> 保存 -->
<%-- 						</s:a> --%>
						<button method="indexImg!update.action" class="btn btn-success">
							<i class="icon-ok icon-white"></i> 保存
						</button>
					</s:else>
			</tr>
		</table>
	</s:form>
<script charset="utf-8" src="<%=request.getContextPath() %>/resource/kindeditor-4.1.7/kindeditor-min.js"></script>
<script charset="utf-8" src="<%=request.getContextPath() %>/resource/kindeditor-4.1.7/lang/zh_CN.js"></script>
<script>
//删除图片主路径
function clearRootImagePath(picInput){
	var _pifeSpan = $("#pifeSpan").text();
	var _imgVal = picInput.val();
	picInput.val(_imgVal.substring(_imgVal.indexOf("/indexImg/")));
	//if(_imgVal && _imgVal.length>0 && _imgVal.indexOf(_pifeSpan)==0){
		//picInput.val(_imgVal.substring(_pifeSpan.length));
	//}
}

KindEditor.ready(function(K) {
	var editor = K.editor({
		fileManagerJson : '<%=request.getContextPath() %>/resource/kindeditor-4.1.7/jsp/file_manager_json.jsp?prefix=indexImg'
	});
	K('input[name=filemanager]').click(function() {
		var imagesInputObj = $(this).parent().children("input[ccc=imagesInput]");
		editor.loadPlugin('filemanager', function() {
			editor.plugin.filemanagerDialog({
				viewType : 'VIEW',
				dirName : 'image',
				clickFn : function(url, title) {
					//K('#picture').val(url);
					//alert(url);
					imagesInputObj.val(url);
					editor.hideDialog();
					clearRootImagePath(imagesInputObj);//$("#picture"));
				}
			});
		});
	});
	
});
</script>

</body>
</html>
