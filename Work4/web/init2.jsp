<%@ page import="cn.edu.zjut.jprofiler.test.TestMain" %>
<%@ page import="cn.edu.zjut.jprofiler.test.TestBean" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/4/16
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>init2</title>
</head>
<body>
<%
    for(int i = 0; i < 10000; i++) {
        TestBean b = new TestBean();
        TestMain.list.add(b);
    }
    TestMain.testHotTwo();
%>
SIZE:<%=TestMain.list.size()%> <br/>
Counter:<%=++TestMain.counter%>
</body>
</body>
</html>
