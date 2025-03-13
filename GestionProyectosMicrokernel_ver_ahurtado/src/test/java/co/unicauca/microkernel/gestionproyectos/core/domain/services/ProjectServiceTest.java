import co.unicauca.microkernel.gestionproyectos.core.domain.entities.Project;
import co.unicauca.microkernel.gestionproyectos.core.domain.entities.User;
import co.unicauca.microkernel.gestionproyectos.core.domain.services.ProjectService;
import co.unicauca.microkernel.gestionproyectos.access.ProjectsRepositoryArrayPlugin;
import co.unicauca.microkernel.gestionproyectos.core.plugin.manager.PluginManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectServiceTest {

    private ProjectService projectService;
    private ProjectsRepositoryArrayPlugin repositorio;

    @BeforeEach
    public void setUp() {
        repositorio = new ProjectsRepositoryArrayPlugin();
        PluginManager.getPlugins().clear();
        PluginManager.registerPlugin(repositorio);
        projectService = new ProjectService();
    }

    @Test
    public void testRegisterProject_Success() {
        // Arrange
        String title = "Sistema de Inventarios";
        String description = "Desarrollar un sistema de gestión de inventarios.";
        User empresa = new User("TechCorp", "contacto@techcorp.com", "EMPRESA");

        // Act
        projectService.registerProject(title, description, empresa);

        // Assert
        List<Project> proyectos = repositorio.getProjects();
        assertEquals(1, proyectos.size(), "Debe haber un proyecto registrado");
        assertEquals(title.toUpperCase(), proyectos.get(0).getTitle(), "El título debe coincidir");
    }

    @Test
    public void testListProject_NotEmpty() {
        // Arrange
        User empresa = new User("TechCorp", "contacto@techcorp.com", "EMPRESA");
        projectService.registerProject("Sistema de Inventarios", "Gestión de inventarios.", empresa);

        // Act
        List<Project> proyectos = repositorio.getProjects();

        // Assert
        assertFalse(proyectos.isEmpty(), "La lista de proyectos no debe estar vacía");
    }

    @Test
    public void testAssignProject_Success() {
        // Arrange
        String title = "Sistema de Inventarios";
        User empresa = new User("TechCorp", "contacto@techcorp.com", "EMPRESA");
        User estudiante = new User("Juan Pérez", "juan@example.com", "ESTUDIANTE");

        projectService.registerProject(title, "Gestión de inventarios.", empresa);

        // Act
        projectService.assignProject(title, estudiante);
        Project proyecto = repositorio.findProjectByTitle(title);

        // Assert
        assertNotNull(proyecto.getStudent(), "El proyecto debe tener un estudiante asignado");
        assertEquals(estudiante, proyecto.getStudent(), "El estudiante asignado debe coincidir");
    }

    @Test
    public void testFindProjectByTitle_ExistingProject() {
        // Arrange
        String title = "Sistema de Inventarios";
        User empresa = new User("TechCorp", "contacto@techcorp.com", "EMPRESA");

        projectService.registerProject(title, "Gestión de inventarios.", empresa);

        // Act
        Project proyecto = repositorio.findProjectByTitle(title);

        // Assert
        assertNotNull(proyecto, "El proyecto debe existir");
        assertEquals(title.toUpperCase(), proyecto.getTitle(), "El título debe coincidir");
    }

    @Test
    public void testFindProjectByTitle_NonExistentProject() {
        // Act
        Project proyecto = repositorio.findProjectByTitle("Proyecto Inexistente");

        // Assert
        assertNull(proyecto, "Debe devolver null si el proyecto no existe");
    }
}
