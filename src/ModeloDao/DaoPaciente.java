/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDao;

import ModeloBeans.BeansPacientes;
import modeloConection.ConexãoBD;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Kaitungwa Júnior
 */
public class DaoPaciente {

    BeansPacientes pac = new BeansPacientes();
    ConexãoBD conex = new ConexãoBD();
    ConexãoBD conexBairro = new ConexãoBD();
    String nomeBairro;
    int CodBairro;

    public void SalvarP(BeansPacientes pac) {
        buscaBaiCod(pac.getBairroPacientes());

        conex.conexao();
        try {
            PreparedStatement pst = conex.con.prepareStatement("insert into pacientes (NomePaciente,NacimentoPaciente, BIPaciente, TelefonePaciente, BairroPaciente, RuaPaciente, ComplementoPaciente) Values (?,?,?,?,?,?,?)");

            pst.setString(1, pac.getNomePacientes());
            pst.setString(2, pac.getNascimentoPacientes());
            pst.setString(3, pac.getBIPacientes());
            pst.setString(4, pac.getTelemovelPacientes());
            pst.setInt(5, CodBairro);
            pst.setString(6, pac.getRuaPacientes());
            pst.setString(7, pac.getComplementoPacientes());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Paciente Salvo com Sucesso");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar Paciente" + e.getMessage());
        }
        conex.desconecta();
    }

    public void AlterarP(BeansPacientes pac) {
        buscaBaiCod(pac.getBairroPacientes());

        conex.conexao();
        try {
            PreparedStatement pst = conex.con.prepareStatement("update pacientes set NomePaciente=?, NacimentoPaciente=?, BIPaciente=?, TelefonePaciente=?, BairroPaciente=?, RuaPaciente=?, ComplementoPaciente=? where IDPaciente=?");

            pst.setString(1, pac.getNomePacientes());
            pst.setString(2, pac.getNascimentoPacientes());
            pst.setString(3, pac.getBIPacientes());
            pst.setString(4, pac.getTelemovelPacientes());
            pst.setInt(5, CodBairro);
            pst.setString(6, pac.getRuaPacientes());
            pst.setString(7, pac.getComplementoPacientes());
            pst.setInt(8, pac.getIDPacientes());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Paciente Altedado com Sucesso");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Alterar Paciente" + e.getMessage());
        }
        conex.desconecta();
    }

    public void ExcluirP(BeansPacientes pac) {
        conex.conexao();
 
        try {
            PreparedStatement pst = conex.con.prepareStatement("delete from pacientes where IDPaciente=?");
            pst.setInt(1, pac.getIDPacientes());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Paciente Excluido com sucesso");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir Paciente " + e.getMessage());
        }
        conex.desconecta();
    }
    
    public void buscaBaiCod(String nome) {
        conex.conexao();

        conex.executaSql("Select * from bairros where nomebairro='" + nome + "'");
        try {
            conex.rs.first();
            CodBairro = conex.rs.getInt("IDbairro");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar bairro" + ex);
        }

        conex.desconecta();
    }

    //Para Converter a Estring do Combobox Bairro e permitir a sua Vizializacao na Pesquisa...
    public void BuscarBairroP(int Cod) {
        conexBairro.conexao();
        try {
            conexBairro.executaSql("Select * from bairros where IDBairro='" + Cod + "'");
            conexBairro.rs.first();
            nomeBairro = conexBairro.rs.getString("nomeBairro");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao  Buscar o nome do Bairro " + ex.getMessage());
        }
    }

    public BeansPacientes BuscarP(BeansPacientes pac) {

        conex.conexao();
        try {
            conex.executaSql("Select * from pacientes where NomePaciente like '%"+pac.getPesquisrPacientes()+"%'");
            conex.rs.first();
            BuscarBairroP(conex.rs.getInt("BairroPaciente"));

            BuscarBairroP(conex.rs.getInt("BIPaciente"));
            pac.setBIPacientes(conex.rs.getString("BIPaciente"));
            pac.setNomePacientes(conex.rs.getString("NomePaciente"));
            pac.setIDPacientes(conex.rs.getInt("IDPaciente"));
            pac.setRuaPacientes(conex.rs.getString("RuaPaciente"));
            pac.setComplementoPacientes(conex.rs.getString("ComplementoPaciente"));
            pac.setNascimentoPacientes(conex.rs.getString("NacimentoPaciente"));
            pac.setTelemovelPacientes(conex.rs.getString("TelefonePaciente"));
            //O Nome do Bairro Localizado vindo do metodo Buscar Nome Bairro...
            pac.setBairroPacientes(nomeBairro);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar Paciente(s) " + e.getMessage());
        }
        conex.desconecta();
        return pac;
    }

}
