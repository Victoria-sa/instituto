package cursoceat.instituto.servicios;




import cursoceat.instituto.modell.Alumno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/*
La clase AlummoDAO es un patron(convencion)donde se realiza una serie de metodos que van a ser utilizados para
acceder a la BBDD: lister, buscar, borrar, inserter...etc)
Como necesita acceder a la BBDD hereda de Conexion
 */
public class AlumnoDAO extends Conexion {
    String sql;

    /**
     * El metodo create insertar n nuevo alumno en la BBDD
     **/
    public void create(Alumno a) {
        Connection con = conector();
        sql = "INSERT INTO alumnos (nombre,curso,media,fnac) VALUES (?,?,?,?);";
        /*Tenemos que capturar un posible error al generar la consulta por medio de un try Casth
         */
        try {
            escribir(a,con,sql,"create");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Crear alumno segun su id

    }

    public Alumno read(int id) {
        Alumno a = null;
        sql = "SELECT * FROM alumnos WHERE cod=?;";

        try {
            Connection con = conector();
            PreparedStatement pt = con.prepareStatement(sql);
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                Float media = rs.getFloat("media");
                String curso = rs.getString("curso");
                String fnac = rs.getString("fnac");
                a = new Alumno(id, nombre, curso, media, fnac);//se envia el constructor con el id
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException();
        }
        return a;
    }
    /**
     * Este metodo escribir reemplaza el codigo es duplicado entre crear y actualizar
     * @param a
     * @param con
     * @param sql
     * @param opcion
     * @throws SQLException
     */
    public void escribir(Alumno a,Connection con, String sql,String opcion) throws  SQLException{
        PreparedStatement pt = con.prepareStatement(sql);
        //asigno la secuencia sql los valores que son pasados en el objeto alumno

        pt.setString(1, a.getNombre());
        pt.setString(2, a.getCurso());
        pt.setFloat(3, a.getMedia());

            /*la fecha de nacimiento la recibimos como tipo java.util.date la debemos convertir(castaer)
            a java.sql.date
             */
        java.sql.Date sqlDate = new java.sql.Date(a.getfNacimiento().getTime());
        pt.setDate(4, sqlDate);
        if(opcion.equals("update")){
            pt.setInt(5,a.getId());
        }
        pt.executeUpdate();
    }
    public void update(Alumno a) {
        if (a!=null){
            sql = "UPDATE alumnos SET nombre=?,curso=?, media=?,fnac=? Where cod=?;";
            try {
                Connection con = conector();
                escribir(a,con,sql,"update");
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
    public void delete(int id){
        sql="DELETE FROM alumnos Where alumnos. cod=?;";
        try{
            Connection con =conector();
            PreparedStatement pt=con.prepareStatement(sql);
            pt.setInt(1,id);
            pt.executeUpdate();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    /**
     * Creamos una lista de todos los datos de la tabla
     */
    public List<Alumno> readAll() throws ParseException{
        List<Alumno> lista=new ArrayList<>();
        sql="SELECT * FROM alumnos;";
        try{
            Connection con=conector();
            PreparedStatement pt=con.prepareStatement(sql);
            ResultSet rs=pt.executeQuery();
            //recorrremos los resultados, creamos un objeto alumno por interaccion y lo agregamos a la lista
            while(rs.next()){
                String nombre=rs.getString("nombre");
                int id= rs.getInt("cod");
                String curso=rs.getString("curso");
                float media=rs.getFloat("media");
                String fnac=rs.getString("fnac");

                Alumno a=new Alumno(id,nombre,curso,media,fnac);
                lista.add(a);
            }
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }
    //Metodo para buscar un ALumno a traves del curso pero serviria para buscar cualquier atributo
    public List<Alumno> buscarCurso(String curso)throws ParseException{
        sql="SELECT * FROM alumnos  Where curso=?;";
        List<Alumno> listaC= new ArrayList<>();
        try {
            Connection con = conector();
            PreparedStatement pt = con.prepareStatement(sql);
            pt.setString(1,curso);

            ResultSet rs=pt.executeQuery();
            while(rs.next()){
                String nombre=rs.getString("nombre");
                int id= rs.getInt("cod");
                float media=rs.getFloat("media");
                String fnac=rs.getString("fnac");

                Alumno a=new Alumno(id,nombre,curso,media,fnac);
                listaC.add(a);
            }
            con.close();
        }catch (SQLException e){
            e.printStackTrace();

        }
        return listaC;
    }



}
