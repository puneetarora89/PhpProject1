<%@ include file="/WEB-INF/tags/customtaglibs.jspf" %>
<script type="text/javascript">
    jQuery(document).ready(function(){
        email.changeReadStatus();
        email.deleteMail();
        email.replyMail();
        jQuery('.msglist li').click(function(){
            jQuery('.msglist li').each(function(){ jQuery(this).removeClass('selected')});
            jQuery(this).addClass('selected');
            jQuery("div.emailBody").hide();
            var divId = jQuery(this).attr("data-id");
            jQuery("div.emailBody[data-id="+divId+"]").show();
            // for mobile
            jQuery('.msglist').click(function(){
                if(jQuery(window).width() < 480) {
                    jQuery('.messageright, .messagemenu .back').show();
                    jQuery('.messageleft').hide();
                }
            });

            jQuery('.messagemenu .back').click(function(){
                if(jQuery(window).width() < 480) {
                    jQuery('.messageright, .messagemenu .back').hide();
                    jQuery('.messageleft').show();
                }
            });
        });



    });
</script>
<div class="messagepanel">
    <div class="messagehead">
        <a href="/email/compose"> <button class="btn btn-success btn-large">Compose Message</button> </a>
    </div><!--messagehead-->
    <div class="messagemenu">
        <ul>
            <li class="back"><a><span class="iconfa-chevron-left"></span> Back</a></li>
            <li class="active"><a href="#"><span class="iconfa-inbox"></span> Inbox</a></li>
            <li><a href="/email/sent"><span class="iconfa-plane"></span> Sent</a></li>
            <%--<li><a href=""><span class="iconfa-edit"></span> Draft</a></li>
            <li><a href=""><span class="iconfa-trash"></span> Trash</a></li>--%>
        </ul>
    </div>
    <div class="messagecontent">
        <div class="messageleft">
            <form class="messagesearch">
                <input type="text" class="input-block-level" placeholder="Search message and hit enter..." />
            </form>
            <ul class="msglist">
                <c:forEach items="${recEmailList}" var="emailDetail">
                    <c:set var="emailClass">
                        <c:choose><c:when test="${emailDetail.readStatus == 0}">unread</c:when></c:choose>
                    </c:set>
                    <li class="${emailClass}"  data-id="${emailDetail.emailId}">
                        <div class="thumb"><img src="${pageContext.request.contextPath}/resources/pages/images/photos/thumb1.png" alt="" /></div>
                        <div class="summary">
                            <span class="date pull-right"><small><utilLibs:dateFormatter dateVar="${emailDetail.sentDate}" /></small></span>
                            <h4><utilLibs:nameUsingEmail loginId="${emailDetail.fromId}" /></h4>
                            <p><strong><commonLib:ellipses maxChar="15" value="${emailDetail.subject}" /></strong> - <commonLib:ellipses maxChar="15" value="${emailDetail.msg}" /></p>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div><!--messageleft-->
        <div class="messageright">
            <c:forEach items="${recEmailList}" var="emailDetail">
                <div class="emailBody" style="display: none" data-id="${emailDetail.emailId}">
                <div class="messageview">

                    <div class="btn-group pull-right">
                        <button data-toggle="dropdown" class="btn dropdown-toggle">Actions <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <li><a href="/email/compose?forward=${emailDetail.emailId}">Forward</a></li>
                            <li><a href="#" class="mailAction" data-type="delRecMail" data-mailId="${emailDetail.emailId}">Delete Message</a></li>
                        </ul>
                    </div>

                    <h1 class="subject">${emailDetail.subject}</h1>
                    <div class="msgauthor">
                        <div class="thumb"><img src="images/photos/thumb1.png" alt="" /></div>
                        <div class="authorinfo">
                            <span class="date pull-right"><utilLibs:dateFormatter dateVar="${emailDetail.sentDate}" /></span>
                            <h5><strong><utilLibs:nameUsingEmail loginId="${emailDetail.fromId}" /></strong> <span class="from">${emailDetail.fromId}</span></h5>
                            <span class="to">to ${emailDetail.toId}<input type="hidden" value="${emailDetail.toId}"></span>
                        </div><!--authorinfo-->
                    </div><!--msgauthor-->
                    <div class="msgbody">
                            ${emailDetail.msg}
                    </div><!--msgbody-->


                </div><!--messageview-->

                <div class="msgreply">
                    <div class="thumb"><img src="${pageContext.request.contextPath}/resources/pages/images/photos/thumb1.png" alt="" /><br>
                        <a class="mailAction" data-type="replyMail" data-mailId="${emailDetail.emailId}" href="#">Send</a></div>
                    <div class="reply">
                        <textarea placeholder="Type something here to reply" data-id="${emailDetail.emailId}"></textarea>
                    </div><!--reply-->
                </div><!--messagereply-->
                </div>
            </c:forEach>




        </div><!--messageright-->
    </div><!--messagecontent-->
</div><!--messagepanel-->