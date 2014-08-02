<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Description</title>
        <script src="../Resources/Java Script/jquery-1.10.2.min.js"></script>
        <script src="../Resources/Java Script/CalenderJS.js"></script>
        <link rel="stylesheet" type="text/css"  href="../Resources/Styles/CalenderCss.css"/>
        <script>
            $(document).ready(function() {
                $("#desc").hide();
                $("#procImg").show();
                var url = "../mail/AirFareCalendar.jsp";
                var url12 = "../mail/GetMsgDesc.jsp?valDt=currentDate";
                $.ajax({
                    url: url,
                    type: 'POST',
                    async: true,
                    data: "go=yes",
                    success: function(data) {
                        $("#dumimageCal").html(data);
                    }
                });
                $.ajax({
                    url: url12,
                    type: 'POST',
                    async: true,
                    data: "go=yes",
                    success: function(data) {
                        $("#desc").show();
                        $("#procImg").hide();
                        $("#desc").html(data.trim());
                    }
                });
            });
            function showMessage(dateVal) {
                $("#desc").hide();
                $("#procImg").show();
                var url = "../mail/GetMsgDesc.jsp?valDt=" + dateVal;
                $.ajax({
                    url: url,
                    type: 'POST',
                    async: true,
                    data: "go=yes",
                    success: function(data) {
                        $("#desc").show();
                        $("#procImg").hide();
                        $("#desc").html(data.trim());
                    }
                });
            }
        </script>
    </head>
    <body>
        <form name="form1" id="form1" method="POST">
            <div id="dumimageCal" style="width: 40%; height: 200px;float: left;"></div>
            <div style="width: 59%; height: 500px;border: 1px solid;float: left;">
                <table>
                    <tr>
                        <td></td>
                        <td>
                            <div id="procImg" style="padding: 179px 351px 0px;display: none;">
                                <table align="center" ><tr><td><img  src="../Resources/Images/loading38.gif" /></td></tr></table>
                            </div>
                            <textarea rows="21" cols="92" autofocus id="desc" style="font-size: 18px;font-family: Arial; padding: 10px 10px 10px;"></textarea>
                        </td>
                    </tr>
                </table>
            </div>
        </form>
    </body>
</html>
