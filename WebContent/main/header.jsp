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
				alt="레서피를 부탁해" /></a>
			<div class="fl">
				<ul class="clr menu">
					<li class=""><a
						href="javascript:location.href='RCP_listAction.action'">레시피</a></li>
					<li class=""><a
						href="javascript:location.href='CMN_listAction.action'">커뮤니티</a></li>
					<li class=""><a href="javascript:location.href='today_listAction.action'">오늘 뭐 먹지?</a></li>
					<s:if test="#session.session_ID==null">
					<li class=""><a href="javascript:location.href='RCP_script.action'">마이페이지</a></li>
					</s:if>
					<s:if test="#session.session_ID!=null">
					<li class=""><a href="javascript:location.href='MyPage.action'">마이페이지</a></li>
					</s:if>
					</ul>
			</div>
			<div class="fr">
				<ul class="clr gnb">
					<s:if test="#session.session_ID == null ">
						<li><a href="javascript:location.href='Login.action'">로그인</a></li>
						<li><a href="javascript:location.href='UserAddForm.action'">회원가입</a></li>
						<li><a href="javascript:location.href='FAQ_listAction.action'">고객센터</a></li>
						<li><a href="javascript:location.href='NOTICE_listAction.action'">공지사항</a></li>
						
					</s:if>
					<s:else>

						<li>${session_ID}님환영합니다.</li>
						<li><a href="javascript:location.href='FAQ_listAction.action'">고객센터</a></li>
						<li><a href="javascript:location.href='NOTICE_listAction.action'">공지사항</a></li>
						<li><a href="javascript:location.href='Logout.action'">로그아웃</a></li>
					
					</s:else>

				</ul>
			</div>
		</div>
	</div>

</div>
</body>
</html>