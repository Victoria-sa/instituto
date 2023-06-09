<%--
  Created by IntelliJ IDEA.
  User: PROGRAMACION
  Date: 09/06/2023
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <title>Instituto</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" >Instituto de Alumnos</a>
    <ul class="nav nav-pills nav-fill">
      <li class="nav-item mx-4 px-4">
        <a class="btn btn-outline-dark" aria-current="page" href="Controller?opcion=listar">Listar</a>
      </li>
      <li class="nav-item mx-4 px-4">
        <a class="btn btn-outline-dark" href="alta.jsp">Nuevo Alumno</a>
      </li>
    </ul>
    <form class="d-flex" role="search" action="Controller">
      <input type="hidden" name="opcion" value="buscar">
      <input class="form-control me-2" type="search" placeholder="Buscar" aria-label="Search" name="nombreBusc">
      <button class="btn btn-outline-dark" type="submit" name="search">Buscar</button>
    </form>
   </div>
</nav>

</body>
</html>
