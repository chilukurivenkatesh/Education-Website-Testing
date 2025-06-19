<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<c:url var="addSearch" value="/home/login/student/course/search" />
<c:url var="enroll" value="/home/login/student/enroll?cId=" />
<br>
<sf:form method="post"
		action="${pageContext.request.contextPath}/home/login/student/course/search"
		modelAttribute="form">
<div class="container"> 
<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item linkSize"><i class="fas fa-tachometer-alt"></i> <a class="link-dark" href="<c:url value="/welcome"/>">Home</a></li>
    <li class="breadcrumb-item linkSize active" aria-current="page"> <i class="fa fa-arrow-right" aria-hidden="true"></i> Courses</li>
  </ol>
</nav>
</div>
<hr>

<div class="container">

<div class="row">
  <div class="col-10">
   <s:bind path="name">
					<div class="col">
						<sf:input path="${status.expression}"
							class="form-control form-control-sm"
							placeholder="Search By Name" />
					</div>
				</s:bind>
  </div>
  <div class="col-2">
   <input type="submit" class="btn btn-sm btn-outline-primary"
							name="operation" value="Search"></input> or <input type="submit"
							class="btn btn-sm btn-outline-secondary" name="operation"
							value="Reset">
  </div>
</div>
<br>
<div class="row">

<c:forEach items="${list}" var="pl" varStatus="course">
  <div class="col-2">
    <div class="card">
      <img src="<c:url value="/ctl/course/getImage/${pl.id}"/>" class="card-img-top" alt="...">
      <div class="card-body">
        <h5 class="card-title">${pl.name}</h5>
        <font>${pl.instructor}</font>
         <font>${pl.language}</font>
        <p class="card-text">${pl.description}</p>
      </div>
        <ul class="list-group list-group-flush">
    <li class="list-group-item">${pl.instructor}</li>
    <li class="list-group-item">${pl.language}</li>
  </ul>
  <div class="card-body">
    <a href="${enroll}${pl.id}" class="btn btn-info">Enroll</a>
  </div>
    </div>
    
  </div>

  
</c:forEach>
</div>
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
</sf:form>
<br>
