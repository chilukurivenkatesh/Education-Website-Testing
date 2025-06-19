<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<nav class="navbar navbar-expand-lg"
	style="height: 59px; background-color: #00061df7;">
	<div class="container-fluid">
		<a class="navbar-brand " href="#"
			style="font-size: 26px; font-family: serif; color: white;"> <span
			style="font-family: emoji; font-variant: petite-caps; color: #e76a00">E</span>
			<span>-Learning</span>
		</a>

		<form class="container-fluid">
			<div class="input-group" style="margin-top: 20px; width: 500px;">
			</div>
		</form>

		<div class="collapse navbar-collapse" id="navbarText">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">

				<c:if test="${sessionScope.user != null}">
					<c:if test="${sessionScope.user.roleId == 2}">
						<li class="nav-item linkSize"><a class="nav-link link-light"
							href="<c:url value="/home/login/student/mycourse/enroll/search"/>"><b
								style="margin-left: -26px; margin-top: 5px;">My Learning</b></a></li>
					</c:if>

					<%-- <li class="nav-item linkSize"><span class="navbar-text"
					style="color: white; font-size: 14px; font-variant-caps: petite-caps;" data-letters="${sessionScope.lgName}"></span></li> --%>

					<li class="nav-item dropdown"><a
						data-letters="${sessionScope.lgName}" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
					</a>
						<ul class="dropdown-menu" style="left: -120"
							aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item"
								href="<c:url value="/home/login/profile"/>">My Profile</a></li>
							<li><a class="dropdown-item"
								href="<c:url value="/home/login/changepassword"/>">Change Password</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="<c:url value="/home/login"/>">Logout</a></li>
						</ul></li>
				</c:if>
				<c:if test="${sessionScope.user == null}">
					<li class="nav-item linkSize"><span class="navbar-text"
						style="color: white; font-size: 14px; font-variant-caps: petite-caps;">
							Hello,Guest </span></li>
				</c:if>

			</ul>

		</div>
	</div>
</nav>
<div class="shadow bg-body rounded">
	<nav class="navbar navbar-expand-lg "
		style="height: 39px; background-color: #01081dd9;">
		<div class="container-fluid">
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item linkSize"><a
						class="nav-link active link-light" aria-current="page"
						href="<c:url value="/home"/>">Home</a></li>
					<c:if test="${sessionScope.user != null}">
					
						<c:if test="${sessionScope.user.roleId == 2}">
						<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/student/course/search"/>">Courses</a></li>	
							<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/helpDesk"/>">HelpDesk</a></li>
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/helpDesk/search"/>">View Queries</a></li>	
								
							<li class="nav-item linkSize"><a class="nav-link link-light"
								href="${pageContext.request.contextPath}/assignmentlist">View Assignment</a></li>	
							<li class="nav-item linkSize"><a class="nav-link link-light"
								href="${pageContext.request.contextPath}/faqlist">View FAQ</a></li>	

						</c:if>

						<c:if test="${sessionScope.user.roleId == 3}">
							<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/dashboard"/>">Dashboard</a></li>
							<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/dashboard/course/search"/>">View Course</a></li>
							<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/dashboard/course"/>">Add Course</a></li>
							<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/home/login/helpDesk/search"/>">View Queries</a></li>
								
							<li class="nav-item linkSize"><a class="nav-link link-light"
								href="${pageContext.request.contextPath}/assignment">Add Assignment</a></li>
							<li class="nav-item linkSize"><a class="nav-link link-light"
								href="${pageContext.request.contextPath}/assignmentlist">View Assignment</a></li>
								
							<li class="nav-item linkSize"><a class="nav-link link-light"
								href="${pageContext.request.contextPath}/faq">Add FAQ</a></li>
							<li class="nav-item linkSize"><a class="nav-link link-light"
								href="${pageContext.request.contextPath}/faqlist">View FAQ</a></li>			

						</c:if>

					</c:if>
					<c:if test="${sessionScope.user == null}">

						<li class="nav-item linkSize"><a class="nav-link link-light"
							href="<c:url value="/home/aboutUs"/>">AboutUs</a></li>
						<li class="nav-item linkSize"><a class="nav-link link-light"
							href="<c:url value="/home/contactUs"/>">ContactUs</a></li>
					</c:if>
				</ul>
			</div>
			<ul class="nav justify-content-end">

				<c:if test="${sessionScope.user == null}">
					<li class="nav-item linkSize"><a class="nav-link link-light"
						style="padding: 6px;" href="<c:url value="/home/login"/>">SignIn</a></li>


					<li class="nav-item dropdown linkSize"><a
						class="nav-link dropdown-toggle link-light" style="padding: 6px;"
						#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
						aria-expanded="false"> SignUp </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown"
							style="left: -80">
							<li><a class="dropdown-item" href="<c:url value="/home/signUp"/>">As
									Student</a></li>
							<li><a class="dropdown-item"
								href="<c:url value="/home/facilitator"/>">As Facilitator</a></li>
						</ul></li>
				</c:if>

			</ul>
		</div>
	</nav>
</div>
