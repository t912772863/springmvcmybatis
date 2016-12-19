/* Set the defaults for DataTables initialisation */
$.extend( true, $.fn.dataTable.defaults, {
	/*"sDom":
		"<'row'<'col-sm-6'l><'col-sm-6'f>r>"+
		"t"+
		"<'row'<'col-sm-6'i><'col-sm-6'p>>",*/
    'sDom': 'rt<"row"<"col-sm-6"<"p_left"l>i><"col-sm-6"p>>',
    "bProcessing": true, //DataTables载入数据时，是否显示‘进度’提示
    "bServerSide": true, //是否启动服务器端数据导入
    "bStateSave": false, //是否打开客户端状态记录功能,此功能在ajax刷新纪录的时候不会将个性化设定回复为初始化状态
    "sServerMethod": 'post',
    "bJQueryUI": false, //是否使用 jQury的UI theme
    "aLengthMenu": [
        [10, 20, 50],
        [10, 20, 50]
    ],
    "iDisplayLength": 10,
    "bAutoWidth": false, //是否自适应宽度
    //"bScrollInfinite" : false, //是否启动初始化滚动条
    "bScrollCollapse": true, //是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变
    "bPaginate": true, //是否显示（应用）分页器
    "bInfo": true, //是否显示页脚信息，DataTables插件左下角显示记录数
    "sPaginationType" : "bootstrap", //详细分页组，可以支持直接跳转到某页
    "bSort": true, //是否启动各个字段的排序功能
    "oLanguage": { //国际化配置
        "sProcessing": "正在获取数据，请稍后...",
        "sLengthMenu": "显示 _MENU_ 条",
        "sZeroRecords": "没有您要搜索的内容",
        "sInfo": "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
        "sInfoEmpty": "记录数为0",
        "sInfoFiltered": "(全部记录数 _MAX_ 条)",
        "sInfoPostFix": "",
        "sSearch": "搜索",
        "sUrl": "",
        "oPaginate": {
            "sFirst": "首页",
            "sPrevious": "上一页",
            "sNext": "下一页",
            "sLast": "尾页"
        }
    }
} );


/* Default class modification */
$.extend( $.fn.dataTableExt.oStdClasses, {
	"sWrapper": "dataTables_wrapper form-inline",
	"sFilterInput": "form-control",
	"sLengthSelect": ""
} );

// In 1.10 we use the pagination renderers to draw the Bootstrap paging,
// rather than  custom plug-in
// if ( $.fn.dataTable.Api ) {
// 	$.fn.dataTable.defaults.renderer = 'bootstrap';
// 	$.fn.dataTable.ext.renderer.pageButton.bootstrap = function ( settings, host, idx, buttons, page, pages ) {
// 		var api = new $.fn.dataTable.Api( settings );
// 		var classes = settings.oClasses;
// 		var lang = settings.oLanguage.oPaginate;
// 		var btnDisplay, btnClass;

// 		var attach = function( container, buttons ) {
// 			var i, ien, node, button;
// 			var clickHandler = function ( e ) {
// 				e.preventDefault();
// 				if ( e.data.action !== 'ellipsis' ) {
// 					api.page( e.data.action ).draw( false );
// 				}
// 			};

// 			for ( i=0, ien=buttons.length ; i<ien ; i++ ) {
// 				button = buttons[i];

// 				if ( $.isArray( button ) ) {
// 					attach( container, button );
// 				}
// 				else {
// 					btnDisplay = '';
// 					btnClass = '';

// 					switch ( button ) {
// 						case 'ellipsis':
// 							btnDisplay = '&hellip;';
// 							btnClass = 'disabled';
// 							break;

// 						case 'first':
// 							btnDisplay = lang.sFirst;
// 							btnClass = button + (page > 0 ?
// 								'' : ' disabled');
// 							break;

// 						case 'previous':
// 							btnDisplay = lang.sPrevious;
// 							btnClass = button + (page > 0 ?
// 								'' : ' disabled');
// 							break;

// 						case 'next':
// 							btnDisplay = lang.sNext;
// 							btnClass = button + (page < pages-1 ?
// 								'' : ' disabled');
// 							break;

// 						case 'last':
// 							btnDisplay = lang.sLast;
// 							btnClass = button + (page < pages-1 ?
// 								'' : ' disabled');
// 							break;

// 						default:
// 							btnDisplay = button + 1;
// 							btnClass = page === button ?
// 								'active' : '';
// 							break;
// 					}

// 					if ( btnDisplay ) {
// 						node = $('<li>', {
// 								'class': classes.sPageButton+' '+btnClass,
// 								'aria-controls': settings.sTableId,
// 								'tabindex': settings.iTabIndex,
// 								'id': idx === 0 && typeof button === 'string' ?
// 									settings.sTableId +'_'+ button :
// 									null
// 							} )
// 							.append( $('<a>', {
// 									'href': '#'
// 								} )
// 								.html( btnDisplay )
// 							)
// 							.appendTo( container );

// 						settings.oApi._fnBindAction(
// 							node, {action: button}, clickHandler
// 						);
// 					}
// 				}
// 			}
// 		};

