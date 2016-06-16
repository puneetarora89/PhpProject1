/**
 * Created by puneet on 18/04/16.
 */
var $ = jQuery.noConflict();

var email = {
    deleteMail: function(){
        $(".mailAction").on("click", function(e){
            var mailId = $(this).attr("data-mailId");
            if($(this).attr("data-type")=="delRecMail")
                deleteMail(mailId, "recDel");
            e.preventDefault();
        });
        function deleteMail(mailId, sentDel){
            $.ajax({
                url: "/email/delete/",
                method: "POST",
                data: {mailId: mailId, mode: "recDel"},
                async: false,
                success: function(result) {
                    $(".messagemenu").append('<h4 style="text-align: center" id="mailDelAlert" class="widgettitle title-success">Email deleted successfully!!!</h4>');
                    $("div.emailBody[data-id="+mailId+"]").remove();
                    $("ul.msglist li[data-id="+mailId+"]").remove();
                    setTimeout(function(){
                        $("#mailDelAlert").remove();
                    }, 3000);
                }
            });
        }
    },

    changeReadStatus: function(){
        $(".unread").on("click", function(){
            var emailId = $(this).attr("data-id");
            var thisVar = $(this);
            $.ajax({
                url: "/email/read/",
                method: "POST",
                data: {mailId: emailId},
                async: false,
                success: function(result) {
                    thisVar.removeClass("unread");
                }
            });
        });
    },

    replyMail: function(){
        $(".mailAction").on("click", function(e){
            var mailId = $(this).attr("data-mailId");
            if($(this).attr("data-type")=="replyMail")
            {
                var thisVar = $(this);
                var sub = "RE: "+$("div.emailBody[data-id="+mailId+"]").find("h1.subject").text();
                var msg = $("div.emailBody[data-id="+mailId+"]").find("textarea").val();
                var repliedMailId = mailId;
                var fromId = $("div.emailBody[data-id="+mailId+"]").find("span.to input").val();
                var toId = $("div.emailBody[data-id="+mailId+"]").find("span.from").text();
                $.ajax({
                    url: "/email/sendemail/",
                    method: "POST",
                    data: {subject: sub, msg: msg, repliedMailId: repliedMailId, toId: toId, fromId: fromId, isAjax: true},
                    async: false,
                    success: function(result) {
                        $(".messagemenu").append('<h4 style="text-align: center" id="mailDelAlert" class="widgettitle title-success">Email sent successfully!!!</h4>');
                        setTimeout(function(){
                            $("#mailDelAlert").remove();
                        }, 5000);
                    }
                });
            }
            e.preventDefault();
        });
    }
}

var user = {
    roleChangerEvent: function(){
        $(".roleDepender").hide();
        $("#roleSelect").change(function(){
            var flag = false;
            var thisVar = $(this);
            $(".roleDepender").hide();
            var selected = $("option:selected", this).val();
            thisVar.parents("div.control-group").removeClass("error").find("span").text("");
            $(".btn-primary").removeAttr("disabled");
            if($("p[for='"+selected+"']").length>0){
                $("p[for='"+selected+"']").each(function(){
                    if($(this).find("select option").length == 1)
                    {
                        $(".btn-primary").attr("disabled", true);
                        thisVar.parents("div.control-group").addClass("error").find("span").text($(this).find("label.control-label").attr("data-supErrMsg"));
                        flag=true;
                        return;
                    }
                });
            }

            if(!flag)
                $("p[for='"+selected+"']").slideDown();
        });
    }
}

