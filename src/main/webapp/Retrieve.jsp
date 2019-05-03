<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,vaalikone.Vaalikone,persist.*"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Diginide Vaalikone 2.0</title>

<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>

<div id="container">
<img id="headerimg" src="Logo.png" width="500" height="144" alt=""/>
<div id="stuff" style="margin-left:300px; margin-top:50px;">
<h4>Candidate id</h4>
<p>${candidateid}</p>
<h4>Question number</h4>
<p>${q}</p>
<h4>Question</h4>
<p>${question}</p>
<h4>Answer (1-5)</h4>
<p>${answer}</p>
<h4>Comment</h4>
<p>${comment}</p>

</div>

			<form action="/rest/editanswer" style="margin-left:300px; margin-top:50px;">
			<p> Change answers:</p>
			Answer (1-5)<input type="number" min="1" max="5" name="answer" value=${answer} style="width=50;"><br>
			Comment: <textarea name="comment" rows="1" cols="50" maxlength="50">${comment}</textarea><br>
			<input type="hidden" name="q" value=${q}>
			<input type="hidden" name="candidateid" value=${candidateid}>
			<input type="submit" />
			</form> 
			

</div>

</body>
</html>