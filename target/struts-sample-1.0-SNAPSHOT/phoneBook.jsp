<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>

<html>
<head>
    <title>Phone Book</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container h-100">
    <div class="row h-100 justify-content-center align-items-center">
        <div class="col-10 col-md-8 col-lg-6">
                <div class="form-group">
                    <h2 style="color: blue">Welcome To Online Phone Book!</h2>
                </div>
                <div class="form-group">
                    <h4>find persons and their phone numbers based on your inputs below</h4>
                </div>
        </div>
    </div>
    <div class="row h-100 justify-content-center align-items-center">
        <div class="col-10 col-md-8 col-lg-6">
            <html:form action="/phoneBook">
                <div class="form-group">
                    <label>First Name:</label>
                    <html:text property="firstName"/>
                </div>
                <div class="form-group">
                    <label>Last Name:</label>
                    <html:text property="lastName"/>
                </div>
                <html:submit value="Find Any Number" styleClass="btn btn-info"/>
            </html:form>
            <logic:notEmpty name="resultPersons">
                <logic:iterate name="resultPersons" id="person">
                    <h4>
                        <bean:write name="person" property="firstName"/>
                        <bean:write name="person" property="lastName"/>:
                        <p style="color: blue"><bean:write name="person" property="phoneNumber"/></p>
                    </h4>
                </logic:iterate>
            </logic:notEmpty>
            <logic:empty name="resultPersons">
                <h4 style="color: red">no result</h4>
            </logic:empty>
        </div>
    </div>
</div>


</body>
</html>