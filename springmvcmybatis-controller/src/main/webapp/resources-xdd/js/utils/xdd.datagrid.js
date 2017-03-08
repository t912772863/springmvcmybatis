/*columns
名称	类型	说明	默认值
title	string	列的标题文字。	undefined
field	string	列的字段名。	undefined
width	number	列的宽度。	undefined
rowspan	number	指一个单元格占据多少行。	undefined
colspan	number	指一个单元格占据多少列。	undefined
align	string	指如何对齐此列的数据，可以用 'left'、'right'、'center'。	undefined
sortable	boolean	True 就允许此列被排序。	undefined
resizable	boolean	True 就允许此列被调整尺寸。	undefined
hidden	boolean	True 就隐藏此列。	undefined
checkbox	boolean	True 就显示 checkbox。	undefined
formatter	function	单元格的格式化函数，需要三个参数： value： 字段的值。rowData： 行的记录数据。rowIndex： 行的索引。	undefined
styler	function	单元格的样式函数，返回样式字符串来自定义此单元格的样式，例如 'background:red' 。此函数需要三个参数：value： 字段的值。rowData： 行的记录数据。rowIndex： 行的索引。	undefined
 */
jQuery.fn.extend({
	datagrid: function(options){
		var defaults = {
				columns:null,		//datagrid 的 column 的配置对象，更多详细请参见 column 的特性。
				fitColumns:true, 	//True 就会自动扩大或缩小列的尺寸以适应表格的宽度并且防止水平滚动。	false
				striped:true, 		//True 就把行条纹化。（即奇偶行使用不同背景色）	false
				method:'post', 		//请求远程数据的 method 类型。	post
				nowrap:true,		//True 就会把数据显示在一行里。	true
				url:null,			//从远程站点请求数据的 URL。	null
				loadMsg:'Processing, please wait …',		//当从远程站点加载数据时，显示的提示信息。	Processing, please wait …
				noDataMsg:'Sorry! This category have nothing data.',
				noDataImg:null,//没有数据时显示的图片地址
				pagination:{show:true, data:{
					showLength: 5,
					next: '>>',
					prev: '<<',
					first: '首页',
					theme: 'bigBlue',
					last: '尾页',
					go: 'Go',
					showGo: true,
					showFirst: false,
					showLast: false
				}},	//True 就会在 datagrid 的底部显示分页栏。	false
				rownumbers:false,	//就会显示行号的列。	false
				singleSelect:false,	//就会只允许选中一行。	false
				pageNumber:1,		//当设置了 pagination 特性时，初始化页码。	1
				pageSize:10,		//当设置了 pagination 特性时，初始化页码尺寸。	10
				sortName:null,		//定义可以排序的列。	null
				sortOrder:'asc',	//定义列的排序顺序，只能用 'asc' 或 'desc'。	asc
				showFooter:false,	//定义是否显示一行页脚。	false
				rowStyler:null,		//返回例如 'background:red' 的样式，该函数需要两个参数：rowIndex： 行的索引，从 0 开始。rowData： 此行相应的记录。	 
				loadFilter:null,	//返回过滤的数据去显示。这个函数需要一个参数 'data' ，表示原始数据。你可以把原始数据变成标准数据格式，此函数必须返回标准数据对象，含有 'total' 和 'rows'特性。	 
				rowsData:{rows:[], total:0},		//当前数据
				onLoadSuccess:function(data){},//{total:0, rows:[]}
				onBeforeLoad:function(param){return true},//return false 则不执行远程访问
				onLoadError:function(){}
			};
		
		var operation = false;
		var type = '';
		//重新加载
		if(typeof options == 'string'){
			operation = true;
			type = options;
			options = {};
		}
		
		var settings = $.extend(true, defaults, {'operation':operation, 'type':type, 'args':arguments}, options||{});
		return this.each(function(){
			_$this = $(this);
			//
			var preOptions = _$this.data('options');
			//获取数据
			
			var _$content;
			var _$tbody;
			//分页条
			var paginatorDiv;
			
			if(preOptions){
				_$content = _$this.find('div[mark="content"]');
				_$tbody = _$this.find('table[mark="content"]');
				if(_$tbody.length == 0){
					_$tbody = $('<table>',{
						'mark':"content",
						'class':'filterTable storesTable tdBgColor',                                          
						'style':'table-layout:fixed;'
					});
				}
				paginatorDiv  =_$this.find('div[mark="paginator"]');
			}
			if(settings.operation && settings.type == 'reload') {
				settings = preOptions;
				settings.pageNumber = 1;
				obtainData();
				return this;
			}
			
			//定义css样式
			_$this.addClass('datagridListBox clearfix').data('options', settings);
			//创建table
			_$headTable = $('<table>', {
				'cellpadding':"0",
				'cellspacing':"0",
				'border':"0",
				'class':"datagridTable",
				'style':'table-layout:fixed;'
			});

			//创建表头
			var _$head = $('<thead>');
			var _$headTr = $('<tr>');
			if(settings.rownumbers) 
				_$headTr.append($('<th>', {'width': 50, 'align':'center'}));


			//如果自动填充则计算宽度
			if(settings.fitColumns){
				var pwidth = _$this.width();//父页面大小
				var sWidth = 0;
				var noWidth = [];
				var percentage = [];
				for(var x in settings.columns){
					var col = settings.columns[x];
					//数字
					if(col.width && /^[0-9]*$/.test(col.width)) sWidth += col.width;
					else if(/^-?\d+%$/.test(col.width)){//设置了百分比
						//取得百分比
						//var w = col.width.match(/^-?\d/);
						percentage.push(col);
					}else noWidth.push(col);
				}
				//都设置了宽度
				if(noWidth.length == 0 && percentage.length == 0){
					var t = 0;
					for(var x in settings.columns){
						var col = settings.columns[x];
						if(x == settings.columns.length){
							col.width = (100 - t) + '%';
							break;
						}

						var w = Math.ceil(col.width*100/sWidth);
						col.width = w + '%';
						t += w;
					}
				}
			}

			//拼接表头
			for(var x in settings.columns) 
				_$headTr.append(assemblyHeadTh(x, settings.columns[x]));

			_$content = $('<div>',{'mark':"content", style:'width:100%;text-align:center;'});
			_$tbody = $('<table>',{
				'mark':"content",
				'class':'filterTable storesTable tdBgColor',                                          
				'style':'table-layout:fixed;'
			});

			//添加表头
			_$headTable.append(_$head.append(_$headTr));

			//处理分页条
			if(settings.pagination.show){
				var paginData = settings.pagination.data;
				paginData.onchange = function(page){
					settings.pageNumber = page;
					obtainData();
				};
			}

			obtainData();

			//将创建好的table加入到容器中
			_$this.html($('<div>',{'class':'datagridList'}).append(_$headTable).append(_$content));

			//如果文本溢出 则显示title
			if(settings.nowrap) _$tbody.find('div[mark="nowrap"]').each(function(){
				if (this.offsetWidth < this.scrollWidth){
					$(this).attr('title', $(this).text());
				}else{
					$(this).removeAttr('title');
				}
			});
			
			$(window).resize(function(){
				if(settings.nowrap) _$this.find('div[mark="nowrap"]').each(function(){
					if (this.offsetWidth < this.scrollWidth){
						$(this).attr('title', $(this).text());
					}else{
						$(this).removeAttr('title');
					}
				});
			});


			//处理拼接表头
			function assemblyHeadTh(index, column){
				var $th = $('<th>', {'cellIndex': index, 'text':column.title});
				if(column.width) $th.attr('width', column.width);
				if(column.align) $th.attr('align', column.align);
				return $th;
			}

			function obtainData(){
				//url的优先级要高
				if(settings.url){
					var p = {};
					p.pageNumber = settings.pageNumber;
					p.pageSize = settings.pageSize;
					if(settings.onBeforeLoad){
						var r = settings.onBeforeLoad(p);
						if(r != undefined && !r) return;
					}
					$.ajax({
						type: settings.method,
						url: settings.url,
						data: p,
						beforeSend:function(){
							var h = _$content.height();
							if(h < 300) h = 300;
							_$tbody.remove();
							_$content.html('<div class="tipBox" style="height:'+ h +'px;"></div>');
							if(paginatorDiv) paginatorDiv.hide();
						},
						success: function(d){
							if(typeof d == 'string') d = eval('(' + d + ')');
							if(settings.onLoadSuccess) {
								var dd = settings.onLoadSuccess(d);
								if(dd) d = dd;
							}
							resolveData(d, settings.columns, _$tbody);
							//如果文本溢出 则显示title
							if(settings.nowrap) _$tbody.find('div[mark="nowrap"]').each(function(){
								if (this.offsetWidth < this.scrollWidth){
									$(this).attr('title', $(this).text());
								}
							});
						},
						dataType: 'text'
					});
					return;
				}
				if(settings.rowsData) {
					resolveData(settings.rowsData, settings.columns, _$tbody);
				}
			}

			/**
			 * data示例：{total:0, rows:[]}
			 * 处理每一行数据
			 */
			function resolveData(data, columns, $tbody){
				if(typeof data == 'string') data = eval('(' + data + ')');
				$tbody.empty();
				var rows = data.rows;
				if(rows.length == 0){
					_$tbody.remove();
					if(settings.noDataImg != null){
						_$content.html($('<div style="height:300px;"></div>')
						.css('background', 'url('+settings.noDataImg+') no-repeat center'));
					}else{
						_$content.html('<div style="font-size:14px; height:200px; line-height:200px;">'
								+settings.noDataMsg+'</div>');
					}
					resolvePagination(data.total);
					return;
				}
				for(var i in rows){
					var row = rows[i];
					var $tr = $('<tr>', {'rowIndex':i});
					if(settings.nowrap && settings.striped && i%2 == 1){
						$tr.css('backgroundColor', 'rgb(241, 241, 241)');
					}
					for(var x in columns){
						var col = columns[x];
						var _$td = $('<td>');
						if(i == 0 && col.width){
							_$td.attr('width', col.width);
						}
						if(col.align) _$td.attr('align', col.align);
						var _$div = $('<div>', {mark:'nowrap', width:'100%'});
						//自定义渲染器
						if(col.formatter) {
							_$div.html(col.formatter(row[col.field], row, i));
						}else{
							_$div.text(row[col.field]);
						}
						
						if(settings.nowrap) {
							_$div.css({'overflow':'hidden','white-space':'nowrap','text-overflow':'ellipsis'});
						}
						else _$div.css({'white-space':'normal'});
						
						$tr.append(_$td.append(_$div));
					}
					$tbody.append($tr);
				}
				_$content.html($tbody);
				resolvePagination(data.total);
			}

			//paginator
			function resolvePagination(total){
				if(!settings.pagination.show) return;
				var data = settings.pagination.data;
				data.total = total;
				data.pageNumber = settings.pageNumber;
				data.pageSize = settings.pageSize;
				if(!paginatorDiv) {
					paginatorDiv = $('<div>', {'mark':'paginator'}).paginator(data);
					_$this.append(paginatorDiv);
				}
				if(total > 0){
					paginatorDiv.paginator(data).show();
				}else{
					paginatorDiv.paginator(data).hide();
				}
			}

		});
	},
	//分页条
	paginator: function(options) {
		var settings = $.extend({
			total:0,
			pageSize: 10,
			showLength: 5,
			next: 'next',
			prev: 'prev',
			first: 'first',
			last: 'last',
			go: 'Go',
			theme: 'default',
			pageNumber: 1,
			showGo: true,
			showFirst: true,
			showLast: true,
			onchange: function(cur){},
			initCall: false
		}, options);
		return this.each(function() {
			var container = $(this).addClass('myPager '+settings.theme);
			container.empty();
			if(settings.total == 0)return;
			var totalPages = numPages();
			var pageNumber = settings.pageNumber > totalPages? totalPages:settings.pageNumber;
			var list = $('<span/>',{id:'pager'});
			var btnFirst,btnLast;
			if(settings.showFirst){
				btnFirst = $('<a>',{text:settings.first,click:function(){
					buildNavigation(1);
				}});
				container.append(btnFirst);
			}
			var btnPrev = $('<a>',{text:settings.prev,click:function(e){
				if($(e.currentTarget).attr('class') == 'disabled')return;
				var cur = getCurrentPage();
				var prev = cur > 1? cur-1: cur;
				buildNavigation(prev);
			}});
			container.append(btnPrev);
			initNavigation();
			var btnNext = $('<a>',{text:settings.next,click:function(e){
				if($(e.currentTarget).attr('class') == 'disabled')return;
				var cur = getCurrentPage();
				var next = cur < totalPages? cur+1: cur;
				buildNavigation(next);
			}});
			container.append(btnNext);
			if(settings.showLast){
				btnLast = $('<a>',{text:settings.last,click:function(){
					buildNavigation(totalPages);
				}});
				container.append(btnLast);
			}
			var jump;
			if(settings.showGo){
				jump = $('<span/>',{id:'jump'});
				var inputPage = $('<input>',{type: 'text','class':'input',value:pageNumber,focusout:function(e){
					var v = $(this).val();
					if(v.match(new RegExp("^[0-9]*[1-9][0-9]*$"))==null){
						$(this).val(1);
					}else if(v < 0 || v > totalPages){
						$(this).val(1);
					}
				},focusin:function(e){
					$(this).select();
				}});
				var go = $('<a>',{'text':settings.go,click:function(){
					var v = container.find('#jump input[class=input]').val();
					if(v != pageNumber){
						v = parseInt(v);
						buildNavigation(v);
					}
				}});
				jump.append(inputPage).append(go).append(" 共 " + totalPages + " 页　共 " + settings.total + ' 条记录');
				container.append(jump);
			}
			showRequiredButtons(pageNumber);
			if(settings.initCall){
				settings.onchange(pageNumber);
			}
			this.set = function (key,value){
				settings = $.extend(settings, {key:value});
				totalPages = numPages();
				pageNumber = settings.pageNumber > totalPages? totalPages:settings.pageNumber;
				buildNavigation(pageNumber);
			};

			/**
			 * 计算最大分页显示数目
			 */
			function numPages() {
				return Math.ceil(settings.total/settings.pageSize);
			} 
			/**
			 * 极端分页的起始和结束点，这取决于 当前页 和 显示条目数.
			 * @返回 {数组(Array)}
			 */
			function getInterval() {
				var ne_half = Math.ceil(settings.showLength/2);
				var np = numPages();
				var upper_limit = np - settings.showLength;
				var start = pageNumber > ne_half? Math.max(Math.min(pageNumber-ne_half+1, upper_limit+1), 1):1;
				var end = pageNumber > ne_half? Math.min(pageNumber+ne_half-1, np):Math.min(settings.showLength, np);
				return [start,end];
			}
			function getCurrentPage(){
				var text = container.find("span.current").text();
				return parseInt(text);
			}
			function initNavigation(){
				var inter = getInterval();
				for(var i=inter[0];i<=inter[1];i++){
					var p;
					if(i == pageNumber){
						p = $('<span>',{title:i, text:i, 'class':'current'});
					}else{
						p = $('<a>',{title:i, text:i,'href':'javascript:void(0)',click:function(ev){
							var tar = $(ev.currentTarget).attr('title');
							tar = parseInt(tar);
							buildNavigation(tar);
						}});
					}
					list.append(p);
				}
				container.append(list);
			}

			function buildNavigation(page){
				pageNumber = page;
				var inter = getInterval();
				var pageSpan = container.find('#pager').empty();
				for(var i=inter[0];i<=inter[1];i++){
					var p;
					if(i == page){
						p = $('<span>',{title:i, text: i,'class':'current'});
					}else{
						p = $('<a>',{title:i, text:i,'href':'javascript:void(0)',click:function(ev){
							var tar = $(ev.currentTarget).attr('title');
							tar = parseInt(tar);
							buildNavigation(tar);
						}});
					}
					pageSpan.append(p);
				}
				showRequiredButtons(page);
				settings.onchange(page);
			}
			function showRequiredButtons(page){
				if (totalPages >= settings.showLength) {
					if (page > 1) { btnPrev.removeClass('disabled'); }
					else { btnPrev.addClass('disabled'); }
					if (settings.showFirst && page != 1 && page > settings.showLength / 2 - 1) { btnFirst.css('display', ''); }
					else if(settings.showFirst){ btnFirst.css('display', 'none'); }
					if (page == totalPages) { btnNext.addClass('disabled'); }
					else btnNext.removeClass('disabled');
					if (settings.showLast && totalPages > settings.showLength && page < totalPages) { btnLast.css('display', ''); }
					else if(settings.showLast){ btnLast.css('display', 'none'); };
				} else if(totalPages > 1 && pageNumber == 1){
					if(settings.showFirst) btnFirst.css('display', 'none');
					if(settings.showLast) btnLast.css('display', '');
					btnPrev.addClass('disabled');
					btnNext.removeClass('disabled');
					/*if(settings.showGo && totalPages>1){
						jump.css('display', '');
					}else if(settings.showGo){
						jump.css('display', 'none');
					}*/
				} else if(totalPages > 1 && pageNumber == totalPages){
					if(settings.showFirst) btnFirst.css('display', '');
					if(settings.showLast) btnLast.css('display', 'none');
					btnNext.addClass('disabled');
					btnPrev.removeClass('disabled');
					/*if(settings.showGo && totalPages>1){
						jump.css('display', '');
					}else if(settings.showGo){
						jump.css('display', 'none');
					}*/
				} else if(totalPages > 1 && 1 < pageNumber < totalPages){
					/*if(settings.showGo && totalPages>1){
						jump.css('display', '');
					}else if(settings.showGo){
						jump.css('display', 'none');
					}*/
				} else {
					if(settings.showFirst)btnFirst.css('display', 'none');
					btnPrev.addClass('disabled');
					btnNext.addClass('disabled');
					if(settings.showLast) btnLast.css('display', 'none');
					/*if(settings.showGo && totalPages>1){
						jump.css('display', '');
					}else if(settings.showGo){
						jump.css('display', 'none');
					}*/
				}
			}
		});
	}
});

