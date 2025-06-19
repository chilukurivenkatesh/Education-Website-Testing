<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>



<br>
<div class="container">
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item linkSize"><i
				class="fas fa-tachometer-alt"></i> <a class="link-dark"
				href="<c:url value="/home"/>">Home</a></li>
			<li class="breadcrumb-item linkSize active" aria-current="page">
				<i class="fa fa-arrow-right" aria-hidden="true"></i>View
			</li>
		</ol>
	</nav>
</div>
<hr>
<div class="container">
	<div class="row">
		<div class="col-12">
			<video  controls>
				<source src="<c:url value="/ctl/content/getVideo/${sessionScope.cId}" />"
					type="video/mp4">
			</video>
		</div>
	</div>
	<br>
	<div class="row">
	<div class="col-4">
			<div class="card">
				<h5 class="card-header"
					style="background-color: #00061df7; color: white;">Material</h5>
				<div class="card-body">
					<a
						href="${pageContext.request.contextPath}/ctl/content/getMaterial/${sessionScope.cId}">Download
						Material</a>
				</div>
			</div>
		</div>
	</div>
</div>
<br>