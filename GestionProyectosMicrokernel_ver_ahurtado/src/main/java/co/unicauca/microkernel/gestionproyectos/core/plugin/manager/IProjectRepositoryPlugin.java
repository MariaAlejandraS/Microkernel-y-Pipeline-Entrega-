package co.unicauca.microkernel.gestionproyectos.core.plugin.manager;

import co.unicauca.microkernel.gestionproyectos.core.domain.entities.Project;
import java.util.List;

public interface IProjectRepositoryPlugin {

    /**
     * Registra un nuevo proyecto en el sistema.
     * 
     * @param proyecto Instancia de Project que contiene la información del proyecto.
     */
    void addProject(Project proyecto);

    /**
     * Localiza un proyecto dentro del repositorio utilizando su título.
     * 
     * @param titulo Nombre del proyecto que se desea buscar.
     * @return El objeto Project si existe, de lo contrario, retorna null.
     */
    Project findProjectByTitle(String titulo);

    /**
     * Devuelve todos los proyectos almacenados en el repositorio.
     * 
     * @return Lista con los proyectos disponibles.
     */
    List<Project> getProjects();
    
}
