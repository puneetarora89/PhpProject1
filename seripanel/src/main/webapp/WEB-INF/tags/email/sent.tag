<%@ include file="/WEB-INF/tags/customtaglibs.jspf" %>
<script type="text/javascript">
    jQuery(document).ready(function(){
        jQuery('.msglist li').click(function(){
            jQuery('.msglist li').each(function(){ jQuery(this).removeClass('selected')});
            jQuery(this).addClass('selected');
            jQuery("div.messageview").hide();
            var divId = jQuery(this).attr("data-id");
            jQuery("div.messageview[data-id="+divId+"]").show();
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

        jQuery(".mailAction").on("click", function(e){
            var mailId = jQuery(this).attr("data-mailId");
            if(jQuery(this).attr("data-type")=="delSentMail")
                deleteMail(mailId, "sentDel");
            e.preventDefault();
        });
        function deleteMail(mailId, sentDel){
            jQuery.ajax({
                url: "/email/delete/",
                method: "POST",
                data: {mailId: mailId, mode: "sentDel"},
                async: false,
                success: function(result) {
                    jQuery(".messagemenu").append('<h4 style="text-align: center" id="mailDelAlert" class="widgettitle title-success">Email deleted successfully!!!</h4>');
                    jQuery("div.messageview[data-id="+mailId+"]").remove();
                    jQuery("ul.msglist li[data-id="+mailId+"]").remove();
                    setTimeout(function(){
                        jQuery("#mailDelAlert").remove();

                    }, 3000);
                }
            });
        }

    });
</script>
<div class="messagepanel">
    <div class="messagehead">
        <a href="/email/compose"> <button class="btn btn-success btn-large">Compose Message</button> </a>
    </div><!--messagehead-->
    <div class="messagemenu">
        <ul>
            <li class="back"><a><span class="iconfa-chevron-left"></span> Back</a></li>
            <li><a href="/email/inbox"><span class="iconfa-inbox"></span> Inbox</a></li>
            <li class="active"><a href=""><span class="iconfa-plane"></span> Sent</a></li>
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
                <c:forEach items="${sentEmailList}" var="emailDetail">
                    <li class=""  data-id="${emailDetail.emailId}">
                        <div class="thumb"><img src="${pageContext.request.contextPath}/resources/pages/images/photos/thumb1.png" alt="" /></div>
                        <div class="summary">
                            <span class="date pull-right"><small><utilLibs:dateFormatter dateVar="${emailDetail.sentDate}" /></small></span>
                            <h4><utilLibs:nameUsingEmail loginId="${emailDetail.toId}" /></h4>
                            <p><strong><commonLib:ellipses maxChar="15" value="${emailDetail.subject}" /></strong> - <commonLib:ellipses maxChar="15" value="${emailDetail.msg}" /></p>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div><!--messageleft-->
        <div class="messageright">

            <c:forEach items="${sentEmailList}" var="emailDetail">
                <div class="messageview" style="display: none;" data-id="${emailDetail.emailId}">

                    <div class="btn-group pull-right">
                        <button data-toggle="dropdown" class="btn dropdown-toggle">Actions <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <%--<li><a href="#">Forward</a></li>
                            <li><a href="#">Report as Spam</a></li>--%>
                            <li><a href="#" class="mailAction" data-type="delSentMail" data-mailId="${emailDetail.emailId}">Delete Message</a></li>
                            <%--<li><a href="#">Print Message</a></li>
                            <li><a href="#">Mark as Unread</a></li>--%>
                        </ul>
                    </div>

                    <h1 class="subject">${emailDetail.subject}</h1>
                    <div class="msgauthor">
                        <div class="thumb"><img src="${pageContext.request.contextPath}/resources/pages/images/photos/thumb1.png" alt="" /></div>
                        <div class="authorinfo">
                            <span class="date pull-right"><utilLibs:dateFormatter dateVar="${emailDetail.sentDate}" /></span>
                            <h5><strong><utilLibs:nameUsingEmail loginId="${emailDetail.fromId}" /></strong> <span>${emailDetail.fromId}</span></h5>
                            <span class="to">to ${emailDetail.toId}</span>
                        </div><!--authorinfo-->
                    </div><!--msgauthor-->
                    <div class="msgbody">
                            ${emailDetail.msg}
                    </div><!--msgbody-->


                </div><!--messageview-->
            </c:forEach>

        </div><!--messageright-->
    </div><!--messagecontent-->
</div><!--messagepanel-->