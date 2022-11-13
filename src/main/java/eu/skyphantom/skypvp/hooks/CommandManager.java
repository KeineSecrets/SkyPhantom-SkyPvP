package eu.skyphantom.skypvp.hooks;
/*
 * CommandManager.java | 14:17
 * (c) KeineSecrets (2022)
 */


import eu.skyphantom.skypvp.lms.commands.LMSCommand;
import eu.skyphantom.skypvp.utils.advent.KalenderCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

public class CommandManager {

    CommandMap commandMap;
    Field bukkitCommandMap;

    public CommandManager() {
        try {
            bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        bukkitCommandMap.setAccessible(true);
        try {
            commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        commandMap.register("", new KalenderCommand());
        commandMap.register("", new LMSCommand());

        for (Class<? extends Command> clazz : getCommandClasses()) {
            try {
                commandMap.register("", clazz.getConstructor().newInstance());
                Bukkit.getLogger().log(Level.INFO, "[CommandManager] Registered Command " + clazz.getSimpleName());
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException operationException) {
                throw new RuntimeException(operationException);
            }
        }

        for (Class<? extends Command> clazz : getTestCommandClasses()) {
            try {
                commandMap.register("", clazz.getConstructor().newInstance());
                Bukkit.getLogger().log(Level.WARNING, "[TEST] Registered Command " + clazz.getSimpleName());
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException operationException) {
                throw new RuntimeException(operationException);
            }
        }

    }

    public Set<Class<? extends Command>> getCommandClasses() {
        Reflections reflections = new Reflections("eu.skyphantom.skypvp.commands", new SubTypesScanner(false));
        return new HashSet<>(reflections.getSubTypesOf(Command.class));
    }

    public Set<Class<? extends Command>> getTestCommandClasses() {
        Reflections reflections = new Reflections("eu.skyphantom.skypvp.test", new SubTypesScanner(false));
        return new HashSet<>(reflections.getSubTypesOf(Command.class));
    }

    public CommandMap getCommandMap() {
        return commandMap;
    }
}

