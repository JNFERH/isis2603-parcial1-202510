package co.edu.uniandes.dse.parcialprueba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import java.util.List;


@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

    List<PacienteEntity> findByNombre(String nombre);

}
