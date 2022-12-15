package ar.charlycimino.cac.crud.controlador;

import ar.charlycimino.cac.crud.modelo.Alumno;
import ar.charlycimino.cac.crud.modelo.Modelo;
import ar.charlycimino.cac.crud.modelo.ModeloMySQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Charly Cimino Aprendé más Java en mi canal:
 * https://www.youtube.com/c/CharlyCimino Encontrá más código en mi repo de
 * GitHub: https://github.com/CharlyCimino
 */
@WebServlet(name = "AppServlet", urlPatterns = {"/app"})
public class AppServlet extends HttpServlet {

    private Modelo model;
    private static final String URI_LIST = "WEB-INF/pages/alumnos/listadoAlumnos.jsp";
    private static final String URI_REMOVE = "WEB-INF/pages/alumnos/borrarAlumno.jsp";
    private static final String URI_EDIT = "WEB-INF/pages/alumnos/editarAlumno.jsp";
    private static final String URI_VISUALIZAR = "WEB-INF/pages/alumnos/visualizarAlumno.jsp";


    @Override
    public void init() throws ServletException {
        this.model = new ModeloMySQL();
    }
   
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String laAccion = req.getParameter("accion");
        String idStr = req.getParameter("id");
        String volver = req.getParameter("volver");
        String nuevoid = req.getParameter("nuevoid");
        String listOrderBy = req.getParameter("listOrderBy");

        int id = (idStr == null ? -1 : Integer.parseInt(idStr));
        laAccion = (laAccion == null ? "" : laAccion);
        volver = (volver == null ? "" : volver);
        nuevoid = (nuevoid == null ? "" : nuevoid);
        listOrderBy = (listOrderBy == null ? "" : listOrderBy);
 
        switch (volver) {
            case "visualizar":
                req.setAttribute("Volver", "?accion=visualizar&id=" + id);
                break;
            default:
                req.setAttribute("Volver", "");
         }
        
        switch (laAccion) {
            case "remove":
                req.setAttribute("alumnoABorrar", model.getAlumno(id));
                req.getRequestDispatcher(URI_REMOVE).forward(req, resp);
                break;
            case "edit":
                Alumno alu = model.getAlumno(id);
                req.setAttribute("alumnoAEditar", alu);
                req.setAttribute("yaTieneFoto", !alu.getFoto().contains("no-face"));
                req.getRequestDispatcher(URI_EDIT).forward(req, resp);
                break;
            case "visualizar":
                Alumno verAlu = model.getAlumno(id);
                req.setAttribute("alumnoAVisualizar", verAlu);
                req.setAttribute("yaTieneFoto", !verAlu.getFoto().contains("no-face"));
                req.getRequestDispatcher(URI_VISUALIZAR).forward(req, resp);
                break;      
            default:
                req.setAttribute("listaAlumnos", model.getAlumnos(listOrderBy));
                req.setAttribute("nuevoid", nuevoid);
                req.getRequestDispatcher(URI_LIST).forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String laAccion = req.getParameter("accion");
        String idStr = req.getParameter("id");
        int idGenerado = 0;

        int id = (idStr == null ? -1 : Integer.parseInt(idStr));
        laAccion = (laAccion == null ? "" : laAccion);

        switch (laAccion) {
            case "delete":                
                model.removeAlumno(id);
                break;
            case "update":
                Alumno aluEditar = model.getAlumno(id);
                cargarAlumnoSegunParams(aluEditar, req);
                model.updateAlumno(aluEditar);
                break;
            case "add":
                Alumno aluAgregar = new Alumno();
                cargarAlumnoSegunParams(aluAgregar, req);
                idGenerado = model.addAlumno(aluAgregar);
                System.out.println(idGenerado);
                break;          
          
        }
        System.out.println(idGenerado);
        resp.sendRedirect(getServletContext().getContextPath() + "/app?nuevoid=" + idGenerado);
    }
    
    private void cargarAlumnoSegunParams(Alumno a, HttpServletRequest request) {
        a.setNombre(request.getParameter("nombre"));
        a.setApellido(request.getParameter("apellido"));
        a.setMail(request.getParameter("mail"));
        a.setFechaNacimiento(request.getParameter("fechaNac"));
        a.setFoto(request.getParameter("fotoBase64"));
    }

}
