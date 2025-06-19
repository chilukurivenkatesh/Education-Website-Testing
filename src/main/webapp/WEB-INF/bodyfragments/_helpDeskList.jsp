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
				style="background-color: #00061df7; color: white;">Help Desk</h5>
			<div class="card-body">
				<div class="row g-3">
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
							<th scope="col">#</th>
							<th scope="col">Problem</th>
							<th scope="col">Description</th>
							<th scope="col">Comment</th>
							<c:if test="${sessionScope.user.roleId == 3}">
							<th scope="col">Add Comment</th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="pl" varStatus="helpDesk">
							<tr>
							
								<td scope="row">${helpDesk.index+1}</td>
								<td scope="row">${pl.problem}</td>
								<td scope="row"><span> Question Posted On Time : ${pl.createdDatetime}</span><p>${pl.description}</p></td>
								<td scope="row">
								<c:if test="${pl.comment!=null}">									
								<span>Answered on : ${pl.modifiedDatetime}</span><p>${pl.comment}</p>
								</c:if>
								</td>
								<c:if test="${sessionScope.user.roleId == 3}">
								<td><a href="${editUrl} ${pl.id}"
									><i class="fas fa-edit"></i></a></td>
									</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<div class="clearfix">
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
