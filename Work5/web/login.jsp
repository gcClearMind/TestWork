<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
<body>
<form id="from1" action="login.do" method="post">
    <table>
        <tr height=40>
            <td colspan="2" align="center">
                <h2>登录</h2>
            </td>
        </tr>
        <tr height=40>
            <td class="text" height=40>&nbsp;用&nbsp;户&nbsp;名&nbsp;:</td>
            <td height=40 width=240>
                <input type="text" name="UserName" id="UserName" placeholder="请输入您的用户名">
            </td>
        </tr>

        <tr height=40>
            <td class="text" height=40>&nbsp;密&nbsp;码&nbsp;:</td>
            <td height=40 width=240>
                <input type="text" name="password" id="password" placeholder="请输入您的密码">
            </td>
        </tr>
        <tr height=40>
            <td colspan="2" align="center" height=40 width="80%">
                <button class="but-1" id="login" value="立即登录">立即登录</button>
            </td>
        </tr>
        <tr height=40>
            <td colspan="2" align="right" height=40>
                没有账号？<a href="register.jsp">免费注册&nbsp;&nbsp;</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>