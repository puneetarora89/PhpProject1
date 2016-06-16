<%@ include file="/WEB-INF/tags/customtaglibs.jspf" %>

<div class="">
    <script type="text/javascript">
        jQuery(document).ready(function(){



            // delete row in a table
            if(jQuery('.deleterow').length > 0) {
                jQuery('.deleterow').click(function(){
                    var conf = confirm('Continue delete?');
                    if(conf)
                        jQuery(this).parents('tr').fadeOut(function(){
                            jQuery(this).remove();
                            // do some other stuff here
                        });
                    return false;
                });
            }

        });
    </script>

        <h4 class="widgettitle">School List</h4>
        <table class="table table-bordered responsive">
            <thead>
            <tr>
                <th>School Name</th>
                <th>Email</th>
                <th>Contact No</th>
                <th>Address</th>
                <%--<th>Status</th>--%>
                <th>Other Information</th>
                <th>Manage Syllabus</th>
                <th>&nbsp;</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="schoolItem" items="${schoolList}">
                <tr>
                    <td>${schoolItem.schoolName}</td>
                    <td>${schoolItem.schoolEmail}</td>
                    <td>${schoolItem.schoolContact}</td>
                    <td>${schoolItem.schoolAddress}</td>
                    <%--<td>${schoolItem.status}</td>--%>
                    <td>Principal Name : <utilLibs:nameUsingEmail loginId="${schoolItem.principalUserLogin}" /><br><a href="/teacher/manage/?schoolid=${schoolItem.schoolId}">No of teachers :  <schoolLibs:schoolTeacherCount schoolId="${schoolItem.schoolId}" /></a> | <a href="/student/manage/?schoolid=${schoolItem.schoolId}">No of Students : <schoolLibs:schoolStudentCount schoolId="${schoolItem.schoolId}" /></a> | <a href="/hod/manage/?schoolid=${schoolItem.schoolId}">No of HOD : <hodLibs:schoolHodCount schoolId="${schoolItem.schoolId}" /></a></td>
                    <td>
                        <c:forEach var="standard" items="${standardList}">
                            <a href='/syllabus/content/?standardid=${standard.standardId}&schoolid=${schoolItem.schoolId}'>${standard.standardName}</a>
                        </c:forEach>
                    </td>
                    <td class="centeralign"><a href="/school/editschool?id=${schoolItem.schoolId}" class="editSchool"><span class="icon-edit"></span></a> |
                        <sec:authorize access="hasRole('ROLE_SUP_ADMIN')">
                            <a href="" class="schoolLogin" title="Login as School"><span class="iconfa-signin"></span></a> |
                        </sec:authorize>
                        <a href="" class="deleterow"><span class="icon-trash"></span></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div><!--widgetcontent-->
