  <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>    
  <c:choose>
        <c:when test = "${param.IdAlumno == param.nuevoid}">
            <div class="card-header text-center bg-success text-white">
                <p>Nuevo Alumno</p> 
            </div>    
        </c:when>
        <c:otherwise>
            <div>   
                <p> &nbsp</p>
            </div>
        </c:otherwise>
    </c:choose>