var teacher = {

    teacherListing: function(){
        var rpp = 10;
        if(getUrlVars()['rpp'] != undefined && getUrlVars()['rpp']>0){

            rpp=getUrlVars()['rpp'];
            if(rpp.indexOf('#')>-1)
                rpp = rpp.substr(0,rpp.indexOf('#'));
            $(".recordSelector").find("option[value="+rpp+"]").attr("selected", "selected");
        }


        var p=1;
        if(getUrlVars()['p'] != undefined && getUrlVars()['p']>0)
            p=getUrlVars()['p'];

        var deptId = $("#hidDepartmentId").val();

        $(".recordSelector").change(function(e){
            rpp = $("option:selected", this).val();
            teacher.listingAjax($("#hidSchoolId").val(), deptId, 1, rpp);
            e.preventDefault();
        });
        loader.init($(".teacher-table-container"));
        teacher.listingAjax($("#hidSchoolId").val(), deptId, p, rpp);

        $(document).on("click", ".pager li a", function(e){
            if($(this).parent("li").hasClass("disabled"))
                return;
            loader.init($(".teacher-table-container"));
            var p = $(this).attr("data-p");
            if($(this).text() == 'Next')
                $(this).attr("data-p", (parseInt(p)+1))
            teacher.listingAjax($("#hidSchoolId").val(), deptId, p, rpp);
            e.preventDefault();
        });
    },

    listingAjax: function(schoolid, deptId, pageno, rpp){
        $.ajax({
            url: "/teacher/teacherlisting/",
            method: "GET",
            data: {schoolid: schoolid, deptId: deptId, p: pageno, rpp:rpp},
            async: false,
            success: function(result) {
                $(".teacher-table-container").html(result);
            }
        });
    }
}

var student = {
    studentListing: function(){
        var rpp = 10;
        if(getUrlVars()['rpp'] != undefined && getUrlVars()['rpp']>0){

            rpp=getUrlVars()['rpp'];
            if(rpp.indexOf('#')>-1)
                rpp = rpp.substr(0,rpp.indexOf('#'));
            $(".recordSelector").find("option[value="+rpp+"]").attr("selected", "selected");
        }


        var p=1;
        if(getUrlVars()['p'] != undefined && getUrlVars()['p']>0)
            p=getUrlVars()['p'];

        $(".recordSelector").change(function(e){
            rpp = $("option:selected", this).val();
            student.listingAjax($("#hidSchoolId").val(), 1, rpp);
            e.preventDefault();
        });
        loader.init($(".student-table-container"));
        student.listingAjax($("#hidSchoolId").val(), p, rpp);

        $(document).on("click", ".pager li a", function(e){
            if($(this).parent("li").hasClass("disabled"))
                return;
            loader.init($(".student-table-container"));
            var p = $(this).attr("data-p");
            if($(this).text() == 'Next')
                $(this).attr("data-p", (parseInt(p)+1))
            student.listingAjax($("#hidSchoolId").val(), p, rpp);
            e.preventDefault();
        });
    },
    listingAjax: function(schoolid, pageno, rpp){
        $.ajax({
            url: "/student/studentlisting/",
            method: "GET",
            data: {schoolid: schoolid, p: pageno, rpp:rpp},
            async: false,
            success: function(result) {
                $(".student-table-container").html(result);
            }
        });
    }
};

var hod = {
    hodListing: function(){
        var rpp = 10;
        if(getUrlVars()['rpp'] != undefined && getUrlVars()['rpp']>0){

            rpp=getUrlVars()['rpp'];
            if(rpp.indexOf('#')>-1)
                rpp = rpp.substr(0,rpp.indexOf('#'));
            $(".recordSelector").find("option[value="+rpp+"]").attr("selected", "selected");
        }


        var p=1;
        if(getUrlVars()['p'] != undefined && getUrlVars()['p']>0)
            p=getUrlVars()['p'];

        $(".recordSelector").change(function(e){
            rpp = $("option:selected", this).val();
            hod.listingAjax($("#hidSchoolId").val(), 1, rpp);
            e.preventDefault();
        });
        loader.init($(".hod-table-container"));
        hod.listingAjax($("#hidSchoolId").val(), p, rpp);

        $(document).on("click", ".pager li a", function(e){
            if($(this).parent("li").hasClass("disabled"))
                return;
            loader.init($(".hod-table-container"));
            var p = $(this).attr("data-p");
            if($(this).text() == 'Next')
                $(this).attr("data-p", (parseInt(p)+1))
            hod.listingAjax($("#hidSchoolId").val(), p, rpp);
            e.preventDefault();
        });
    },
    listingAjax: function(schoolid, pageno, rpp){
        $.ajax({
            url: "/hod/hodlisting/",
            method: "GET",
            data: {schoolid: schoolid, p: pageno, rpp:rpp},
            async: false,
            success: function(result) {
                $(".hod-table-container").html(result);
            }
        });
    }
};

