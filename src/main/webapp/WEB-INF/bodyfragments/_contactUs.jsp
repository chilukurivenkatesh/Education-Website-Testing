<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<br>

<div class="shadow-sm  mb-5 bg-body rounded">
	<div class="jumbotron"
		style="background-color: #00061df7; padding: 15px;">
		<h1 style="color: white;">Contact Us</h1>
	</div>
</div>
<div class="container">
	<div class="row">
		<div class="col-md-8">
		<b><%@ include file="businessMessage.jsp"%></b>
			<div class="well well-sm">
				<sf:form action="${pageContext.request.contextPath}/home/contactUs"
					method="post" modelAttribute="form">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<s:bind path="name">
									<label for="name"> Name</label>
									<sf:input class="form-control" placeholder="Enter name"
										path="${status.expression}" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
								</s:bind>
							</div>
							<div class="form-group">
								<label for="email"> Email Address</label>
								<s:bind path="email">
								<div class="input-group">
								
									
									<sf:input class="form-control" placeholder="Enter Email Address"
										path="${status.expression}" />
										
								</div>
								<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
								</s:bind>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
							<s:bind path="message">
								<label for="name"> Message</label>
								<sf:textarea path="${status.expression}"  class="form-control"
									rows="6" cols="15"  placeholder="Message"/>
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
							</s:bind>
							</div>
						</div>
						<div class="col-md-12">
							<input type="submit" name="operation" class="btn btn-primary pull-right"
								 value="Submit"/>
						</div>
					</div>
				</sf:form>
			</div>
		</div>
		<div class="col-md-4">
			<form>
				<legend>
					<span class="glyphicon glyphicon-globe"></span> Our office
				</legend>
				<address>
					<strong>Twitter, Inc.</strong><br> 795 Folsom Ave, Suite 600<br>
					San Francisco, CA 94107<br> <abbr title="Phone"> P:</abbr>
					(123) 456-7890
				</address>
				<address>
					<strong>Full Name</strong><br> <a href="mailto:#">first.last@example.com</a>
				</address>
			</form>
		</div>
	</div>
</div>
