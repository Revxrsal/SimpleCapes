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
package net.reflxction.simplecapes.updater;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.reflxction.simplecapes.SimpleCapes;
import net.reflxction.simplecapes.commons.SimpleSender;
import net.reflxction.simplecapes.proxy.ClientProxy;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Listener which sends the player a notification update
 */
public class NotificationSender {

    // Whether the notification was already sent or not
    private boolean sent;

    @SubscribeEvent
    public void onFMLNetworkClientConnectedToServer(ClientConnectedToServerEvent event) {
        if (!sent && SimpleCapes.getSettings().sendNotification() && ClientProxy.getChecker().isUpdateAvailable()) {
            new Timer().schedule(new TimerTask() { // We use a timer to send after the player joins with 2.5 seconds, because the game player is possibly not initialized yet
                @Override
                public void run() {
                    SimpleSender.send("&eAn update is available for SimpleCapes! Do &a/sc update&e &eto update.");
                    sent = true;
                }
            }, 2500);
        }
    }
}
