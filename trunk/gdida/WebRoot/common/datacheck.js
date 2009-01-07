/*
Validator�ṩ3�ֲ�ͬ�Ĵ�����ʾģʽ��Ŀǰ��ʵ�ֵ���֤�����У� 
1.�Ƿ�Ϊ�գ�
2.�����ַ���
3.˫�ֽ��ַ���
4.Ӣ�ģ�
5.���֣�
6.������
7.ʵ����
8.Email��ַ��
9.ʹ��HTTPЭ�����ַ��
10.�绰���룻
11.���ң�
12.�ֻ����룻
13.�������룻
14.���֤����(1.05��ǿ)��
15.QQ���룻
16.���ڣ�
17.���ϰ�ȫ��������룻
18.ĳ����ظ�ֵ��
19.�����Ĺ�ϵ�Ƚϣ�
20.�ж�����ֵ�Ƿ���(n, m)���䣻
21.�����ַ���������(�ɰ��ֽڱȽ�)��
22.���ھ�����ͬ���Ƶĵ�ѡ��ť��ѡ���жϣ�
23.���ƾ�����ͬ���ƵĶ�ѡ��ť��ѡ����Ŀ��
24.�Զ����������ʽ��֤�� 
25.�ļ��ϴ���ʽ����(1.04)��
26.IP��ַ��֤��
======���¹�����Light���======
27.ע���ʺ�
28.������
29.�ϴ��ļ���С
30.�ϴ��ļ��ĸ�ʽ����С
31.���ݲ�ͬ��֤�������ж���Ч��֤������
32.������
33.��ϵ�绰��ֻ�ܰ������֡��ո��*#-()�ȷ���
34.������ֻ�������֣�������Ӣ�ģ��ո�
35.�����б��ѡ���ж�
36.�Ǹ�����
37.�������ڣ�У��������16-99֮�䣬���ڸ�ʽΪYYYY-MM-DD
*/
 Validator = {
	Require : /.+/,
	//�û�ע���ʺţ�ֻ�ܰ�����ĸ�����ֺ��»��ߣ��ұ�������ĸ��ͷ�����ܳ���16���ַ�
	UserName : /^[A-Za-z]\w{0,31}$/,
	//�����룬������4���ַ���������32���ַ�
	Password : /^.{4,32}$/,
	Email : /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
	Phone : /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/,
	Mobile : /^((\(\d{2,3}\))|(\d{3}\-))?13\d{9}$/,
	//��ϵ�绰��ֻ�ܰ������֡��ո񡢶��ź�*#-()�ȷ���
	MyPhone : /^(\d|,|\*|\+|#|-| |\(|\))+$/,
	Url : /^(http:\/\/)?[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/,
	IP : /^(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5])$/,
	IdCard : "this.IsIdCard(value)",
	//���ݲ�ͬ��֤�������ж���Ч��֤������
	CertificateNo : "this.ValidCertificateNo(value, document.getElementsByName(getAttribute('to'))[0].value)",
	Currency : /^\d+(\.\d+)?$/,
	Number : /^\d+$/,
	Zip : /^[1-9]\d{5}$/,
	QQ : /^[1-9]\d{4,8}$/,
	Integer : /^[-\+]?\d+$/,
	//������
	PstInteger : /^[1-9]\d*$/,
	//�Ǹ�����
	NotNgtInt : /^0|([1-9]\d*)$/,
	Double : /^[-\+]?\d+(\.\d+)?$/,
	English : /^[A-Za-z]+$/,
	Chinese :  /^[\u4e00-\u9fa5]+$/,
	//������ֻ�������֣�������Ӣ�ģ��ո�
	Name : "this.IsChinese(value) || this.IsEnglish(value)",
	Username : /^[A-za-z]\w{2,}$/i,
	UnSafe : /^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/,
	IsSafe : function(str){return !this.UnSafe.test(str);},
	SafeString : "this.IsSafe(value)",
	Filter : "this.DoFilter(value, getAttribute('accept'))",
	//�ж��ϴ��ļ���С
	FileSize : "this.CtrlFileSize(value, getAttribute('acceptSize'))",
	//�ж��ϴ��ļ��ĸ�ʽ����С
	File : "this.DoFilter(value, getAttribute('accept')) && this.CtrlFileSize(value, getAttribute('acceptSize'))",
	Limit : "this.limit(value.length,getAttribute('min'),  getAttribute('max'))",
	LimitB : "this.limit(this.LenB(value), getAttribute('min'), getAttribute('max'))",
	Date : "this.IsDate(value, getAttribute('min'), getAttribute('format'))",
	Repeat : "value == document.getElementsByName(getAttribute('to'))[0].value",
	Range : "getAttribute('min') <= (value|0) && (value|0) <= getAttribute('max')",
	Compare : "this.compare(value,getAttribute('operator'),getAttribute('to'))",
	Custom : "this.Exec(value, getAttribute('regexp'))",
	//�����б��ѡ���ж�
	SelectBox : "this.MustSelect(getAttribute('name'))",
	Group : "this.MustChecked(getAttribute('name'), getAttribute('min'), getAttribute('max'))",
	//��������
	Birth : "this.BirthdayCheck(value)",
	ErrorItem : [document.forms[0]],
	ErrorMessage : ["����ԭ�����ύʧ�ܣ�\t\t\t\t"],
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
				/*typeof(_dataType) == "object"�жϱ�Ԫ���Ƿ���dataType����
					typeof(this[_dataType]) == "undefined"�ж�Validator�Ƿ����˸���֤����*/
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
				/*typeof(_dataType) == "object"�жϱ�Ԫ���Ƿ���dataType����
					typeof(this[_dataType]) == "undefined"�ж�Validator�Ƿ����˸���֤����*/
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
				alert('û�з����ļ���Ȩ��\n\n��ˢ����ҳ������ActiveX�ؼ�����');
				return true;
			}
			else 
				return false;
		}
		var fso = new ActiveXObject('Scripting.FileSystemObject');
		if (fso == null)	{
			alert("����������FSO����ļ���С");
		}		
		var file = fso.GetFile(fileName);
		window.onerror = window.oldOnError;
		return file.Size <= fileSize*1024;
	},
	
	IsIdCard : function(number){
		var date, Ai;
		var verify = "10x98765432";
		var Wi = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
		var area = ['','','','','','','','','','','','����','���','�ӱ�','ɽ��','���ɹ�','','','','','','����','����','������','','','','','','','','�Ϻ�','����','�㽭','��΢','����','����','ɽ��','','','','����','����','����','�㶫','����','����','','','','����','�Ĵ�','����','����','����','','','','','','','����','����','�ຣ','����','�½�','','','','','','̨��','','','','','','','','','','���','����','','','','','','','','','����'];
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
	//У���������
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
* ��ղ�ѯ�����
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
LTrim(string):ȥ����ߵĿո�
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
RTrim(string):ȥ���ұߵĿո�
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
Trim(string):ȥ��ǰ��ո�
==================================================================
*/
function Trim(str){
    return RTrim(LTrim(str));
}
