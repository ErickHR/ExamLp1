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

/**
 *
 * @author Rivas
 */
public class DistritoDao {
    public static ArrayList<Distrito> listar(){
        String sql = "select * from distrito";
        ArrayList<Distrito> lista = new ArrayList<>();
        Distrito dis = null;
        Connection cn = Conexion.conectar();
        
        try {
            PreparedStatement stmt = cn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while ( rs.next() ) {
                dis = new Distrito();
                dis.setNombre(rs.getString("nombre"));
                lista.add(dis);
            }
            
            rs.close();
            stmt.close();
            cn.close();
            
            return lista;
            
        } catch (Exception e) {
            System.out.println("erroe en distritoDao listar");
            return null;
        }
        
    }
    
    public static ArrayList<Distrito> listarNoViven(){
        String sql = "select d.nombre from distrito d left JOIN  paciente p on d.iddistrito = p.distrito_iddistrito where p.distrito_iddistrito is null";
        ArrayList<Distrito> lista = new ArrayList<>();
        Distrito dis = null;
        Connection cn = Conexion.conectar();
        
        try {
            PreparedStatement stmt = cn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while ( rs.next() ) {
                dis = new Distrito();
                dis.setNombre(rs.getString("nombre"));
                lista.add(dis);
            }
            
            rs.close();
            stmt.close();
            cn.close();
            
            return lista;
            
        } catch (Exception e) {
            System.out.println("erroe en distritoDao listar");
            return null;
        }
        
    }
    
}
