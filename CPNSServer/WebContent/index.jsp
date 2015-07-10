<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>CPNS Sender</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/foundation.css" />
<script src="<%=request.getContextPath()%>/js/vendor/modernizr.js"></script>
</head>
<body>
	<div class="row">
		<div class="small-12 columns text-center">
			<h1>Push Notification Service</h1>
		</div>
	</div>

	<form action="<%=request.getContextPath()%>/sendNotification"
		method="post">
		<div class="row">
			<div class="small-12 medium-8 large-6 columns">
				<fieldset>
					<legend>Notification Request</legend>
					<div class="row">
						<div class="small-12 columns">
							<label>Platform: <select name="platform">
									<option value="0">--Select--</option>
									<option value="android">Android</option>
									<option value="iOS">iOS</option>
									<option value="windowsPhone">Windows Phone</option>
							</select>
							</label>
						</div>
					</div>
					<div class="row">
						<div class="small-12 columns">
							<label>Notification Title: <input type="text" name="title"
								id="title" />
							</label>
						</div>
					</div>
					<div class="row">
						<div class="small-12 columns">
							<label>Notification Body: <textarea name="message" id="message"></textarea>
							</label>
						</div>
					</div>
					<div class="row">
						<div class="small-12 columns">
							<label>Device Token: <textarea name="deviceToken"
									id="deviceToken"></textarea>
							</label>
						</div>
					</div>
					<!-- <table cellspacing="10">
						<tr>
							<td>Platform:</td>
							<td><select name="platform">
									<option value="0" selected>--Select--</option>
									<option value="android">Android</option>
									<option value="iOS">iOS</option>
									<option value="windowsPhone">Windows Phone</option>
							</select></td>
						</tr>
						<tr>
							<td>Notification Title:</td>
							<td><input type="text" name="title" id="title"></td>
						</tr>
						<tr>
							<td>Notification Body:</td>
							<td><textarea rows="3" cols="50" name="message" id="message"></textarea></td>
						</tr>
						<tr>
							<td>Device Token:</td>
							<td><textarea rows="4" cols="50" name="deviceToken"
									id="deviceToken"></textarea></td>
						</tr>
						<tr>
							<td align="center" colspan="2"><input type="submit"
								value="Send" /> <input type="reset" value="Reset" /> <br /></td>

						</tr>
					</table> -->
				</fieldset>
			</div>
		</div>
	</form>
	<%
		if (request.getAttribute("errorMsg") != null) {
	%>
	<h4 style="color: red">
		Error:
		<%=request.getAttribute("errorMsg").toString()%></h4>
	<%
		}
	%>
	<%
		if (request.getAttribute("responseMsg") != null) {
	%>
	<fieldset style="display: inline-block;">
		<legend>Response</legend>
		<%=request.getAttribute("responseMsg")%>
	</fieldset>
	<%
		}
	%>
	<script src="<%=request.getContextPath()%>/js/vendor/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/js/foundation.min.js"></script>
	<script>
		$(document).foundation();
	</script>
</body>
</html>