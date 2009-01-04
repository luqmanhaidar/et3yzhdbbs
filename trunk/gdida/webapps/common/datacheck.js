/*
Validator提供3种不同的错误提示模式，目前可实现的验证类型有： 
1.是否为空；
2.中文字符；
3.双字节字符；
4.英文；
5.数字；
6.整数；
7.实数；
8.Email地址；
9.使用HTTP协议的网址；
10.电话号码；
11.货币；
12.手机号码；
13.邮政编码；
14.身份证号码(1.05增强)；
15.QQ号码；
16.日期；
17.符合安全规则的密码；
18.某项的重复值；
19.两数的关系比较；
20.判断输入值是否在(n, m)区间；
21.输入字符长度限制(可按字节比较)；
22.对于具有相同名称的单选按钮的选中判断；
23.限制具有相同名称的多选按钮的选中数目；
24.自定义的正则表达式验证； 
25.文件上传格式过滤(1.04)；
26.IP地址验证；
======以下功能由Light添加======
27.注册帐号
28.简单密码
29.上传文件大小
30.上传文件的格式及大小
31.根据不同的证件类型判断有效的证件号码
32.正整数
33.联系电话，只能包含数字、空格和*#-()等符号
34.姓名，只有中文字，或者是英文＋空格
35.下拉列表的选中判断
36.非负整数
37.出生日期，校验年龄在16-99之间，日期格式为YYYY-MM-DD
*/
 Validator = {
	Require : /.+/,
	//用户注册帐号，只能包含字母，数字和下划线，且必须以字母开头，不能超过16个字符
	UserName : /^[A-Za-z]\w{0,31}$/,
	//简单密码，不少于4个字符，不多于32个字符
	Password : /^.{4,32}$/,
	Email : /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
	Phone : /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/,
	Mobile : /^((\(\d{2,3}\))|(\d{3}\-))?13\d{9}$/,
	//联系电话，只能包含数字、空格、逗号和*#-()等符号
	MyPhone : /^(\d|,|\*|\+|#|-| |\(|\))+$/,
	Url : /^(http:\/\/)?[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/,
	IP : /^(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5])$/,
	IdCard : "this.IsIdCard(value)",
	//根据不同的证件类型判断有效的证件号码
	CertificateNo : "this.ValidCertificateNo(value, document.getElementsByName(getAttribute('to'))[0].value)",
	Currency : /^\d+(\.\d+)?$/,
	Number : /^\d+$/,
	Zip : /^[1-9]\d{5}$/,
	QQ : /^[1-9]\d{4,8}$/,
	Integer : /^[-\+]?\d+$/,
	//正整数
	PstInteger : /^[1-9]\d*$/,
	//非负整数
	NotNgtInt : /^0|([1-9]\d*)$/,
	Double : /^[-\+]?\d+(\.\d+)?$/,
	English : /^[A-Za-z]+$/,
	Chinese :  /^[\u4e00-\u9fa5]+$/,
	//姓名，只有中文字，或者是英文＋空格
	Name : "this.IsChinese(value) || this.IsEnglish(value)",
	Username : /^[A-za-z]\w{2,}$/i,
	UnSafe : /^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/,
	IsSafe : function(str){return !this.UnSafe.test(str);},
	SafeString : "this.IsSafe(value)",
	Filter : "this.DoFilter(value, getAttribute('accept'))",
	//判断上传文件大小
	FileSize : "this.CtrlFileSize(value, getAttribute('acceptSize'))",
	//判断上传文件的格式及大小
	File : "this.DoFilter(value, getAttribute('accept')) && this.CtrlFileSize(value, getAttribute('acceptSize'))",
	Limit : "this.limit(value.length,getAttribute('min'),  getAttribute('max'))",
	LimitB : "this.limit(this.LenB(value), getAttribute('min'), getAttribute('max'))",
	Date : "this.IsDate(value, getAttribute('min'), getAttribute('format'))",
	Repeat : "value == document.getElementsByName(getAttribute('to'))[0].value",
	Range : "getAttribute('min') <= (value|0) && (value|0) <= getAttribute('max')",
	Compare : "this.compare(value,getAttribute('operator'),getAttribute('to'))",
	Custom : "this.Exec(value, getAttribute('regexp'))",
	//下拉列表的选中判断
	SelectBox : "this.MustSelect(getAttribute('name'))",
	Group : "this.MustChecked(getAttribute('name'), getAttribute('min'), getAttribute('max'))",
	//出生日期
	Birth : "this.BirthdayCheck(value)",
	ErrorItem : [document.forms[0]],
	ErrorMessage : ["以下原因导致提交失败：\t\t\t\t"],
	Validate : function(theForm, mode){
		var obj = theForm || event.srcElement;
		if (obj==null) return false;
		if (obj.elements==null) return false;
		var count = obj.elements.length;
		this.ErrorMessage.length = 1;
		this.ErrorItem.length = 1;
		this.ErrorItem[0] = obj;
		for(var i=0;i<count;i++){
			with(obj.elements[i]){
				if (getAttribute("disabled")) continue;
				var _dataType = getAttribute("dataType");
				/*typeof(_dataType) == "object"判断表单元素是否有dataType属性
					typeof(this[_dataType]) == "undefined"判断Validator是否定义了该验证类型*/
				if(typeof(_dataType) == "object" || typeof(this[_dataType]) == "undefined") continue;
				this.ClearState(obj.elements[i]);
				if(getAttribute("require") == "false" && value == "") continue;
				switch(_dataType){
					case "IdCard" :
					case "Date" :
					case "Repeat" :
					case "Range" :
					case "Compare" :
					case "Custom" :
					case "Group" : 
					case "Limit" :
					case "LimitB" :
					case "SafeString" :
					case "Filter" :
					case "FileSize" :
					case "File" :
					case "CertificateNo" :
					case "Name" :
					case "SelectBox" :
					case "Birth" :
						if(!eval(this[_dataType]))	{
							this.AddError(i, getAttribute("re"));
						}
						break;
					default :
						if(!this[_dataType].test(value)){
							this.AddError(i, getAttribute("re"));
						}
						break;
				}
			}
		}
		if(this.ErrorMessage.length > 1){
			mode = mode || 1;
			var errCount = this.ErrorItem.length;
			switch(mode){
			case 2 :
				for(var i=1;i<errCount;i++)
					this.ErrorItem[i].style.color = "red";
			case 1 :
				alert(this.ErrorMessage.join("\n"));
				this.ErrorItem[1].focus();
				break;
			case 3 :
				for(var i=1;i<errCount;i++){
				try{
					var span = document.createElement("SPAN");
					span.id = "__ErrorMessagePanel";
					span.style.color = "red";
					this.ErrorItem[i].parentNode.appendChild(span);
					span.innerHTML = this.ErrorMessage[i].replace(/\d+:/,"*");
					}
					catch(e){alert(e.description);}
				}
				this.ErrorItem[1].focus();
				break;
			default :
				alert(this.ErrorMessage.join("\n"));
				break;
			}
			return false;
		}
		return true;
	},
	ValidateSingle : function(theForm, mode, checkDateType){
		var obj = theForm || event.srcElement;
		if (obj==null) return false;
		if (obj.elements==null) return false;
		var count = obj.elements.length;
		this.ErrorMessage.length = 1;
		this.ErrorItem.length = 1;
		this.ErrorItem[0] = obj;
		for(var i=0;i<count;i++){
			with(obj.elements[i]){
				if (getAttribute("disabled")) continue;
				var _dataType = getAttribute("dataType");
				if(_dataType!=checkDateType) continue;
				/*typeof(_dataType) == "object"判断表单元素是否有dataType属性
					typeof(this[_dataType]) == "undefined"判断Validator是否定义了该验证类型*/
				if(typeof(_dataType) == "object" || typeof(this[_dataType]) == "undefined") continue;
				this.ClearState(obj.elements[i]);
				if(getAttribute("require") == "false" && value == "") continue;
				switch(checkDateType){
					case "IdCard" :
					case "Date" :
					case "Repeat" :
					case "Range" :
					case "Compare" :
					case "Custom" :
					case "Group" : 
					case "Limit" :
					case "LimitB" :
					case "SafeString" :
					case "Filter" :
					case "FileSize" :
					case "File" :
					case "CertificateNo" :
					case "Name" :
					case "SelectBox" :
					case "Birth" :
						if(!eval(this[checkDateType]))	{
							this.AddError(i, getAttribute("re"));
						}
						break;
					default :
						if(!this[checkDateType].test(value)){
							this.AddError(i, getAttribute("re"));
						}
						break;
				}
			}
		}
		if(this.ErrorMessage.length > 1){
			mode = mode || 1;
			var errCount = this.ErrorItem.length;
			switch(mode){
			case 2 :
				for(var i=1;i<errCount;i++)
					this.ErrorItem[i].style.color = "red";
			case 1 :
				alert(this.ErrorMessage.join("\n"));
				this.ErrorItem[1].focus();
				break;
			case 3 :
				for(var i=1;i<errCount;i++){
				try{
					var span = document.createElement("SPAN");
					span.id = "__ErrorMessagePanel";
					span.style.color = "red";
					this.ErrorItem[i].parentNode.appendChild(span);
					span.innerHTML = this.ErrorMessage[i].replace(/\d+:/,"*");
					}
					catch(e){alert(e.description);}
				}
				this.ErrorItem[1].focus();
				break;
			default :
				alert(this.ErrorMessage.join("\n"));
				break;
			}
			return false;
		}
		return true;
	},
	limit : function(len,min, max){
		min = min || 0;
		max = max || Number.MAX_VALUE;
		return min <= len && len <= max;
	},
	LenB : function(str){
		return str.replace(/[^\x00-\xff]/g,"**").length;
	},
	ClearState : function(elem){
		with(elem){
			if(style.color == "red")
				style.color = "";
			var lastNode = parentNode.childNodes[parentNode.childNodes.length-1];
			if(lastNode.id == "__ErrorMessagePanel")
				parentNode.removeChild(lastNode);
		}
	},
	AddError : function(index, str){
		this.ErrorItem[this.ErrorItem.length] = this.ErrorItem[0].elements[index];
		this.ErrorMessage[this.ErrorMessage.length] = this.ErrorMessage.length + ":" + str;
	},
	Exec : function(op, reg){
		return new RegExp(reg,"g").test(op);
	},
	compare : function(op1,operator,op2){
		switch (operator) {
			case "NotEqual":
				return (op1 != op2);
			case "GreaterThan":
				return (op1 > op2);
			case "GreaterThanEqual":
				return (op1 >= op2);
			case "LessThan":
				return (op1 < op2);
			case "LessThanEqual":
				return (op1 <= op2);
			default:
				return (op1 == op2);            
		}
	},
	MustChecked : function(name, min, max){
		var groups = document.getElementsByName(name);
		var hasChecked = 0;
		min = min || 1;
		max = max || groups.length;
		for(var i=groups.length-1;i>=0;i--)
			if(groups[i].checked) hasChecked++;
		return min <= hasChecked && hasChecked <= max;
	},
	
	MustSelect :function(name){
		var selected = document.getElementsByName(name)[0].value;
		return (selected!=null && selected!="") ? true : false;
	},

	DoFilter : function(input, filter){
		return new RegExp("^.+\.(?=EXT)(EXT)$".replace(/EXT/g, filter.split(/\s*,\s*/).join("|")), "gi").test(input);
	},

	CtrlFileSize : function(fileName, fileSize){
		window.oldOnError = window.onerror;
		window.onerror = function (err) {
			if (err.indexOf('utomation') != -1) {
				alert('没有访问文件的权限\n\n请刷新网页并允许ActiveX控件交互');
				return true;
			}
			else 
				return false;
		}
		var fso = new ActiveXObject('Scripting.FileSystemObject');
		if (fso == null)	{
			alert("请允许启用FSO检查文件大小");
		}		
		var file = fso.GetFile(fileName);
		window.onerror = window.oldOnError;
		return file.Size <= fileSize*1024;
	},
	
	IsIdCard : function(number){
		var date, Ai;
		var verify = "10x98765432";
		var Wi = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
		var area = ['','','','','','','','','','','','北京','天津','河北','山西','内蒙古','','','','','','辽宁','吉林','黑龙江','','','','','','','','上海','江苏','浙江','安微','福建','江西','山东','','','','河南','湖北','湖南','广东','广西','海南','','','','重庆','四川','贵州','云南','西藏','','','','','','','陕西','甘肃','青海','宁夏','新疆','','','','','','台湾','','','','','','','','','','香港','澳门','','','','','','','','','国外'];
		var re = number.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/i);
		if(re == null) return false;
		if(re[1] >= area.length || area[re[1]] == "") return false;
		if(re[2].length == 12){
			Ai = number.substr(0, 17);
			date = [re[9], re[10], re[11]].join("-");
		}
		else{
			Ai = number.substr(0, 6) + "19" + number.substr(6);
			date = ["19" + re[4], re[5], re[6]].join("-");
		}
		if(!this.IsDate(date, "ymd")) return false;
		var sum = 0;
		for(var i = 0;i<=16;i++){
			sum += Ai.charAt(i) * Wi[i];
		}
		Ai +=  verify.charAt(sum%11);
		return (number.length ==15 || number.length == 18 && number == Ai);
	},

	ValidCertificateNo : function(number, CertificateType){
		switch (CertificateType){
			case "I" :
				return this.IsIdCard(number);
			default :
				return this["Require"].test(number); 
		}
	},

	IsDate : function(op, formatString){
		formatString = formatString || "ymd";
		var m, year, month, day;
		switch(formatString){
			case "ymd" :
				m = op.match(new RegExp("^((\\d{4})|(\\d{2}))([-./])(\\d{1,2})\\4(\\d{1,2})$"));
				if(m == null ) return false;
				day = m[6];
				month = m[5]*1;
				year =  (m[2].length == 4) ? m[2] : GetFullYear(parseInt(m[3], 10));
				break;
			case "dmy" :
				m = op.match(new RegExp("^(\\d{1,2})([-./])(\\d{1,2})\\2((\\d{4})|(\\d{2}))$"));
				if(m == null ) return false;
				day = m[1];
				month = m[3]*1;
				year = (m[5].length == 4) ? m[5] : GetFullYear(parseInt(m[6], 10));
				break;
			default :
				break;
		}
		if(!parseInt(month)) return false;
		month = month==0 ?12:month;
		var date = new Date(year, month-1, day);
        return (typeof(date) == "object" && year == date.getFullYear() && month == (date.getMonth()+1) && day == date.getDate());
		function GetFullYear(y){return ((y<30 ? "20" : "19") + y)|0;}
	},
	
	IsChinese : function(name){
		return this["Chinese"].test(name);
	},
	IsEnglish : function(name){
		return new RegExp("^[A-Za-z]+( [A-Za-z]+)*$", "g").test(name);
	},
	//校验出生日期
	BirthdayCheck : function(birthday){
		if (birthday == null || birthday == ""){
			return false;
		}
		var sysDate = new Date();
		var sysYear = parseInt(sysDate.getFullYear());
		var birthYear = parseInt(birthday.split("-")[0]);
		var age = sysYear - birthYear;
		if (age < 16 || age > 99)	{
			return false;
		} else {
			return true;
		}
	}
}

