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

import net.minecraft.client.Minecraft;
import net.reflxction.simplecapes.proxy.ClientProxy;
import net.reflxction.simplecapes.utils.Reference;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Class which handles updating the mod
 */
public class UpdateManager {

    // Whether the new version is a snapshot or not
    private boolean snapshot;

    /**
     * Initiates a new update manager
     *
     * @param snapshot Whether the new version is a snapshot or not
     */
    public UpdateManager(boolean snapshot) {
        this.snapshot = snapshot;
    }

    /**
     * Updates the mod file
     *
     * @return {@code true} if it was successful, {@code false} if otherwise.
     */
    public boolean updateMod() {
        boolean success = true;
        try {
            File modsDirectory = new File(Minecraft.getMinecraft().mcDataDir, "mods");
            File modFile = new File(modsDirectory, "AutoImpurity-1.1.jar");
            if (modFile.exists() && modFile.delete()) {
                if (modFile.createNewFile()) {
                    URL updateURL = new URL(getDownloadLink());
                    FileUtils.copyURLToFile(updateURL, modFile);
                }
            }
        } catch (IOException e) {
            success = false;
        }
        return success;
    }

    private String getDownloadLink() {
        String version = String.valueOf(ClientProxy.getChecker().getLatestVersion()) + (snapshot ? "-SNAPSHOT" : "");
        return "https://github.com/ReflxctionDev/" +
                Reference.REPOSITORY_NAME +
                "/" +
                "releases" +
                "/" +
                "download" +
                "/" +
                version +
                "/" +
                Reference.REPOSITORY_NAME +
                "-" +
                version +
                ".jar";
    }

}
