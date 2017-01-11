<html>
	<body>	
		<form method="post" action="submitLogin">
			<table style="margin-left:auto;margin-right:auto;width:auto;" cellpadding="5">				
				<tr><td colspan="2" align="center"><h2>Login Form</h2></td></tr>
				<tr>
					<td><b>Email:</b></td>
					<td><input type="text" name="txtemail" size="30"></td>
				</tr>
				<tr>
					<td><b>Password:</b></td>
					<td><input type="password" name="txtpassword" size="30"></td>
				</tr>
				<tr><td colspan="2" align="center"><input type="submit" value="Login"></td></tr>
				<tr><td colspan="2" align="center"><h2><%
				if(session.getAttribute("message")!=null){				
					out.print(session.getAttribute("message"));
					session.removeAttribute("message");
				}
				%></h2></td></tr>
			</table>
		</form>	
	</body>
</html>