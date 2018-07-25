<%--
  Created by IntelliJ IDEA.
  User: yanbi
  Date: 2018/7/24
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>登录</title>
  </head>
  <body>
    <form action="${pageContext.request.contextPath}/loginUrl" method="post">
      用户名：<input type="text" name="username"/><br/>
      密码：<input type="text" name="password"/><br/>
      记住密码：<input type="checkbox" name="rememberMe"/>
      <input type="submit" value="登录">
    </form>
  </body>
</html>
