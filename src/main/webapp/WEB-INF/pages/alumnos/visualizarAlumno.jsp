<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../comunes/inicioHTML.jsp">
    <jsp:param name="elTitulo" value="Visualizar a ${alumnoAVisualizar.nombreCompleto}" />
</jsp:include>

<jsp:include page="../comunes/navbar.jsp" />

<section class="container py-3">
    <div class="row">
        <h1 class="h3">Editando alumno</h1>
        <p class="lead">Estás visualizando los datos de ${alumnoAVisualizar.nombreCompleto}</p>
    </div>

    <div class="row align-items-center px-4 mt-3">
        <div class="col-md-5 col-lg-4" >
            <div class="card h-100">
                <jsp:include page="partes/dataCardAlumno.jsp">
                    <jsp:param name="fotoAlumno" value="${alumnoAVisualizar.foto}" />
                    <jsp:param name="nombreCompletoAlumno" value="${alumnoAVisualizar.nombreCompleto}" />
                </jsp:include>
            </div>
        </div>
        <div class="col-md-7 col-lg-8" >
            <form id="formAgregarAlumno" action="${pageContext.request.contextPath}/app?accion=update&id=${alumnoAVisualizar.id}"
                  method="post" class="border p-4 rounded-3 bg-light">
                <div class="row">
                    <div class="col-sm-6 mb-3">
                        <label for="nombre" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" value="${alumnoAVisualizar.nombre}" disabled>
                    </div>
                    <div class="col-sm-6 mb-3">
                        <label for="apellido" class="form-label">Apellido</label>
                        <input type="text" class="form-control" id="apellido" name="apellido" value="${alumnoAVisualizar.apellido}" disabled>
                    </div>
                    <div class="col-sm-6 mb-3">
                        <label for="mail" class="form-label">Mail</label>
                        <input type="text" class="form-control" id="mail" name="mail" value="${alumnoAVisualizar.mail}" disabled>
                    </div>
                    <div class="col-sm-6 mb-3">
                        <label for="fechaNac" class="form-label">Fecha de nacimiento</label>
                        <input type="date" class="form-control" id="fechaNac" name="fechaNac" value="${alumnoAVisualizar.fechaNacimiento}" disabled>
                    </div>

                    <div class="col-12 mb-3">
                        <label for="foto" class="form-label">
                            <c:choose>
                                <c:when test="${yaTieneFoto}">Modificar</c:when>
                                <c:otherwise>Agregar</c:otherwise>
                            </c:choose>
                            foto
                        </label>
                        <input type="file" class="form-control" id="inputFoto" name="foto" disabled>
                        <input type="hidden" id="fotoBase64" name="fotoBase64" value="${alumnoAVisualizar.foto}}">
                    </div>
                </div>
                <div class="row justify-content-end mt-2">
                    <div class="col-12">
                        <div class="float-end">
                            <a href="${pageContext.request.contextPath}/app" class="btn btn-secondary">Volver atrás</a>
                            <a href="${pageContext.request.contextPath}/app?volver=visualizar&accion=edit&id=${alumnoAVisualizar.id}" class="btn btn-warning">Editar</a>
                            <a href="${pageContext.request.contextPath}/app?volver=visualizar&accion=remove&id=${alumnoAVisualizar.id}" class="btn btn-danger">Borrar</a>
                        </div>
                    </div>      
            </form>
        </div>
    </div>

</section>

<script src="scripts/fotobase64.js"></script>
<jsp:include page="../comunes/footer.jsp"/>
<jsp:include page="../comunes/finHTML.jsp"/>