// 		attach(
// 			$(host).empty().html('<ul class="pagination"/>').children('ul'),
// 			buttons
// 		);
// 	}
// }
// else {
	// Integration for 1.9-
	$.fn.dataTable.defaults.sPaginationType = 'bootstrap';

	/* API method to get paging information */
	$.fn.dataTableExt.oApi.fnPagingInfo = function ( oSettings )
	{
		return {
			"iStart":         oSettings._iDisplayStart,
			"iEnd":           oSettings.fnDisplayEnd(),
			"iLength":        oSettings._iDisplayLength,
			"iTotal":         oSettings.fnRecordsTotal(),
			"iFilteredTotal": oSettings.fnRecordsDisplay(),
			"iPage":          oSettings._iDisplayLength === -1 ?
				0 : Math.ceil( oSettings._iDisplayStart / oSettings._iDisplayLength ),
			"iTotalPages":    oSettings._iDisplayLength === -1 ?
				0 : Math.ceil( oSettings.fnRecordsDisplay() / oSettings._iDisplayLength )
		};
	};

	/* Bootstrap style pagination control */
    $.extend( $.fn.dataTableExt.oPagination, {
        "bootstrap": {
            "fnInit": function( oSettings, nPaging, fnDraw ) {
                var oLang = oSettings.oLanguage.oPaginate;
                var fnClickHandler = function ( e ) {
                    e.preventDefault();
                    if ( oSettings.oApi._fnPageChange(oSettings, e.data.action) ) {
                        fnDraw( oSettings );
                    }
                };
                $(nPaging).append(
                        '<ul class="pagination">'+
                        '<li class="first disabled"><a href="#">'+oLang.sFirst+'</a></li>'+//此处添加
                        '<li class="prev disabled"><a href="#">'+oLang.sPrevious+'</a></li>'+
                        '<li class="next disabled"><a href="#">'+oLang.sNext+'</a></li>'+
                        '<li class="last disabled"><a href="#">'+oLang.sLast+'</a></li>'+//此处添加
                        '</ul>'
                );
                var els = $('a', nPaging);
                $(els[0]).bind( 'click.DT', { action: "first" }, fnClickHandler );//此处添加
                $(els[1]).bind( 'click.DT', { action: "previous" }, fnClickHandler );
                $(els[2]).bind( 'click.DT', { action: "next" }, fnClickHandler );
                $(els[3]).bind( 'click.DT', { action: "last" }, fnClickHandler );//此处添加
            },

            "fnUpdate": function ( oSettings, fnDraw ) {
                var iListLength = 5;
                var oPaging = oSettings.oInstance.fnPagingInfo();
                var an = oSettings.aanFeatures.p;
                var i, ien, j, sClass, iStart, iEnd, iHalf=Math.floor(iListLength/2);

                if ( oPaging.iTotalPages < iListLength) {
                    iStart = 1;
                    iEnd = oPaging.iTotalPages;
                }
                else if ( oPaging.iPage <= iHalf ) {
                    iStart = 1;
                    iEnd = iListLength;
                } else if ( oPaging.iPage >= (oPaging.iTotalPages-iHalf) ) {
                    iStart = oPaging.iTotalPages - iListLength + 1;
                    iEnd = oPaging.iTotalPages;
                } else {
                    iStart = oPaging.iPage - iHalf + 1;
                    iEnd = iStart + iListLength - 1;
                }

                for ( i=0, ien=an.length ; i<ien ; i++ ) {
                    // Remove the middle elements
                    $('li:gt(1)', an[i]).filter(':lt(-2)').remove();//此处修改 $('li:gt(0)', an[i]).filter(':not(:last)').remove();

                    // Add the new list items and their event handlers
                    for ( j=iStart ; j<=iEnd ; j++ ) {
                        sClass = (j==oPaging.iPage+1) ? 'class="active"' : '';
                        $('<li '+sClass+'><a href="#">'+j+'</a></li>')
                            .insertBefore( $('li:eq(-2)', an[i])[0] )//此处修改 .insertBefore( $('li:last', an[i])[0] )
                            .bind('click', function (e) {
                                e.preventDefault();
                                oSettings._iDisplayStart = (parseInt($('a', this).text(),10)-1) * oPaging.iLength;
                                fnDraw( oSettings );
                            } );
                    }

                    // Add / remove disabled classes from the static elements
                    if ( oPaging.iPage === 0 ) {
                        $('li:lt(2)', an[i]).addClass('disabled'); //此处修改 $('li:first', an[i]).addClass('disabled');
                    } else {
                        $('li:lt(2)', an[i]).removeClass('disabled'); //此处修改$('li:first', an[i]).removeClass('disabled');
                    }

                    if ( oPaging.iPage === oPaging.iTotalPages-1 || oPaging.iTotalPages === 0 ) {
                        $('li:gt(-3)', an[i]).addClass('disabled'); //此处修改$('li:last', an[i]).addClass('disabled');
                    } else {
                        $('li:gt(-3)', an[i]).removeClass('disabled'); //此处修改$('li:last', an[i]).removeClass('disabled');
                    }
                }
            }
        }
    } );
//}


/*
 * TableTools Bootstrap compatibility
 * Required TableTools 2.1+
 */
if ( $.fn.DataTable.TableTools ) {
	// Set the classes that TableTools uses to something suitable for Bootstrap
	$.extend( true, $.fn.DataTable.TableTools.classes, {
		"container": "DTTT btn-group",
		"buttons": {
			"normal": "btn btn-default",
			"disabled": "disabled"
		},
		"collection": {
			"container": "DTTT_dropdown dropdown-menu",
			"buttons": {
				"normal": "",
				"disabled": "disabled"
			}
		},
		"print": {
			"info": "DTTT_print_info modal"
		},
		"select": {
			"row": "active"
		}
	} );

	// Have the collection use a bootstrap compatible dropdown
	$.extend( true, $.fn.DataTable.TableTools.DEFAULTS.oTags, {
		"collection": {
			"container": "ul",
			"button": "li",
			"liner": "a"
		}
	} );
}

