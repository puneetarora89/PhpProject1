<%@ include file="/WEB-INF/tags/customtaglibs.jspf" %>

<div class="">
    <script type="text/javascript">
        $(document).ready(function(){
            standard.standardListing();
        });
    </script>


    <h4 class="widgettitle">Standard List</h4>



    <utilLibs:recordSelector />
    <div class="standard-table-container"></div>


</div><!--widgetcontent-->
