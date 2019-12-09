/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.bean.Paciente;

/**
 *
 * @author Rivas
 */
public class PacienteDao {

    public static void registrar(Paciente p) {
        String sql = "insert into paciente(nombre, edad, sexo, seguro, distrito_iddistrito) values(?, ?, ?, ?, ?)";

        Connection cn = Conexion.conectar();

        try {
            PreparedStatement stmt = cn.prepareStatement(sql);
            stmt.setString(1, p.getNombre());
            stmt.setInt(2, p.getEdad());
            stmt.setString(3, p.getSexo());
            stmt.setString(4, p.getSeguro());
            stmt.setInt(5, p.getIddistrito());

            int rs = stmt.executeUpdate();

            stmt.close();
            cn.close();
            

        } catch (Exception e) {
            System.out.println("erroe en pacienteDao registrar");
        }

    }

    public static int cantidad() {
        String sql = "SELECT COUNT(*) cantidad FROM paciente";

        Connection cn = Conexion.conectar();
        int cantidad = 0;
        try {
            PreparedStatement stmt = cn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cantidad = rs.getInt("cantidad");
            }
            rs.close();
            stmt.close();
            cn.close();
            return cantidad;
        } catch (Exception e) {
            System.out.println("error en pacienteDao cantidad");
            return -1;
        }

    }

    public static int cantidadSinSeguros() {
        String sql = "SELECT COUNT(*) cantidad FROM paciente where seguro = 's'";

        Connection cn = Conexion.conectar();
        int cantidad = 0;
        try {
            PreparedStatement stmt = cn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cantidad = rs.getInt("cantidad");
            }
            rs.close();
            stmt.close();
            cn.close();
            return cantidad;
        } catch (Exception e) {
            System.out.println("erroe en pacienteDao cantidadSinSeguros");
            return -1;
        }

    }

    public static float porcHosp() {
        float porc = 0;
        try {

            porc = (100 * cantidadSinSeguros()) / cantidad();
            return porc;
        } catch (Exception e) {
            System.out.println("error en pacienteDao porcHosp");
            return -1;
        }

    }

}
