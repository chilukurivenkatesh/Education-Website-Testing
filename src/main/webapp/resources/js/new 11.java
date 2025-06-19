<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>



 <section style="background-color: #eee;">

  <div class="container py-3">
  <div class="row">
  	
    <c:forEach items="${list}" var="li" varStatus="u">
    <div class="col">
    <sf:form method="post" action="${pageContext.request.contextPath}/movieBooking" modelAttribute="form">
    
    <sf:input type="hidden" id="id" class="form-control form-control-lg" path="id" name="id" value="${li.id}"/>
	
      <div >
        <div class="card">
          <div class="d-flex justify-content-between p-3">
            <p class="lead mb-0">${li.title}</p>
          </div>
          <img alt="" src="${pageContext.request.contextPath}/getImage/${li.id}" class="card-img-top" height="300px" width="200px">
          <div class="card-body">

            <div class="d-flex justify-content-between mb-3">
              <h5 class="mb-0">Price</h5>
              <h5 class="text-dark mb-0">${li.price} RS</h5>
            </div>

            <div class="d-flex justify-content-between mb-3">
              <h5 class="mb-0">Language</h5>
              <h5 class="text-dark mb-0">${li.language}</h5>
            </div>
            <div>
             <div class="col-md-6 mb-4">
              <div class="form-check">
  					<input class="form-check-input" type="radio" name="show" value="1">
  					<label class="form-check-label" for="flexRadioDefault1">
    				10:00 AM
  					</label>
			</div>
			
			 <div class="form-check">
  				   <input class="form-check-input" type="radio" name="show" value="2">
  					<label class="form-check-label" for="flexRadioDefault1">
    				1:00 PM
  					</label>
			</div>
			
			 <div class="form-check">
  					<input class="form-check-input" type="radio" name="show" value="3">
  					<label class="form-check-label" for="flexRadioDefault1">
    				4:00 PM
  					</label>
			</div>
			
			 <div class="form-check">
  					<input class="form-check-input" type="radio" name="show" value="4">
  					<label class="form-check-label" for="flexRadioDefault1">
    				7:00 PM
  					</label>
			</div>
			
			 <div class="form-check">
  					<input class="form-check-input" type="radio" name="show" value="5">
  					<label class="form-check-label" for="flexRadioDefault1">
    				10:00 PM
  					</label>
			</div>
             
             </div>
            </div>
            
         <div>
      
          <input class="btn btn-primary btn-lg" type="submit" value="Book" />
          <a href="${pageContext.request.contextPath}/seatView?id=${li.id}">Check Available Seat</a>
          
          </div>
            
          </div>

        </div>
      </div>

      </sf:form>
      </div>
       </c:forEach>
       </div>
    </div>
 </section>
 
  
  
  
  
  
  
  
  
  
  
  
  
  
  
     
