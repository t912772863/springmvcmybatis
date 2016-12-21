//JavaScript Document
/**
 * [{}]
 */
jQuery.fn.extend({
	selectCss : function(options){
		var defaults = {
            speed : 'fast',
            name : $(this).attr('name'), //如果是form提交，设置一个隐藏属性保存值
            nameField : null,
            valueField : null,
            value:null,
            data:[],
            url:null,
            queryParams:{},
            method:'post',
            disable:false,
            searchable:false,
            delay:500,
            onBeforeLoad:function(param){return true},
            onLoadSuccess:function(data){return data},
            onChange:function(cur, prev){}
		};
		
		var operation = false;
		var type = '';
		//重新加载
		if(typeof options == 'string'){
			operation = true;
			type = options;
			options = {};
		}
		
		var settings = $.extend({'operation':operation, 'type':type, 'args':arguments}, defaults, options||{});
		
		return this.each(function(){
			var preOptions = $(this).data('options');
			//重新加载
			if(settings.url == null && settings.data == null) return this;
			var divoptions;
			var divselect;
			var inputHidden;
			$this = $(this);
			if(preOptions){
				divoptions = $this.find('dl[mark="divoptions"]');
				divselect = $this.find('.tag_select');
				inputHidden = $this.find(':input[name="'+preOptions.name+'"]');
			}
			if(settings.operation && settings.type == 'getValue') {
				var d = $this.data("selectValue");
				return d;
			}
			if(settings.operation && settings.type == 'reload') {
				inputHidden.val('');
				divselect.val('');
				preOptions.value = settings.args.length > 1? settings.args[1]:null;
				settings = preOptions;
				loadData($this);
				return this;
			}
			if(settings.operation && settings.type == 'resetUrl') {
				inputHidden.val('');
				divselect.val('');
				preOptions.value = settings.args.length > 2? settings.args[2]:null;
				preOptions.url = settings.args.length > 1? settings.args[1]:null;
				settings = preOptions;
				loadData($this);
				return this;
			}
			if(settings.operation && settings.type == 'setData') {
				inputHidden.val('');
				var d = settings.args.length > 1? settings.args[1]:null;
                preOptions.value = settings.args.length > 2? settings.args[2]:null;
				settings = preOptions;
				resolveData(d, $this);
				return this;
			}
            if(settings.operation && settings.type == 'disable') {
                preOptions.disable = settings.args.length > 1? settings.args[1]:true;
                settings = preOptions;
                if(settings.disable){
                    divselect.attr('readonly','readonly');
                    divselect.addClass('disable');
                }else{
                    divselect.removeAttr('readonly');
                    divselect.removeClass('disable');
                }
                return this;
            }
			if(settings.operation && settings.type == 'setValue'){
				var val = settings.args.length > 1? settings.args[1]:null;
				preOptions.value = null;
				settings = preOptions;
				var rowData;
				var rowDatas = $this.data('rowDatas');
				if(val != null && typeof val == 'object'){
					rowData = val;
					val = val[settings.valueField];
				}else if(val != null){
					for(var x in rowDatas){
						var d = rowDatas[x];
						if(val == d[settings.valueField]){
							rowData = d;
							break;
						}
					}
				}
				$this.data("selectValue", rowData);
				if(!rowData) return this;
				divselect.val(rowData[settings.nameField]);
				divoptions.find('dd[value="'+ val +'"]').addClass("open_selected").siblings().removeClass("open_selected");
				inputHidden.val(val);
				return this;
			}
            if(settings.operation){
                console.log("不支持的方法：" + settings.type);
                return this;
            }
			
			$this.data('options', settings);
			
			$this.addClass('selectBoxs').empty();
			inputHidden = $("<input type='hidden' name='" + settings.name + "'/>");
			divselect = $("<input type='text' name='" + settings.name + "_label' class='tag_select'>");
            if(settings.searchable == false) divselect.attr('readonly', 'readonly');
            else divselect.keydown(function(){
                if($(this).val() == "") {//清除选择项
                }
            });
			$this.append(inputHidden).append(divselect);

			divoptions = $("<dl mark='divoptions' style='overflow-y: auto;'></dl>").insertAfter(divselect).addClass("tag_options").hide();

			divselect.click(function(e){
                if(settings.disable) return;
				if($($(document).data("nowselectoptions")).get(0) != $(this).nextAll("dl.tag_options").get(0)){
					hideOptions(settings.speed); 
				}
				if(!$(this).nextAll("dl.tag_options").is(":visible"))
				{ 
					e.stopPropagation(); 
					$(document).data("nowselectoptions",$(this).nextAll("dl.tag_options"));
					showOptions(settings.speed); 
				} 
			}); 

			divselect.hover(function(){
                if(settings.disable) return;
				$(this).addClass("tag_select_hover"); 
			} , function(){
                if(settings.disable) return;
				$(this).removeClass("tag_select_hover"); 
			});
			
			loadData($this);
			
			function loadData($this){
				if(settings.url){
					var p = settings.queryParams;
					if(!p) p = {};
					if(settings.onBeforeLoad){
						var r = settings.onBeforeLoad(p);
						if(r != undefined && !r) return;
					}
					$.xAjax({
						type: settings.method,
						url: settings.url,
						data: p,
						success: function(d){
							if(typeof d == 'string') d = eval('(' + d + ')');
							if(settings.onLoadSuccess) {
								var dd = settings.onLoadSuccess(d);
								if(dd) d = dd;
							}
							resolveData(d, $this);
						},
						dataType: 'text'
					});
				}else{
					resolveData(settings.data, $this);
				}
			}

			function resolveData(rowDatas, $this){
				divoptions.empty();
				$this.data('rowDatas', rowDatas);
				for (var x in rowDatas){
					var data = rowDatas[x];
					var ddoption= $("<dd>", {'rowindex':x, value:data[settings.valueField]})
						.data('option', data).html(data[settings.nameField]).appendTo(divoptions);
					
					if(settings.value == data[settings.valueField]){
						ddoption.addClass("open_selected"); 
						divselect.val(data[settings.nameField]);
						inputHidden.val(ddoption.data('option')[settings.valueField]);
						$this.data('selectValue', data);
					}
					ddoption.click(function(){
						var $p = $(this).parent().parent();
						var prev = $p.data('selectValue');
						var d = $(this).data('option');
						$p.data('selectValue', d);
						if(prev && prev == d) return;
						
						$(this).addClass("open_selected"); 
						divselect.val($(this).text());
						
						inputHidden = $p.find(':input[name="'+settings.name+'"]');
						inputHidden.val(d[settings.valueField]);
						$(this).siblings().removeClass("open_selected");
						
						if(settings.onChange) settings.onChange(d, prev);
					}); 
					ddoption.hover( 
                        function(){$(this).addClass("open_hover");},
                        function(){$(this).removeClass("open_hover");}
					);
				}
			}

			function hideOptions(speed){ 
				if(speed.data){speed=speed.data} 
				if($(document).data("nowselectoptions")) 
				{ 
					$($(document).data("nowselectoptions")).slideUp(speed); 
					$($(document).data("nowselectoptions")).prev("div").removeClass("tag_select_open"); 
					$(document).data("nowselectoptions",null); 
					$(document).unbind("click",hideOptions); 
					$(document).unbind("keyup",hideOptionsOnEscKey); 
				} 
			} 
			function hideOptionsOnEscKey(e){ 
				var myEvent = e || window.event; 
				var keyCode = myEvent.keyCode; 
				if(keyCode==27)hideOptions(e.data); 
			} 
			function showOptions(speed){ 
				$(document).bind("click",speed,hideOptions); 
				$(document).bind("keyup",speed,hideOptionsOnEscKey); 
				$($(document).data("nowselectoptions")).slideDown(speed); 
				$($(document).data("nowselectoptions")).prev("div").addClass("tag_select_open"); 
			} 
		});
	}
});