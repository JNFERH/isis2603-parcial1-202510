package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.HistoriaClinicaRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    HistoriaClinicaRepository historiaClinicaRepository;

    //Guardar un nuevo libro

    @Transactional
    public PacienteEntity createPaciente(PacienteEntity pacienteEntity) throws EntityNotFoundException, IllegalOperationException{

        String primerosTres=pacienteEntity.getTelefono().substring(0,2);
        String completo=pacienteEntity.getTelefono();

        if((int)completo.length() != 11 ){

            throw new IllegalOperationException("Número no válido");

        }

        if (!primerosTres.equals("311") || !primerosTres.equals("601")){

            throw new IllegalOperationException("Número no válido");

        }

        return pacienteRepository.save(pacienteEntity);

    }

}
