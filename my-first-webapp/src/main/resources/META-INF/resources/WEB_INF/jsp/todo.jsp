<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- we need to use form backing object in jsp also how can we do that using
to configure that we need to use spring form tag libraries -->
<html>
	<head>
	<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
	<link href="webjars/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css" rel="stylesheet">
		<title>Add Todo Page</title>
	</head>
	<body> --%>


<%@ include file="common/navigation.jspf"%>
<%@ include file="common/header.jspf"%>


<div class="container">
	<h1>Enter Todo Details</h1>
	<form:form method="post" modelAttribute="todo">
		<!-- now we need to tie this to Todo specific bean i.e todo specific attribute  by adding attribute modelAttribute-->

		<fieldset class="mb-3">
			<form:label path="description">Description</form:label>
			<form:input type="text" path="description" required="required" />
			<!-- we need to map the description also to Todo Bean by using form: and path -->
			<form:errors path="description" cssClass="text-warning" />
		</fieldset>

		<fieldset class="mb-3">
			<form:label path="targetDate">Target Date</form:label>
			<form:input type="text" path="targetDate" required="required" />
			<form:errors path="targetDate" cssClass="text-warning" />
		</fieldset>


		<form:input type="hidden" path="id" />
		<form:input type="hidden" path="done" />


		<input type="submit" class="btn btn-success" />
	</form:form>
</div>
<!-- 	<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
	<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
	<script src="webjars/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript" >
	$('#targetDate').datepicker({
	    format: 'yyyy-mm-dd',
	    
	});
	</script>
	
	</body> -->

<%@ include file="common/footer.jspf"%>

<script type="text/javascript">
	$('#targetDate').datepicker({
	    format: 'yyyy-mm-dd',
	    
	});
	</script>
</html>


