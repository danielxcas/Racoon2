package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public Cliente buscar(String dni){
        Cliente c = new Cliente();
    String sql="Select * from cliente where Dni="+dni;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
                c.setIdCliente(rs.getInt(1));
                c.setDni(rs.getString(2));
                c.setNombres(rs.getString(3));
                c.setDireccion(rs.getString(4));
                c.setEstado(rs.getString(5));
                
            }
        } catch (Exception e) {
        }
        return c;
    };
    
    // Método para validar cliente (si es necesario)
    public Cliente validar(String dni) {
        Cliente cl = new Cliente();
        String sql = "select* from cliente where Dni=?";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, dni);
            rs = ps.executeQuery();
            while (rs.next()) {
                cl.setIdCliente(rs.getInt("IdCliente"));
                cl.setDni(rs.getString("Dni"));
                cl.setNombres(rs.getString("Nombres"));
                cl.setDireccion(rs.getString("Direccion"));
                cl.setEstado(rs.getString("Estado"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cl;
    }

    // Método para listar todos los clientes
    public List<Cliente> listar() {
        String sql = "select * from cliente";
        List<Cliente> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cl = new Cliente();
                
                cl.setIdCliente(rs.getInt(1));
                cl.setDni(rs.getString(2));
                cl.setNombres(rs.getString(3));
                cl.setDireccion(rs.getString(4));
                cl.setEstado(rs.getString(5));
                lista.add(cl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Método para agregar un nuevo cliente
    public int agregar(Cliente cl) {
        String sql = "insert into cliente(Dni, Nombres, Direccion, Estado) values(?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNombres());
            ps.setString(3, cl.getDireccion());
            ps.setString(4, cl.getEstado());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    // Método para listar un cliente por ID
    public Cliente listarId(int id) {
        Cliente cl = new Cliente();
        String sql = "select * from cliente where IdCliente=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cl.setIdCliente(rs.getInt(1));
                cl.setDni(rs.getString(2));
                cl.setNombres(rs.getString(3));
                cl.setDireccion(rs.getString(4));
                cl.setEstado(rs.getString(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cl;
    }

    // Método para actualizar un cliente
    public int actualizar(Cliente cl) {
        String sql = "update cliente set Dni=?, Nombres=?, Direccion=?, Estado=? where IdCliente=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNombres());
            ps.setString(3, cl.getDireccion());
            ps.setString(4, cl.getEstado());
            ps.setInt(5, cl.getIdCliente());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    // Método para eliminar un cliente
    public void delete(int id) throws SQLException {
        String sql = "delete from cliente where IdCliente=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
