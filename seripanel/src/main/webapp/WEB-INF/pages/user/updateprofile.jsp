<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/tags/customtaglibs.jspf" %>

<body>
<design:head />
<div class="mainwrapper">
    <design:header />

    <design:navigation />

    <div class="rightpanel">

        <design:breadcrumbs />

        <design:pageheader />

        <!--pageheader-->

        <div class="maincontent">

            <div class="maincontentinner">

                <sec:authorize access="hasRole('ROLE_SUP_ADMIN') or hasRole('ROLE_SUB_ADMIN')">
                    <adminLibs:adminProfile />
                </sec:authorize>

                <sec:authorize access="hasRole('ROLE_SCHOOL_ADMIN')">
                    <schoolLibs:school_form />
                </sec:authorize>

                <sec:authorize access="hasRole('ROLE_TEACHER')">
                    <teacherLibs:teacherProfileUpdate />
                </sec:authorize>

                <sec:authorize access="hasRole('ROLE_HOD')">
                    <hodLibs:hodProfile />
                </sec:authorize>

                <design:footer /><!--footer-->

            </div><!--maincontentinner-->

        </div><!--maincontent-->

    </div><!--rightpanel-->

</div><!--mainwrapper-->
</body>
</html>
