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
package net.reflxction.simplecapes.cape;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import net.reflxction.simplecapes.SimpleCapes;
import net.reflxction.simplecapes.utils.ImageUtils;
import net.reflxction.simplecapes.utils.Reference;

import java.awt.image.BufferedImage;

/**
 * Class which downloads capes from the URL
 */
public class CapeDownloader {

    // Singleton instance
    public static final CapeDownloader DOWNLOADER = new CapeDownloader();

    // The cached texture. This is updated when a new URL is set
    private ResourceLocation cachedTexture = getCapeTexture();

    /**
     * The cape texture as a resource location
     *
     * @return The cape texture as a resource location
     */
    private ResourceLocation getCapeTexture() {
        ResourceLocation location = null;
        switch (SimpleCapes.getSettings().getCurrentMode()) {
            case URL:
                location = Minecraft.getMinecraft().getTextureManager()
                        .getDynamicTextureLocation(Reference.MOD_ID, new DynamicTexture(ImageUtils.getImageFromURL(SimpleCapes.getSettings().getCapeURL())));
                break;
            case LOCAL:
                location = Minecraft.getMinecraft().getTextureManager()
                        .getDynamicTextureLocation(Reference.MOD_ID, new DynamicTexture(ImageUtils.getImageFromFile(SimpleCapes.getSettings().getCapePath())));
                break;
            case CLIPBOARD:
                if (!SimpleCapes.getSettings().isClipboardSaved()) {
                    BufferedImage clipboard = ImageUtils.getImageFromClipboard();
                    if (clipboard != null) {
                        location = Minecraft.getMinecraft().getTextureManager()
                                .getDynamicTextureLocation(Reference.MOD_ID, new DynamicTexture(clipboard));
                    }
                } else {
					BufferedImage cape = ImageUtils.getImageFromFile("clipboard.png");
				    location = Minecraft.getMinecraft().getTextureManager()
                                .getDynamicTextureLocation(Reference.MOD_ID, new DynamicTexture(cape));
				}
                break;
        }
        return location;
    }

    /**
     * The cached resource location that is updated every time a new URL is set
     *
     * @return The cached texture
     */
    ResourceLocation getCachedTexture() {
        return cachedTexture;
    }

    /**
     * Updates the cached texture.
     */
    public void updateCachedTexture() {
        cachedTexture = getCapeTexture();
    }

}
