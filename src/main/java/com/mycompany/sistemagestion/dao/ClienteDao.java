/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemagestion.dao;

import com.mycompany.sistemagestion.models.Cliente;
import com.mysql.jdbc.StringUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jimen
 */
public class ClienteDao {
    
    public Connection conectar() {
        
        String baseDeDatos = "java";
        String usuarios = "root";
        String password = "";
        String hosting = "localhost";
        String port = "3306";
        String driver = "com.mysql.jdbc.Driver";
        String conexionUrl = "jdbc:mysql://" + hosting + ":" + port + "/" + baseDeDatos + "?useSSL=false";

        //Guardado de conexion en variable
        Connection conexion = null;
        List<Cliente> listado = new ArrayList<>();
        /*try intentar catch capturar en  caso que exista algun error va a mostrar 
        lo que est{a en catch en la linea try est{a haciendo la conexion
         */
        
        try {
            //se le est{a indicando que se est{a yutilizando el driver de MYSQL
            Class.forName(driver);
            
            conexion = DriverManager.getConnection(conexionUrl, usuarios, password);
            
        } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexion;
        
    }
    
    public void agregar(Cliente cliente) {

        /*try intentar catch capturar en  caso que exista algun error va a mostrar 
        lo que est{a en catch en la linea try est{a haciendo la conexion
         */
        try {
            Connection conexion = conectar();
            //se le esta indicando que se est{a yutilizando el driver de MYSQL
            /*Class.forName(driver);

            conexion = DriverManager.getConnection(conexionUrl, usuarios, password);
             */
            
            String sql = "INSERT INTO `clientes` (`id`, `nombre`, `apellido`, `telefono`, `email`) VALUES (NULL, '" + cliente.getNombre() + "', '" + cliente.getApellido() + "', '" + cliente.getTelefono() + "', '" + cliente.getEmail() + "')";
            Statement statement = conexion.createStatement();
            statement.execute(sql);
        } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public List<Cliente> listar() {
        
        List<Cliente> listado = new ArrayList<>();
        /*try intentar catch capturar en  caso que exista algun error va a mostrar 
        lo que est{a en catch en la linea try est{a haciendo la conexion
         */
        
        try {
            
            Connection conexion = conectar();
            /*se le est{a indicando que se est{a yutilizando el driver de MYSQL
            Class.forName(driver);

            conexion = DriverManager.getConnection(conexionUrl, usuarios, password);
             */
            String sql = "SELECT * FROM `clientes`";
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery(sql);

            //recorrer cada una de la fila , ccada iteracion que da es una fila de la  tabla 
            while (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(resultado.getString("id"));
                cliente.setNombre(resultado.getString("nombre"));
                cliente.setApellido(resultado.getString("apellido"));
                cliente.setTelefono(resultado.getString("telefono"));
                cliente.setEmail(resultado.getString("email"));
                listado.add(cliente);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listado;
    }
    
    public void actualizar(Cliente cliente) {
        
        try {
            
            Connection conexion = conectar();

            /*se le est{a indicando que se est{a yutilizando el driver de MYSQL
            Class.forName(driver);

            conexion = DriverManager.getConnection(conexionUrl, usuarios, password);
             */
            String sql = "UPDATE `clientes` SET `nombre` = '" + cliente.getNombre() + "', `apellido` = '" + cliente.getApellido() + "', `telefono` = '" + cliente.getTelefono() + "', `email` = '" + cliente.getEmail() + "' WHERE `clientes`.`id` = " + cliente.getId() + "";
            
            Statement statement = conexion.createStatement();
            statement.execute(sql);

            //recorrer cada una de la fila , ccada iteracion que da es una fila de la  tabla 
        } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void eliminar(String id) {
        
        try {
            
            Connection conexion = conectar();

            /*se le est{a indicando que se est{a yutilizando el driver de MYSQL
            Class.forName(driver);

            conexion = DriverManager.getConnection(conexionUrl, usuarios, password);
             */
            String sql = "DELETE FROM clientes WHERE `clientes`.`id` =  " + id;
            
            Statement statement = conexion.createStatement();
            statement.execute(sql);

            //recorrer cada una de la fila , ccada iteracion que da es una fila de la  tabla 
        } catch (Exception ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void guardar(Cliente cl) {
        
        if (StringUtils.isEmptyOrWhitespaceOnly(cl.getId())) {
            
            agregar(cl);
            
            
        } else {
            
            actualizar(cl);
            
        }
        
    }
    
}
