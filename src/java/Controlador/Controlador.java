package Controlador;

import Config.GenerarSerie;
import Modelo.Cliente;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.ClienteDAO;
import Modelo.Producto;
import Modelo.ProductoDAO;
import Modelo.Venta;
import Modelo.VentaDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controlador extends HttpServlet {

    Empleado em = new Empleado();
    EmpleadoDAO edao = new EmpleadoDAO();
    Cliente c = new Cliente();
    ClienteDAO cdao = new ClienteDAO();
    Producto p = new Producto();
    ProductoDAO pdao = new ProductoDAO();

    int ide;
    int idc;
    int idp;

    Venta v = new Venta();
    List<Venta> lista = new ArrayList<>();
    int item;
    int cod;
    String descripcion;
    double precio;
    int cantidad;
    double subtotal;
    double totalPagar;

    String numeroserie;
    VentaDAO vdao = new VentaDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");

        String accion = request.getParameter("accion");

        if (menu.equals("Principal")) {
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }
        if (menu.equals("Empleado")) {

            switch (accion) {
                case "Listar":
                    List lista = edao.listar();
                    request.setAttribute("empleados", lista);
                    break;
                case "Agregar":
                    String dni = request.getParameter("txtDni");
                    String nom = request.getParameter("txtNombres");
                    String tel = request.getParameter("txtTel"); // Cambiado a txtTel
                    String est = request.getParameter("txtEstado");
                    String user = request.getParameter("txtUsuario");
                    em.setDni(dni);
                    em.setNombres(nom);
                    em.setTel(tel); // Asegúrate de que este método exista y esté configurado correctamente en tu modelo
                    em.setEstado(est);
                    em.setUser(user);
                    edao.agregar(em);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    Empleado e = edao.listarId(ide);
                    request.setAttribute("empleado", e);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String dni1 = request.getParameter("txtDni");
                    String nom1 = request.getParameter("txtNombres");
                    String tel1 = request.getParameter("txtTel"); // Cambiado a txtTel
                    String est1 = request.getParameter("txtEstado");
                    String user1 = request.getParameter("txtUsuario");
                    em.setDni(dni1);
                    em.setNombres(nom1);
                    em.setTel(tel1); // Asegúrate de que este método exista y esté configurado correctamente en tu modelo
                    em.setEstado(est1);
                    em.setUser(user1);
                    em.setId(ide);
                    edao.actualizar(em);

                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Delete":
                    ide = Integer.parseInt(request.getParameter("id"));
                     {
                        try {
                            edao.delete(ide);
                        } catch (SQLException ex) {
                            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;

                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Empleado.jsp").forward(request, response);
        }
        if (menu.equals("Clientes")) {
            request.getRequestDispatcher("Clientes.jsp").forward(request, response);
        }
        if (menu.equals("Producto")) {
            request.getRequestDispatcher("Producto.jsp").forward(request, response);
        }
        if (menu.equals("Venta")) {
            switch (accion) {
                case "BuscarCliente":
                    String dni = request.getParameter("codigocliente");
                    c.setDni(dni);
                    c = cdao.buscar(dni);
                    request.setAttribute("c", c);
                    request.getRequestDispatcher("Venta.jsp").forward(request, response);
                    break;
                case "BuscarProducto":
                    int id = Integer.parseInt(request.getParameter("codigoproducto"));
                    p = pdao.listarId(id);
                    request.setAttribute("c", c);
                    request.setAttribute("producto", p);
                    request.setAttribute("lista", lista);
                    request.setAttribute("totalpagar", totalPagar);
                    request.getRequestDispatcher("Venta.jsp").forward(request, response);
                    break;
                case "Agregar":
    try {
                    request.setAttribute("nserie", numeroserie);
                    request.setAttribute("c", c);
                    double totalPagar = 0.0;
                    item = item + 1;
                    cod = p.getIdProducto();
                    descripcion = request.getParameter("nombreproducto");

                    String precioStr = request.getParameter("precio");
                    String cantidadStr = request.getParameter("cant");

                    if (precioStr != null && !precioStr.isEmpty() && cantidadStr != null && !cantidadStr.isEmpty()) {
                        precio = Double.parseDouble(precioStr);
                        cantidad = Integer.parseInt(cantidadStr);
                        subtotal = precio * cantidad;

                        v = new Venta();
                        v.setItem(item);
                        v.setIdproducto(cod);
                        v.setDescripcionP(descripcion);
                        v.setPrecio(precio);
                        v.setCantidad(cantidad);
                        v.setSubtotal(subtotal);
                        lista.add(v);

                        for (Venta venta : lista) {
                            totalPagar += venta.getSubtotal();
                        }

                        request.setAttribute("totalpagar", totalPagar);
                        request.setAttribute("lista", lista);
                    } else {
                        // Manejar el caso en que los parámetros son nulos o vacíos
                        request.setAttribute("error", "Los campos de precio y cantidad son obligatorios.");
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Formato de precio o cantidad inválido.");
                }
                request.getRequestDispatcher("Venta.jsp").forward(request, response);
                break;

                case "GenerarVenta":
                    // ACTUALIZAR STOCK
                    for (int i = 0; i < lista.size(); i++) {
                        Producto pr = new Producto();
                        int cantidad = lista.get(i).getCantidad();
                        int idproducto = lista.get(i).getIdproducto();
                        ProductoDAO aO = new ProductoDAO();
                        pr = aO.buscar(idproducto);
                        if (pr != null && pr.getIdProducto() != 0) {
                            int sac = pr.getStock() - cantidad;
                            aO.actualizarStock(idproducto, sac);
                        } else {
                            System.out.println("Producto no encontrado con id: " + idproducto);
                        }
                    }
                    // Asegúrate de que `totalPagar` sea una variable de instancia
                    totalPagar = 0.0;

                    // Calcular el total a pagar
                    for (Venta venta : lista) {
                        totalPagar += venta.getPrecio() * venta.getCantidad();
                    }

                    // Generar número de serie y guardar venta
                    v.setIdcliente(c.getIdCliente());
                    v.setIdempleado(2);
                    v.setNumserie(numeroserie);
                    v.setFecha("2024-06-14");
                    v.setMonto(totalPagar); // Asegúrate de que totalPagar se actualice correctamente antes de esta línea
                    v.setEstado("1");
                    vdao.guardarVenta(v);

                    // Obtener el ID de la venta recién insertada
                    int idv = Integer.parseInt(vdao.IdVentas());

                    // Guardar detalles de la venta
                    for (Venta venta : lista) {
                        Venta detalleVenta = new Venta();
                        detalleVenta.setId(idv);
                        detalleVenta.setIdproducto(venta.getIdproducto());
                        detalleVenta.setCantidad(venta.getCantidad());
                        detalleVenta.setPrecio(venta.getPrecio());
                        vdao.guardarDetaleventas(detalleVenta);
                    }

                    // Redireccionar a la página de ventas
                    request.setAttribute("mensaje", "Venta generada correctamente");
                    request.getRequestDispatcher("Venta.jsp").forward(request, response);
                    break;

                default:
                    numeroserie = vdao.GenerarSerie();
                    if (numeroserie == null || numeroserie.isEmpty()) {
                        numeroserie = "00000001";
                        request.setAttribute("nserie", numeroserie);
                    } else {
                        try {
                            int incrementar = Integer.parseInt(numeroserie);
                            GenerarSerie gs = new GenerarSerie();
                            numeroserie = gs.NumeroSerie(incrementar);
                            request.setAttribute("nserie", numeroserie);
                        } catch (NumberFormatException e) {
                            // Manejar la excepción si la conversión falla
                            System.out.println("Error al convertir numeroserie a entero: " + numeroserie);
                            request.setAttribute("error", "Número de serie inválido: " + numeroserie);
                        }
                    }

                    request.getRequestDispatcher("Venta.jsp").forward(request, response);
            }

        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
