<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<title>header</title>
</head>
<body>

	<div id="con_wrapper">


	<div id="header">
		<div class="contain clr">
			<a href="javascript:location.href='http://192.168.30.126:8080/RecipePlz/main/main.jsp'" class="h_logo"><img
				src="/RecipePlz/upload/please.png"
				alt="�����Ǹ� ��Ź��" /></a>
			<div class="fl">
				<ul class="clr menu">
					<li class=""><a
						href="javascript:location.href='RCP_listAction.action'">������</a></li>
					<li class=""><a
						href="javascript:location.href='CMN_listAction.action'">Ŀ�´�Ƽ</a></li>
					<li class=""><a href="javascript:location.href='today_listAction.action'">���� �� ����?</a></li>
					<s:if test="#session.session_ID==null">
					<li class=""><a href="javascript:location.href='RCP_script.action'">����������</a></li>
					</s:if>
					<s:if test="#session.session_ID!=null">
					<li class=""><a href="javascript:location.href='MyPage.action'">����������</a></li>
					</s:if>
					</ul>
			</div>
			<div class="fr">
				<ul class="clr gnb">
					<s:if test="#session.session_ID == null ">
						<li><a href="javascript:location.href='Login.action'">�α���</a></li>
						<li><a href="javascript:location.href='UserAddForm.action'">ȸ������</a></li>
						<li><a href="javascript:location.href='FAQ_listAction.action'">������</a></li>
						<li><a href="javascript:location.href='NOTICE_listAction.action'">��������</a></li>
						
					</s:if>
					<s:else>

						<li>${session_ID}��ȯ���մϴ�.</li>
						<li><a href="javascript:location.href='FAQ_listAction.action'">������</a></li>
						<li><a href="javascript:location.href='NOTICE_listAction.action'">��������</a></li>
						<li><a href="javascript:location.href='Logout.action'">�α׾ƿ�</a></li>
					
					</s:else>

				</ul>
			</div>
		</div>
	</div>

</div>
</body>
</html>