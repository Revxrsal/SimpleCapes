/*
 * * Copyright 2018 github.com/ReflxctionDev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.reflxction.simplecapes;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.reflxction.simplecapes.proxy.IProxy;
import net.reflxction.simplecapes.commons.Settings;
import net.reflxction.simplecapes.updater.UpdateManager;
import net.reflxction.simplecapes.utils.Reference;

import java.io.File;

/**
 * SimpleCapes: A mod which gives a cape to the player
 */
@Mod(
        modid = Reference.MOD_ID,
        name = Reference.NAME,
        version = Reference.VERSION,
        acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS
)
public class SimpleCapes {

    // Config for saving data
    private static Configuration config;

    // Mod commons
    private static Settings settings;

    // Assign proxies of the mod
    @SidedProxy(

            // Client side proxy
            clientSide = Reference.CLIENT_PROXY,

            // Server side proxy
            serverSide = Reference.SERVER_PROXY
    )
    private static IProxy proxy;

    // The update manager
    private static UpdateManager updateManager;

    /*
     * Initialize variables here
     */
    static {
        config = new Configuration(new File("config/simple-capes.cfg"));
        settings = new Settings();
        updateManager = new UpdateManager(true);
    }

    /**
     * Called before the mod is fully initialized
     * <p>
     * Registries: Initiate variables and client command registries
     *
     * @param event Forge's pre-init event
     */
    @EventHandler
    public void onFMLPreInitialization(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    /**
     * Called when the mod has been fully initialized
     * <p>
     * Registries: Events and client-server command registries
     *
     * @param event Forge's init event
     */
    @EventHandler
    public void onFMLInitialization(FMLInitializationEvent event) {
        proxy.init(event);
    }

    /**
     * Called after the mod has been successfully initialized
     * <p>
     * Registries: Nothing
     *
     * @param event Forge's post init event
     */
    @EventHandler
    public void onFMLPostInitialization(FMLPostInitializationEvent event) {
    }

    /**
     * The mod config
     *
     * @return The config file used to store all the mod data and HTTP caches if any
     */
    public static Configuration getConfig() {
        return config;
    }

    /**
     * Mod settings
     *
     * @return An instance of the mod commons
     */
    public static Settings getSettings() {
        return settings;
    }

    /**
     * The mod update manager
     *
     * @return An instance of the mod update manager
     */
    public static UpdateManager getUpdateManager() {
        return updateManager;
    }

}
