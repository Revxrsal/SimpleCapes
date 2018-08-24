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

import net.reflxction.simplecapes.utils.Reference;
import net.reflxction.simplejson.json.JsonURLReader;

import java.io.IOException;

public class VersionChecker {

    // Whether an update is available or not
    private boolean updateAvailable = false;

    // The current mod version, not necessarily the latest
    private double version = Double.parseDouble(Reference.VERSION);

    // The JSON file to get the latest version
    private String checkerURL;

    /**
     * Initiates a new version checker
     */
    public VersionChecker() {
        checkerURL = "https://raw.githubusercontent.com/ReflxctionDev/" + Reference.REPOSITORY_NAME + "/master/version.json";
    }

    /**
     * Gets the latest version from the JSON file.
     * This method also updates the state of {@link VersionChecker#updateAvailable}
     *
     * @return The latest version of the mod
     */
    double getLatestVersion() {
        try {
            JsonURLReader reader = new JsonURLReader(checkerURL);
            return reader.readContent().getDouble( "version");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1.0;
    }

    /**
     * Whether an update is available or not
     *
     * @return ^
     */
    public boolean isUpdateAvailable() {
        return updateAvailable;
    }

    /**
     * Updates the state of {@link #updateAvailable}
     */
    public void updateState() {
        updateAvailable = getLatestVersion() > version;
    }
}
