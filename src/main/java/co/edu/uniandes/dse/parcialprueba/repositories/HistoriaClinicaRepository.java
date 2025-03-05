package co.edu.uniandes.dse.parcialprueba.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.dse.parcialprueba.entities.HistoriaClinicaEntity;
import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;

@Repository
public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinicaEntity, Long> {

    List<HistoriaClinicaEntity> findByPaciente(PacienteEntity paciente);

}
