package cursoceat.instituto.modell;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


    public class Alumno {
        private int id;
        private String nombre;
        private  String curso;
        private float media;
        //dar formato a la fecha
        private Date fNacimiento;

        //crear una constante con el tamaño (longitud)de caaracteres del nombre, como esta definida en la base de datos
        private final int TAMNOMBRE=50;
        //creamos el constructor
        public Alumno(int id){
            this.id=id;
        }

        public Alumno(int id, String nombre, String curso, float media, String fNacimiento) throws ParseException {
            this.id = id;
            setNombre(nombre);
            setCurso(curso);
            this.media = media;
            //dams formato a la fecha haciendo un casteo, la leemos en string la guardamos en date, y para enviarlo
            //necesitamos javaSql
            SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd");
            //, puedee dar error pero se le agrega un parseExcepcion.
            this.fNacimiento = formato.parse(fNacimiento);
        }
        //Creamos un constructor sin el id
        public Alumno( String nombre, String curso, float media, String fNacimiento) throws ParseException {

            setNombre(nombre);
            setCurso(curso);
            this.media = media;
            //dams formato a la fecha haciendo un casteo, la leemos en string la guardamos en date, y para enviarlo
            //necesitamos javaSql
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            //, puedee dar error pero se le agrega un parseExcepcion.
            this.fNacimiento = formato.parse(fNacimiento);
        }
        //metodo para delimitar caracteres del nombre ->50 caracteres
        public void setNombre(String nombre){
            //utilizando la constante definida TAMNOMBRE -> atraves de la funcio math pasrle el min
            //este metodo lo que hace es limitar la longitud de la cadena que se pasa
            this.nombre=nombre.substring(0,Math.min(TAMNOMBRE,nombre.length()));
            //Math.min(50,65) -> 0.50
        }
        //metodos para delimitar del curso->2 caracteres
        public void setCurso(String curso){
            this.curso=curso.substring(0,Math.min(2,curso.length()));
        }
        public void  setMedia(){

        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public float getMedia() {
            return media;
        }

        public void setMedia(float media) {
            this.media = media;
        }

        public Date getfNacimiento() {
            return fNacimiento;
        }

        public void setfNacimiento(Date fNacimiento) {
            this.fNacimiento = fNacimiento;
        }

        public String getNombre() {
            return nombre;
        }

        public String getCurso() {
            return curso;
        }

        @Override
        public String toString() {
            return "Alumno{" +
                    "id=" + id +
                    ", nombre='" + nombre + '\'' +
                    ", curso='" + curso + '\'' +
                    ", media=" + media +
                    ", fNacimiento=" + fNacimiento +
                    ", TAMNOMBRE=" + TAMNOMBRE +
                    '}';
        }
    }


