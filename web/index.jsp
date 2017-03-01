<%--suppress ALL --%>
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
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<title>TryToUseKendoUI</title>

<%--<meta charset="utf-8">--%>

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
<div class="date-panel k-content">

<div class="calendars">
    <div id="calendar-from-date"></div>
    <div id="calendar-to-date"></div>
</div>
</div>
<div style="clear:both"></div>
<div class="example-inscriptions k-content">
    <div class id="description">DESCRIPTION: </div>
    <span color="red">INSERT into DB:</span>
    <ul>
        <li><b>SET DATE</b> at first calendar</li>
        <li>Set <b>USERNAME</b> and <b>FILENAME</b> in the textboxes and <b>click ADD</b> button</li>
    </ul>
    <span color="red">SELECT from DB:</span>
    <ul>
        <li><b>Set DATES </b> in calendars</li>
        <li>Set <b>USERNAME or FILENAME </b> in textboxes</li>
        <li><b>Click SELECT Button</b> to GET data from table in DB</li>
    </ul>
</div>


<%--<div class="example-inscriptions k-content">--%>

<%--</div>--%>

<div class="search-panel k-content">
<%--<article>--%>


    <%--<div id="view1">--%>
        <%--<input onfocus="if (this.value=='Enter username please...') this.value = ''" onblur="if (this.value=='') this.value='Enter username please...'" class="k-textbox" id="add-username-textbox" data-bind="value: username" />--%>
        <%--<input onfocus="if (this.value=='Enter filename please...') this.value = ''" onblur="if (this.value=='') this.value='Enter filename please...'" class="k-textbox" id="add-filename-textbox" data-bind="value: filename" />--%>
        <%--<button class="k-button" id="jsp-send" data-bind="click: addData">Add Dates and name to DB</button>--%>
    <%--</div>--%>
    <div id="view2">
        <div id="username-field">
            <input onfocus="if (this.value=='Enter username please...') this.value = ''" onblur="if (this.value=='') this.value='Enter username please...'" class="k-textbox" id="get-username-textbox" data-bind="value: username" />
        </div>
        <div id="filename-field">
            <input onfocus="if (this.value=='Enter filename please...') this.value = ''" onblur="if (this.value=='') this.value='Enter filename please...'" class="k-textbox" id="get-filename-textbox" data-bind="value: filename" />
        </div>
        <div id="select-button-field">
            <button class="k-button" id="jsp-receive" data-bind="click: getData">SELECT data from DB</button>
            <%--onClick="javascript:location.href = '#db-out';"--%>
        </div>
    </div>
    <%--<div id="view2" margin-bottom="20px">--%>

    <%--</div>--%>

<%--<script> $("#datepicker").kendoDatePicker(); </script>--%>
    <input type="hidden" id="jsonData" name="jsonData"/>
    <input type="hidden" id="test" name="test"/>

</article>
</div>
<%--<div class="search-result">--%>
<%--<div id="div-head-iscription" class="k-content"> Output of JS/JAVA+DB: </div>--%>

<%--<div id="div-clean-output" class="k-content">--%>
    <%--<font color="gray">Transactions:</font>--%>
<%--</div>--%>

<%--<div id="div-json-from-js" class="k-content">--%>
        <%--<font color="#b0c4de">JSON Out !</font>--%>

<%--</div>--%>
    <%--<div id="div-jsp-out" class="k-content">--%>
        <%--<%--%>
              <%--out.println("<font color=\"red\">JSP out !</font>");--%>
        <%--%>--%>
    <%--</div>--%>

<%--</div>--%>

<div id="db-out">

    <div id="status"/>
    <div id="listView" class="k-content"/>
    <div id="pager" class="k-pager-wrap"/>

</div>

<script src="kendo/js/jquery.min.js"></script>
<script src="kendo/js/kendo.all.min.js"></script>
<script src="kendo/js/jszip.min.js"></script>

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

            $('#div-clean-output').append("<br/>");
            $('#div-clean-output').append("<font color=\"red\">Username:</font>" + json_buffer.username);
            $('#div-clean-output').append("<br/>");
            $('#div-clean-output').append("<font color=\"red\">Filename:</font>" + json_buffer.filename);
            $('#div-clean-output').append("<br/>");
            $('#div-clean-output').append("<font color=\"red\">FirstDate: </font>" + json_buffer.firstDate);
            $('#div-clean-output').append("<br/>");
            $('#div-clean-output').append("<font color=\"red\">LastDate:</font>" + json_buffer.lastDate);
            $('#div-clean-output').append("<br/>");
            $('#div-clean-output').append("--------------------------");

//            $('#div-json-from-js').html(JSON.stringify(json_buffer));

            $.post('AddToDB', json_buffer, function (data) {
                $('#div-jsp-out').html(data);
            });

            $('#div-jsp-out').hide().fadeIn('fast');

