<%-- 
    Document   : Empleado
    Created on : 13 jul 2024, 22:58:50
    Author     : axele
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Empleado</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head>
    <body>
        <div class="d-flex" style="margin: 10px">
            <div class="card col-sm-4">
                <div class="card-body">
                    <form action="Controlador?menu=Empleado" method="POST">
                        <div class="form-group" style="margin-bottom: 5px; margin-top: 5px ">
                            <label>DNI</label>
                            <input type="text" value="${empleado.getDni()}" name="txtDni" class="form-control">
                        </div>
                        <div class="form-group" style="margin-bottom: 5px; margin-top: 5px ">
                            <label>Nombres</label>
                            <input type="text" value="${empleado.getNombres()}"name="txtNombres" class="form-control" style="margin-bottom: 5px; margin-top: 5px ">
                        </div>
                        <div class="form-group" style="margin-bottom: 5px; margin-top: 5px ">
                            <label>Telefono</label>
                            <input type="text" value="${empleado.getTel()}" name="txtTel" class="form-control" style="margin-bottom: 5px; margin-top: 5px ">
                        </div>
                        <div class="form-group" style="margin-bottom: 5px; margin-top: 5px ">
                            <label>Estado</label>
                            <input type="text" value="${empleado.getEstado()}" name="txtEstado" class="form-control" style="margin-bottom: 5px; margin-top: 5px ">
                        </div>
                        <div class="form-group" style="margin-bottom: 5px; margin-top: 5px ">
                            <label>Usuario</label>
                            <input type="text" value="${empleado.getUser()}" name="txtUsuario" class="form-control"style="margin-bottom: 5px; margin-top: 5px ">
                        </div>
                        <input type="submit" name="accion" value="Agregar" class="btn btn-info" style="margin-bottom: 5px; margin-top: 5px ">
                        <input type="submit" name="accion" value="Actualizar" class="btn btn-success" style="margin-bottom: 5px; margin-top: 5px ; margin-left: 20px">
                    </form>
                </div>
            </div>
            <div class="col-sm-8" style="margin-left: 10px">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <<th>ID</th>
                            <th>DNI</th>
                            <th>Nombres</th>
                            <th>Telefono</th>
                            <th>Estado</th>
                            <th>User</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="em" items="${empleados}">
                            <tr>
                                <td>${em.getId()}</td>
                                <td>${em.getDni()}</td>
                                <td>${em.getNombres()}</td>
                                <td>${em.getTel()}</td>
                                <td>${em.getEstado()}</td>
                                <td>${em.getUser()}</td>
                                <td>
                                    <a class="btn btn-warning" href="Controlador?menu=Empleado&accion=Editar&id=${em.getId()}">Editar</a>
                                    <a class="btn btn-danger" href="Controlador?menu=Empleado&accion=Delete&id=${em.getId()}">Eliminar</a>
                                </td>
                            </tr>

                        </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>




        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>

    </body>
</html>
