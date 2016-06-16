<%@ include file="/WEB-INF/tags/customtaglibs.jspf" %>
<c:choose>
    <c:when test="${emailErr==true}">
        <c:set var="emailErrClass" scope="request" value="error" />
    </c:when>
</c:choose>
<c:choose>
    <c:when test="${contactErr==true}">
        <c:set var="contactErrClass" scope="request" value="error" />
    </c:when>
</c:choose>
<div class="widget">
    <h4 class="widgettitle">School Form</h4>
    <c:choose>
        <c:when test="${param.addschool.equals('success')}">
            <h4 class="widgettitle title-success">New School Added Successfully</h4>
        </c:when>
        <c:when test="${param.update.equals('success')}">
            <h4 class="widgettitle title-success">School Information Updated Successfully</h4>
        </c:when>
    </c:choose>

    <div class="widgetcontent">
        <form class="stdform" action="/school/${formAction}" name="schoolForm" method="post">
            <input type="hidden" name="schoolId" value="${schoolForm.schoolId}">
            <input type="hidden" name="principalUserLogin" value="${schoolForm.principalUserLogin}">
            <div class="par control-group ${emailErrClass}">
                <label for="schoolEmail" class="control-label">School Email Id</label>
                <div class="controls">
                    <input type="text" class="span4 " id="schoolEmail" name="schoolEmail" value="${schoolForm.schoolEmail}" placeholder="Enter Email Id">
                    <span class="help-inline">${emailErrrrMsg}</span>
                </div>
            </div><!--par-->

            <div class="par control-group">
                <label for="schoolName" class="control-label">School Name</label>
                <div class="controls">
                    <input type="text" class="span4" id="schoolName" name="schoolName" value="${schoolForm.schoolName}" placeholder="Enter School Name">
                    <span class="help-inline"></span>
                </div>
            </div><!--par-->

            <p>
                <label>School Address</label>
                <span class="field"><textarea style="resize: vertical; height: 100px;" name="schoolAddress" class="span5" rows="5" cols="80" id="autoResizeTA">${schoolForm.schoolAddress}</textarea></span>
            </p>

            <div class="par control-group">
                <label for="country" class="control-label">Country</label>
                <div class="controls">
                    <utilLibs:getCountryList ctrlName="country" ctrlId="country" ctrlClass="country" selectedCountry="${schoolForm.country}" />
                    <span class="help-inline">${contactErrrrMsg}</span>
                </div>
            </div><!--par-->

            <div class="par control-group">
                <label for="state" class="control-label">State</label>
                <div class="controls">
                    <input type="text" class="span4" id="state" name="state" value="${schoolForm.state}" placeholder="Enter state">
                    <span class="help-inline">${contactErrrrMsg}</span>
                </div>
            </div><!--par-->

            <div class="par control-group ${contactErrClass}">
                <label for="schoolContact" class="control-label">Contact No</label>
                <div class="controls">
                    <input type="text" class="span4" id="schoolContact" name="schoolContact" value="${schoolForm.schoolContact}" placeholder="Enter Contact No">
                    <span class="help-inline">${contactErrrrMsg}</span>
                </div>
            </div><!--par-->
<input type="hidden" name="status" value="1">
            <%--<p>
                <label>Status</label>
                    <span class="formwrapper">
                        <input type="radio" name="status" value="1" checked="checked" /> Active &nbsp; &nbsp;
                        <input type="radio" name="status" value="0" /> In-Active
                    </span>
            </p>--%>

            <p class="stdformbutton">
                <button class="btn btn-primary">Submit Button</button>
                <button type="reset" class="btn">Reset Button</button>
            </p>

        </form>
    </div><!--widgetcontent-->
</div><!--widget-->