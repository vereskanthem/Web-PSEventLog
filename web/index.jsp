<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.JSONValue" %>
<%@ page import="static java.lang.Math.random" %>
<%@ page import="java.util.Random" %>

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
    <%--<nav>--%>
        <%--<button id="nav-button1" class="head-button k-button"> Button to check Data Send through JAVA</button>--%>
    <%--</nav>--%>
</div>

<%--<nav>--%>
    <%--<ul>--%>
        <%--<li><a href="#onepage"/></li>--%>
        <%--<li><a href="#twopage"/></li>--%>
    <%--</ul>--%>
<%--</nav>--%>

<div class="left-of-page k-content">
<%--<article>--%>

    <div id="calendar-from-date"></div>
    <div id="calendar-to-date"></div>

    <div class="example-inscriptions k-content"> <span color="red">INSERT into DB:</span>
        <ul>
            <li><b>SET DATE</b> at first calendar</li>
            <li>Set <b>USERNAME</b> and <b>FILENAME</b> in the textboxes and <b>click ADD</b> button</li>
        </ul>
    </div>

    <div id="view1">
        <input onfocus="if (this.value=='Enter username please...') this.value = ''" onblur="if (this.value=='') this.value='Enter username please...'" class="k-textbox" id="add-username-textbox" data-bind="value: username" />
        <input onfocus="if (this.value=='Enter filename please...') this.value = ''" onblur="if (this.value=='') this.value='Enter filename please...'" class="k-textbox" id="add-filename-textbox" data-bind="value: filename" />
        <button class="k-button" id="jsp-send" data-bind="click: addData">Add Dates and name to DB through Java<span>SPANB!</span></button>
    </div>

    <div id="view2">
        <button class="k-button" id="jsp-receive" data-bind="click: getData">SELECT data from DB through Java</button>
    </div>

    <div class="example-inscriptions k-content"> <span color="red">SELECT from DB:</span>
        <ul>
            <li><b>Set DATES </b> in calendars</li>
            <li>Set <b>USERNAME or FILENAME </b> in textboxes</li>
            <li><b>Click SELECT Button</b> to GET data from table in DB</li>
        </ul>
    </div>

    <div id="view2" margin-bottom="20px">

    </div>

<script src="kendo/js/jquery.min.js"></script>
<script src="kendo/js/kendo.all.min.js"></script>

<%--<script> $("#datepicker").kendoDatePicker(); </script>--%>

<%--<input type="hidden" id="jsonData" name="jsonData"/>--%>
<%--<input type="hidden" id="test" name="test"/>--%>

<script>

    <%-- Create the View-Model --%>
    var addDataToDB;
    addDataToDB = kendo.observable({

        username: "Enter username please...",
        filename: "Enter filename please...",
        addData: function () {

            var calendar;
//            var name = this.get("name");
            var firstDate;
            var lastDate;

            var username_textbox;
            var filename_textbox;

            var json_buffer;

            calendar = $("#calendar-from-date").data("kendoCalendar");
            // .getTime() - get time in milliseconds
            firstDate = calendar.current().getTime();

            calendar = $("#calendar-to-date").data("kendoCalendar");
            // .getTime() - get time in milliseconds
            lastDate = calendar.current().getTime();

//            username_textbox = $("#add-username-textbox").val();
            username_textbox = this.get("username");
//            filename_textbox = $("#add-filename-textbox").val();
            filename_textbox = this.get("filename");

//            alert("\"" + username_textbox + "\"");
//            alert("\"" + filename_textbox + "\"");

            if((username_textbox == "" && filename_textbox == "") || (username_textbox == 'Enter username please...' && filename_textbox == 'Enter filename please...'))    {

                alert("Username OR filename filed MUST be not null!");
                throw new FatalError("Username OR filename filed MUST be not null!");

            }   else {

                json_buffer = {

                    username: username_textbox,
                    filename: filename_textbox,
                    firstDate: firstDate,
                    lastDate: lastDate

                };

            };

            if(username_textbox == 'Enter username please...' || filename_textbox == 'Enter filename please...') {

                if(username_textbox == 'Enter username please...') {

                    json_buffer = {

                        username: "",
                        filename: filename_textbox,
                        firstDate: firstDate,
                        lastDate: lastDate

                    };

                };

                if(filename_textbox == 'Enter filename please...') {

                    json_buffer = {

                        username: username_textbox,
                        filename: "",
                        firstDate: firstDate,
                        lastDate: lastDate

                    };

                };

            };

            $('#div-box1').append("<br/>");
            $('#div-box1').append("<font color=\"red\">Username:</font>" + json_buffer.username);
            $('#div-box1').append("<br/>");
            $('#div-box1').append("<font color=\"red\">Filename:</font>" + json_buffer.filename);
            $('#div-box1').append("<br/>");
            $('#div-box1').append("<font color=\"red\">FirstDate: </font>" + json_buffer.firstDate);
            $('#div-box1').append("<br/>");
            $('#div-box1').append("<font color=\"red\">LastDate:</font>" + json_buffer.lastDate);
            $('#div-box1').append("<br/>");
            $('#div-box1').append("--------------------------");

            $('#div-box2').html(JSON.stringify(json_buffer));

            $.post('Test', json_buffer, function (data) {
                $('#div-jsp').html(data);
            });

            $('#div-jsp').hide().fadeIn('fast');

//            To load another jsp
//            $('#div-jsp').load("request_out.jsp");

        }

    });

    var getDataFromDB = kendo.observable({

        name: "Enter username please...",
        getData: function () {


        }

    });

    kendo.bind($('#view1'), addDataToDB);
    kendo.bind($('#view2'), getDataFromDB);

    <%-- --------------------- --%>

    $("#calendar-from-date").kendoCalendar({
        min: new Date(2016,7,1),
        max: new Date(2020,7,1),
        start: 'month',
//        value: new Date(2016,7,22),
        change: function(e) {

            console.log(e)

        }
    });

    $("#calendar-to-date").kendoCalendar({
        min: new Date(2016,7,1),
        max: new Date(2020,7,1),
        start: 'month',
//        value: new Date(2016,12,1),
        change: function(e) {

            console.log(e)

        }
    });

