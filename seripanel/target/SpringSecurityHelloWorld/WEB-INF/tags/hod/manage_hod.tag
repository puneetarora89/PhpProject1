<%@ include file="/WEB-INF/tags/customtaglibs.jspf" %>

<div class="">
    <script type="text/javascript">
        $(document).ready(function(){
            hod.hodListing()
        });
    </script>


    <c:choose>
        <c:when test="${param.schoolid != null && param.schoolid>0}">
            <h4 class="widgettitle">HOD List <em>(<utilLibs:getSchoolAttribute schoolId="${param.schoolid}" propName="name" /> )</em></h4>
            <input type="hidden" id="hidSchoolId" value="${param.schoolid}">
        </c:when>
        <c:otherwise>
            <h4 class="widgettitle">Teacher List</h4>
        </c:otherwise>
    </c:choose>

        <utilLibs:recordSelector />
    <div class="hod-table-container"></div>


</div><!--widgetcontent-->
