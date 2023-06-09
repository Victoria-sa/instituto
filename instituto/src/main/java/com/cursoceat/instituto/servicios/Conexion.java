package cursoceat.instituto.servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;





    public class Conexion {
        private Connection con;
        private  String url;
        private  String user;
        private  String pass;

        public  Connection conector() {
            //Existe Documentacion donde indeica que no necesitas invocar el Driver.Class, pero si  lo necesstias
            //Busca el conector de laBBDD para podet realizar la conexion

            //Estos valores no van a cambiar por lo que podrian ser finaly en los atributos de la clase*
            //Insertar en la URL el utf-8para que reconozca los caracters, o asegurarnos que los reconozca

            url = "jdbc:mysql://localhost:3306/instituto?useUnicode=true&characterEncoding=utf-8";
            user = "root";
            pass = "";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");


                /****/
                con = DriverManager.getConnection(url, user, pass);
                System.out.println("Conexion realizada con exito");

            } catch (SQLException e) {
                e.printStackTrace();
            }catch (ClassNotFoundException e){
                e.printStackTrace();

            }
            return con;

        }
    }


