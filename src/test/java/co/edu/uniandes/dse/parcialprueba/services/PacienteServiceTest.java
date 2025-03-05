package co.edu.uniandes.dse.parcialprueba.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcialprueba.entities.HistoriaClinicaEntity;
import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import jakarta.transaction.TransactionScoped;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.PodamFactory;

@DataJpaTest
@TransactionScoped
@Import(PacienteService.class)
public class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory= new PodamFactoryImpl();

    private PacienteEntity p1;
    private PacienteEntity p2;
    private HistoriaClinicaEntity h1;

    @BeforeEach
    void setUp() {
		clearData();
		insertData();
	}

    private void clearData(){

        entityManager.getEntityManager().createQuery("delete from PacienteEntity");
        entityManager.getEntityManager().createQuery("delete from HistoriaClinicaEntity");

    }

    private void insertData(){

        p1= factory.manufacturePojo(PacienteEntity.class);
        p2= factory.manufacturePojo(PacienteEntity.class);
        h1= factory.manufacturePojo(HistoriaClinicaEntity.class);
    
        entityManager.persist(p1);
        entityManager.persist(p2);
        entityManager.persist(h1);
    }

    @Test
    void testCreatePaciente() throws IllegalOperationException, EntityNotFoundException{

        PacienteEntity nuevo= factory.manufacturePojo(PacienteEntity.class);
        nuevo.setTelefono("31100000000");

        PacienteEntity resultado= pacienteService.createPaciente(nuevo);

        assertEquals(nuevo.getId(), resultado.getId());
        assertEquals(nuevo.getCorreo(), resultado.getCorreo());
        assertEquals(nuevo.getTelefono(), resultado.getTelefono());

    }

    @Test
    void testCretePacienteNo() throws IllegalOperationException, EntityNotFoundException{

        assertThrows(IllegalOperationException.class,() ->{
        PacienteEntity nuevo= factory.manufacturePojo(PacienteEntity.class);
        nuevo.setTelefono("3110000000");
        PacienteEntity resultado= pacienteService.createPaciente(nuevo);
    }
        
        );

    }
}
