<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册界面</title>
    <script language="JavaScript" type="text/javascript">
        function judge_click() {
            var UserName = document.getElementById("UserName");
            var password = document.getElementById("password");
            if(UserName.value == "" || UserName.value == "请输入您的用户名") {
                alert("用户名不能为空!");
                return false;
            }
            else if(password.value == "" || password.value == "请输入您的密码") {
                alert("密码不能为空!");
                return false;
            }
        }
    </script>
<body>
<form id="from1" action="register.do" method="post" onsubmit="return judge_click();">
    <table class="table1">
        <tr height=40>
            <td colspan="2" align="center">
                <h2><font color=blue>用户注册</font></h2>
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
        </tr
        <tr height=40>
            <td colspan="2" align="center" height=40>
                <button class="but-1" id = "fregister" value="立即注册" >立即注册</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>