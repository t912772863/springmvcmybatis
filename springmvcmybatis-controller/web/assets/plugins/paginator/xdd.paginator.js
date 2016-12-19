
jQuery.fn.extend({
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
					if (settings.showFirst && page != 1 && page > settings.showLength / 2 - 1) { btnFirst.removeClass('disabled'); }
					else if(settings.showFirst){ btnFirst.addClass('disabled'); }
					if (page == totalPages) { btnNext.addClass('disabled'); }
					else btnNext.removeClass('disabled');
					if (settings.showLast && totalPages > settings.showLength && page < totalPages) { btnLast.removeClass('disabled'); }
					else if(settings.showLast){ btnLast.addClass('disabled'); };
				} else if(totalPages > 1 && pageNumber == 1){
					if(settings.showFirst) btnFirst.addClass('disabled');
					if(settings.showLast) btnLast.removeClass('disabled');
					btnPrev.addClass('disabled');
					btnNext.removeClass('disabled');
					/*if(settings.showGo && totalPages>1){
						jump.css('display', '');
					}else if(settings.showGo){
						jump.css('display', 'none');
					}*/
				} else if(totalPages > 1 && pageNumber == totalPages){
//					if(settings.showFirst) btnFirst.css('display', '');
                    if(settings.showFirst)btnFirst.removeClass('disabled');
//					if(settings.showLast) btnLast.css('display', 'none');
                    if(settings.showLast) btnLast.addClass('disabled');
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
//					if(settings.showFirst)btnFirst.css('display', 'none');
                    if(settings.showFirst)btnFirst.addClass('disabled');
					btnPrev.addClass('disabled');
					btnNext.addClass('disabled');
//					if(settings.showLast) btnLast.css('display', 'none');
                    if(settings.showLast) btnLast.addClass('disabled');
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

