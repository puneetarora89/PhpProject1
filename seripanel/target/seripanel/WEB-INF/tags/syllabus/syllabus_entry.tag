<script type="text/javascript" src="${pageContext.request.contextPath}/resources/pages/js/tinymce/jquery.tinymce.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/pages/js/wysiwyg.js"></script>
<%@ include file="/WEB-INF/tags/customtaglibs.jspf" %>
<form class="stdform" action="${formAction}" method="post">
    <c:set var="hidSubId" value="0" />
    <c:choose>
        <c:when test="${not empty syllabusForm.subjectId && syllabusForm.subjectId>0}">
            <c:set var="hidSubId" value="${syllabusForm.subjectId}" />
        </c:when>
    </c:choose>

<script>
    $(document).ready(function(){
        syllabus.content();
        utils.schoolSubsSelector("standardId", "subjectIdContainer", "subjectId", "subjectId", "subjectId", "${subjectId}");
        syllabus.standardSyllabusFetcher("subjectId", "");
    });
</script>

        <input type="hidden" value="${syllabusForm.taskId}" id="syllabusId" name="taskId1">

    <p>
        <label>Select School</label>
        <span class="field">
                <schoolLibs:activeSchoolList ctrlName="schoolId" selectedSchool="${schoolId}" />
        </span>
    </p>

    <p>
        <label>Select Standard</label>
        <span class="field">
            <standardLibs:standardList ctrlName="standardId" selectedStandard="${standardId}" />
        </span>
    </p>

    <p>
        <label>Select Subject</label>
        <span class="field" id="subjectIdContainer">
            Select Subject First
        </span>
    </p>

    <p>
        <label>Due Date</label>
        <span class="field"><input type="text" name="taskDueDate" id="syllabusDueDate" value="${syllabusForm.taskDueDate}" class="input-large" placeholder=".input-xlarge" /></span>
    </p>


    <div>
        <textarea id="elm1" name="content" rows="15" cols="80" style="width: 80%" class="tinymce">
            &lt;p&gt;
                ${syllabusForm.content}
            &lt;/p&gt;
        </textarea>
    </div>
    <br />
    <button type="submit" name="submit" class="btn btn-primary">Submit</button>
    <button type="reset" name="reset" class="btn resetBtn">Reset</button>
</form>