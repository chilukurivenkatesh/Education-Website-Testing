<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>

<c:url var="addUrl" value="/ctl/course" />

<c:url var="addSearch" value="/ctl/course/search" />

<c:url var="editUrl" value="/ctl/course?id=" />

<c:url var="detailUrl" value="/ctl/course/search/detail?id=" />

<c:url var="addContentUrl" value="/home/login/student/myCourse/content/view?cId=" />

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
<div class="container">
<div class="row">
		<div class="col-2">
			<img alt="" height="200px" width="200px" src="${pageContext.request.contextPath}/ctl/course/getImage/${cDto.id}">		
		</div>
		<div class="col-10">
		<div class="card">
		<h5 class="card-header"
			style="background-color: #00061df7; color: white;">About Course</h5>
		<div class="card-body">
		${cDto.about}
		</div>
		</div>
		</div>
		</div>
		<br>
<sf:form method="post"
	action="${pageContext.request.contextPath}/home/login/student/myCourse/content/search"
	modelAttribute="form">
	<div class="card">
		<h5 class="card-header"
			style="background-color: #00061df7; color: white;">Contents</h5>
		<div class="card-body">
			<div class="row g-3">

			</div>
			<b><%@ include file="businessMessage.jsp"%></b>
			<br>

					<c:forEach items="${list}" var="ct" varStatus="content">
					<div class="clearfix">
							<H6 class="float-start">${content.index+1}. 
							${ct.title}</H6>
							<a class="btn btn-info float-end" href="${addContentUrl}${ct.id}">Start</a>
					</div>
					<hr>
					</c:forEach>
		</div>

	</div>

</sf:form>
</div>