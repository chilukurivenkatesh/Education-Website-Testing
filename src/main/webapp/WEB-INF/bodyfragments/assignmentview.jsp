<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<br>

<div class="shadow-sm  mb-5 bg-body rounded">
	<div class="jumbotron"
		style="background-color: #00061df7; padding: 15px;">
		<h1 style="color: white;">Add Assignment</h1>
	</div>
</div>
<div class="container">
	<div class="row">
		<div class="col-md-8">
			<b><%@ include file="businessMessage.jsp"%></b>
			<div class="well well-sm">
				<sf:form action="${pageContext.request.contextPath}/AddAssignment"
					method="post" modelAttribute="form" enctype="multipart/form-data">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<s:bind path="title">
									<label for="title"> Title</label>
									<sf:input class="form-control" placeholder="Enter name"
										path="${status.expression}" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
								</s:bind>
							</div>

						
						<div class="col-md-6">
							<div class="form-group">
								<s:bind path="description">
									<label for="description"> Description</label>
									<sf:textarea path="${status.expression}" class="form-control"
										rows="6" cols="15" placeholder="Description" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
								</s:bind>
							</div>

							<div class="col-md-8">
								<s:bind path="afile">
									<label for="inputEmail4" class="form-label">Upload Doc</label>
									<sf:input type="file" path="${status.expression}"
										placeholder="Enter a file" class="form-control"
										required="required" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
								</s:bind>
							</div>

						</div>

</div>


						<div class="col-md-12">
							<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="Submit" />
						</div>
					</div>
				</sf:form>
			</div>
		</div>
	
	</div>
</div>