var standard = {
    standardListing: function(){
        var rpp = 10;
        if(getUrlVars()['rpp'] != undefined && getUrlVars()['rpp']>0){

            rpp=getUrlVars()['rpp'];
            if(rpp.indexOf('#')>-1)
                rpp = rpp.substr(0,rpp.indexOf('#'));
            $(".recordSelector").find("option[value="+rpp+"]").attr("selected", "selected");
        }


        var p=1;
        if(getUrlVars()['p'] != undefined && getUrlVars()['p']>0)
            p=getUrlVars()['p'];


        $(".recordSelector").change(function(e){
            rpp = $("option:selected", this).val();
            standard.listingAjax(0, 1, rpp);
            e.preventDefault();
        });
        loader.init($(".standard-table-container"));
        standard.listingAjax(0, p, rpp);

        $(document).on("click", ".pager li a", function(e){
            if($(this).parent("li").hasClass("disabled"))
                return;
            loader.init($(".standard-table-container"));
            var p = $(this).attr("data-p");
            if($(this).text() == 'Next')
                $(this).attr("data-p", (parseInt(p)+1))
            standard.listingAjax(0, p, rpp);
            e.preventDefault();
        });
    },

    listingAjax: function(schoolid, pageno, rpp){
        $.ajax({
            url: "/standard/standardlisting/",
            method: "GET",
            data: {schoolid: schoolid, p: pageno, rpp:rpp},
            async: false,
            success: function(result) {
                $(".standard-table-container").html(result);
            }
        });
    }
}

var standardId=0;
var subjectId = 0;
var subject = {
    subAddModal: function () {
        $(document).on("click", ".standardSubs > li a", function(e){
            if($(this).attr('data-action')=='add') {
                $("#subName").val("");
                $(".updateSubBtn").addClass("addSubBtn").removeClass("updateSubBtn");
                $(".addUpdateSubTitle").text("Add Subject");
                standardId = $(this).parent("li").parent("ul").attr("data-id");
                $(".modal-body h4").text("Selected Standard " + $(this).parents("td").prev("td").text());
                e.preventDefault();
            }
        });
    },

    addEditSubAjax: function(){
        $(document).on("click", ".addSubBtn", function(){
            $.ajax({
                url: "/subject/addsubject/",
                method: "GET",
                data: {name: $("#subName").val(), status: 1, standardId:standardId, isCompulsary:1},
                async: false,
                success: function(result) {
                    $(".msg-container").html('<h4 class="widgettitle title-success">Subject Added Successfully</h4>');
                    $("#subName").val("");
                    setTimeout(function(){
                        $(".msg-container").html("");
                    }, 4000);
                }
            });
        });

        $(document).on("click", ".updateSubBtn", function(){
            $.ajax({
                url: "/subject/addsubject/",
                method: "GET",
                data: {name: $("#subName").val(), status: 1, standardId:standardId, subjectId:subjectId, isCompulsary:1},
                async: false,
                success: function(result) {
                    $(".msg-container").html('<h4 class="widgettitle title-success">Subject Updated Successfully</h4>');
                    setTimeout(function(){
                        $(".msg-container").html("");
                    }, 4000);
                }
            });
        });
    },
    manageSubModal: function(){
        $(document).on("click", ".standardSubs > li a", function(e){
            if($(this).attr('data-action')=='manage') {
                $(".modal-body h4").text("Selected Standard " + $(this).parents("td").prev("td").text());
                standardId = $(this).parent("li").parent("ul").attr("data-id");
                subject.listingAjax(standardId);
                e.preventDefault();
            }
        });
    },
    editModal: function(){
        $(document).on("click", ".sub-edit-a", function(e){
            standardId = $(this).attr("data-standard-id");
            subjectId = $(this).attr("data-sub-id");
            $("#subName").val($(this).parents("td").prev("td").text());
            $(".addUpdateSubTitle").text("Update Subject");
            $(".addSubBtn").removeClass("addSubBtn").addClass("updateSubBtn");
            $(".close-btn").trigger("click");
            e.preventDefault();
        });
    },
    listingAjax: function(standardId){
        $.ajax({
            url: "/subject/subjectlisting/",
            method: "GET",
            data: {standardId: standardId},
            async: false,
            success: function(result) {
                $(".subject-listing-table tbody").html(result);
            }
        });
    }
};

