<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/tags/customtaglibs.jspf" %>

<c:choose>
    <c:when test="${emailErr==true}">
        <c:set var="emailErrClass" scope="request" value="error" />
    </c:when>
</c:choose>
<div class="widget">
    <h4 class="widgettitle">Parent Profile</h4>
    <div class="widgetcontent">
        <form class="stdform" action="/${formAction}" name="studentForm" method="post">
            <input type="hidden" name="parentId" value="${parentsForm.parentId}" />
            <input type="hidden" name="studentId" value="${parentsForm.studentId}" />
            <input type="hidden" name="p" value="${param.p}">
            <input type="hidden" name="rpp" value="${param.rpp}">
            <input type="hidden" name="schoolid" value="${param.cid}">
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

            <div class="par control-group">
                <label for="fOccupation" class="control-label">Father Occupation</label>
                <div class="controls">
                    <input type="text" class="span4" id="fOccupation" name="fOccupation" value="${parentsForm.fOccupation}" placeholder="Enter Father Occupation">
                    <span class="help-inline"></span>
                </div>
            </div><!--par-->

            <div class="par control-group">
                <label for="fDesignation" class="control-label">Father Designation</label>
                <div class="controls">
                    <input type="text" class="span4" id="fDesignation" name="fDesignation" value="${parentsForm.fDesignation}" placeholder="Enter Father Designation">
                    <span class="help-inline"></span>
                </div>
            </div><!--par-->

            <div class="par control-group">
                <label for="fQualification" class="control-label">Father Qualification</label>
                <div class="controls">
                    <input type="text" class="span4" id="fQualification" name="fQualification" value="${parentsForm.fQualification}" placeholder="Enter Father Qualification">
                    <span class="help-inline"></span>
                </div>
            </div><!--par-->

            <div class="par control-group">
                <label for="fMobNo" class="control-label">Father Mobile No</label>
                <div class="controls">
                    <input type="text" class="span4" id="fMobNo" name="fMobNo" value="${parentsForm.fMobNo}" placeholder="Enter Father Mobile No">
                    <span class="help-inline"></span>
                </div>
            </div><!--par-->
            <div class="par control-group">
                <label for="fOccupation" class="control-label">Monthly Income</label>
                <div class="controls">
                    <input type="text" class="span4" id="monthlyIncome" name="monthlyIncome" value="${parentsForm.monthlyIncome}" placeholder="Enter Monthly Income">
                    <span class="help-inline"></span>
                </div>
            </div><!--par-->






            <div class="par control-group">
                <label for="mOccupation" class="control-label">Mother Occupation</label>
                <div class="controls">
                    <input type="text" class="span4" id="mOccupation" name="mOccupation" value="${parentsForm.mOccupation}" placeholder="Enter Mother Occupation">
                    <span class="help-inline"></span>
                </div>
            </div><!--par-->

            <div class="par control-group">
                <label for="mDesignation" class="control-label">Mother Designation</label>
                <div class="controls">
                    <input type="text" class="span4" id="mDesignation" name="mDesignation" value="${parentsForm.mDesignation}" placeholder="Enter Mother Designation">
                    <span class="help-inline"></span>
                </div>
            </div><!--par-->

            <div class="par control-group">
                <label for="mQualification" class="control-label">Mother Qualification</label>
                <div class="controls">
                    <input type="text" class="span4" id="mQualification" name="mQualification" value="${parentsForm.mQualification}" placeholder="Enter Mother Qualification">
                    <span class="help-inline"></span>
                </div>
            </div><!--par-->

            <div class="par control-group">
                <label for="mMobNo" class="control-label">Mother Mobile No</label>
                <div class="controls">
                    <input type="text" class="span4" id="mMobNo" name="mMobNo" value="${parentsForm.mMobNo}" placeholder="Enter Mother Mobile No">
                    <span class="help-inline"></span>
                </div>
            </div><!--par-->






            <p class="stdformbutton">
                <button class="btn btn-primary">Submit Button</button>
                <button type="reset" class="btn">Reset Button</button>
            </p>

        </form>
    </div><!--widgetcontent-->
</div><!--widget-->