//            To load another jsp
//            $('#div-jsp').load("request_out.jsp");

        }

    });

    var getDataFromDB = kendo.observable({

        username: "Enter username please...",
        filename: "Enter filename please...",
        getData: function () {

            var calendar;
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

//            username_textbox = $("#get-username-textbox").val();
            username_textbox = this.get("username");
//            filename_textbox = $("#get-filename-textbox").val();
            filename_textbox = this.get("filename");

            if((username_textbox == "" && filename_textbox == "") || (username_textbox == 'Enter username please...' && filename_textbox == 'Enter filename please...') || (username_textbox == 'Enter username please...' && filename_textbox == '') || (username_textbox == '' && filename_textbox == 'Enter filename please...'))    {

//                alert("Setting up username and filename like empty string!");
//                alert(firstDate);
//                alert(lastDate);

                json_buffer = {

                    username: "",
                    filename: "",
                    firstDate: firstDate,
                    lastDate: lastDate,
                    nocache: $.now()

                };

//                throw new FatalError("Username OR filename filed MUST be not null!");

            }   else {

                json_buffer = {

                    username: username_textbox,
                    filename: filename_textbox,
                    firstDate: firstDate,
                    lastDate: lastDate,
                    nocache: $.now()

                };

            };

            if(username_textbox == 'Enter username please...' || filename_textbox == 'Enter filename please...') {

                if(username_textbox == 'Enter username please...') {

                    json_buffer = {

                        username: "",
                        filename: filename_textbox,
                        firstDate: firstDate,
                        lastDate: lastDate,
                        nocache: $.now()

                    };

                };

                if(filename_textbox == 'Enter filename please...') {

                    json_buffer = {

                        username: username_textbox,
                        filename: "",
                        firstDate: firstDate,
                        lastDate: lastDate,
                        nocache: $.now()

                    };

                };

            };

//            $('#div-json-from-js').html(JSON.stringify(json_buffer));
//
//            $.post('GetFromDB', json_buffer, function (data) {
////                $('#div-jsp-out').html(data);
//            });

//            var json_input = JSON.stringify(json_buffer);

            var dataSource = new kendo.data.DataSource({

                transport: {
                    read: {
                        url: "GetFromDB",
                        dataType: "json",
//                        contentType: 'application/json; charset=utf-8',
                        type: "POST",
                        data: function() {

                            return json_buffer

                        }
                    }
                },
                schema: {
                    model:  {
                        fields: {
                            USERNAME:   { type: "string" },
                            FILENAME:   { type: "string" },
                            TIME_EVENT: { type: "date" }
                        }
                    }
                },
                change: function (e) {
                    $("#status").innerHTML("qwer");
                },
                pageSize: 15
            });

//            $("#listView").html(json_buffer);

//            if(json_buffer ==)   {

//                $("#listView").html("<li><b>Ничего не найдено!</b> Нет данных с указанными параметрами. Попробуйте изменить дату или уточните корректность введенного имени пользователя или файла.</li>");
//                  $("#listView").html(json_buffer);

//            }   else {

//                alert("Данных по выбоке из базы: " + dataSource.total());

//                $("#listView").html("<li><b>Идет процесс выборки из базы... </b></li>");

                $("#listView").kendoGrid({

                    toolbar: ["excel", "pdf"],
                    excel: {
                        fileName: $.now() + ".xlsx",
                        allPages: true,
                        filterable: true
                    },
                    pdf: {
                        filename: $.now() + ".pdf",
                        allPages: true,
                        filterable: true
                    },
                    allowCopy: true,
                    resizable: true,
                    navigatable: true,
//                selectable: "multiple cell",
                    selectable: true,
                    dataSource: dataSource,
                    pageable: true,
                    filterable: true,
//                    groupable: true,
                    sortable: {
                        mode: "multiple",
                        allowUnsort: true
                    },
                    columns: [

                        {field: "USERNAME", title: "Пользователь", width: "100px"},
                        {field: "FILENAME", title: "Имя файла", width: "100px"},
                        {
                            field: "TIME_EVENT",
                            title: "Время удаления",
                            width: "60px",
                            format: "{0:dd.MM.yyyy HH:mm}"
//                        template: "#= kendo.toString(kendo.parseDate(TIME_EVENT, 'yyyy-MM-dd'T'HH:mm:ssz'), 'dd.MM.yyyy hh:mm') #"
                        }

                    ]
                });

//            }
//            $('#pager').show();

//            $("#pager").kendoPager({
//                autoBind: false,
//                dataSource: dataSource,
//                buttonCount: 10,
//                async: false
//            });

//            $('#db-out').html(dataSource);
//            $('#db-out').hide().fadeIn('fast');


        }

    });

    kendo.bind($('#view1'), addDataToDB);
    kendo.bind($('#view2'), getDataFromDB);

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

    $(document).ready(function() {
       $('#pager').hide();
    });

</script>

</body>
</html>