/**
* 清空查询输入框
* samland 
*/
function clearform(frm){
  if (typeof frm == "undefined") return false;
	var c = frm.elements.length;
	for (var i=0;i<c;i++){
		var e=frm.elements[i];
		
		if (e.type=="select-one"){
			try{
				e.options[0].selected=true;
			}catch (e){
				//
			}
		}
		if (e.type=="text" || e.type=="hidden"){			
			e.value="";
		}
		if (e.type=="checkbox"){
			e.checked=false;
		}
	}
}
function clearformExceptHidden(frm){
  if (typeof frm == "undefined") return false;
	var c = frm.elements.length;
	for (var i=0;i<c;i++){
		var e=frm.elements[i];
		if (e.type=="hidden") continue;
		if (e.type=="select-one"){
			try{
				e.options[0].selected=true;
			}catch (e){
				//
			}
		}
		if (e.type=="text" || e.type=="hidden"){			
			e.value="";
		}
		if (e.type=="checkbox"){
			e.checked=false;
		}
	}
}
/*
==================================================================
LTrim(string):去除左边的空格
==================================================================
*/
function LTrim(str){
    var whitespace = new String(" \t\n\r");
    var s = new String(str);
    
    if (whitespace.indexOf(s.charAt(0)) != -1)
    {
        var j=0, i = s.length;
        while (j < i && whitespace.indexOf(s.charAt(j)) != -1)
        {
            j++;
        }
        s = s.substring(j, i);
    }
    return s;
}
 
/*
==================================================================
RTrim(string):去除右边的空格
==================================================================
*/
function RTrim(str){
    var whitespace = new String(" \t\n\r");
    var s = new String(str);
 
    if (whitespace.indexOf(s.charAt(s.length-1)) != -1)
    {
        var i = s.length - 1;
        while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1)
        {
            i--;
        }
        s = s.substring(0, i+1);
    }
    return s;
}
 
/*
==================================================================
Trim(string):去除前后空格
==================================================================
*/
function Trim(str){
    return RTrim(LTrim(str));
}
