<%@ include file="/WEB-INF/tags/customtaglibs.jspf" %>

<div class="widget">
    <h4 class="widgettitle">Form Elements</h4>
    <div class="widgetcontent">
        <form class="stdform" action="/${formAction}" name="userForm" method="post">

            <input type="hidden" name="standardId" value="${standardForm.standardId}">
            <input type="hidden" name="pid" value="${standardForm.pid}">
            <input type="hidden" name="schoolId" value="${standardForm.schoolId}">
            <input type="hidden" name="status" value="${standardForm.status}">

            <div class="par control-group">
                <label for="standardName" class="control-label">Standard Name</label>
                <div class="controls">
                    <input type="text" class="span4" id="standardName" name="standardName" value="${standardForm.standardName}" placeholder="Enter Standard Name">
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