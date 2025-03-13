package co.unicauca.microkernel.gestionproyectos.core.plugin.manager;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class PluginFactory {

    private static final Map<String, IProjectRepositoryPlugin> cache = new HashMap<>();

    public static IProjectRepositoryPlugin createPlugin(String className) throws Exception {
        className = "co.unicauca.microkernel.gestionproyectos.access." + className;
        
        // Si ya existe en caché, devolverlo en lugar de crearlo nuevamente
        if (cache.containsKey(className)) {
            return cache.get(className);
        }

        Class<?> clazz = Class.forName(className);
        Constructor<?> constructor = clazz.getConstructor();
        IProjectRepositoryPlugin plugin = (IProjectRepositoryPlugin) constructor.newInstance();

        // Guardar la instancia en caché
        cache.put(className, plugin);

        return plugin;
    }
}
