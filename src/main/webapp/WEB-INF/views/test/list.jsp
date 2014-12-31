<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>SAP Devops demo</title>
	<link href="<c:url value="/resources/form.css" />" rel="stylesheet"  type="text/css" />		
	<link href="<c:url value="/resources/jqueryui/1.8/themes/base/jquery.ui.core.css" />" rel="stylesheet" type="text/css"/>
	<link href="<c:url value="/resources/jqueryui/1.8/themes/base/jquery.ui.theme.css" />" rel="stylesheet" type="text/css"/>
	<link href="<c:url value="/resources/jqueryui/1.8/themes/base/jquery.ui.tabs.css" />" rel="stylesheet" type="text/css"/>
	
	<!--
		Used for including CSRF token in JSON requests
		Also see bottom of this file for adding CSRF token to JQuery AJAX requests
	-->
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
	<div id="async">
		<h2>test/list</h2>
		<ul>
			<li>
				<a id="testAdd" class="textLink"
					href="<c:url value="/test/add" />">POST  /test/add</a>
			</li>
		</ul>
	</div>
<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.11.1.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jqueryform/2.8/jquery.form.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jqueryui/1.8/jquery.ui.core.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jqueryui/1.8/jquery.ui.widget.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jqueryui/1.8/jquery.ui.tabs.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/json2.js" />"></script>

<script type="text/javascript">
$(document).ready(function() {
				

	$("#testAdd").click(function(e) {
		e.preventDefault();
		var form = $(this);
		var data = "{ \"foo\": \"bar\", \"fruit\": \"apple\" }";
		$.ajax({
				type: "POST", 
				url: '/test/add', 
				data: data, 
				contentType: "application/json", 
				dataType: "text", 
				success: function(text) { 
					console.log(text);
				}, 
				error: function(xhr) { 
					console.log(xhr.responseText); 
				}
		});
		return false;
	});



	// Include CSRF token as header in JQuery AJAX requests
	// See http://docs.spring.io/spring-security/site/docs/3.2.x/reference/htmlsingle/#csrf-include-csrf-token-ajax
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});

});
</script>
</body>
</html>