<%@ include file="/WEB-INF/tags/customtaglibs.jspf" %>
<%@ attribute name="value" description="value" %>
<%@ attribute name="maxChar" description="maxChar" %>
<c:choose>
    <c:when test="${fn:length(value)>maxChar}">
        ${fn:substring(value, 0, maxChar)}...
    </c:when>
    <c:otherwise>
        ${value}
    </c:otherwise>
</c:choose>