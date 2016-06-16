<%@ include file="/WEB-INF/tags/customtaglibs.jspf" %>


<sec:authorize access="hasRole('ROLE_SUP_ADMIN')">
    <option value="ROLE_SUP_ADMIN" selected>Super Admin</option>
    <option value="ROLE_SUB_ADMIN">Sub Admin</option>
    <option value="ROLE_SCHOOL_ADMIN">School Admin (Principal)</option>
    <option value="ROLE_HOD">HOD</option>
    <option value="ROLE_TEACHER">Teacher</option>
    <option value="ROLE_PARENT">Parents</option>
    <option value="ROLE_STUDENT">Student</option>
    <option value="ROLE_ALUMNI">Alumni</option>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_SUB_ADMIN')">
    <option value="ROLE_SCHOOL_ADMIN">School Admin (Principal)</option>
    <option value="ROLE_HOD">HOD</option>
    <option value="ROLE_TEACHER">Teacher</option>
    <option value="ROLE_PARENT">Parents</option>
    <option value="ROLE_STUDENT">Student</option>
    <option value="ROLE_ALUMNI">Alumni</option>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_SCHOOL_ADMIN')">
    <option value="ROLE_HOD">HOD</option>
    <option value="ROLE_TEACHER">Teacher</option>
    <option value="ROLE_PARENT">Parents</option>
    <option value="ROLE_STUDENT">Student</option>
    <option value="ROLE_ALUMNI">Alumni</option>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_TEACHER')">
    <option value="ROLE_PARENT">Parents</option>
    <option value="ROLE_STUDENT">Student</option>
    <option value="ROLE_ALUMNI">Alumni</option>
</sec:authorize>