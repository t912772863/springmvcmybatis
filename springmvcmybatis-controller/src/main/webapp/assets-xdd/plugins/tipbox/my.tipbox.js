jQuery.extend({    
	tipbox: function(message, icon, opts){//'success'、'warning'、'error'、'loading',info
		var options = $.extend({
			close: function(){},
			modal: false,
			timeout: 2000
		}, opts || {});
		$('body').find('#my_tipbox').remove();
		var box = $('<div/>',{id:'my_tipbox','class': 'my_tipbox_layer_wrap'});
		var tip = $('<span/>',{'class': 'my_tipbox_layer'}).css('z-index',10000);
		switch(icon){
		case 'success':
			$('<span/>',{'class': 'gtl_ico_succ'}).appendTo(tip);
			break;
		case 'warning':
			$('<span/>',{'class': 'gtl_ico_hits'}).appendTo(tip);
			break;
		case 'error':
			$('<span/>',{'class': 'gtl_ico_fail'}).appendTo(tip);
			break;
		case 'loading':
			$("<span class='gtl_ico_clear'></span><span class='loading'></span>").appendTo(tip);
			break;
		default:
			$('<span/>',{'class': 'gtl_ico_clear'}).appendTo(tip);
		break;
		}
		tip.append($('<span/>',{text:' '+message, id:'tip_message'})).append('<span class="gtl_end"></span>');
		box.append(tip);
		var top = ($(window).height() - 50) / 2 +"px";
		box.css('top',top).appendTo('body');
		if(options.modal){
			$('<div/>',{'class': 'my_tipbox_model'}).appendTo('body');
		}
		if(icon != 'loading'){
			options.timeout = options.timeout > 0? options.timeout:2000;
			setTimeout(function(){
				$.closeTipbox(options.close);
			}, options.timeout);
		}
	},
	closeTipbox: function(callback){
		$('body').find('#my_tipbox').fadeOut("normal",function(){
			$('.my_tipbox_model').remove();
			$(this).remove();
			if(callback)callback();
		});
	}
});