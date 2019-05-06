<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,vaalikone.Vaalikone,persist.*"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Diginide Vaalikone 2.0</title>

<link href="style.css" rel="stylesheet" type="text/css">

          	<script lang="javascript">
            function editAnswer()
            {
            	
                //Collect input from html page
                var answer = new Object();
                answer.vastaus = document.getElementById("answer").value;
                answer.kommentti = document.getElementById("comment").value;
                answer.ehdokasid = document.getElementById("candidateid").value;
                answer.kysymysid = document.getElementById("q").value;
                
                var jsonAnswer = JSON.stringify(answer);
                
                
                var xhttp = new XMLHttpRequest();
                                 
				xhttp.open("POST", "./rest/editanswer/", true);
				xhttp.setRequestHeader("Content-Type", "application/json");
				xhttp.send(jsonAnswer);
				
                //Use the REST API response                                
                xhttp.onreadystatechange = function(){
                	if (this.readyState == 4 && this.status == 200){
                		
                		var palautettu=JSON.parse(this.responseText);
                		document.getElementById("editAnswerAJAX").innerHTML="You changed answer from candidate " +  palautettu.ehdokasid + " to question nro " + palautettu.kysymysid +  " to: " + palautettu.vastaus + " and the comment to: " + palautettu.kommentti;
                		
                	}
                	else{
                		document.getElementById("editAnswerAJAX").innerHTML=xhttp.response;
                		
                	}
                }
            }
        </script>
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

			<form method="post" style="margin-left:300px; margin-top:50px;">
			<p> Change answers:</p>
			Answer (1-5)<input type="number" min="1" max="5" name="answer" id="answer" value=${answer} style="width=50;"><br>
			Comment: <textarea name="comment" id="comment" rows="1" cols="50" maxlength="50">${comment}</textarea><br>
			<input type="hidden" name="q" id="q" value=${q}>
			<input type="hidden" name="candidateid" id="candidateid" value=${candidateid}>
			<input type="button" value="Edit Answer" onclick="editAnswer()" />
			</form> 
			
			<div id="editAnswerAJAX" style="margin-left:300px; margin-top:20px;"></div>
			

</div>

</body>
</html>