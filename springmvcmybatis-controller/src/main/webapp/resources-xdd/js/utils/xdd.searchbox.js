//searchbox
jQuery.fn.extend({
	
	/**
	 * fields: example
	 * display:显示的名称
	 * name 当前表单name
	 * type 类型 hidden、text、number、select
	 * newline 是否换行
	 * group:true//先分组后换行，
	 * 以下全部分组知道遇到下一个值
	 * {}
	 */
	searchBox: function(options){
		var settings = $.extend({
			width: null,// 表单的宽度
			inputWidth: 180,//控件宽度
			labelWidth: 90,//标签宽度
			space: 40, //间隔宽度
			rightToken: '：', 
			labelAlign: 'left',//标签对齐方式
			align: 'left', //控件对齐方式
			fields: [],
			buttons: null,//按钮组
		}, options);

		return this.each(function(){
			var _$content = $('<div>', {'class':'usersearchFilter searchListFilter clearfix'});
			
			if(!settings.fields) return;

			var _$preDiv = $('<div>', {'class':'group'});
			var _$preul = $('<ul>');
			for(var x in settings.fields){
				//{display:'', name:'',type:'hidden',newline:true}
				var field = settings.fields[x];
				if(x != 0 && field.group){
					_$content.append(_$preDiv.append(_$preul));
					_$preul = $('<ul>');
					_$preDiv = $('<div class="group">');
				}
				var li = $('<li>');
				if(field.id) li.attr('id', field.id);
				if(field.newLine) li.attr('style','clear:both;');
				
				if(field.showLabel != false && field.type != 'button') 
					li.append($('<em>',{'text':field.display + settings.rightToken}));
				else if(field.type == 'button') li.attr('class', 'butBox');
				if(field.formatter) 
					li.append(field.formatter(li));
				else createDefaultElement(li, li, field);
				_$preul.append(li);
			}
			_$content.append(_$preDiv.append(_$preul));
			$(this).empty().append(_$content);

			function createDefaultElement($li, $container, field){
				switch(field.type){
				case 'hidden':
					$container.append($("<input type='hidden'/>", {'name':field.name, 'value':field.value}));
					break;
				case 'button':
					var btn = $("<input type='button' name='" + field.name + "' " + 'value="'+field.value+'" class="fSearchBut"/>');
					if(field.click) btn.bind('click', field.click);
					$container.append(btn);
					break;
				case 'select':
					$container.append($('<div>', {id : field.options.id}).selectCss(field.options));
					break;
				case 'date':
					var args = "WdatePicker("+JSON.stringify(field.options)+")";
					$container.append($('<input type="text" class="Wdate"/>', {click:WdatePicker(field.options)}));
					break;
				case 'radio':
					var nf = field.options.nameField ? field.options.nameField : 'name';
					var vf = field.options.valueField ? field.options.valueField : 'value';
					$li.addClass('typeBox');
					for(var x in field.options.data){
						var row = field.options.data[x];
						var id = 'radio_' + x + '_' + Math.random();
						var input = $('<input type="radio" name="'+ field.options.name +'" id="' + id + '" value="' + row[vf] + '"/>');
						if(field.options.onChange) input.bind('change', field.options.onChange);
						var label = $('<label style="cursor:pointer; padding-left:5px;" for="' + id + '"/>').text(row[nf]);
						if(row.checked) input.attr('checked', 'true');
						$container.append(input).append(label);
						if(field.options.space) label.css('paddingRight', 
								(typeof field.options.space) == 'string'? field.options.space:field.options.space+'px');
					}
					break;
				case 'group':
					for(var x in field.options.data){
						var f = field.options.data[x];
						var _$preDiv = $('<div>', {'class':'group'});
						createDefaultElement($li, _$preDiv, f);
						$li.append(_$preDiv);
					}
					break;
				default:
					var input = $("<input type='text' class='filterText' name='" + field.name + "'/>");
					if(field.value) input.val(field.value);
					$container.append(input);
				}
			}
			
		});
	},
	serializeJson : function(){
	   var o = {};
	   var a = this.serializeArray();
	   $.each(a, function() {  
	       if (o[this.name]) {  
	           if (!o[this.name].push) {  
	               o[this.name] = [o[this.name]];
	           }  
	           o[this.name].push(this.value || '');
	       } else {  
	           o[this.name] = this.value || '';
	       }  
	   });
	   return o;
	}
});