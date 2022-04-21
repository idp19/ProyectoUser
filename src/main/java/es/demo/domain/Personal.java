package es.demo.domain;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "personal")
public class Personal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_PERSONAL;
    public String LOGGIN;
    public String PASSWORD;
    private int ID_DEPARTAMENTO;
    private String NOMBRE;
    private String APELLIDO1;
    private String APELLIDO2;
    private String DNI;
    private String EMAIL;
    private String CADUCADA;
    private Date FECHA_CAD;
    public String INHABILITADA;
    private String PUEDE_CAMBIAR;
    private String N_INTENTOS;
    private int ID_PERFIL;
    private double COSTE;
    private double HORAS_SEMANA;
    private String COD_PERSONAL;
}