//        $('#nav-button1').click(function () {
//
//            var JSONSrcArray;
//            var calendar;
//            var firstDate;
//            var lastDate;
//
//            calendar = $("#calendar-to-date").data("kendoCalendar");
//            firstDate = calendar.current();
//
//            calendar = $("#calendar-to-date").data("kendoCalendar");
//            lastDate = calendar.current();
//
//            JSONSrcArray = {
//
//                username:  $("#add-data-textbox").val(),
//                firstDate: firstDate,
//                lastDate: lastDate
//
//            };
//            JSONSrcArray = { username: data };
//            alert(JSONSrcArray.username);

//            var JSONSerial = JSONStringArray.serialize();

//            $.post('Test', { test: "testing!" }, function (data) {
//                $('#div-jsp').html(data);
//            });

//            $.post('Test', JSONSrcArray, function (data) {
//                $('#div-jsp').html(data);
//            });

//            $.ajax({
//
//                type: "post",
//                dataType: "json",
//                data: JSONConverted,
//                contentType: "application/json",
//                url:  "/Test",
//                success: function (data) {
//
//                    alert("Working well! Json: " + data);
//                    $('#div-jsp').html(data);
//
//                },
//                error: function () {
//                    alert("Error! Cannot get response.");
//                }
//            });

//        });

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
        <%--<font color="gray">Transactions:</font>--%>
        <ul id="commands">

        </ul>
    </div>

    <div id="div-box2" class="k-content">
        <font color="#b0c4de">JSON Out</font>
        <%--<%--%>

            <%--Random rand;--%>

            <%--rand = new Random();--%>

            <%--if(rand.nextDouble() > 0.5) {--%>
                <%--out.println("OUT from java at index.jsp!");--%>
            <%--}   else    {--%>
                <%--out.println("OUT from java at index.jsp!!");--%>
            <%--}--%>
            <%----%>
        <%--%>--%>
    </div>
    <div id="div-jsp" class="k-content">
        <%--<%--%>
            <%--String req = request.getParameter("jsonData");--%>
            <%--if(req != null) out.println(req);--%>
            <%--else            out.println("<font color=\"red\">JSP Out</font>");--%>
        <%--%>--%>

        <%--<%--%>

<%--//            (String)request.getAttribute("jsonData")--%>
            <%--String testParam = request.getParameter("test");--%>

            <%--if(testParam == null)   {--%>

                <%--out.println("FUUUUUUU!");--%>

            <%--}   else    {--%>

                <%--out.println(testParam);--%>

            <%--}--%>

        <%--%>--%>
        <%
//            session.getAttribute("test")


//            if(testParam == null) {

              out.println("<font color=\"red\">JSP out</font>");

//            }   else    {



//            }

        %>
    </div>
    <%--<div id="div-status">STATUS</div>--%>

    <%--</div>--%>
</div>

</body>
</html>
