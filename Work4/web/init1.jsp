<%@ page import="cn.edu.zjut.jprofiler.test.TestBean" %>
<%@ page import="cn.edu.zjut.jprofiler.test.TestMain" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/4/16
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>init1</title>
</head>
<body>
    <%
        for(int i = 0; i < 10000; i++) {
            TestBean b = new TestBean();
            TestMain.list.add(b);
        }
        TestMain.testHotOne();
    %>
    SIZE:<%=TestMain.list.size()%> <br/>
    Counter:<%=++TestMain.counter%>
</body>
</html>
