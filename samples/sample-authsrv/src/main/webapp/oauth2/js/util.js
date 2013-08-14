_.templateSettings = {
		    interpolate: /\<\@\=(.+?)\@\>/gim,
		    evaluate: /\<\@([\s\S]+?)\@\>/gim,
		    escape: /\<\@\-(.+?)\@\>/gim,
		    variable: "rc"
		};

(function( $ ){
	$.fn.serializeJSON=function() {
	var json = {};
	jQuery.map($(this).serializeArray(), function(n, i){
		if (n['value'])
		{
			json[n['name']] = n['value'];
		}
	});
	return json;
	};
	})( jQuery );