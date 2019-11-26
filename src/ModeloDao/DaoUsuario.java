/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDao;

import ModeloBeans.BeansUsuario;
import modeloConection.ConexãoBD;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class DaoUsuario {
     ConexãoBD Conex = new ConexãoBD();
    BeansUsuario mode = new BeansUsuario();

    public void Salvar(BeansUsuario mode) {
        Conex.conexao();

        try {
            PreparedStatement pst = Conex.con.prepareStatement("insert into usuarios (NomeUser, SenhaUser, TipoUser) Values (?,?,?)");
            pst.setString(1, mode.getNomeUser());
            pst.setString(2, mode.getSenhaUser());
            pst.setString(3, mode.getTipoUser());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Usuário inserido com sucesso");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir Usuário" + e.getMessage());
        }
        Conex.desconecta();
   
   
    }


    public BeansUsuario BuscarUsuario(BeansUsuario mode) {
        Conex.conexao();
        Conex.executaSql("Select * from usuarios NomeUser where NomeUser like '%" + mode.getPesquisaUser()+ "%'");
        try {
            Conex.rs.first();
            mode.setCodUser(Conex.rs.getInt("usu_cod"));
            mode.setNomeUser(Conex.rs.getString("usu_nome"));
            mode.setTipoUser(Conex.rs.getString("usu_senha"));
            mode.setSenhaUser(Conex.rs.getString("usu_tipo"));
        } catch (SQLException ex) {
            Logger.getLogger(DaoMedico.class.getName()).log(Level.SEVERE, null, ex);
         JOptionPane.showMessageDialog(null, "Usuário Não Cadastrado");
                }
        Conex.desconecta();
        return mode;
    }
    
    
    public void Excluir(BeansUsuario mode) {
        Conex.conexao();
 
        try {
            PreparedStatement pst = Conex.con.prepareStatement("delete from usuarios where ID=?");
            pst.setInt(1, mode.getCodUser());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Dados Excluidos com sucess");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir Dados" + e.getMessage());
        }
        Conex.desconecta();
    }
    

public void Editar(BeansUsuario mode) {
        Conex.conexao();

        try {
            PreparedStatement pst = Conex.con.prepareStatement("update usuarios set NomeUser=?, SenhaUser=?, TipoUser=? where ID=?");
            pst.setString(1, mode.getNomeUser());
            pst.setString(2, mode.getSenhaUser());
            pst.setString(3, mode.getTipoUser());
            pst.setInt(4, mode.getCodUser());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Usuario alterado com sucesso");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na alteraçao de Dados do Usuario" + e.getMessage());
        }
        Conex.desconecta();
    }
    


}