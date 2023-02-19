package eu.skyphantom.skypvp.hooks;
/*
 * EventManager.java | 14:18
 * (c) KeineSecrets (2022)
 */


import eu.skyphantom.skypvp.SkyPvP;
import eu.skyphantom.skypvp.lms.listener.*;
import eu.skyphantom.skypvp.utils.ChunkLoader;
import eu.skyphantom.skypvp.utils.advent.KalenderListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

public class EventManager {

    public EventManager() {

        Bukkit.getPluginManager().registerEvents(new ChunkLoader(), SkyPvP.getInstance());
        Bukkit.getLogger().log(Level.INFO, "[EventManager] Registered Listener ChunkLoader");
        Bukkit.getPluginManager().registerEvents(new KalenderListener(), SkyPvP.getInstance());
        Bukkit.getLogger().log(Level.INFO, "[EventManager] Registered Listener KalenderListener");

        Bukkit.getPluginManager().registerEvents(new LMSCreateListener(), SkyPvP.getInstance());
        Bukkit.getLogger().log(Level.INFO, "[EventManager] Registered Listener LMSCreateListener");
        Bukkit.getPluginManager().registerEvents(new LMSPlayerJoinListener(), SkyPvP.getInstance());
        Bukkit.getLogger().log(Level.INFO, "[EventManager] Registered Listener LMSPlayerJoinListener");
        Bukkit.getPluginManager().registerEvents(new LMSPlayerKillListener(), SkyPvP.getInstance());
        Bukkit.getLogger().log(Level.INFO, "[EventManager] Registered Listener LMSPlayerKillListener");
        Bukkit.getPluginManager().registerEvents(new LMSPlayerQuitListener(), SkyPvP.getInstance());
        Bukkit.getLogger().log(Level.INFO, "[EventManager] Registered Listener LMSPlayerQuitListener");
        Bukkit.getPluginManager().registerEvents(new LMSStartListener(), SkyPvP.getInstance());
        Bukkit.getLogger().log(Level.INFO, "[EventManager] Registered Listener LMSStartListener");
        Bukkit.getPluginManager().registerEvents(new LMSStopListener(), SkyPvP.getInstance());
        Bukkit.getLogger().log(Level.INFO, "[EventManager] Registered Listener LMSStopListener");
        Bukkit.getPluginManager().registerEvents(new LMSEntityDamageByEntityListener(), SkyPvP.getInstance());
        Bukkit.getLogger().log(Level.INFO, "[EventManager] Registered Listener LMSEntityDamageByEntityListener");

        for (Class<? extends Listener> clazz : this.getListeners()) {
            try {
                Listener listener = clazz.getDeclaredConstructor().newInstance();
                Bukkit.getPluginManager().registerEvents(listener, SkyPvP.getInstance());
                Bukkit.getLogger().log(Level.INFO, "[EventManager] Registered Listener " + clazz.getSimpleName());
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException operationException) {
                operationException.printStackTrace();
            }
        }

        for (Class<? extends PluginMessageListener> clazz : this.getMessageListeners()) {
            try {
                PluginMessageListener pluginMessageListener = clazz.getDeclaredConstructor().newInstance();
                Bukkit.getMessenger().registerIncomingPluginChannel(SkyPvP.getInstance(), "MC|Brand", pluginMessageListener);
                Bukkit.getMessenger().registerIncomingPluginChannel(SkyPvP.getInstance(), "labymod3:main", pluginMessageListener);
                Bukkit.getLogger().log(Level.INFO, "[EventManager] Registered PluginMessageListener " + clazz.getSimpleName());
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException operationException) {
                operationException.printStackTrace();
            }
        }

        for (Class<? extends Listener> clazz : this.getTestListeners()) {
            try {
                Listener listener = clazz.getDeclaredConstructor().newInstance();
                Bukkit.getPluginManager().registerEvents(listener, SkyPvP.getInstance());
                Bukkit.getLogger().log(Level.WARNING, "[TEST] Registered Listener " + clazz.getSimpleName());
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException operationException) {
                operationException.printStackTrace();
            }
        }

        for (Class<? extends PluginMessageListener> clazz : this.getTestMessageListeners()) {
            try {
                PluginMessageListener pluginMessageListener = clazz.getDeclaredConstructor().newInstance();
                Bukkit.getMessenger().registerIncomingPluginChannel(SkyPvP.getInstance(), "MC|Brand", pluginMessageListener);
                Bukkit.getMessenger().registerIncomingPluginChannel(SkyPvP.getInstance(), "labymod3:main", pluginMessageListener);
                Bukkit.getLogger().log(Level.WARNING, "[TEST] Registered PluginMessageListener " + clazz.getSimpleName());
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException operationException) {
                operationException.printStackTrace();
            }
        }
    }

    public Set<Class<? extends Listener>> getListeners() {
        HashSet<Class<? extends Listener>> hashSet = new HashSet<>();
        Reflections reflections = new Reflections("eu.skyphantom.skypvp.events", new SubTypesScanner(false));
        hashSet.addAll(reflections.getSubTypesOf(Listener.class));
        return hashSet;
    }

    public Set<Class<? extends PluginMessageListener>> getMessageListeners() {
        Reflections reflections = new Reflections("eu.skyphantom.skypvp.events", new SubTypesScanner(false));
        return new HashSet<>(reflections.getSubTypesOf(PluginMessageListener.class));
    }

    public Set<Class<? extends Listener>> getTestListeners() {
        HashSet<Class<? extends Listener>> hashSet = new HashSet<>();
        Reflections reflections = new Reflections("eu.skyphantom.skypvp.test", new SubTypesScanner(false));
        hashSet.addAll(reflections.getSubTypesOf(Listener.class));
        return hashSet;
    }

    public Set<Class<? extends PluginMessageListener>> getTestMessageListeners() {
        Reflections reflections = new Reflections("eu.skyphantom.skypvp.test", new SubTypesScanner(false));
        return new HashSet<>(reflections.getSubTypesOf(PluginMessageListener.class));
    }

}
