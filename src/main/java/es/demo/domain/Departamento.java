package es.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "departamento")
public class Departamento {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_DEPARTAMENTO;
    public String COD_DEPARTAMENTO;
    public String NOM_DEPARTAMENTO;
    private String EN_USO;

}
