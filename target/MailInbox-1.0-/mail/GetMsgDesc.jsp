<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.util.Properties"%>
<%@page import="com.fastek.mailinbox.GetInboxData"%>
<%
    String dateVal = "";
    Properties prop = new Properties();
    InputStream input = null;
    String email = "";
    String pass = "";
    String sub = "";
    Object desc = null;
    try {
        String configP = request.getServletContext().getRealPath("/config");
        input = input = new FileInputStream(configP + "/maildetail.properties");
        prop.load(input);
        email = prop.getProperty("emailId");
        pass = prop.getProperty("password");
        sub = prop.getProperty("subject");
    } catch (Exception e) {
        e.printStackTrace();
    }

    GetInboxData inbox = new GetInboxData();
    try {
        if ("currentDate".equals(request.getParameter("valDt"))) {
            System.out.println("i am inside current date if block");
            desc = inbox.getCurrentDateDesc(dateVal, sub, email, pass);
        } else {
            dateVal = request.getParameter("valDt") == null ? "" : request.getParameter("valDt");
            desc = inbox.getDesc(dateVal, sub, email, pass);
        }
        response.getWriter().println(desc);
    } catch (Exception e) {
    }
%>
