<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Venta</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head>
    <body>
        <div class="container mt-5">
            <div class="row">
                <div class="col-sm-6">
                    <div class="card">
                        <form action="Controlador" method="post">
                            <div class="card-body">
                                <h5 class="card-title">Datos del Cliente</h5>
                                <form action="${pageContext.request.contextPath}/Controlador" method="post">
                                    <input type="hidden" name="menu" value="Venta">
                                    <div class="row mb-3">
                                        <div class="col">
                                            <input type="text" name="codigocliente" value="${c != null ? c.dni : ''}" class="form-control" placeholder="Dni">
                                        </div>
                                        <div class="col-auto">
                                            <input type="submit" name="accion" value="BuscarCliente" class="btn btn-outline-info">
                                        </div>
                                        <div class="col">
                                            <input type="text" name="nombrescliente" value="${c != null ? c.nombres : ''}" class="form-control" placeholder="Nombre">
                                        </div>
                                    </div>
                                    <h5 class="card-title">Datos Producto</h5>
                                    <div class="row mb-3">
                                        <div class="col">
                                            <input type="text" name="codigoproducto" value="${producto.getIdProducto()}"class="form-control" placeholder="Código Producto">
                                        </div>
                                        <div class="col-auto">
                                            <input type="submit" name="accion" value="BuscarProducto" class="btn btn-outline-info">
                                        </div>
                                        <div class="col">
                                            <input type="text" name="nombreproducto" value="${producto.getNombres()}" class="form-control" placeholder="Nombre Producto">
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col">
                                            <input type="text" name="precio" value="${producto.getPrecio()}" class="form-control" placeholder="s/.0.00">
                                        </div>
                                        <div class="col">
                                            <input type="number" name="cant" value="1" class="form-control" placeholder="Cantidad">
                                        </div>
                                        <div class="col">
                                            <input type="text" name="stock" value="${producto.getStock()}" class="form-control" placeholder="Stock">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <button type="submit" name="accion" value="Agregar" class="btn btn-outline-info">Agregar</button>
                                    </div>
                                </form>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="card">
                        <div class="card-body">
                            <div class="d-flex col-sm-6 ml-auto">
                                <label style="margin: 5px">NumeroSerie</label>
                                <input type="text" name="NroSerie" value="${nserie}" class="form-control">
                            </div>

                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Nro</th>
                                        <th>codigo</th>
                                        <th>Descripcion</th>
                                        <th>Precio</th>
                                        <th>Cantidad</th>
                                        <th>Subtotal</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="list" items="${lista}">
                                    <tr>
                                        <td>${list.getItem()}</td>
                                        <td><${list.getIdproducto()}/td>
                                        <td>${list.getDescripcionP()}</td>
                                        <td>${list.getPrecio()}</td>
                                        <td>${list.getCantidad()}</td>
                                        <td>${list.getSubtotal()}</td>
                                        <td class="d-flex">
                                            <a class="btn btn-warning">Editar</a>
                                            <a class="btn btn-danger">Delete</a>
                                            
                                        </td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="card-footer d-flex">
                            <div class="col-sm-6">
                                <a href="Controlador?menu=Venta&accion=GenerarVenta" class="btn btn-success">generar venta</a>
                                
                                <input type="submit" name="accion" value="Cancelar" class="btn btn-danger">
                            </div>
                            <div class="col-sm-4 ml-auto">
                                <input type="text" name="txttotal" value="S/. ${totalpagar}" class="form-control text-center">
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
    </body>
</html>
