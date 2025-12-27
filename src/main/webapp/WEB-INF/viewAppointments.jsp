<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
<title>Admin | View Appointments</title>

<style>
body{
    font-family:Segoe UI, sans-serif;
    background:#f4f6f9;
}
.container{
    width:90%;
    margin:40px auto;
    background:white;
    padding:30px;
    border-radius:14px;
    box-shadow:0 15px 40px rgba(0,0,0,0.15);
}
h2{
    text-align:center;
    color:#0d6efd;
}
table{
    width:100%;
    border-collapse:collapse;
    margin-top:25px;
}
th,td{
    padding:14px;
    border-bottom:1px solid #ddd;
    text-align:center;
}
th{
    background:#0d6efd;
    color:white;
}
tr:hover{
    background:#f1f5ff;
}
.back{
    margin-top:25px;
    display:inline-block;
    padding:12px 25px;
    background:#20c997;
    color:white;
    text-decoration:none;
    border-radius:25px;
    font-weight:bold;
}
.back:hover{
    box-shadow:0 8px 20px rgba(0,0,0,0.25);
}
</style>

</head>

<body>

<div class="container">
<h2>📋 All Appointments</h2>

<table>
<tr>
    <th>Name</th>
    <th>Gender</th>
    <th>Phone</th>
    <th>Department</th>
    <th>Type</th>
    <th>Token</th>
</tr>

<%
ArrayList<String[]> list =
    (ArrayList<String[]>) request.getAttribute("appointments");

if(list != null && !list.isEmpty()){
    for(String[] row : list){
%>
<tr>
    <td><%= row[0] %></td>
    <td><%= row[1] %></td>
    <td><%= row[2] %></td>
    <td><%= row[3] %></td>
    <td><%= row[4] %></td>
    <td><%= row[5] %></td>
</tr>
<%
    }
} else {
%>
<tr>
    <td colspan="6">No Appointments Found</td>
</tr>
<%
}
%>

</table>

<!-- ✅ FIXED BACK LINK (NO 404 EVER) -->
<a href="<%= request.getContextPath() %>/admin" class="back">
    ⬅ Back to Admin
</a>

</div>

</body>
</html>
