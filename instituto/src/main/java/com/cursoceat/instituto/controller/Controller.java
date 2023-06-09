package cursoceat.instituto.controller;

import cursoceat.instituto.modell.Alumno;
import cursoceat.instituto.servicios.AlumnoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "Controller", value = "/Controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    //variable global que guardara los mensajes tanto de exito como erro al front-end
    String msnExito,msnError;
    AlumnoDAO alumnoDAO = new AlumnoDAO();
    String opcion= "";
    int id=0;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        opcion = request.getParameter("opcion");
        System.out.println(opcion);//mania de IRINA
        if (!opcion.isEmpty()) {
            try {
                switch (opcion) {
                    case "listar": {
                        listar(request, response);
                        break;
                    }
                   case "alta":{
                       alta(request, response);
                    break;
                }case "modificar":
                    case "eliminar":{

                        id = Integer.parseInt(request.getParameter("cod"));
                        if (opcion.equals("eliminar")) {
                            eliminar(request, response);
                        }else {
                            modificar(request,response);
                        }
                        break;
                    }

                    case "ejecutaModificacion":{
                        ejecutaModificacion(request,response);
                        break;
                    }
                    case "buscar":{
                        String nombreBusq=request.getParameter("nombreBusq");
                        System.out.println(nombreBusq);
                        //crear en Dao busqueda por nombre / por curso / media
                        break;
                    }
                    default: {
                        System.out.println("opcion invalida");
                    }
                }
            } catch (ParseException e) {
                throw  new  RuntimeException(e);
            }

            }
        //idependientemente la opcion que reciba, va al index
        request.getRequestDispatcher("index.jsp").forward(request, response);


        }
        public void doPost (HttpServletRequest request, HttpServletResponse response) throws
        IOException, ServletException {
           // String nombre = request.getParameter("nombre");
           // String curso = request.getParameter("curso");
          //  String fnac = request.getParameter("fnac");
          //  float media = Float.parseFloat(request.getParameter("media"));

          //  System.out.println("Nombre " + nombre);
          //  System.out.println("Curso  " + curso);
          //  System.out.println("Fecha de Nacimiento " + fnac);
          //  System.out.println("Nota Media : " + media);


        }
    protected void listar(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ParseException, ServletException {

        List<Alumno> alumnos = alumnoDAO.readAll();
        request.getSession().setAttribute("listaA", alumnos);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }





    protected void alta(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ParseException, ServletException {

        String nombre = request.getParameter("nombre");
        String curso = request.getParameter("curso");
        String fnac = request.getParameter("fnac");
        float media = Float.parseFloat(request.getParameter("media"));
        try {
            //aqui deberiamos recibir si la escritura es correcta o no, por medio de un booelano
            Alumno a = new Alumno(nombre, curso, media, fnac);
            alumnoDAO.create(a);
            msnExito = "Alumno creado correctamente";
            request.setAttribute("msnExito", msnExito);
        } catch (Exception e) {
            msnError = "Error en la nueva alta";
            request.setAttribute("msnError", msnError);
        }finally {
            listar(request,response);
        }

    }

    protected void modificar(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ParseException, ServletException {
        Alumno a = alumnoDAO.read(id);
        System.out.println(a);
        request.setAttribute("id", a.getId());
        request.setAttribute("nombre", a.getNombre());
        request.setAttribute("curso", a.getCurso());
        request.setAttribute("media",a.getMedia());

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = date.format(a.getfNacimiento());
        System.out.println(fecha);

        request.setAttribute("fnac",fecha);
        request.getRequestDispatcher("modificar.jsp").forward(request,response);
    }
    protected  void  ejecutaModificacion(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ParseException, ServletException {
        int id=Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String curso = request.getParameter("curso");
        String fnac = request.getParameter("fnac");
        float media = Float.parseFloat(request.getParameter("media"));
        try{
            Alumno a= new Alumno(id,nombre,curso, media, fnac);
            alumnoDAO.update(a);
            msnExito = "Alumno Modificado correctamente";
            request.setAttribute("msnExito", msnExito);
        }catch (Exception e){
            msnError="Un error ha ocurrido al intentar modificar el registro";
            request.setAttribute("msnError", msnError);
        }  finally {
            listar(request, response);
        }

    }
    protected void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ParseException, ServletException {
        try {
            alumnoDAO.delete(id);
            msnExito = "Alumno Eliminado correctamente";
            request.setAttribute("msnExito", msnExito);
        } catch (Exception e) {
            msnError = "Error al borrar el alumno # " + id;
            request.setAttribute("msnError", msnError);
        } finally {
            listar(request, response);
        }
    }

}