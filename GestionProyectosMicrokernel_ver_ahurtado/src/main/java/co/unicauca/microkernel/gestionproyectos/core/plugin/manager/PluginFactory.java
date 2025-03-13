package co.unicauca.microkernel.gestionproyectos.core.plugin.manager;

import java.lang.reflect.Constructor;

/**
 * Fábrica de Plugin específica para crear el repositorio de arrays.
 */
public class PluginFactory {
    public static IProjectRepositoryPlugin createPlugin(String className) throws Exception {
    className = "co.unicauca.microkernel.gestionproyectos.access." + className;
    Class<?> clazz = Class.forName(className);
    Constructor<?> constructor = clazz.getConstructor();
    return (IProjectRepositoryPlugin) constructor.newInstance();
    }
}
