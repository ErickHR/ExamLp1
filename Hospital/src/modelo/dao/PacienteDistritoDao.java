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
import java.util.ArrayList;
import modelo.bean.Distrito;
import modelo.bean.Paciente;
import modelo.bean.PacienteDistrito;

/**
 *
 * @author Rivas
 */
public class PacienteDistritoDao {
    public static ArrayList<PacienteDistrito> listar(){
        String sql = "SELECT p.nombre, p.edad, p.seguro, d.nombre distrito FROM paciente p, distrito d WHERE p.distrito_iddistrito = d.iddistrito and seguro = 's'";
        ArrayList<PacienteDistrito> lista = new ArrayList<>();
        PacienteDistrito padi = null;
        Paciente pa = null;
        Distrito di = null;
        Connection cn = Conexion.conectar();
        
        try {
            PreparedStatement stmt = cn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while ( rs.next() ) {   
                padi = new PacienteDistrito();
                pa = new Paciente();
                di = new Distrito();
                pa.setNombre(rs.getString("nombre"));
                pa.setEdad(rs.getInt("edad"));
                pa.setSeguro(rs.getString("seguro"));
                di.setNombre(rs.getString("distrito"));
                
                padi.setDistrito(di);
                padi.setPaciente(pa);
                
                lista.add(padi);
            }
            
            return lista;
            
        } catch (Exception e) {
            System.out.println("error en pacientedistritoDao listar");
            return null;
        }
        
    }
    
}
