<%@ page language="java" import="cn.edu.zjut.jprofiler.test.*" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>free</title>
	</head>
	<body>
        <%
        for(int i=0; i<TestCPU.threadList.size(); i++){
			TestCPU.free(TestCPU.threadList.get(i));
		}
		TestCPU.threadList.clear();
        %>
        All threads killed! <br />
        Thread Counter:<%=TestCPU.threadList.size()%>
    </body>
</html>