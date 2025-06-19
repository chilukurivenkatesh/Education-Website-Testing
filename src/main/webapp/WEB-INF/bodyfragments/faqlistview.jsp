<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>



<c:url var="addSearch" value="/home/login/helpDesk/search" />

<c:url var="editUrl" value="/home/login/helpDesk?id=" />


<br>
<div class="container"> 
<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item linkSize"><i class="fas fa-tachometer-alt"></i> <a class="link-dark" href="<c:url value="/home"/>">Home</a></li>
    <li class="breadcrumb-item linkSize active" aria-current="page"> <i class="fa fa-arrow-right" aria-hidden="true"></i> Help Desk</li>
  </ol>
</nav>
</div>
<hr>
	<sf:form method="post"
		action="${pageContext.request.contextPath}/home/login/helpDesk/search"
		modelAttribute="form">
		<div class="card">
			<h5 class="card-header"
				style="background-color: #00061df7; color: white;">FAQ List</h5>
			<div class="card-body">
				<div class="row g-3">
				</div>
				<b><%@ include file="businessMessage.jsp"%></b>
				<br>
			

				<table class="table table-bordered border-primary">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Questions</th>
							<th scope="col">Answers</th>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="li" varStatus="helpDesk">
							<tr>
							
								<td scope="row">${helpDesk.index+1}</td>
								<td scope="row">${li.question}</td>
								<td scope="row">${li.ans}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			
				
				
			</div>

		</div>
	</sf:form>
