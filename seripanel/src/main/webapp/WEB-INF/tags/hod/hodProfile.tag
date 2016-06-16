<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/tags/customtaglibs.jspf" %>

<c:choose>
    <c:when test="${emailErr==true}">
        <c:set var="emailErrClass" scope="request" value="error" />
    </c:when>
</c:choose>
<div class="widget">
    <h4 class="widgettitle">HOD Profile</h4>
    <div class="widgetcontent">
        <form class="stdform" action="/${formAction}" name="studentForm" method="post">
            <input type="hidden" name="hodId" value="${hodForm.hodId}" />
            <input type="hidden" name="p" value="${param.p}">
            <input type="hidden" name="rpp" value="${param.rpp}">
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
                    <input type="text" class="span4" id="fName" name="fName" readonly="readonly" value="${userForm.fName}" readonly="readonly" placeholder="Enter First Name">
                    <span class="help-inline"></span>
                </div>
            </div><!--par-->

            <div class="par control-group">
                <label for="mName" class="control-label">Middle Name</label>
                <div class="controls">
                    <input type="text" class="span4" id="mName" name="mName" readonly="readonly" value="${userForm.mName}" readonly="readonly" placeholder="Enter Middle Name">
                    <span class="help-inline"></span>
                </div>
            </div><!--par-->

            <div class="par control-group">
                <label for="lName" class="control-label">Last Name</label>
                <div class="controls">
                    <input type="text" class="span4" id="lName" name="lName" readonly="readonly" value="${userForm.lName}" readonly="readonly" placeholder="Enter Last Name">
                    <span class="help-inline"></span>
                </div>
            </div><!--par-->

            <div class="par control-group">
                <label for="dob" class="control-label">Date of Birth</label>
                <div class="controls">
                    <input type="text" class="span4" id="dob" name="dob" value="${userForm.dob}" readonly="readonly" placeholder="Enter Date of Birth">
                    <span class="help-inline"></span>
                </div>
            </div><!--par-->

            <p>
                <label>Gender</label>
                <span class="formwrapper">
                    <c:choose>
                        <c:when test="${userForm.gender.equals('male')}">
                            <input type="radio" name="gender" value="male" checked disabled="disabled" /> Male &nbsp; &nbsp;
                            <input type="radio" name="gender" value="female" disabled /> Female
                        </c:when>
                        <c:otherwise>
                            <input type="radio" name="gender" value="male" disabled /> Male &nbsp; &nbsp;
                            <input type="radio" name="gender" value="female" checked disabled="disabled" /> Female
                        </c:otherwise>
                    </c:choose>
                </span>
            </p>


            <sec:authorize access="hasRole('ROLE_SUP_ADMIN') or hasRole('ROLE_SCHOOL_ADMIN')">
                <div class="par control-group">
                    <label for="hodSchoolId" class="control-label">School</label>
                    <div class="controls">
                        <schoolLibs:activeSchoolList ctrlName="hodSchoolId" selectedSchool="${hodForm.hodSchoolId}" />
                        <span class="help-inline"></span>
                    </div>
                </div><!--par-->

                <div class="par control-group">
                    <label for="hodDepartmentId" class="control-label">Department</label>
                    <div class="controls">
                        <utilLibs:getDepartmentList ctrlName="hodDepartmentId" ctrlId="hodDepartmentId" ctrlClass="hodDepartmentId" selectedDepartment="${hodForm.hodDepartmentId}" />
                        <span class="help-inline"></span>
                    </div>
                </div><!--par-->
            </sec:authorize>

            <p>
                <label>Address</label>
                <span class="field"><textarea style="resize: vertical; height: 100px;" name="hodAddress" class="span5" rows="5" cols="80" id="hodAddress">${hodForm.hodAddress}</textarea></span>
            </p>


            <p class="stdformbutton">
                <button class="btn btn-primary">Submit Button</button>
                <button type="reset" class="btn">Reset Button</button>
            </p>

        </form>
    </div><!--widgetcontent-->
</div><!--widget-->