var syllabus = {
    content: function(){
        var preSchoolId = $("option:selected", "#schoolId").val();

        $(document).on("change", "#schoolId", function(e){
            if($(this).val()==preSchoolId) {

            }

            tinymce.activeEditor.setContent("");
            $("#standardId").val(0).change();
            $("#subjectIdContainer").html("Select Subject First");
            $("#syllabusDueDate").val("");
            $(".stdform").attr("action", "/syllabus/addsyllabus");
            $("#syllabusId").val("0");
            e.preventDefault();
        });
    },
    standardSyllabusFetcher: function(ctrlSubjectId, placeCtrlId){
        $(document).on("change", "#"+ctrlSubjectId, function(){
            var schoolId = parseInt($("option:selected", "#schoolId").val());
            var standardId = parseInt($("option:selected", "#standardId").val());
            var subjectId = parseInt($(this).val());
            if(schoolId>0 && standardId>0 && subjectId>0)
                syllabus.fetchStandardCurrentSyllabus(schoolId,standardId,subjectId,placeCtrlId);
            else {
                tinymce.activeEditor.setContent("");
                $("#syllabusDueDate").val("");
                $(".stdform").attr("action", "/syllabus/addsyllabus");

            }

        });
    },
    fetchStandardCurrentSyllabus: function(schoolId,standardId,subjectId,placeCtrlId){
        $.ajax({
            url: "/syllabus/findlatestsyllabus/",
            method: "GET",
            data: {schoolId:schoolId, standardId: standardId, subjectId: subjectId},
            async: false,
            dataType:'json',
            success: function(result) {
                if(result.result==true){
                    tinymce.activeEditor.setContent(result.content);
                    $("#syllabusDueDate").val(result.dueDate);
                    $(".stdform").attr("action", "/syllabus/editsyllabus");
                    $("#syllabusId").val(result.id);

                } else {
                    tinymce.activeEditor.setContent("");
                    $("#syllabusDueDate").val("");
                    $(".stdform").attr("action", "/syllabus/addsyllabus");
                }
            }
        });
    }
};

var utils = {
    schoolSubsSelector: function(standardId, placeCtrlId, ctrlName, ctrlId, ctrlClass, selectedSubjectVal){
        var standardSelId = parseInt($("option:selected", "#"+standardId).val());
        if(standardSelId>0) {
            utils.subjectControlBuilder(standardSelId, placeCtrlId, ctrlName, ctrlId, ctrlClass, selectedSubjectVal)
        }

        $(document).on("change", "#"+standardId, function(){
            var standardSelId = parseInt($("option:selected", "#"+standardId).val());
            if(standardSelId>0) {
                utils.subjectControlBuilder(standardSelId, placeCtrlId, ctrlName, ctrlId, ctrlClass, selectedSubjectVal)
            }
        });
    },
    subjectControlBuilder: function(standardId, placeCtrlId, ctrlName, ctrlId, ctrlClass, selectedSubjectVal){
        $.ajax({
            url: "/subject/getschoolsubjects/",
            method: "GET",
            data: {standardId: standardId, ctrlName: ctrlName, ctrlId: ctrlId, ctrlClass: ctrlClass, selectedSubjectVal: selectedSubjectVal},
            async: false,
            success: function(result) {
                $("#"+placeCtrlId).html(result);
            }
        });
    }
};



var loader={
    init: function(ctrl){
        ctrl.html('<div align="center"><img src="/resources/pages/images/loaders/loader19.gif" alt=""></div>');
    }
}

function getUrlVars()
{
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)
    {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }

    return vars;
}