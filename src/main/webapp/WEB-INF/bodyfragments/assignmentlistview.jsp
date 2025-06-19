<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Assignment List</title>
</head>
<body>

	<div class="container bg-image bgimage">

		<h2 style="padding: 30px">Assignment List</h2>
		<%@include file="businessMessage.jsp"%>
		<table class="table bg-light text-dark">
			<thead>
				<tr>
					<th scope="col">Title</th>
					<th scope="col">Description</th>
					<th scope="col">Assignment Q</th>
					<th scope="col">Status</th>
					<th scope="col">Submit Assignment</th>
					<th scope="col">Submitted Doc</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${list}" var="li" varStatus="u">
					<tr>
						<td>${li.title}</td>
						<td>${li.description}</td>
						
						<td><a
							href="${pageContext.request.contextPath}/getAfile/${li.id}">Download</a></td>
							<td>${li.status}</td>
						<td>
						<sf:form
								action="${pageContext.request.contextPath}/submitAssignment"
								method="post" modelAttribute="form"
								enctype="multipart/form-data">
								<sf:input type="hidden" path="id" value = "${li.id}" />
						
								
								<div class="col-md-8">
									<s:bind path="ansAssignment">
										<label for="inputEmail4" class="form-label">Upload Doc</label>
										<sf:input type="file" path="${status.expression}"
											placeholder="Enter a file" class="form-control"
											required="required" />
										<font color="red" style="font-size: 13px"><sf:errors
												path="${status.expression}" /></font>
									</s:bind>
                                 <input type="submit" name="operation" class="btn btn-primary pull-right" value="Submit" />
									
								</div>
							</sf:form>
							</td>
							<td>
							<c:if test="${li.status == 'Submitted'}">
							
							<a href="${pageContext.request.contextPath}/getSubmitfile/${li.id}">Download Answers</a>
							
							</c:if>
							
							
							</td>
							
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>

</body>
</html>