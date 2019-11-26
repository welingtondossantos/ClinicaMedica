package ModeloDao;

import ModeloBeans.BeansAgendamento;
import modeloConection.ConexãoBD;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DaoAgendamento {

    BeansAgendamento Agenda = new BeansAgendamento();
    ConexãoBD conex = new ConexãoBD();
    ConexãoBD conexPaciente = new ConexãoBD();
    ConexãoBD conexMedico = new ConexãoBD();

    int codMedico;
    int codPaciente;
    private Object ex;

    public void Salvar(BeansAgendamento agenda) {
        BuscarMedico(agenda.getNomeMedico());
        BuscarPaciente(agenda.getNomePaciente());
        conex.conexao();
        JOptionPane.showMessageDialog(null, " Agendamento Marcado Com Sucesso");
        conex.desconecta();
    }

    public void BuscarMedico(String nomeMedico) {
        conexMedico.conexao();
        conexMedico.executaSql("Select * from medico where NomeMedico = '" + nomeMedico + "'");
        conexMedico.rs.equals(this);
       // codMedico = conexMedico.rs./*hashCode*/("CodMedico");
    }

    public int BuscarCodMedico(String nomeMedico) {
        conexMedico.conexao();
        conexMedico.executaSql("Select * from medico where NomeMedico = '" + nomeMedico + "'");
       // conexMedico.rs.first();
       // codMedico = conexMedico.rs.getInt("CodMedico");
        return codMedico;
    }

    public void BuscarPaciente(String nomePaciente) {
        conexPaciente.conexao();
        conexPaciente.executaSql("Select * from pacientes where NomePaciente = '" + nomePaciente + "'");
      //  conexPaciente.rs.first();
      //  codPaciente = conexPaciente.rs.getInt("IDPaciente");
    }

        public void BuscarAgendamento(BeansAgendamento agenda) {
        conexMedico.conexao();
        conexMedico.executaSql("Select * from agendamento where DataAgenga = '" + agenda.getData() + "'");
       // conexMedico.rs.first();
        //codMedico = conexMedico.rs.getInt("CodMedico");
        }   
    
    public void AlterarStatus(BeansAgendamento agenda) {
        conex.conexao();
        try {
        //    PreparedStatement pst = conex.con PreparedStatement("update agendamento set StatusAgenda=? where IDAgenda=?");
          //  pst.setString(1, agenda.getStatus());
         //   pst.setInt(2, agenda.getIDAgenda());
          //  pst.execute();
            JOptionPane.showMessageDialog(null, " Agendamento Em Atendimento");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atender agendamento " + e.getMessage());
        }
        conex.desconecta();
    }
    
    //Para Retornar 
        public BeansAgendamento BuscarAgendaPorCodigo(int cod) {
        BeansAgendamento agend = new BeansAgendamento();
        
            conex.conexao();
        conex.executaSql("Select * from agendamento inner join pacientes on AIDPaciente=IDPaciente inner join medico on IDMedico=CodMedico where IDAgenda = '" + cod + "'");
        /*try*/  {
           // conex.rs.();
           // agend.setNomePaciente(conex.rs.toString("NomePaciente"));
           // agend.setNomeMedico(conex.rs.getString("NomeMedico"));
           // agend.setMitivo(conex.rs.getString("AgendaMotivo"));
           // agend.setNasPac(conex.rs.getString("NacimentoPaciente"));
           // } catch (SQLException ex) {
         //   JOptionPane.showMessageDialog(null, "Erro ao Buscar Agendamento por Codigo " + ex.getMessage());
        }
        return agend;
        }
}
