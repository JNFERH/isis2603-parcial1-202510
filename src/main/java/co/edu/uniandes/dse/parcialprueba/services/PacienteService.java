package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import java.util.Optional;

@Slf4j
@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;


    //Guardar un nuevo libro

    @Transactional
    public PacienteEntity createPaciente(PacienteEntity pacienteEntity) throws EntityNotFoundException, IllegalOperationException{

        String primerosTres=pacienteEntity.getTelefono().substring(0,2);
        String completo=pacienteEntity.getTelefono();

        if((int)completo.length() != 11 ){

            throw new IllegalOperationException("Número no válido");

        }

        if (!primerosTres.equals("311") && !primerosTres.equals("601")){

            throw new IllegalOperationException("Número no válido");

        }

        return pacienteRepository.save(pacienteEntity);

    }

    @Transactional
    public void asociarAcudiente(Long id1, Long id2) throws EntityNotFoundException , IllegalOperationException{

        Optional<PacienteEntity> pacienteBase= pacienteRepository.findById(id1);

        if(pacienteBase.isEmpty()){

            throw new EntityNotFoundException("Paciente base no encontrado");

        }

        PacienteEntity baseEncontrado= pacienteBase.get();

        Optional<PacienteEntity> pacienteAcudiente= pacienteRepository.findById(id2);

        if(pacienteAcudiente.isEmpty()){

            throw new EntityNotFoundException("Paciente acudiente no encontrado");

        }

        PacienteEntity acudienteEncontrado= pacienteAcudiente.get();

        if(acudienteEncontrado.getHistoriasClinicas().isEmpty()){

            throw new IllegalOperationException("Acudiente debe tener historia clinica");
        }

        baseEncontrado.setAcudiente(acudienteEncontrado);

    }

}
