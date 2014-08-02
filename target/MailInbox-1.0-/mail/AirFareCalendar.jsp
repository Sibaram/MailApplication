
<%@page import="com.fastek.commons.CalendarUtilty"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.List"%>
<link rel="stylesheet" type="text/css"  href="../Resources/Styles/CalenderCss.css"/>

<%
    String currDate = "";
    String CurrentMonth = "";
    String dd = "";
    String MM = "";
    String yyyy = "";
    String YYYYMMforQurey = "";
    String YYYYMMDDforQurey = "";
    List<Object[]> lsi = null;
    CalendarUtilty cu = new CalendarUtilty();
    String curDateCol = "";
    Date curDate = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
    String curDateStr = dateFormat.format(curDate);
    try {
        String reqFrom = request.getParameter("go");
        if ("yes".equals(reqFrom)) {
            currDate = cu.getCurrentdate();
            StringTokenizer st = new StringTokenizer(currDate, "-");
            dd = st.nextToken();
            MM = st.nextToken();
            yyyy = st.nextToken();
            CurrentMonth = cu.getMonthName(MM);
            YYYYMMforQurey = yyyy + "-" + MM;
        } else if ("next".equals(reqFrom)) {
            String screenDate = request.getParameter("screenDate");
            currDate = cu.getNextMonthRawDate(screenDate);
            StringTokenizer st = new StringTokenizer(currDate, "-");
            dd = st.nextToken();
            MM = st.nextToken();
            yyyy = st.nextToken();
            CurrentMonth = cu.getMonthName(MM);
            YYYYMMforQurey = yyyy + "-" + MM;
        } else if ("prv".equals(reqFrom)) {
            String screenDate = request.getParameter("screenDate");
            currDate = cu.getPreviousMonthRawDate(screenDate);
//            System.out.println("/////prv---" + currDate + "--screenDate--" + screenDate);
            StringTokenizer st = new StringTokenizer(currDate, "-");
            dd = st.nextToken();
            MM = st.nextToken();
            yyyy = st.nextToken();
            CurrentMonth = cu.getMonthName(MM);
            YYYYMMforQurey = yyyy + "-" + MM;
        }
  //      System.out.println("currDate--" + currDate);
        List list = cu.getCalenderData(currDate);

    //    System.out.println("/////YYYYMMDDforQurey--->" + YYYYMMDDforQurey + "--CurrentMonthandYear--" + CurrentMonth);

        String datet[] = (String[]) list.get(0);

        String day = list.get(1).toString();
      //  System.out.println("day--" + day + "--date[]--size--" + datet.length);
        int monthRow = cu.monthRowValue(datet.length, "fri");
        List dayArr = new ArrayList();
        dayArr.add("Sun");
        dayArr.add("Mon");
        dayArr.add("Tue");
        dayArr.add("Wed");
        dayArr.add("Thu");
        dayArr.add("Fri");
        dayArr.add("Sat");
        int monthStartIndex = dayArr.indexOf(day);
        int dateVal = 0;
        //System.out.println("monthStartIndex--" + monthStartIndex + "--date[]--size--" + datet.length + "...monthRow==" + monthRow);

%>
<div class="CalMainDiv">
    <div class="CalMonthDiv">
        <table class="CalMonthTB">
            <td align="left" class="LMonth"><img src="../Resources/Images/prev_1.png" style="width: 20px;height: 20px; float: left; cursor: pointer;" onclick="goPreviousMonth('prv')" /></td>
            <td align="center" class="MMonth"><label class="CalMMYYYY"><%=CurrentMonth%> <%=yyyy%></label></td>
            <td align="right" class="RMonth"><img src="../Resources/Images/next_1.png" style="width: 20px;height: 20px; float: right; cursor: pointer;" onclick="goNextMonth('next')" /></td>

        </table>
    </div>
    <table align="left" style="width: 100%; background: white;border: 1px solid;">
        <thead class="calHead" >
        <td align="center">Sun</td>
        <td align="center">Mon</td>
        <td align="center">Tue</td>
        <td align="center">Wed</td>
        <td align="center">Thu</td>
        <td align="center">Fri</td>
        <td align="center">Sat</td>
        </thead>
        <%for (int k = 0; k < monthRow; k++) {%>
        <tr class="trStyle">
            <%
                if (k == 0) {
                    for (int i = 0; i < monthStartIndex; i++) {
            %>
            <td align="center" class="CalDayNoSchedule"></td>
            <%                            }
                }
                for (int j = 0; j < 7 - monthStartIndex && dateVal != datet.length; j++) {
                    String dt = datet[dateVal].toString() + "-" + CurrentMonth + "-"+yyyy;
                    String dt1 = datet[dateVal].toString() + "-" + CurrentMonth + "-"+yyyy;
                    if (curDateStr.equals(dt)) {
                        curDateCol = "color:red;font-size:17px;";
                        dt = "";
                    }else{
                        dt = "";
                        curDateCol = "";
                    }
            %>
            <td valign="top" class="CalDay" onclick="showMessage('<%=dt1%>');">
                <label class="onlineClassData" style="<%=curDateCol%>"> <%=datet[dateVal].toString()%></label>
            </td>
            <%
                    dateVal++;
                }
                monthStartIndex = 0;
            %>
        </tr>
        <%}%>
    </table>
</div>
<input type="hidden" id="CalScreDate" value="<%=currDate%>"/>
<%
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
