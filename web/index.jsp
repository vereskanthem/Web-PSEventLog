<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.JSONValue" %><%--<%@ page import="org.json.simple.JSONObject" %>--%>
<%--<%@ page import="org.json.simple.JSONValue" %>--%>

<%--
  Created by IntelliJ IDEA.
  User: nlare
  Date: 23.11.16
  Time: 9:23
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<title>TryToUseKendoUI</title>

<meta charset="utf-8">

<link rel="stylesheet" href="styles/main.css">
<link rel="stylesheet" href="kendo/styles/kendo.common.css">
<link rel="stylesheet" href="kendo/styles/kendo.common-nova.min.css">
<link rel="stylesheet" href="kendo/styles/kendo.flat.min.css">
<link rel="stylesheet" href="kendo/styles/kendo.rtl.min.css">
<link rel="stylesheet" href="kendo/styles/kendo.dataviz.min.css">
<link rel="stylesheet" href="kendo/styles/kendo.dataviz.default.min.css">
<link rel="stylesheet" href="kendo/styles/kendo.mobile.common.min.css">
<link rel="stylesheet" href="kendo/styles/kendo.mobile.common.black.min.css">

</head>

<body>
<div class="header-container">
    <header class="mini-container k-header">
        <h2>This page content includes same examples of using KendoUI.</h2>
        <h3>----------------------------------------------------------</h3>
        <h4>Yo-ho-ho! And the bottle of Rum!</h4>
    </header>
</div>

<%--<nav>--%>
    <%--<ul>--%>
        <%--<li><a href="#onepage"/></li>--%>
        <%--<li><a href="#twopage"/></li>--%>
    <%--</ul>--%>
<%--</nav>--%>

<div class="left-of-page k-content">
<article>
    <div class="example-inscriptions k-content"> <span color="red">INSERT into DB:</span>
        <ul>
            <li>Set dates at the calendars</li>
            <li>Set name in the textbox and click ADD button</li>
        </ul>
    </div>

    <div id="view1">
        <input class="k-textbox" data-bind="value: name" />
        <button class="k-button"  data-bind="click: addData">Add Dates and name to DB through Java</button>
    </div>

    <div class="example-inscriptions k-content"> <span color="red">SELECT from DB:</span>
        <ul>
            <li>Set date interval in calendars</li>
            <li>Use GET Button to SELECT data from table in DB</li>
        </ul>
    </div>

    <div id="view2" margin-bottom="20px">
        <input class="k-textbox" data-bind="value: name" />
        <button class="k-button" data-bind="click: getData">SELECT data from DB through Java</button>
    </div>

<div id="calendar-from-date"></div>
<div id="calendar-to-date"></div>

<script src="kendo/js/jquery.min.js"></script>
<script src="kendo/js/kendo.all.min.js"></script>

<%--<script> $("#datepicker").kendoDatePicker(); </script>--%>

<input type="hidden" id="jsonData" name="jsonData">

<script>

    function convert_to_JSON(jsvar)  {

        var jsonvar = JSON.stringify(jsvar)

        return jsonvar
    }

    <%-- Create the View-Model --%>
    var addDataToDB;
    addDataToDB = kendo.observable({

        name: "Add name ...",
        addData: function () {

            var ul = document.getElementById('commands');
            var div = document.getElementById('div-box1');

            var name = this.get("name");
            var node = document.createElement("li");
            var br = document.createElement("br");

            var calendar = $("#calendar-from-date").data("kendoCalendar");
            var firstDate = calendar.current();

            var calendar = $("#calendar-to-date").data("kendoCalendar");
            var lastDate = calendar.current();

            var str = 'Try to ADD:';
            var litextnode = document.createTextNode(str);

            str = 'Name: ' + name;
            var nametextNode = document.createTextNode(str);

            str = 'Date Interval: ' + firstDate + ' - ' + lastDate;
            var firstdateNode = document.createTextNode(str);

            var json_buffer =  {

                "name": name,
                "firstDate": firstDate,
                "lastDate": lastDate

            }

            var json = convert_to_JSON(json_buffer);

            $('#jsonData').val(json);

            $('#div-box1').append(json)

            node.appendChild(litextnode);

            ul.appendChild(node);
//            ul.appendChild(br);
            ul.appendChild(nametextNode);
            ul.appendChild(br);
            ul.appendChild(firstdateNode);
//            ul.appendChild(br);
            ul.appendChild(lastdateNode);

        }

    });

    var getDataFromDB = kendo.observable({

        name: "Get name ... ",
        getData: function () {

        var name = this.get("name");
        var ul = document.getElementById('commands');
        var node = document.createElement("li");
        var str = 'Try to GET ' + name;

        var textnode = document.createTextNode(str);

        node.appendChild(textnode);
        ul.appendChild(node);

        }

    });

    kendo.bind($('#view1'), addDataToDB);
    kendo.bind($('#view2'), getDataFromDB);

    <%-- --------------------- --%>

    $("#calendar-from-date").kendoCalendar({
        min: new Date(2016,7,1),
        max: new Date(2020,7,1),
        start: 'month',
        value: new Date(2016,7,22),
        change: function(e) {

            console.log(e)

        }
    });

    $("#calendar-to-date").kendoCalendar({
        min: new Date(2016,7,1),
        max: new Date(2020,7,1),
        start: 'month',
        value: new Date(2016,12,1),
        change: function(e) {

            console.log(e)

        }
    });

</script>

</article>
</div>
<div class="example-inscriptions k-content"> Output of JS/JAVA+DB: </div>
<div class="right-of-page k-content">

    <div class="div-inside-right-of-page k-content"></div>

    <%--Same text.--%>
    <%--..... <br>--%>
    <%--.....--%>
    <%--.....--%>
    <%--.....--%>
    <div id="div-box1" class="k-content">
        <ul id="commands">

        </ul>
    </div>
    <div id="div-box2" class="k-content">

    <%= request.getParameter("jsonData")%>

    <%

//        String jsonString;
//        JSONObject jsObject;

//        jsObject = (JSONObject) JSONValue.parse(jsonString);

//        System.out.println(jsonObject.get("name"));

    %>
    <%--Test JSP <%= jsonString%>--%>

    </div>

    <%--<div id="div-box2" class="k-content">--%>

    <%--</div>--%>
</div>

</body>
</html>
