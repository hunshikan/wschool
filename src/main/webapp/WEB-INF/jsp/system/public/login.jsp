<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <meta charset="utf-8">
    <title>登录--layui后台管理模板</title>
    <!-- jsp文件头和头部 -->
    <link rel="stylesheet" href="<%=basePath%>static/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="<%=basePath%>static/css/login.css" media="all" />
</head>
<body>
<video class="video-player" preload="auto" autoplay="autoplay" loop="loop" data-height="1080" data-width="1920" height="1080" width="1920">
    <source src="static/video/login.mp4" type="video/mp4">
</video>
<div class="video_mask"></div>
<div class="login">
    <h1>wschool-管理登录</h1>
    <form class="layui-form">
        <div class="layui-form-item">
            <input class="layui-input" name="username" placeholder="用户名" lay-verify="required" type="text" autocomplete="off">
        </div>
        <div class="layui-form-item">
            <input class="layui-input" name="password" placeholder="密码" lay-verify="required" type="password" autocomplete="off">
        </div>
        <div class="layui-form-item form_code">
            <input class="layui-input" name="code" placeholder="验证码" lay-verify="required" type="text" autocomplete="off">
            <div class="code"><img src="" id="codeIMG" width="116" height="36"></div>
        </div>
        <button class="layui-btn login_btn" lay-submit="" lay-filter="login">登录</button>
    </form>
</div>
<script type="text/javascript" src="<%=basePath%>static/layui/layui.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/login.js"></script>
</body>
</html>
