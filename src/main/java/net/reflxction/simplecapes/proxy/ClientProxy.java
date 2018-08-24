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
package net.reflxction.simplecapes.proxy;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.*;
import net.reflxction.simplecapes.SimpleCapes;
import net.reflxction.simplecapes.commands.SCCommand;
import net.reflxction.simplecapes.commons.Multithreading;
import net.reflxction.simplecapes.listeners.EntityWorldJoinListener;
import net.reflxction.simplecapes.updater.NotificationSender;
import net.reflxction.simplecapes.updater.VersionChecker;
import net.reflxction.simplecapes.utils.Reference;

import java.io.File;

public class ClientProxy implements IProxy {

    // Instance of the version checker
    private static VersionChecker checker = new VersionChecker();

    /**
     * Called before the mod is fully initialized
     * <p>
     * Registries: Initiate variables and client command registries
     *
     * @param event Forge's pre-init event
     */
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        if (SimpleCapes.getSettings().sendNotification()) {
            new Multithreading<>().schedule(o -> checker.updateState());
        }
        ClientCommandHandler.instance.registerCommand(new SCCommand());
        File modDirectory = new File(Minecraft.getMinecraft().mcDataDir, Reference.MOD_ID);
        if (!modDirectory.exists()) modDirectory.mkdirs();
    }

    /**
     * Called when the mod has been fully initialized
     * <p>
     * Registries: Events and client-server command registries
     *
     * @param event Forge's init event
     */
    @Override
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new NotificationSender());
        MinecraftForge.EVENT_BUS.register(new EntityWorldJoinListener());
    }

    /**
     * Called after the mod has been successfully initialized
     * <p>
     * Registries: Nothing
     *
     * @param event Forge's post init event
     */
    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }


    /**
     * Called after {@link FMLServerAboutToStartEvent} and before {@link FMLServerStartedEvent}.
     * <p>
     * Registries: Server commands
     *
     * @param event Forge's server starting event
     */
    @Override
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new SCCommand());
    }

    public static VersionChecker getChecker() {
        return checker;
    }
}
