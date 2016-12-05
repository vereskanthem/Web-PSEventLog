<%--
  Created by IntelliJ IDEA.
  User: nlare
  Date: 29.11.16
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <script src="kendo/js/jquery.min.js"></script>
    <script src="kendo/js/kendo.all.min.js"></script>
    <script>

//        var requestToJsp = "dataFromJS";
//        var jspResponse;
//
//        $.ajax({
//
//            type: "POST",
//            url: "http://localhost:8080/Test",
//            contentType: "text/html",
//            data: requestToJsp,
//            success: function (response) {
//                $('#test').html('Response is work!' + response);
//                jspResponse = response;
//            }
//
//        });

    </script>
    <input type="hidden" id="test" name="test"/>
    <%--<%= request.getParameter("jsonData") %>--%>
    <%
//        out.println("Jsp out work's fine at request.jsp");

//        String testParam = request.getParameter("test");
//
//        out.println("Name: " + testParam);

    %>

    <div id="test"></div>
</body>
</html>
