<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
	<head>
		<title>シャフトラボ</title>
	</head>
	<body>
		<form:form modelAttribute="userSearchForm" action="./search" method="POST">
			<h2>ユーザー検索</h2>
			<table border="1">
				<tr>
					<th>キーワード検索</th>
					<td colspan="3">
						<form:input path="keyword" placeholder="姓、名、カナ" />
					</td>
				</tr>
				<tr>
					<th>ユーザー姓</th>
					<td>
						<form:input path="sei" />
					</td>
					<th>ユーザー名</th>
					<td>
						<form:input path="mei" />
					</td>
				</tr>
				<tr>
					<th>性別</th>
					<td colspan="3">
						<form:radiobuttons items="${userSearchForm.sexComboDataVoList}" path="sexCd" itemValue="valStr" itemLabel="dispStr"/>
						
					</td>
				</tr>
				<tr>
					<th>都道府県</th>
					<td colspan="3">
						<form:select items="${userSearchForm.stateComboDataVoList}" path="stateCd" itemValue="valStr" itemLabel="dispStr"/>
						
					</td>
				</tr>
			</table>
			<form:button name="search">検索</form:button>
			<table border="1">
				<c:if test="${userSearchForm.resultRecordList != null}">
				<thead>
					<tr>
						<th>ユーザー姓</th>
						<th>ユーザー名</th>
						<th>ユーザー姓カナ</th>
						<th>ユーザー名カナ</th>
						<th>性別</th>
						<th>都道府県</th>
					</tr>
				</thead>
				</c:if>
				<c:forEach items="${userSearchForm.resultRecordList}" var="resultRecord">
					<tr>
						<td><c:out value="${resultRecord.sei}" /></td>
						<td><c:out value="${resultRecord.mei}" /></td>
						<td><c:out value="${resultRecord.seiKana}" /></td>
						<td><c:out value="${resultRecord.meiKana}" /></td>
						<td><c:out value="${resultRecord.sex}" /></td>
						<td><c:out value="${resultRecord.state}" /></td>
					</tr>
				</c:forEach>
			</table>
		</form:form>
	</body>
</html>