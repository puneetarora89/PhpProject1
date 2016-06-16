<%@ include file="/WEB-INF/tags/customtaglibs.jspf" %>

<sec:authorize access="hasRole('ROLE_SCHOOL_ADMIN')">
    <design:principalNavigation />
</sec:authorize>

<sec:authorize access="hasRole('ROLE_SUP_ADMIN')">
    <design:supAdminNavigation />
</sec:authorize>

<sec:authorize access="hasRole('ROLE_HOD')">
    <design:hodNavigation />
</sec:authorize>