<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>

<c:url var="addUrl" value="/home/login/dashboard/course" />

<c:url var="addSearch" value="/home/login/dashboard/course/search" />

<c:url var="editUrl" value="/home/login/dashboard/course?id=" />

<c:url var="addContentUrl" value="/home/login/dashboard/course/content?cId=" />

<c:url var="showContentUrl" value="/home/login/dashboard/course/content/search?cId=" />

<br>
<div class="container"> 
<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item linkSize"><i class="fas fa-tachometer-alt"></i> <a class="link-dark" href="<c:url value="/home"/>">Home</a></li>
    <li class="breadcrumb-item linkSize active" aria-current="page"> <i class="fa fa-arrow-right" aria-hidden="true"></i> Courses</li>
  </ol>
</nav>
</div>
<hr>
	<sf:form method="post"
		action="${pageContext.request.contextPath}/home/login/dashboard/course/search"
		modelAttribute="form">
		<div class="card">
			<h5 class="card-header"
				style="background-color: #00061df7; color: white;">Courses</h5>
			<div class="card-body">
				<div class="row g-3">
					
					<s:bind path="name">
						<div class="col">
							<sf:input path="${status.expression}" class="form-control form-control-sm"
								placeholder="Search By Name" />
						</div>
					</s:bind>
					
					
					<div class="col">
						<input type="submit" class="btn btn-sm btn-outline-primary"
							name="operation" value="Search"></input> or <input type="submit"
							class="btn btn-sm btn-outline-secondary" name="operation"
							value="Reset">
					</div>
				</div>
				<b><%@ include file="businessMessage.jsp"%></b>
				<br>
				<sf:input type="hidden" path="pageNo" />
				<sf:input type="hidden" path="pageSize" />

				<sf:input type="hidden" path="listsize" />
				<sf:input type="hidden" path="total" />
				<sf:input type="hidden" path="pagenosize" />

				<table class="table table-bordered border-primary">
					<thead>
						<tr>
						<th scope="col"><input type="checkbox" id="selectall">Select
								All</th>
							<th scope="col">#</th>
							<th scope="col">Image</th>
							<th scope="col">Name</th>
							<th scope="col">Instructor</th>
							<th scope="col">Language</th>
							<th scope="col">Description</th>
							<th scope="col">Action</th>
							<th scope="col">Add Content</th>
							<th scope="col">Show Content</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="pl" varStatus="course">
							<tr>
							<td><input type="checkbox" class="case"
									name="ids" value="${pl.id}"></td>
								<td scope="row">${course.index+1}</td>
								<td scope="row"><img height="100px" width="140px" src='<c:url value="/ctl/course/getImage/${pl.id}" />'></td>
								<td scope="row">${pl.name}</td>
								<td scope="row">${pl.instructor}</td>
								<td scope="row">${pl.language}</td>
								<td scope="row">${pl.description}</td>
								<td><a href="${editUrl} ${pl.id}"
									><i class="fas fa-edit"></i></a></td>
								<td><a href="${addContentUrl} ${pl.id}"
									><i class="fa fa-plus" aria-hidden="true"></i></a></td>	
								<td><a href="${showContentUrl} ${pl.id}"
									><i class="fa fa-eye" aria-hidden="true"></i></a></td>		
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<div class="clearfix">
				<c:if test="${sessionScope.user.roleId == 3}">
				<input type="submit" name="operation"
								class="btn btn-sm btn-danger float-start" 	<c:if test="${listsize == 0}">disabled="disabled"</c:if>
								value="Delete">
				</c:if>
				
				
			<nav aria-label="Page navigation example float-end">
				<ul class="pagination justify-content-end" style="font-size: 13px">
					<li class="page-item"><input type="submit" name="operation"
								class="page-link" 	<c:if test="${form.pageNo == 1}">disabled="disabled"</c:if>
								value="Previous"></li>
								 <c:forEach var = "i" begin = "1" end = "${(listsize/10)+1}">
								 <c:if test="${i== pageNo}">
								<li class="page-item active"><a class="page-link activate" href="${addSearch}?pageNo=${i}">${i}</a></li>
								</c:if>
								<c:if test="${i != pageNo}">
								<li class="page-item"><a class="page-link" href="${addSearch}?pageNo=${i}">${i}</a></li>
								</c:if>
								</c:forEach>
					<li class="page-item"><input type="submit" name="operation"
								class="page-link" <c:if test="${total == pagenosize  || listsize < pageSize   }">disabled="disabled"</c:if>
								value="Next"></li>
				</ul>
			</nav>
			</div>
				
				
			</div>

		</div>
	</sf:form>
