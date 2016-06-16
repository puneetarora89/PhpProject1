<%@ include file="/WEB-INF/tags/customtaglibs.jspf" %>

<div class="">
    <script type="text/javascript">
        $(document).ready(function(){
            student.studentListing();
        });
    </script>

    <h4 class="widgettitle">Student List</h4>
    <c:choose>
        <c:when test="${param.schoolid != null && param.schoolid>0}">
            <input type="hidden" id="hidSchoolId" value="${param.schoolid}">
        </c:when>
    </c:choose>

    <utilLibs:recordSelector />
    <div class="student-table-container"></div>


</div><!--widgetcontent-->