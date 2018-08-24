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
package net.reflxction.simplecapes.listeners;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.reflxction.simplecapes.SimpleCapes;
import net.reflxction.simplecapes.cape.CapeLayer;

/**
 * Listener which adds the cape to the player upon joining the world
 */
public class EntityWorldJoinListener {

    // Minecraft instance
    private Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if (!SimpleCapes.getSettings().isEnabled()) return; // Return if the mod isn't enabled
        if (!(event.entity instanceof EntityPlayer)) return; // Return if the entity wasn't a player
        if (event.entity.getPersistentID().equals(mc.getSession().getProfile().getId())) renderCape();
    }

    /**
     * Renders the cape by adding the layer to the player skin map
     */
    private void renderCape() {
        Minecraft.getMinecraft().gameSettings.setModelPartEnabled(EnumPlayerModelParts.CAPE, true);
        for (RenderPlayer render : Minecraft.getMinecraft().getRenderManager().getSkinMap().values()) {
            render.addLayer(new CapeLayer(render));
        }
    }
}
