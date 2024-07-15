<%-- 
    Document   : Principal
    Created on : 13 jul 2024, 17:50:35
    Author     : axele
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <style>
            .navbar-custom {
                background-color: #f12200;
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light navbar-custom">


            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Home</a>
                </li>
                <li class="nav-item">
                    <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="Controlador?menu=Producto" target="myFrame">Producto</a>
                </li>
                <li class="nav-item">
                    <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="Controlador?menu=Empleado&accion=Listar" target="myFrame">Empleado</a>
                </li>
                <li class="nav-item">
                    <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="Controlador?menu=Clientes" target="myFrame">Clientes</a>
                </li>
                <li class="nav-item">
                    <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="Controlador?menu=Venta&accion=default" target="myFrame">Nueva Venta</a>
                </li>
            </ul>
            <form class="d-flex" role="search" action="Validar" method="POST"> 
                <div class="dropdown">
                    <button style="border: none"class="btn btn-outline-light dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        ${usuario.getNombres()}
                    </button>
                    <ul class="dropdown-menu text-center">
                        <li><a class="dropdown-item" href="#">
                                <img src="img/user1.png" alt="Imagen de usuario" width="60"/>
                            </a></li>

                        <li><a class="dropdown-item" href="#">${usuario.getUser()}</a></li>
                        <li><a class="dropdown-item" href="#">usuario@gmail</a></li>
                        <div class="dropdown-divider"></div>                           
                        <input type="submit" name="accion" value="SALIR" class="btn btn-primary w-75">                           
                    </ul>
                </div>
            </form>


        </nav>
        <div class="m-4" style="height: 550px">
            <iframe name="myFrame" style="height: 100%; width: 100%">

            </iframe>

        </div>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
    </body>
</html>

