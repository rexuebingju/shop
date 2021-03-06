<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta name="decorator" content="default"/>
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <title>首页</title>
    <link href="${ctxStatic }/css/mall.css" rel="stylesheet" type="text/css">
    <link href="${ctxStatic }/css/style.css" rel="stylesheet" type="text/css">
    <link href="${ctxStatic }/css/all.css" rel="stylesheet" type="text/css">
    <link href="${ctxStatic }/css/common.css" rel="stylesheet" type="text/css">

</head>
<body class="full">
<script type="text/javascript" src="${ctxStaticJS }/js/jquery-all.js"></script>
<script type="text/javascript" src="${ctxStaticJS }/js/common.js"></script>
<script type="text/javascript" src="${ctxStaticJS }/js/jquery.js"></script>
<script type="text/javascript" src="${ctxStaticJS }/js/lrtk.js"></script>
<!--列表-->
<div class="new_admall_box">
<div class="new_admall_topContent clearfix">
		<!--分类-->
        	<!--导航 Start-->
<style type="text/css">
.all_menu {float:left;}
.menu{position:relative;width:210px;height:40px;background-color:#E4393C;}
.menu div{position:absolute;top:0px;height:40px;font-family:"Microsoft Yahei";}
.menu .all-sort{left:0px;width:210px;text-align:center;font-size:14px;}
.menu .all-sort a{color:#FFF;display:block;width:100%;height:100%;line-height:40px;}
.menu .nav{left:210px;}
.menu .nav ul li{float:left;width:85px;line-height:40px;}
.menu .nav ul li a{display:block;width:100%;color:#FFF;text-align:center;font:700 15px/40px "Microsoft Yahei";}
.menu .nav ul li a:hover, .menu .nav ul li a.current{background:#A40000;text-decoration:none;}

.wrap{width:203px;}
.all-sort-list{position:relative;width:203px;border:2px solid #E4393C;border-top:none;padding:3px 3px 3px 0px;background:#FAFAFA;}
.all-sort-list .item{height:30px;border-top:1px solid #FFFFFF;}
.all-sort-list .item.bo{border-top:none;} 
.all-sort-list .item h3{height:28px;line-height:28px;border:1px 0px;font-size:14px;font-weight:normal;width:199px;overflow:hidden;}
.all-sort-list .hover h3{position:relative;z-index:13;background:#FFF;border-color:#DDD;border-width:1px 0px;border-style:solid;}
.all-sort-list .item span{padding:0px 5px;color:#A40000;font-family:"\5B8B\4F53";} 
.all-sort-list .item a{color:#000;text-decoration:none;}
.all-sort-list .item a:hover{font-weight:bold;color:#E4393C;}

.all-sort-list .item-list{
	display:none;
	position:absolute;
	width:705px;
	min-height:400px;
	_height:400px;
	background:#FFF;
	left:198px;
	box-shadow:0px 0px 10px #DDDDDD;
	border:1px solid #DDD;
	top:3px;
	z-index:10;
}
.all-sort-list .item-list .close{
	position:absolute;
	width:26px;
	height:26px;
	color:#FFFFFF;
	cursor:pointer;
	top:-1px;
	right:-26px;
	font-size:20px;
	line-height:20px;
	text-align:center;
	font-family:"Microsoft Yahei";
	background:rgba(0, 0, 0, 0.6);
	background-color:transparent\9;
	filter:progid:DXImageTransform.Microsoft.Gradient(GradientType=1, startColorstr='#60000000', endColorstr='#60000000');
}

.item-list .subitem{float:left;width:477px;padding:0px 4px 0px 8px;}
.item-list .subitem dl{border-top:1px solid #EEE;padding:6px 0px;overflow:hidden;zoom:1;}
.item-list .subitem .fore1{border-top:none;}
.item-list .subitem dt{float:left;width:54px;line-height:22px;text-align:right;padding:3px 6px 0px 0px;font-weight:700;color:#E4393C;}
.item-list .subitem dt a{color:#E4393C;text-decoration:underline;}
.item-list .subitem dd{float:left;width:415px;padding:3px 0px 0px;overflow:hidden;}
.item-list .subitem dd em{float:left;height:14px;line-height:14px;padding:0px 8px;margin-top:5px;border-left:1px solid #CCC;}
.item-list .subitem dd em a, .item-list .cat-right dd a{color:#666;text-decoration:none;}
.item-list .subitem dd em a:hover, .item-list .cat-right dd a:hover{font-weight:normal;text-decoration:underline;}
.item-list .cat-right{float:right;width:210px;}
.item-list .cat-right dl{width:194px;padding:6px 8px;}
.item-list .cat-right dd{padding-top:6px;line-height:22px;overflow:hidden;padding:3px 0px 0px;}
.item-list .cat-right dt{padding:3px 6px 0px 0px;font-weight:700;color:#E4393C; }
.item-list .cat-right dd a:hover{color:#666;}
</style>
<div class="all_menu">
	<div class="menu">
		<div class="all-sort"><h2><a href="javascript:void(0);">全部商品分类</a></h2></div>
	</div>
	<!--导航 End-->
	<!--所有分类 Start-->
	<div class="wrap">
		<div class="all-sort-list">
			<c:forEach items="${PRODUCT_CATALOG_LIST}" var="c">
				<div class="item bo">
					<h3><span>·</span><a href="${ctx}/product/productList?catalogId=39">${c.name}</a></h3>
					<p class="new_admall_menu_cont">
						<c:forEach items="${c.recommend}" var="r">
							<a target="_blank" href="${ctx}/product/productList?catalogId=39">${r.name}</a>
						</c:forEach>
					</p>
					<div class="item-list clearfix">
						<div class="close">x</div>
						<div class="subitem">
							<c:forEach items="${c.children}" var="d" varStatus="status">
								<dl class="fore${status.index + 1}">
									<dt><a href="${ctx}/product/productList?catalogId=39">${d.name}</a></dt>
									<dd>
										<c:forEach items="${d.children}" var="e">
											<em><a href="${ctx}/product/productList?catalogId=39">${e.name}</a></em>
										</c:forEach>
									</dd>
								</dl>
							</c:forEach>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
    </div>
	<!--所有分类 End-->
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript">
		$('.all-sort-list > .item').hover(function(){
			var eq = $('.all-sort-list > .item').index(this),				//获取当前滑过是第几个元素
				h = $('.all-sort-list').offset().top,						//获取当前下拉菜单距离窗口多少像素
				s = $(window).scrollTop(),									//获取游览器滚动了多少高度
				i = $(this).offset().top,									//当前元素滑过距离窗口多少像素
				item = $(this).children('.item-list').height(),				//下拉菜单子类内容容器的高度
				sort = $('.all-sort-list').height();						//父类分类列表容器的高度
			
			if ( item < sort ){												//如果子类的高度小于父类的高度
				if ( eq == 0 ){
					$(this).children('.item-list').css('top', (i-h));
				} else {
					$(this).children('.item-list').css('top', (i-h)+1);
				}
			} else {
				if ( s > h ) {												//判断子类的显示位置，如果滚动的高度大于所有分类列表容器的高度
					if ( i-s > 0 ){											//则 继续判断当前滑过容器的位置 是否有一半超出窗口一半在窗口内显示的Bug,
						$(this).children('.item-list').css('top', (s-h)+2 );
					} else {
						$(this).children('.item-list').css('top', (s-h)-(-(i-s))+2 );
					}
				} else {
					$(this).children('.item-list').css('top', 3 );
				}
			}	

			$(this).addClass('hover');
			$(this).children('.item-list').css('display','block');
		},function(){
			$(this).removeClass('hover');
			$(this).children('.item-list').css('display','none');
		});

		$('.item > .item-list > .close').click(function(){
			$(this).parent().parent().removeClass('hover');
			$(this).parent().hide();
		});
	</script>
		<!--分类-->
	  <div class="new_admall_right_area clearfix">
		<!--广告-->
 <style type="text/css">
#focus_mall{width:860px;height:320px;overflow:hidden;position:relative; margin-left:10px;}
#focus_mall ul{height:380px;position:absolute;}
#focus_mall ul li{float:left;width:860px;height:320px;overflow:hidden;position:relative;background:#000;}
#focus_mall ul li div{position:absolute;overflow:hidden;}
#focus_mall .btnBg{position:absolute;width:800px;height:20px;left:0;bottom:0;}
#focus_mall .btn{position:absolute;width:780px;height:10px;padding:5px 10px;right:0;bottom:0;text-align:right;}
#focus_mall .btn span{display:inline-block;_display:inline;_zoom:1;width:25px;height:10px;_font-size:0;margin-left:5px;cursor:pointer;background:#fff;}
#focus_mall .btn span.on{background:#fff;}
#focus_mall .preNext{width:45px;height:100px;position:absolute;top:100px;background:url(images/sprite.png) no-repeat 0 0;cursor:pointer;}
#focus_mall .pre{left:0;}
#focus_mall .next{right:0;background-position:right top;}
</style>  <script type="text/javascript">
$(function() {
	var sWidth = $("#focus_mall").width(); //获取焦点图的宽度（显示面积）
	var len = $("#focus_mall ul li").length; //获取焦点图个数
	var index = 0;
	var picTimer;
	
	//以下代码添加数字按钮和按钮后的半透明条，还有上一页、下一页两个按钮
	var btn = "<div class='btnBg'></div><div class='btn'>";
	for(var i=0; i < len; i++) {
		btn += "<span></span>";
	}
	btn += "</div><div class='preNext pre'></div><div class='preNext next'></div>";
	$("#focus_mall").append(btn);
	$("#focus_mall .btnBg").css("opacity",0.5);

	//为小按钮添加鼠标滑入事件，以显示相应的内容
	$("#focus_mall .btn span").css("opacity",0.4).mouseover(function() {
		index = $("#focus_mall .btn span").index(this);
		showPics(index);
	}).eq(0).trigger("mouseover");

	//上一页、下一页按钮透明度处理
	$("#focus_mall .preNext").css("opacity",0.2).hover(function() {
		$(this).stop(true,false).animate({"opacity":"0.5"},300);
	},function() {
		$(this).stop(true,false).animate({"opacity":"0.2"},300);
	});

	//上一页按钮
	$("#focus_mall .pre").click(function() {
		index -= 1;
		if(index == -1) {index = len - 1;}
		showPics(index);
	});

	//下一页按钮
	$("#focus_mall .next").click(function() {
		index += 1;
		if(index == len) {index = 0;}
		showPics(index);
	});

	//本例为左右滚动，即所有li元素都是在同一排向左浮动，所以这里需要计算出外围ul元素的宽度
	$("#focus_mall ul").css("width",sWidth * (len));
	
	//鼠标滑上焦点图时停止自动播放，滑出时开始自动播放
	$("#focus_mall").hover(function() {
		clearInterval(picTimer);
	},function() {
		picTimer = setInterval(function() {
			showPics(index);
			index++;
			if(index == len) {index = 0;}
		},4000); //此4000代表自动播放的间隔，单位：毫秒
	}).trigger("mouseleave");
	
	//显示图片函数，根据接收的index值显示相应的内容
	function showPics(index) { //普通切换
		var nowLeft = -index*sWidth; //根据index值计算ul元素的left值
		$("#focus_mall ul").stop(true,false).animate({"left":nowLeft},300); //通过animate()调整ul元素滚动到计算出的position
		//$("#focus_mall .btn span").removeClass("on").eq(index).addClass("on"); //为当前的按钮切换到选中的效果
		$("#focus_mall .btn span").stop(true,false).animate({"opacity":"0.4"},300).eq(index).stop(true,false).animate({"opacity":"1"},300); //为当前的按钮切换到选中的效果
	}
});

</script>
<div id="focus_mall">
<ul>
	<c:forEach items="${indexImgList}" var="c">
	    <li><a target="_blank" href="${c.link}"><img alt="" src="${systemSetting.imageRootPath}${c.picture}" /></a></li>
	</c:forEach>
</ul>
</div>
<!--广告-->
</div>
<div class="clear"></div>
  <!--热门推荐-->
<div class="mt">热门推荐</div>
<div class="box">
	<div class="picbox">
		<ul class="piclist mainlist">
			<c:forEach items="${hotRecommendProduct}" var="d">
			<li><a href="" target="_blank"><img src="${systemSetting.imageRootPath}${d.picture}" /></a></li>
			</c:forEach>
		</ul>
	    <ul class="piclist swaplist"></ul>
	</div>
	<div class="og_prev"></div>
	<div class="og_next"></div>
</div>
<!--热门推荐-->
<!--最新商品-->
<div class="newmall_ongoing">
<!-- 		<div class="newmall_ongoing_title" id="mall_ongoing_already"> -->
<!-- 		</div> -->
	<div>最新商品</div>
	    <a href="" style="width:100px; height:24px; line-height:24px;text-align:right; float:left; margin-bottom:10px;">更多&gt;&gt;</a>
	    <div class="clear"></div>
		<ul class="ongoing_already_list clearfix" id="ongoingAlreadyList">
			<c:forEach items="${newProduct}" var="d">
				<li class="ongoing_area fl">
					<div class="big_img">
<!-- 						<div class="all_bicon_box"> -->
<!-- 							<div class="securityCode security_show"> -->
<!-- 							</div> -->
<!-- 						</div> -->
						<img src="${systemSetting.imageRootPath}${d.picture}" alt=""/>
					</div>
					<div class="desc_area clearfix">
						<div class="left_area fl">
							<p class="ongoing_item_title">
								${d.name }
							</p>
							<p class="ongoing_item_sub_title">
								${d.introduce }
							</p>
							<p class="ongoing_item_discount_desc">
								原价<em>${d.price }</em>现价<em>${d.nowPrice}</em>
							</p>                    
		                    <div class="p-operate">
							    <a class="p-o-btn focus_mall" href="#"><i></i>收藏</a>
							    <a class="p-o-btn addcart" target="_blank" href="#"><i></i>加入购物车</a>
							</div>
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<!--热门商品-->
<div class="newmall_ongoing">
<!-- 		<div class="newmall_ongoing_title" id="mall_ongoing_already"> -->
<!-- 		</div> -->
	<div>热门商品</div>
	    <a href="" style="width:100px; height:24px; line-height:24px;text-align:right; float:left; margin-bottom:10px;">更多&gt;&gt;</a>
	    <div class="clear"></div>
		<ul class="ongoing_already_list clearfix" id="ongoingAlreadyList">
			<c:forEach items="${hotProduct}" var="d">
				<li class="ongoing_area fl">
					<div class="big_img">
<!-- 						<div class="all_bicon_box"> -->
<!-- 							<div class="securityCode security_show"> -->
<!-- 							</div> -->
<!-- 						</div> -->
						<img src="${systemSetting.imageRootPath}${d.picture}" alt=""/>
					</div>
					<div class="desc_area clearfix">
						<div class="left_area fl">
							<p class="ongoing_item_title">
								${d.name }
							</p>
							<p class="ongoing_item_sub_title">
								${d.introduce }
							</p>
							<p class="ongoing_item_discount_desc">
								原价<em>${d.price }</em>现价<em>${d.nowPrice}</em>
							</p>                    
		                    <div class="p-operate">
							    <a class="p-o-btn focus_mall" href="#"><i></i>收藏</a>
							    <a class="p-o-btn addcart" target="_blank" href="#"><i></i>加入购物车</a>
							</div>
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<!--特价商品-->
<div class="newmall_ongoing">
<!-- 		<div class="newmall_ongoing_title" id="mall_ongoing_already"> -->
<!-- 		</div> -->
	<div>特价商品</div>
	    <a href="" style="width:100px; height:24px; line-height:24px;text-align:right; float:left; margin-bottom:10px;">更多&gt;&gt;</a>
	    <div class="clear"></div>
		<ul class="ongoing_already_list clearfix" id="ongoingAlreadyList">
			<c:forEach items="${saleProduct}" var="d">
				<li class="ongoing_area fl">
					<div class="big_img">
<!-- 						<div class="all_bicon_box"> -->
<!-- 							<div class="securityCode security_show"> -->
<!-- 							</div> -->
<!-- 						</div> -->
						<img src="${systemSetting.imageRootPath}${d.picture}" alt=""/>
					</div>
					<div class="desc_area clearfix">
						<div class="left_area fl">
							<p class="ongoing_item_title">
								${d.name }
							</p>
							<p class="ongoing_item_sub_title">
								${d.introduce }
							</p>
							<p class="ongoing_item_discount_desc">
								原价<em>${d.price }</em>现价<em>${d.nowPrice}</em>
							</p>                    
		                    <div class="p-operate">
							    <a class="p-o-btn focus_mall" href="#"><i></i>收藏</a>
							    <a class="p-o-btn addcart" target="_blank" href="#"><i></i>加入购物车</a>
							</div>
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
</body>
</html>
