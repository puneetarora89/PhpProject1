<div class="widget">
    <h4 class="widgettitle">Send Message</h4>
    <div class="widgetcontent">
        <form class="stdform" action="/email/sendemail" name="userForm" method="post">
            <input type="hidden" class="span4 " id="fromId" name="fromId" value="${email.fromId}" placeholder="Enter message subject">
            <div class="par control-group">
                <label for="toId" class="control-label">To</label>
                <div class="controls">
                    <input type="text" class="span4 " id="toId" name="toId" value="" placeholder="Enter Email Id">
                    <span class="help-inline"></span>
                </div>
            </div><!--par-->

            <div class="par control-group">
                <label for="subject" class="control-label">Subject</label>
                <div class="controls">
                    <input type="text" class="span4 " id="subject" name="subject" value="${emailForm.subject}" placeholder="Enter message subject">
                    <span class="help-inline"></span>
                </div>
            </div><!--par-->

            <p>
                <label>Body</label>
                <span class="field"><textarea style="resize: none; width: 600px; height: 150px;" name="msg" class="span5" rows="15" cols="80" id="msg">${emailForm.msg}</textarea></span>
            </p>

            <p class="stdformbutton">
                <button class="btn btn-primary">Submit Button</button>
                <button type="reset" class="btn">Reset Button</button>
            </p>

        </form>
    </div><!--widgetcontent-->
</div><!--widget-->