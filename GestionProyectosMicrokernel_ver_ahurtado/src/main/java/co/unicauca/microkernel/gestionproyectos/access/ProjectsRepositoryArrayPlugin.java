package co.unicauca.microkernel.gestionproyectos.access;

import co.unicauca.microkernel.gestionproyectos.core.plugin.manager.IProjectRepositoryPlugin;
import co.unicauca.microkernel.gestionproyectos.core.domain.entities.Project;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase `ProjectsRepositoryArrayPlugin` es una implementación de la interfaz `IProjectRepositoryPlugin`
 * que almacena proyectos en una lista en memoria. Esta clase proporciona métodos para agregar proyectos,
 * obtener la lista de proyectos y buscar un proyecto por su título.
 * 
 * @author libardo
 */
public class ProjectsRepositoryArrayPlugin implements IProjectRepositoryPlugin {
    private List<Project> projects = new ArrayList<>();

    /**
     * Agrega un nuevo proyecto a la lista de proyectos.
     * 
     * @param proyecto El proyecto que se desea agregar.
     */
    @Override
    public void addProject(Project proyecto) {
        projects.add(proyecto);
    }

    /**
     * Obtiene la lista de todos los proyectos almacenados.
     * 
     * @return Una lista de proyectos.
     */
    @Override
    public List<Project> getProjects() {
        return projects;
    }

    /**
     * Busca un proyecto por su título.
     * 
     * @param titulo El título del proyecto que se desea buscar.
     * @return El proyecto encontrado, o `null` si no se encuentra ningún proyecto con el título especificado.
     */
    @Override
    public Project findProjectByTitle(String titulo) {
            
        for(Project project:projects){
            if(project.getTitle().equals(titulo.toUpperCase())){
                return project;
            }
        }
        return null;
    }
}