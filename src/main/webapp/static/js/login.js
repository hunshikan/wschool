layui.config({
	base : "static/js/"
}).use(['form','layer'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		$ = layui.jquery;
	//video背景
	$(window).resize(function(){
		if($(".video-player").width > $(window).width){
			$(".video-player").css({"height":$(window).height,"width":"auto","left":-($(".video-player").width-$(window).width)/2});
		}else{
			$(".video-player").css({"width":$(window).width,"height":"auto","left":-($(".video-player").width-$(window).width)/2});
		}
	}).resize();

     //获取当前时间，验证码参数
	function genTimestamp() {
		var time = new Date();
		return time.getTime();
	}
	//获取验证码
	function getCode() {
		$("#codeIMG").attr("src","code/getGifCode.do?t="+genTimestamp())
	}
	$("#codeIMG").click(function(){
		getCode();
	})
	$(document).ready(function(){
		getCode();
	})
  
	//登录按钮事件
	form.on("submit(login)",function(data){
		var loginLoading = top.layer.msg('登陆中，请稍候', {icon: 16, time: false, shade: 0.8});
		$.ajax({
			url:"Do_login",
			type:"get",
			dataType:"json",
			data:data.field,
			success:function(data){
				top.layer.close(loginLoading);
				if(data.result=="usererror"){
                    layer.msg('用户名,密码错误')
				}else if(data.result=="codeerror"){
                    layer.msg('验证码错误')
				}else if(data.result=="success"){
					window.location.href="index";
				}

			},
			error:function() {
			}
		});
		return false;
	})
})
