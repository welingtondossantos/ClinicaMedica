package ModeloBeans;

import java.util.Date;

/**
 *
 * @author Kaitungwa Júnior
 */
public class BeansAgendamento {
    private int IDAgenda;
    private String NomeMedico;
    private String NomePaciente;
    private String Turno;
    private Date Data;
    private String Mitivo;
    private String Status;
    private String NasPac;

    public String getNasPac() {
        return NasPac;
    }

    public void setNasPac(String NasPac) {
        this.NasPac = NasPac;
    }

    public int getIDAgenda() {
        return IDAgenda;
    }

    public void setIDAgenda(int IDAgenda) {
        this.IDAgenda = IDAgenda;
    }

    public String getNomeMedico() {
        return NomeMedico;
    }

    public void setNomeMedico(String NomeMedico) {
        this.NomeMedico = NomeMedico;
    }

    public String getNomePaciente() {
        return NomePaciente;
    }

    public void setNomePaciente(String NomePaciente) {
        this.NomePaciente = NomePaciente;
    }

    public String getTurno() {
        return Turno;
    }

    public void setTurno(String Turno) {
        this.Turno = Turno;
    }

    public Date getData() {
        return Data;
    }

    public void setData(Date Data) {
        this.Data = Data;
    }

    public String getMitivo() {
        return Mitivo;
    }

    public void setMitivo(String Mitivo) {
        this.Mitivo = Mitivo;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
}
