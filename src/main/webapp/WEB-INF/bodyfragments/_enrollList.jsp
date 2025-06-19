<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<c:url var="addSearch" value="/user/course/search" />

<c:url var="content" value="/home/login/student/myCourse/content?cId=" />
<br>
<sf:form method="post"
		action="${pageContext.request.contextPath}/home/login/student/enroll"
		modelAttribute="form">
<div class="container"> 
<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item linkSize"><i class="fas fa-tachometer-alt"></i> <a class="link-dark" href="<c:url value="/home"/>">Home</a></li>
    <li class="breadcrumb-item linkSize active" aria-current="page"> <i class="fa fa-arrow-right" aria-hidden="true"></i> My Course</li>
  </ol>
</nav>
</div>
<hr>

<div class="container">
<div class="row">
<c:forEach items="${list}" var="pl" varStatus="enroll">

  <div class="col-2">
    <div class="card" style="margin-top: 10px;">
      <a href="${content}${pl.course.id}"><img height="180px" src="<c:url value="/ctl/course/getImage/${pl.course.id}"/>" class="card-img-top" alt="..."></a>
      <div class="card-body">
        <h5 class="card-title">${pl.course.name}</h5>
        <p class="card-text">${pl.course.description}</p>
      </div>
        <ul class="list-group list-group-flush">
    <li class="list-group-item">${pl.course.instructor}</li>
    <li class="list-group-item">${pl.course.language}</li>
  </ul>
    </div>
    
  </div>

  
</c:forEach>
</div>
</div>
</sf:form>
<br>
