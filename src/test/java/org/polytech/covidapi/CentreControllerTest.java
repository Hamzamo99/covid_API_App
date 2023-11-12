package org.polytech.covidapi;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.polytech.covidapi.controleurs.CentreController;
import org.polytech.covidapi.modele.Centre;
import org.polytech.covidapi.services.CentreService;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class CentreControllerTest {

    @Test
    public void testRechercherCentresParVille() {

        // Créer un mock pour CentreService
        CentreService centreServiceMock = mock(CentreService.class);

        // Créer un objet CentreController en utilisant le mock de CentreService
        CentreController centreController = new CentreController(centreServiceMock);

        // Définir le comportement attendu du mock
        String ville = "Nancy";
        Centre centreAttendu = new Centre(1L, "Centre de Nancy", "123 Rue de Test", "Nancy");
        List<Centre> centresAttendus = Arrays.asList(centreAttendu);
        when(centreServiceMock.rechercherCentresParVille(ville)).thenReturn(centresAttendus);

        // Appeler la méthode à tester
        List<Centre> centresResultats = centreController.rechercherCentresParVille(ville);

        // Vérifier que le mock de CentreService a été appelé avec les bons arguments
        verify(centreServiceMock).rechercherCentresParVille(ville);

        // Vérifier le résultat de la méthode
        assertEquals(centresAttendus, centresResultats);
    }
}
