package ar.edu.utn.frc.tup.lc.iv;

import ar.edu.utn.frc.tup.lc.iv.entities.construction.ConstructionEntity;
import ar.edu.utn.frc.tup.lc.iv.error.UpdateConstructionStatusException;
import ar.edu.utn.frc.tup.lc.iv.models.construction.ConstructionStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * Test class for validating transitions and handling state changes
 * in construction entities based on the construction status.
 */

@SpringBootTest
public class ConstructionStatusTest {

    /**
     * Tests that an exception is thrown when attempting to validate a transition
     * to the APPROVED status if the construction is not approved by the municipality.
     */

    @Test
    public void testValidateTransitionToApprovedThrowsException() {

        ConstructionEntity constructionEntity = Mockito.mock(ConstructionEntity.class);
        Mockito.when(constructionEntity.getApprovedByMunicipality()).thenReturn(false);
        Mockito.when(constructionEntity.getWorkers()).thenReturn(new ArrayList<>());


        UpdateConstructionStatusException exception = assertThrows(UpdateConstructionStatusException.class, () -> {
            ConstructionStatus.APPROVED.validateTransition(constructionEntity);
        });


        assertEquals("The construction is not valid for approval.", exception.getMessage());
    }
    /**
     * Tests that an exception is thrown when attempting to validate a transition
     * to the COMPLETED status if the construction is not in the IN_PROGRESS state.
     */
    @Test
    public void testValidateTransitionToCompletedThrowsException() {
        ConstructionEntity constructionEntity = Mockito.mock(ConstructionEntity.class);
        Mockito.when(constructionEntity.getConstructionStatus()).thenReturn(ConstructionStatus.PLANNED);

        UpdateConstructionStatusException exception = assertThrows(UpdateConstructionStatusException.class, () -> {
            ConstructionStatus.COMPLETED.validateTransition(constructionEntity);
        });

        assertEquals("The construction cannot be marked as COMPLETED because it is not in the IN_PROGRESS state.", exception.getMessage());
    }

    /**
     * Tests that the actual start date of the construction entity is set when
     * handling a state transition to IN_PROGRESS.
     */
    @Test
    public void testHandleStateTransitionToInProgress() {
        ConstructionEntity constructionEntity = new ConstructionEntity();

        ConstructionStatus.IN_PROGRESS.handleStateTransition(constructionEntity);

        assertNotNull(constructionEntity.getActualStartDate());
    }

    /**
     * Tests that the actual end date of the construction entity is set when
     * handling a state transition to COMPLETE.
     */
    @Test
    public void testHandleStateTransitionToCompleted() {
        ConstructionEntity constructionEntity = new ConstructionEntity();

        ConstructionStatus.COMPLETED.handleStateTransition(constructionEntity);

        assertNotNull(constructionEntity.getActualEndDate());
    }

}
