<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix = "s" uri = "/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
	function open_win_noresizable(url, name)
	{
		var oWin = window.open(url, name, "scrollbars=no, status=no, resizable=no, width=300, height=150");
		
	}
	function modalPrintPopUp(login){
		var objectName=new Object();
		ObjectName.login=login;
		var site="/login/LoginForm.jsp";
		var style="dialogWidth:820px;dialogHeight:550px";
		window.showModalDialog(site, objectName, style);
	}
</script>
</head>

<body>
	<center>
	<table width="80%" border="0" cellspacing="0" cellpadding="2">
		<tr>
			<td align="center" width="50%" bgcolor=#C4C0C0>
				<h2>자유게시판</h2>
			</td>
	 
		
		<!-- 	<td align="center">   
				<h2><a href="TIP_listAction.action">팁게시판</a></h2>
			</td> -->
		</tr>
	</table>
	
	<table width="80%" border="0" cellspacing="0" cellpadding="2">
		<tr align="center" bgcolor="#f3f3f3">
			<td width="50">
				<strong>번호</strong>
			</td>
			<td width="350">
				<strong>제목</strong>
			</td>
			<td width="70">
				<strong>글쓴이</strong>
			</td>
			<td width="80">
				<strong>날짜</strong>
			</td>
			<td width="50">	
				<strong>조회</strong>
			</td>
		</tr>
		<tr bgcolor="#777777">
			<td height="1" colspan="5"></td>
		</tr>
		
		<s:iterator value="list" status="stat">
		
			<s:url id="viewURL" action="CMN_viewAction">
				<s:param name="no">
					<s:property value="no"/>
				</s:param>
				<s:param name="currentPage">
					<s:property value="currentPage"/>
				</s:param>
				
				<s:param name="userReq"> cmnList </s:param>
			</s:url>
			
			<tr bgcolor="#FFFFFF" align="center">
				<td>
					<s:property value="no"/>
				</td>
				<td align="left">&nbsp;
					<s:a href="%{viewURL}">
						<s:property value="subject"/>
					</s:a>
				</td>
				<td align="center">
					<s:property value="id"/>
				</td>
				<td align="center">
					<s:property value="regdate"/>
				</td>
				<td>
					<s:property value="count"/>
				</td>
			</tr>
			<tr bgcolor="#777777">
				<td height="1" colspan="5">
				</td>
			</tr>
		</s:iterator>
		
		<s:if test="list.size()<=0">
		
			<tr bgcolor="#FFFFFF" align="center">
				<td colspan="5">등록된 게시물이 없습니다.
				</td>
			</tr>
			<tr bgcolor="#777777">
				<td height="1" colspan="5">
				</td>
			</tr>
			
			</s:if>
			
			<tr align="center">
				<td colspan="5">
					<s:property value="pagingHtml" escape="false"/>
				</td>
			</tr>
			
			<c:if test="${session.session_ID != null}">
			<tr align="right">
				<td colspan="5">
					<input type="button" value="글쓰기" class="inputb" 
					onClick="javascript:location.href='CMN_writeForm.action?currentPage=<s:property value="currentPage"/>';"/>
				</td>
			</tr>
			</c:if>
			
			<c:if test="${session.session_ID == null}">
					<tr align="right">
						<td colspan="5">
						<input type="button" value="글쓰기" class="inputb" 
							onClick="javascript:location.href='CMN_script.action?currentPage=<s:property value="currentPage" />';"/>
						</td>
				
			</c:if>
			
			<tr align="center">
				<td colspan="5">
					<form>
						<select name="searchNum">
							<option value="0">작성자</option>
							<option value="1">제목</option>
							<option value="2">내용</option>
						</select>
						<s:textfield name="searchKeyword" theme="simple" value="" cssStyle="width:120px" maxlength="20"/>
						<input name="submit" type="submit" value="검색" class="inputb">
					</form>
				</td>
			</tr>
	</table>
	</center>


</body>
</html>