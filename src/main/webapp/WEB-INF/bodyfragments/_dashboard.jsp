<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<br>
<div class="container"> 
<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item linkSize"><i class="fas fa-tachometer-alt"></i> <a class="link-dark" href="<c:url value="/home"/>">Home</a></li>
    <li class="breadcrumb-item linkSize active" aria-current="page"> <i class="fa fa-arrow-right" aria-hidden="true"></i> Dashboard</li>
  </ol>
</nav>

<hr>
<div class="row">
<div class="col">
<div class="card text-white bg-danger mb-3" style="max-width: 18rem;">
  <a href="<c:url value="/home/login/dashboard/course/search"/>" class="link-light"><div class="card-body">
    <h2 class="card-title">View Course</h2>
  </div>
  </a>
</div>
</div>
<div class="col">
<div class="card text-white bg-danger mb-3" style="max-width: 18rem;">
   <a href="<c:url value="/home/login/dashboard/course"/>" class="link-light"><div class="card-body">
    <h2 class="card-title">Add Course</h2>
  </div></a>
  </div>
</div>
<div class="col">
<div class="card text-white bg-danger mb-3" style="max-width: 18rem;">
 <a href="<c:url value="/home/login/helpDesk/search"/>" class="link-light"> <div class="card-body">
    <h2 class="card-title">View Query</h2>
  </div></a>
  </div>
</div>
</div>
</div>