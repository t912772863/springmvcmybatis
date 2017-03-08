<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="description" content="Avalon Admin Theme">
<meta name="author" content="The Red Team">
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/resources/images/tubiao.png" media="screen" />
<!--[if lt IE 10]>
<script src="<%=request.getContextPath()%>/assets/js/media.match.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/placeholder.min.js"></script>
<![endif]-->

<link href="<%=request.getContextPath()%>/assets/fonts/font-awesome/css/font-awesome.min.css" type="text/css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/assets/css/styles.min.css" type="text/css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/style.css" type="text/css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/assets/plugins/datatables/dataTables.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/resources/css/elastislide.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/assets/plugins/bootstrapValidator/dist/css/bootstrapValidator.min.css" type="text/css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/xdd.selectCss.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/resources/css/xdd.datagrid.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/resources/css/xdd.searchbox.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/assets/plugins/tipbox/tipbox.css" rel="stylesheet" type="text/css">
<%--<link href="<%=request.getContextPath()%>/resources/css/left-main-style.css" type="text/css" rel="stylesheet">--%>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries. Placeholdr.js enables the placeholder attribute -->
<!--[if lt IE 9]>
<link href="<%=request.getContextPath()%>/assets/css/ie8.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/respond.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/excanvas.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/html5.js"></script>
<![endif]-->
<!--[if IE]>
<link href="<%=request.getContextPath()%>/resources/css/ie.css" type="text/css" rel="stylesheet">
<![endif]-->
<!-- The following CSS are included as plugins and can be removed if unused-->
<script type="text/javascript">
    var contextpath = "<%=request.getContextPath()%>";
</script>
<!-- Load site level scripts -->
<script src="<%=request.getContextPath()%>/resources/js/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<%--<script src="<%=request.getContextPath()%>/assets/plugins/bootstrap-switch/bootstrap-switch.min.js" type="text/javascript"></script>--%>
<script src="<%=request.getContextPath()%>/assets/js/enquire.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/js/application.js" type="text/javascript"></script>
<%--<script src="<%=request.getContextPath()%>/assets/js/jqueryui-1.9.2.min.js" type="text/javascript"></script>--%>
<script src="<%=request.getContextPath()%>/assets/js/bootstrap.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.elastislide.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/plugins/datatables/jquery.dataTables.js"></script>                            <!-- Data Tables -->
<script src="<%=request.getContextPath()%>/assets/plugins/datatables/dataTables.bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/assets/plugins/bootbox/bootbox.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/utils/xdd.common.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/utils/xxd.selectCss.js"></script>
<script src="<%=request.getContextPath()%>/assets/plugins/tipbox/my.tipbox.js"></script>
<script src="<%=request.getContextPath()%>/assets/plugins/bootstrapValidator/dist/js/bootstrapValidator.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function(){
        $('#carousel').elastislide({
            imageW: 120,
            minItems: 0,
            border: 0
        });
        if(($.browser.mozilla && $.browser.version == "11.0") || ($.browser.msie && $.browser.version == "10.0")){
            $('table.table').css('border','1px solid #dadfe3');
        }
    });
</script>
