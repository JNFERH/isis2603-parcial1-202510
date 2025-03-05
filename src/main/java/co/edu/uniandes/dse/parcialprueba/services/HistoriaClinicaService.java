package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.entities.HistoriaClinicaEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.HistoriaClinicaRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HistoriaClinicaService {

    @Autowired
    HistoriaClinicaRepository historiaClinicaRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    //Crear historia clínica dado un ID

    @Transactional
    public HistoriaClinicaEntity createHistoriaClinica(HistoriaClinicaEntity historiaClinicaEntity, Long idPaciente) throws EntityNotFoundException, IllegalOperationException{

        Optional<PacienteEntity> encontrado= pacienteRepository.findById(idPaciente);
        if(encontrado.isEmpty()){

            throw new IllegalOperationException("Paciente no existe");

        }

        PacienteEntity paciente= encontrado.get();

        PacienteEntity acudiente= paciente.getAcudiente();

        if(acudiente!=null){

            String diagnoticoFinal="HistoriaCompratida-"+historiaClinicaEntity.getDiagnostico();
            historiaClinicaEntity.setDiagnostico(diagnoticoFinal);

        }


        return historiaClinicaRepository.save(historiaClinicaEntity);
    }

}
