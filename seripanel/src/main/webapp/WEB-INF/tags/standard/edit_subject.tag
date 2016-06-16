<script>subject.subAddModal();subject.addSubAjax();</script>
<div aria-hidden="false" aria-labelledby="standardAddForm" role="dialog" tabindex="-1" class="modal hide fade in" id="standardAddForm">
    <div class="modal-header">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">&times;</button>
        <h3 id="myModalLabel" class="addUpdateSubTitle">Update Subject</h3>
    </div>
    <div class="modal-body">
        <h4>Selected Standard 10th</h4>
        <form class="stdform" action="/addadmin" name="userForm" method="get">
            <div class="par control-group ${emailErrClass}">
                <label for="subName" class="control-label" style="width: auto">Subject Name</label>
                <div class="controls">
                    <input type="text" class="span4 " id="subName" name="subName" value="" placeholder="Enter Subject Name">
                    <span class="help-inline">${emailErrrrMsg}</span>
                </div>
            </div><!--par-->

        </form>
    </div>
    <div class="modal-footer">
        <button data-dismiss="modal" class="btn">Close</button>
        <button class="btn btn-primary addSubBtn">Save changes</button>
    </div>
</div><!--#myModal-->
