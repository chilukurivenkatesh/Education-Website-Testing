<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<br>
<div class="container"> 
<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item linkSize"><i class="fas fa-tachometer-alt"></i> <a class="link-dark" href="<c:url value="/home"/>">Home</a></li>
    <li class="breadcrumb-item linkSize active" aria-current="page"> <i class="fa fa-arrow-right" aria-hidden="true"></i> HelpDesk</li>
  </ol>
</nav>
</div>
<hr>
<div class="container"> 
	<sf:form method="post"
		action="${pageContext.request.contextPath}/home/login/helpDesk"
		modelAttribute="form" >
		<sf:hidden path="id"/>
		<div class="card">
			<h5 class="card-header"
				style="background-color: #00061df7; color: white;">Help Desk</h5>
			<div class="card-body">
				<b><%@ include file="businessMessage.jsp"%></b>



				<div class="col-md-6">
					<s:bind path="problem">
						<label for="inputEmail4" class="form-label">Problem</label>
						<sf:input path="${status.expression}"
							placeholder="Enter Problem" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>


				<div class="col-md-6">
					<s:bind path="description">
						<label for="inputEmail4" class="form-label">Description</label>
						<sf:textarea path="${status.expression}" placeholder="Enter Description"
							class="form-control" rows="5" cols="4" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>

				<c:if test="${sessionScope.user.roleId == 3}">
				<div class="col-md-6">
					<s:bind path="comment">
						<label for="inputEmail4" class="form-label">Comment</label>
						<sf:textarea path="${status.expression}" placeholder="Enter Comment"
							class="form-control" rows="5" cols="4" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
			</c:if>

				<br>
				<div class="col-12">
					<input type="submit" name="operation"
						class="btn btn-primary pull-right" value="Save">
				</div>
			</div>
		</div>
	</sf:form>
</div>