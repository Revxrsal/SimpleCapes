/*
 * * Copyright 2017-2018 github.com/ReflxctionDev
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

import java.util.function.Consumer;

/**
 * Utility used to schedule tasks and code on new threads. Useful to keep performance well
 */
public class Multithreading<T> {

    @SuppressWarnings("unused")
    private T t;

    @SafeVarargs
    public final void schedule(Consumer<? super T>... consumers) {
        new Thread(() -> {
            for (Consumer<? super T> consumer : consumers) {
                consumer.accept(t);
            }
        }).start();
    }

}
