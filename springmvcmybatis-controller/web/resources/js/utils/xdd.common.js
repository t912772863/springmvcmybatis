// /**
//  * ajax请求重新封装
//  */
// $.extend({
//     xAjax:function(options){
//         var suc = options.success;
//         if(suc){
//             options.success = function(data){
//                 $.successFunc(data, suc);
//             }
//         }
//         var err = options.error;
//         if(err){
//             options.error = function(XMLHttpRequest, textStatus, errorThrown){
//                 err(XMLHttpRequest, textStatus, errorThrown);
//             }
//         }
//         $.ajax(options);
//     },
//     xPost: function (url){
//         if(arguments.length > 1 && typeof arguments[1]  == 'function'){
//             var call = arguments[1];
//             $.post(url, function(data){
//                 $.successFunc(data, call);
//             });
//         }else if(arguments.length > 2 && typeof arguments[2]  == 'function'){
//             var call = arguments[2];
//             $.post(url, arguments[1], function(data){
//                 $.successFunc(data, call);
//             });
//         }
//     },
//     xDataTablesParams:function(params, searchText){
//         var columns = params.columns;
//         var orders = params.order;
//         for(var x in columns){
//             var column = columns[x];
//             if(column.searchable) params['search.' + column.data] = searchText;
//         }
//         for(var x in orders){
//             var order = orders[x];
//             var key = columns[order.column]['data'];
//             params['order.'+key] = order.dir;
//         }
//         delete params.columns;
//         delete params.search;
//         delete params.order;
//         return params;
//     },
//     successFunc:function(data, call){
//         try{
//             if(typeof data == 'string') data = eval("(" + data + ")");
//             $.closeTipbox();
//         }catch(e){}
//         if(typeof data != 'object' || !("status" in data)) {
//             call(data);
//             return;
//         }
//         if(data.status == -1){
//             bootbox.alert("系统异常，请刷新重试！");
//             return;
//         }
//         if(data.status == 1){//业务异常
//             bootbox.alert("登录超时，请重新登录再重试！");
//             return;
//         }
//         if(data.status == 2){//登录超时
//             bootbox.alert(data.result);
//             return;
//         }
//         call(data.result);
//     }
// });
// $(function(){
//     function alignHeight(eleA,eleB,eleC){
//         //alert(eleA);
//         if(!document.getElementById(eleA)){return false;}
//         if(!document.getElementById(eleB)){return false;}
//
//         var heightA = document.getElementById(eleA).clientHeight;
//         var heightB = document.getElementById(eleB).clientHeight;
//
//         var height=new Array(heightA,heightB);
//         var a=Math.max.apply(Math,height);
//         //console.log(a);
//         document.getElementById(eleA).style.height = a + "px";
//         document.getElementById(eleB).style.height = a + "px";
//     }
//     alignHeight("g-left","g-right");
//
//     $('.acc-menu .fix-show li').on('click',function () {
//         $('.acc-menu .fix-show li').removeClass('fix-active');
//         $(this).addClass('fix-active');
//     })
// });
//
