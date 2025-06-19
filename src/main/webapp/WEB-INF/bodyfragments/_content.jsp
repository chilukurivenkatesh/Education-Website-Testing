<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<br>
<div class="container"> 
<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item linkSize"><i class="fas fa-tachometer-alt"></i> <a class="link-dark" href="<c:url value="/home"/>">Home</a></li>
    <li class="breadcrumb-item linkSize active" aria-current="page"> <i class="fa fa-arrow-right" aria-hidden="true"></i> Content</li>
  </ol>
</nav>
</div>

<hr>
<div class="container">
<div class="row">
		<div class="col-4">
			<img alt="" height="343px" width="300px" src="${pageContext.request.contextPath}/ctl/course/getImage/${sessionScope.cId}">		
		</div>
		<div class="col-8">
		<sf:form method="post"
		action="${pageContext.request.contextPath}/home/login/dashboard/course/content"
		modelAttribute="form" enctype="multipart/form-data">
		<sf:hidden path="id"/>
		<div class="card">
			<h5 class="card-header"
				style="background-color: #00061df7; color: white;">Content</h5>
			<div class="card-body">
				<b><%@ include file="businessMessage.jsp"%></b>



				<div class="col-md-8">
					<s:bind path="title">
						<label for="inputEmail4" class="form-label">Title</label>
						<sf:input path="${status.expression}"
							placeholder="Enter Title.." class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
				
				
				
				<div class="col-md-8">
					<s:bind path="video">
						<label for="inputEmail4" class="form-label">Video</label>
						<input type="file" name="${status.expression}"
							placeholder="Enter Video" class="form-control" required="required"/>
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
				
				<div class="col-md-8">
					<s:bind path="material">
						<label for="inputEmail4" class="form-label">Material</label>
						<sf:input type="file" path="${status.expression}"
							placeholder="Enter Material" class="form-control" required="required"/>
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
				
				
				<br>
				<div class="col-12">
					<input type="submit" name="operation"
						class="btn btn-primary pull-right" value="Save"> 
				</div>
			</div>
		</div>
	</sf:form>
		</div>
</div>
	
</div>