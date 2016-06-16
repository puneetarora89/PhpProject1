<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="widget">
    <h4 class="widgettitle">Teacher Profile</h4>
    <div class="widgetcontent">
        <form class="stdform" action="/${formAction}" name="teacherForm" method="post">
            <input type="hidden" name="tLoginId" value="${userForm.userId}" />
            <input type="hidden" name="p" value="${param.p}">
            <input type="hidden" name="rpp" value="${param.rpp}">


            <p>
                <label class="label-text">Email Id</label>
                <c:choose>
                    <c:when test="${not empty teacher.teacherLoginId}">
                        ${teacher.teacherLoginId}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label class="label-text">First Name</label>
                <c:choose>
                    <c:when test="${not empty teacher.fName}">
                        ${teacher.fName}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label class="label-text">Middle Name</label>
                <c:choose>
                    <c:when test="${not empty teacher.mName}">
                        ${teacher.mName}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label class="label-text">Last Name</label>
                <c:choose>
                    <c:when test="${not empty teacher.lName}">
                        ${teacher.lName}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label class="label-text">Date of Birth</label>
                <c:choose>
                    <c:when test="${not empty teacher.dob}">
                        ${teacher.dob}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label class="label-text">Gender</label>
                <c:choose>
                    <c:when test="${not empty teacher.gender}">
                        ${teacher.gender}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label class="label-text">Describe yourself</label>
                <c:choose>
                    <c:when test="${not empty teacher.tDescription}">
                        ${teacher.tDescription}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label class="label-text">Nationality</label>
                <c:choose>
                    <c:when test="${not empty teacher.tNationality}">
                        ${teacher.tNationality}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label class="label-text">Personal Email</label>
                <c:choose>
                    <c:when test="${not empty teacher.tPersonalEmail}">
                        ${teacher.tPersonalEmail}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label class="label-text">Complete Home Address</label>
                <c:choose>
                    <c:when test="${not empty teacher.tHomeAdress}">
                        ${teacher.tHomeAdress}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label class="label-text">Phone No</label>
                <c:choose>
                    <c:when test="${not empty teacher.tPhNo}">
                        ${teacher.tPhNo}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label class="label-text">Mobile No</label>
                <c:choose>
                    <c:when test="${not empty teacher.tMobNo}">
                        ${teacher.tMobNo}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label class="label-text">Total Teaching Experience</label>
                <c:choose>
                    <c:when test="${not empty teacher.tExperiance}">
                        ${teacher.tExperiance}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label class="label-text">10th Percentage</label>
                <c:choose>
                    <c:when test="${not empty teacher.tSecMarks}">
                        ${teacher.tSecMarks}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label class="label-text">12th Percentage</label>
                <c:choose>
                    <c:when test="${not empty teacher.tSenSecMarks}">
                        ${teacher.tSenSecMarks}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label class="label-text">Graduation Subjects</label>
                <c:choose>
                    <c:when test="${not empty teacher.tGradMetadata}">
                        ${teacher.tGradMetadata}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label class="label-text">Post Graduation Subjects</label>
                <c:choose>
                    <c:when test="${not empty teacher.tPgMetadata}">
                        ${teacher.tPgMetadata}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label class="label-text">Any Professional Course</label>
                <c:choose>
                    <c:when test="${not empty teacher.tSpecialization}">
                        ${teacher.tSpecialization}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label class="label-text">Other subject specialization</label>
                <c:choose>
                    <c:when test="${not empty teacher.tOthQualificationMetadata}">
                        ${teacher.tOthQualificationMetadata}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label class="label-text">Teaching Catagory</label>
                <c:choose>
                    <c:when test="${not empty teacher.tOthSpecialization}">
                        ${teacher.tOthSpecialization}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label class="label-text">Department Name</label>
                <c:choose>
                    <c:when test="${not empty teacher.tDeptName}">
                        ${teacher.tDeptName}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label class="label-text">Designation</label>
                <c:choose>
                    <c:when test="${not empty teacher.tDesignation}">
                        ${teacher.tDesignation}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label class="label-text">Other Skills<br><small>Like singing/Dancing/Any sport</small></label>
                <c:choose>
                    <c:when test="${not empty teacher.tOthSkills}">
                        ${teacher.tOthSkills}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>
            <p>&nbsp;</p>
            <p>
                <label class="label-text">Joining Date (DD/MM/YYYY)</label>
                <c:choose>
                    <c:when test="${not empty teacher.tJoiningDate}">
                        ${teacher.tJoiningDate}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label class="label-text">Language Know (Speak)</label>
                <c:choose>
                    <c:when test="${not empty teacher.tLangSpeak}">
                        ${teacher.tLangSpeak}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label class="label-text">Language Know (Written)</label>
                <c:choose>
                    <c:when test="${not empty teacher.tLangWrite}">
                        ${teacher.tLangWrite}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label class="label-text">Any other language</label>
                <c:choose>
                    <c:when test="${not empty teacher.tOthLang}">
                        ${teacher.tOthLang}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label class="label-text">References 1</label>
                <c:choose>
                    <c:when test="${not empty teacher.tRef1Metadata}">
                        ${teacher.tRef1Metadata}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label class="label-text">References 2</label>
                <c:choose>
                    <c:when test="${not empty teacher.tRef2Metadata}">
                        ${teacher.tRef2Metadata}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label class="label-text">Detail of last organisation</label>
                <c:choose>
                    <c:when test="${not empty teacher.tLastOrgMetadata}">
                        ${teacher.tLastOrgMetadata}
                    </c:when>
                    <c:otherwise>--</c:otherwise>
                </c:choose>
            </p>

            <p>
                <label>Select with Search</label>
                            <span class="formwrapper">
                            	<select data-placeholder="Choose a Country..." style="width:350px" class="chzn-select" tabindex="2">
                                    <option value=""></option>
                                    <option value="United States">United States</option>
                                    <option value="United Kingdom" disabled>United Kingdom</option>
                                    <option value="Afghanistan">Afghanistan</option>
                                    <option value="Albania">Albania</option>

                                </select>
                            </span>
            </p>

        </form>
    </div><!--widgetcontent-->
</div><!--widget-->