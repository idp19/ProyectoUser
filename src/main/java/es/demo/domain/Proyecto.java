package es.demo.domain;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "proyecto")
public class Proyecto {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_PROYECTO;
    public String COD_PROYECTO;
    public String NOM_PROYECTO;
    private int ID_PRODUCTO;
    private String EN_USO;
    private Date FECHA_INI;
    private Date FECHA_FIN;
    private double NUM_MAX_HORAS;
    private double PRESUPUESTO_ANUAL;
}
