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
package net.reflxction.simplecapes.commons;

import net.reflxction.simplecapes.SimpleCapes;
import net.reflxction.simplecapes.cape.CapeMode;

/**
 * Class which contains and manages all the mod commons (while saving it to config etc)
 */
public class Settings {

    // Whether the mod is enabled or not
    private boolean enabled;

    // Whether the mod should send a notification to the player if an update is available
    private boolean sendUpdateNotifications;

    // Whether the cape has been set or not. This is to avoid unnecessary crashes
    private boolean capeSet = false;

    // The current cape URL. This is only used if the cape mode is URL
    private String capeURL;

    // The current cape path. This is only used if the cape mode is LOCAL
    private String capePath;

    // The current cape mode
    private CapeMode currentMode;

    // Assign all variables
    public Settings() {
        enabled = SimpleCapes.getConfig().get("Enabled", "Enabled", true).getBoolean();
        sendUpdateNotifications = SimpleCapes.getConfig().get("Settings", "SendUpdates", true).getBoolean();
        capeSet = SimpleCapes.getConfig().get("Cape", "Set", false).getBoolean();
        capePath = SimpleCapes.getConfig().get("Cape", "Path", "").getString();
        capeURL = SimpleCapes.getConfig().get("Cape", "URL", "").getString();
        currentMode = CapeMode.fromName(SimpleCapes.getConfig().get("Cape", "Mode", "URL").getString());
    }

    /**
     * Whether the mod is enabled or not
     *
     * @return True if the mod is enabled, false if not
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets whether the mod is enabled or not
     *
     * @param enabled Boolean to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        SimpleCapes.getConfig().get("Enabled", "Enabled", true).set(enabled);
        SimpleCapes.getConfig().save();
    }

    /**
     * Whether the mod should send notifications if an update is available
     *
     * @return ^
     */
    public boolean sendNotification() {
        return sendUpdateNotifications;
    }

    /**
     * Sets whether the mod should notifications if an update is available
     *
     * @param flag Boolean to set
     */
    public void setSendNotification(boolean flag) {
        this.sendUpdateNotifications = flag;
        SimpleCapes.getConfig().get("Settings", "SendUpdates", true).set(flag);
        SimpleCapes.getConfig().save();
    }

    /**
     * The cape URL that it should render for
     *
     * @return The URL of the cape image
     */
    public String getCapeURL() {
        return capeURL;
    }

    /**
     * Sets the cape URL image
     *
     * @param capeURL New value to set
     */
    public void setCapeURL(String capeURL) {
        this.capeURL = capeURL;
        SimpleCapes.getConfig().get("Cape", "URL", "").set(capeURL);
        SimpleCapes.getConfig().save();
    }

    /**
     * A string of the cape path name
     *
     * @return The path of the cape
     */
    public String getCapePath() {
        return capePath;
    }

    /**
     * Sets the cape path
     *
     * @param capePath New value to set
     */
    public void setCapePath(String capePath) {
        this.capePath = capePath;
        SimpleCapes.getConfig().get("Cape", "Path", "").set(capePath);
        SimpleCapes.getConfig().save();
    }

    /**
     * The current cape mode
     *
     * @return The current cape mode
     */
    public CapeMode getCurrentMode() {
        return currentMode;
    }

    /**
     * Sets the cape mode
     *
     * @param currentMode New value to set
     */
    public void setCurrentMode(CapeMode currentMode) {
        this.currentMode = currentMode;
        SimpleCapes.getConfig().get("Cape", "Mode", "URL").set(currentMode.name());
        if (currentMode == CapeMode.CLIPBOARD) {
            SimpleCapes.getConfig().get("Cape", "ClipboardSaved", false).set(true);
        }
        SimpleCapes.getConfig().save();
    }

    /**
     * Whether a cape has been set or not
     *
     * @return Whether a cape has been set or not
     */
    public boolean isCapeSet() {
        return capeSet;
    }

    /**
     * Sets whether a cape has been set or not
     *
     * @param capeSet New value to set
     */
    public void setCapeSet(boolean capeSet) {
        this.capeSet = capeSet;
        SimpleCapes.getConfig().get("Cape", "Set", false).set(capeSet);
        SimpleCapes.getConfig().save();
    }

    /**
     * Whether the clipboard image has been saved or not.
     *
     * @return Whether the clipboard image has been saved or not.
     */
    public boolean isClipboardSaved() {
        return SimpleCapes.getConfig().get("Cape", "ClipboardSaved", false).getBoolean();
    }

}
