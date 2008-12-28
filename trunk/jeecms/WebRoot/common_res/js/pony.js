Pn = {
	version : '1.0'
};
/**
 * Copies all the properties of config to obj.
 * 
 * @param {Object}
 *            obj The receiver of the properties
 * @param {Object}
 *            config The source of the properties
 * @param {Object}
 *            defaults A different object that will also be applied for default
 *            values
 * @return {Object} returns obj
 * @member Pn apply
 */
Pn.apply = function(o, c, defaults) {
	if (defaults) {
		// no "this" reference for friendly out of scope calls
		Ext.apply(o, defaults);
	}
	if (o && c && typeof c == 'object') {
		for (var p in c) {
			o[p] = c[p];
		}
	}
	return o;
};
/**
 * Pn.ns('Company', 'Company.data');
 */
Pn.ns = function() {
	var a = arguments, o = null, i, j, d, rt;
	for (i = 0;i < a.length; ++i) {
		d = a[i].split(".");
		rt = d[0];
		eval('if (typeof ' + rt + ' == "undefined"){' + rt + ' = {};} o = '
				+ rt + ';');
		for (j = 1;j < d.length; ++j) {
			o[d[j]] = o[d[j]] || {};
			o = o[d[j]];
		}
	}
};
/**
 * ����html����
 */
Pn.create = function(a) {
	return document.createElement(a);
};
/**
 * ��ñ�ҳ����Ψһ��ID
 */
Pn.UID = 0;
Pn.getUID = function() {
	Pn.UID++;
	return Pn.UID;
}
// ����ȫ�� AJAX Ĭ��ѡ�
$.ajaxSetup( {
	dataType : "json",
	type : "POST",
	beforeSend : function(xhr) {
		xhr.setRequestHeader('isAjax', 'true');
	}
});
jQuery.extend( {
	postJson : function(url, data, success, error) {
		$.ajax( {
			url : url,
			data : data,
			success : success,
			error : error
		});
	}
});
/**
 * ���url������doc���ı�����document��key�����������أ�value��
 */
Pn.getParam = function(key) {
	var aParams = location.search.substr(1).split('&');
	var aKv;
	for (var i = 0;i < aParams.length; i++) {
		aKv = aParams[i].split('=');
		if (aKv[0] == key) {
			return aKv[1];
		}
	}
};
/**
 * ��ѡ��ȫѡ��ȡ��
 * 
 * @param name
 *            ��ѡ������
 * @param checked
 *            of boolean ȫѡ��ȡ��
 */
Pn.checkBox = function(name, checked) {
	$("input[name='" + name + "']").attr("checked", checked);
}
//@ TODO �Ƿ���Ҫ
Pn.checkLen = function(o, len) {
	len = len || 1;
	if (o.val() == undefined || $.trim(o.val()) == ''
			|| $.trim(o.val()).length < len) {
		return false;
	} else {
		return true;
	}
}
Pn.Cookie = function Cookie() {
	this.cookies = [];
	/**
	 * @param name
	 */
	this.get = function(name) {
		this._reset();
		return this.cookies[name];
	}

	/**
	 * name & value must be Required
	 * 
	 * @param {string}
	 *            name (Required)
	 * @param {string}
	 *            value (Required)
	 * @param {integer}
	 *            expires (optional, [Units:seconde] , exp (one day):
	 *            1*24*60*60)
	 * @param {string}
	 *            path (optional, exp: '/')
	 * @param {string}
	 *            domain (optional, exp: '.a.com')
	 * @param {boolean}
	 *            secure (optional, exp: false)
	 */
	this.set = function() {
		var args = this.set.arguments;
		var _num = args.length;
		if (_num < 2) {
			return;
		}
		var _cookie = args[0] + '=' + this._encode(args[1]);
		if (_num >= 3) {
			var now = new Date();
			var _expires = new Date(now.getTime() + args[2]);
			_cookie += ';expires=' + _expires.toUTCString();
		}
		if (_num >= 4) {
			_cookie += ';path=' + args[3];
		}
		if (_num >= 5) {
			_cookie += ';domain=' + args[4];
		}
		if (_num >= 6) {
			_cookie += ';secure';
		}
		document.cookie = _cookie;
	}

	this.print_r = function() {
		this._reset();
		for (var i in this.cookies) {
			alert(i + ' -> ' + this.cookies[i]);
		}
	}
	this._reset = function() {
		var cookie = document.cookie.split(';');
		var _num = cookie.length;
		for (var i = 0;i < _num; i++) {
			var _arr = cookie[i].split('=');
			var _name = this._trim(_arr[0]);
			var _value = '';
			if (typeof _arr[1] != 'undefined') {
				_value = this._decode(this._trim(_arr[1]));
			}
			this.cookies[_name] = _value;
		}
	}
	this._trim = function(_str) {
		return _str.replace(/(^\s+)|(\s*$)/g, '');
	}
	this._encode = function(_str) {
		return encodeURI(_str);
	}
	this._decode = function(_str) {
		return decodeURI(_str);
	}
}
Pn.Cookie.countPerPage = "_countPerPage";
Pn.ns('Pn.LTable');
Pn.LTable.lineOver=function(o){
	$(o).addClass("pn-lhover");
};
Pn.LTable.lineOut=function(o){
	$(o).removeClass("pn-lhover");
};
Pn.LTable.lineSelect=function(o){
	if(Pn.LTable.lineSelected){
		$(Pn.LTable.lineSelected).removeClass("pn-lselected");	
	}
	Pn.LTable.lineSelected=o;
	$(o).addClass("pn-lselected");
}
Pn.ns('Pn.Tree');
Pn.Tree.switchDisplay=function(id,open) {	
	var isDisplay = $('#'+id+'-s').attr("isDisplay");
	if(open&&isDisplay=="true") {
		return;
	}
	if(isDisplay=="true") {		
		$('#'+id+'-co').hide();
		$('#'+id+'-fo').hide();
		$('#'+id+'-cc').show();
		$('#'+id+'-fc').show();
		$('#'+id+'-').hide();
		$('#'+id+'-s').attr("isDisplay","false");
	} else {
		$('#'+id+'-cc').hide();
		$('#'+id+'-fc').hide();
		$('#'+id+'-co').show();
		$('#'+id+'-fo').show();
		$('#'+id+'-').show();
		$('#'+id+'-s').attr("isDisplay","true");
	}
}
Pn.Tree.switchSelect=function(element,id,treeId) {
	if(element.checked) {
		$("input:checkbox",$('#'+id)).each(function(){this.checked=true;});
		var head = treeId;
		var tail = "-chk";
		//ȡȥ��ͷ��ȡ�м䲿�֣�t-0-6-1=-0-6-1
		var middle = id.substring(treeId.length);
		while(true) {
			middle = middle.substring(0,middle.lastIndexOf("-"));
			if(middle.length > 0) {
				$('#'+head+middle+tail).attr("checked",true);
			} else {
				break;
			}
		}
	} else {
		$("input:checkbox",$('#'+id)).each(function(){this.checked=false;});
	}
}
Pn.Tree.lineOver=function(element) {
	$(element).addClass("pn-tree-hover");
}
Pn.Tree.lineOut=function(element) {
	$(element).removeClass("pn-tree-hover");
}
Pn.Tree.lineSelected=function(element,treeId) {
	var selectedId = $('#'+treeId).attr("selectedId");
	if(selectedId){
		$('#'+selectedId).removeClass("pn-tree-selected");
	}
	$('#'+treeId).attr("selectedId",$(element).attr("id"));
	$(element).addClass("pn-tree-selected");	
}