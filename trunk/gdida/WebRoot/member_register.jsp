<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ page import="com.gdie.db.Table"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.gdie.common.Page"%>
<%@ page import="com.gdie.gdjrb.Module"%>
<%

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>会员注册</title>
<link href="style/gdida.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="header.jsp" flush="true" />
<table width="1002" border="0" cellspacing="0" cellpadding="0"
	align="center">

	<tr>
	        <td width="51" align="center" height="26"  bgcolor="#f7f3ef"><img src="images/gdida_sub_30.gif" width="17" height="17" /></td>
	        <td width="951" align="left" bgcolor="#f7f3ef">您现在：广东省产业发展研究院首页 &gt; 会员注册</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<textarea rows="30" cols="100">　　在注册前，敬请您阅读以下内容，在进行注册程序过程中点击“同意”按钮即表示用户完全接受本协议项下的全部条款。

　　第一条　会员资格

　　在您承诺完全同意本服务条款并在＿＿＿＿＿＿＿＿＿网站完成注册程序后，即可成为本网站会员，享受＿＿＿＿＿＿＿＿＿网站为您提供的服务。如用户拒绝支付该项费用，则不能使用相关的网络服务。

　　第二条　会员权限

　　1、会员须交纳会员费才能享有本网站提供的服务，可参阅会员收费标准及服务内容表；

　　2、任何会员均有义务遵守本规定及其它网络服务的协议、规定、程序及惯例。

　　第三条　会员资料

　　1、为了使我们能够更好地为会员提供服务，请您提供详尽准确的个人资料，如更改请及时更新，提供虚假资料所造成的后果由会员承担；

　　2、会员有责任保管好自己的注册密码并定期修改避免造成损失，由于会员疏忽所造成的损失由会员承担。用户应当对以其用户帐号进行的所有活动和事件负法律责任。

　　第四条　会员资格的取消

　　如发现任何会员有以下故意行为之一，本网保留取消其使用服务的权利，并无需做出任何补偿；

　　1、可能造成本网站全部或局部的服务受影响，或危害本网站运行；

　　2、以任何欺诈行为获得会员资格；

　　3、在本网站内从事非法商业行为，发布涉及敏感政治、宗教、色情或其它违反有关国家法律和政府法规的文字、图片等信息；

　　4、以任何非法目的而使用网络服务系统；

　　第五条　服务商的权利

　　1、有权审核、接受或拒绝会员的入会申请，有权撤销或停止会员的全部或部分服务内容；

　　2、有权修订会员的权利和义务，有权修改或调整本网站的服务内容；

　　3、有权将修订的会员的权利和义务以E-mail形式通知会员，会员收到通知后仍继续使用本网站服务者即表示会员同意并遵守新修订内容。

　　4、本网提供的服务仅供会员独立使用，未经本网授权，会员不得将会员号授予或转移给第三方。会员如果有违此例，本网有权向客户追索商业损失并保留追究法律责任的权利。

　　第六条　服务商的义务

　　1、认真做好本网站所涉及的网络及通信系统的技术维护工作，保证本网站的畅通和高效；

　　2、除不可抗拒的因素导致本网站临时停止或短时间停止服务以外，乙方如需停止本网站的全部或部分服务时，须提前在本网站上发布通知通告会员。

　　3、如本网站因系统维护或升级等原因需暂停服务，将事先通过主页、电子邮件等方式公告会员；

　　4、因不可抗力而使本网站服务暂停，所导致会员任何实际或潜在的损失，本网不做任何补偿；

　　5、本网不承担会员因遗失密码而受到的一切损失。

　　6、本网仅提供相关的网络服务，除此之外与相关网络服务有关的设备（如电脑、调制解调器及其他与接入互联网有关的装置）及所需的费用（如为接入互联网而支付的电话费及上网费）均应由用户自行负担。

　　第七条　附则

　　1、以上规定的范围仅限于＿＿＿＿＿＿＿＿＿网站；

　　2、本网会员因违反以上规定而触犯有关法律法规，一切后果自负，湖北省金属网站不承担任何责任；

　　3、本规则未涉及之问题参见有关法律法规，当本规定与有关法律法规冲突时，以相应的法律法规为准。在本条款规定范围内，＿＿＿＿＿＿＿＿＿网站拥有最终解释权。
	</textarea><br />
		<input id=accept name="" type="button" value="同意" onclick="location='member_register_do.jsp'"/><input name=""
			type="button" value="不同意" onclick="location='index.jsp'"/></td>
	</tr>
	<tr>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td colspan="2">
		<table width="1002" border="0" cellspacing="0" cellpadding="0"
			align="center">
			<jsp:include page="footer.jsp" flush="true" />
		</table></td>
	</tr>
</table>
</body>
</html>
