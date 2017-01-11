<html>
	<body>
		<form method="post" action="uploadPhoto" enctype="multipart/form-data">
		<table style="margin-left:auto;margin-right:auto;width:400px;" cellpadding="5">
			<tr>
				<td></td>
				<td align="right"><a href="logout">Logout</a></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><h2>User Profile</h2></td>
			</tr>
			<tr>
				<td>ID:</td>
				<td><%=session.getAttribute("id") %></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><%=session.getAttribute("email") %></td>
			</tr>
			<tr>
				<td>Create Date:</td>
				<td><%=session.getAttribute("createDate") %></td>
			</tr>
			<tr>
				<td>Photo:</td>
				<td><% if(session.getAttribute("urlPhoto")!=null && !session.getAttribute("urlPhoto").toString().trim().equals("")){%>
				<img src="<%=session.getAttribute("urlPhoto").toString()%>" width="100"/><%}else{%>
				<input type="file" name="photo"/><%}%></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="hidden" name="hidID" value="<%=session.getAttribute("id")%>"/>
					<input type="hidden" name="hidEmail" value="<%=session.getAttribute("email") %>"/>
					<input type="hidden" name="hidCreateDate" value="<%=session.getAttribute("createDate") %>"/>
					<input type="hidden" name="hidUrlPhoto" value="<%=session.getAttribute("urlPhoto") %>"/>
					<input type="submit" name="submit" value="Submit"/>
                    <input type="button" name="cancel" value="Cancel"/>					
				</td>
			</tr>
		</table>
		</form>
	</body>
</html>