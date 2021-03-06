<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="input" type="java.lang.String" required="true" description="输入框"%>
<%@ attribute name="prefix" type="java.lang.String" required="true" description="前缀"%>
<%@ attribute name="base" type="java.lang.String" required="true" description=""%>
<%@ attribute name="size" type="java.lang.Integer" required="true" description=""%>

<ol id="${input}Preview"></ol>
<a href="javascript:" id="select-${input}" class="btn">选择</a>&nbsp;
<a href="javascript:" onclick="${input}DelAll();" class="btn">清除</a>
<%-- <tags:ckfinder input="image" type="${input}" uploadPath="/shop/catalog"/> --%>
<script type="text/javascript">
function ${input}Preview(){
	var li, urls = $("#${input}").val().split(",");
	$("#${input}Preview").children().remove();
	for (var i=0; i<urls.length; i++){
		if (urls[i]!=""){
			if(urls[i].indexOf('${base}')>=0){
				li = "<li><img src=\"${base}"+urls[i]+"\" url=\""+urls[i]+"\" style=\"max-width:${size}px;max-height:${size}px;_height:${size}px;border:0;padding:3px;\">";
			} else {
				li = "<li><img src=\"${base}"+urls[i]+"\" url=\""+urls[i]+"\" style=\"max-width:${size}px;max-height:${size}px;_height:${size}px;border:0;padding:3px;\">";
			}
			li += "<a href=\""+urls[i]+"\" url=\""+urls[i]+"\" target=\"_blank\">"+decodeURIComponent(urls[i].substring(urls[i].lastIndexOf("/")+1))+"</a>";
			li += "&nbsp;&nbsp;<a href=\"javascript:\" onclick=\"${input}Del(this);\">×</a></li>";
			$("#${input}Preview").append(li);
		}
	}
}

function ${input}Del(obj){
	var url = $(obj).prev().attr("url");
	$("#${input}").val($("#${input}").val().replace("|"+url,"","").replace(url+"|","","").replace(url,"",""));
	${input}Preview();
}

function ${input}DelAll(){
	$("#${input}").val("");
	${input}Preview();
}

$(document).ready(function(){
	KindEditor.ready(function(K) {
		var editor = K.editor({
			uploadJson: '${pageContext.request.contextPath}/servlet/upload?prefix=${prefix}',
			fileManagerJson : '${pageContext.request.contextPath}/servlet/kindeditor?prefix=${prefix}',
			allowFileManager : true
		});
		K('a[id="select-${input}"]').click(function() {
			editor.loadPlugin('image', function() {
				editor.plugin.imageDialog({
					//viewType : 'VIEW',
					//dirName : 'image',
					clickFn : function(url, title, width, height, border, align) {
						$("#${input}").val($("#${input}").val() + ',' + url);
						${input}Preview();
						editor.hideDialog();
					}
				});
			});
		});
		
	});
	
	${input}Preview();
});
</script>
