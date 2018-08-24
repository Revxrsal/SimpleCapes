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

/**
 * Represents a cape mode
 */
public enum CapeMode {

    /**
     * Represents a cape from a URL
     */
    URL,

    /**
     * Represents a cape from clipboard
     */
    CLIPBOARD,

    /**
     * Represents a cape that is saved locally on the machine
     */
    LOCAL;

    public static CapeMode fromName(String name) {
        for (CapeMode mode : values()) {
            if (mode.name().equals(name)) return mode;
        }
        return CapeMode.URL;
    }

}
