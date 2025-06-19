<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>

<c:url var="addUrl" value="/home/login/dashboard/course/content" />

<c:url var="addSearch" value="/home/login/dashboard/course/content/search" />

<c:url var="editUrl" value="/home/login/dashboard/course/content?id=" />

<c:url var="detailUrl" value="/home/login/dashboard/course/content/search/detail?id=" />

<c:url var="addContentUrl" value="/home/login/dashboard/course/content?cId=" />

<br>
<div class="container">
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item linkSize"><i
				class="fas fa-tachometer-alt"></i> <a class="link-dark"
				href="<c:url value="/home"/>">Home</a></li>
			<li class="breadcrumb-item linkSize active" aria-current="page">
				<i class="fa fa-arrow-right" aria-hidden="true"></i> Contents
			</li>
		</ol>
	</nav>
</div>
<hr>
<sf:form method="post"
	action="${pageContext.request.contextPath}/home/login/dashboard/course/content/search"
	modelAttribute="form">
	<div class="card">
		<h5 class="card-header"
			style="background-color: #00061df7; color: white;">Contents</h5>
		<div class="card-body">
			<div class="row g-3">

				<s:bind path="title">
					<div class="col">
						<sf:input path="${status.expression}"
							class="form-control form-control-sm"
							placeholder="Search By Title" />
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
						<th scope="col">Course</th>
						<th scope="col">Title</th>
						<th scope="col">Video</th>
						<th scope="col">Material</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="ct" varStatus="content">
						<tr>
							<td><input type="checkbox" class="case" name="ids"
								value="${ct.id}"></td>
							<td scope="row">${content.index+1}</td>
							<td scope="row"><img height="100px" width="140px"
								src='<c:url value="/ctl/course/getImage/${ct.course.id}" />'></td>
							<td scope="row">${ct.course.name}</td>
							<td scope="row">${ct.title}</td>
							<td scope="row"><video width="400px" height="150px" controls>
									<source src="<c:url value="/ctl/content/getVideo/${ct.id}" />"
										type="video/mp4">
								</video></td>
							<td scope="row">
										<a target="_blank"
											href="<c:url value="/ctl/content/getMaterial/${ct.id}" />">Download the PDF</a>.
								</td>
							<td><a href="${editUrl} ${ct.id}"><i class="fas fa-edit"></i></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<div class="clearfix">
				<c:if test="${sessionScope.user.roleId == 1}">
					<input type="submit" name="operation"
						class="btn btn-sm btn-danger float-start"
						<c:if test="${listsize == 0}">disabled="disabled"</c:if>
						value="Delete">
				</c:if>

				<c:if test="${sessionScope.user.roleId == 2}">
					<input type="submit" name="operation"
						class="btn btn-sm btn-primary float-start"
						<c:if test="${listsize == 0}">disabled="disabled"</c:if>
						value="Compare">
				</c:if>

				<nav aria-label="Page navigation example float-end">
					<ul class="pagination justify-content-end" style="font-size: 13px">
						<li class="page-item"><input type="submit" name="operation"
							class="page-link"
							<c:if test="${form.pageNo == 1}">disabled="disabled"</c:if>
							value="Previous"></li>
						<c:forEach var="i" begin="1" end="${(listsize/10)+1}">
							<c:if test="${i== pageNo}">
								<li class="page-item active"><a class="page-link activate"
									href="${addSearch}?pageNo=${i}">${i}</a></li>
							</c:if>
							<c:if test="${i != pageNo}">
								<li class="page-item"><a class="page-link"
									href="${addSearch}?pageNo=${i}">${i}</a></li>
							</c:if>
						</c:forEach>
						<li class="page-item"><input type="submit" name="operation"
							class="page-link"
							<c:if test="${total == pagenosize  || listsize < pageSize   }">disabled="disabled"</c:if>
							value="Next"></li>
					</ul>
				</nav>
			</div>


		</div>

	</div>
</sf:form>
