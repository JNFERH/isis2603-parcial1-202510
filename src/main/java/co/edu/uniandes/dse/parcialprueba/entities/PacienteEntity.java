package co.edu.uniandes.dse.parcialprueba.entities;

import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

import java.util.List;

@Data
@Entity
public class PacienteEntity extends BaseEntity {

    private String nombre;
    private String correo;
    private String telefono;

    @PodamExclude
    @OneToOne
    private PacienteEntity acudiente;

    @PodamExclude
    @OneToMany(mappedBy= "paciente", cascade= CascadeType.PERSIST, orphanRemoval = true)
    private List<HistoriaClinicaEntity> historiasClinicas= new ArrayList<>();


}
