<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${emailErr==true}">
        <c:set var="emailErrClass" scope="request" value="error" />
    </c:when>
</c:choose>
<div class="widget">
    <h4 class="widgettitle">Update Profile</h4>
    <div class="widgetcontent">
        <form class="stdform" action="/${formAction}" name="schoolForm" method="post">

            <div class="par control-group ${emailErrClass}">
                <label for="login" class="control-label">Email Id</label>
                <div class="controls">
                    <input type="text" class="span4 " id="login" name="login" readonly="readonly" value="${userForm.login}" placeholder="Enter Email Id">
                    <span class="help-inline">${emailErrrrMsg}</span>
                </div>
            </div><!--par-->

            <div class="par control-group">
                <label for="fName" class="control-label">First Name</label>
                <div class="controls">
                    <input type="text" class="span4" id="fName" name="fName" readonly="readonly" value="${userForm.fName}" placeholder="Enter First Name">
                    <span class="help-inline"></span>
                </div>
            </div><!--par-->

            <div class="par control-group">
                <label for="mName" class="control-label">Middle Name</label>
                <div class="controls">
                    <input type="text" class="span4" id="mName" name="mName" readonly="readonly" value="${userForm.mName}" placeholder="Enter Middle Name">
                    <span class="help-inline"></span>
                </div>
            </div><!--par-->

            <div class="par control-group">
                <label for="lName" class="control-label">Last Name</label>
                <div class="controls">
                    <input type="text" class="span4" id="lName" name="lName" readonly="readonly" value="${userForm.lName}" placeholder="Enter Last Name">
                    <span class="help-inline"></span>
                </div>
            </div><!--par-->

            <div class="par control-group">
                <label for="dob" class="control-label">Date of Birth</label>
                <div class="controls">
                    <input type="text" class="span4" id="dob" name="dob" readonly="readonly" value="${userForm.dob}" placeholder="Enter Middle Name">
                    <span class="help-inline"></span>
                </div>
            </div><!--par-->

            <p>
                <label>Gender</label>
                <span class="formwrapper">
                    <input type="radio" name="gender" value="male" checked /> Male &nbsp; &nbsp;
                    <input type="radio" name="gender" value="female" /> Female
                </span>
            </p>

            <p class="stdformbutton">
                <button class="btn btn-primary">Submit Button</button>
                <button type="reset" class="btn">Reset Button</button>
            </p>

        </form>
    </div><!--widgetcontent-->
</div><!--widget-->