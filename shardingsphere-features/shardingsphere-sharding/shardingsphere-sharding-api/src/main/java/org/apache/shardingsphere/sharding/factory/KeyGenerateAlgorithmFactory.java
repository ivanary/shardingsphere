/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.sharding.factory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmFactory;
import org.apache.shardingsphere.sharding.spi.KeyGenerateAlgorithm;
import org.apache.shardingsphere.spi.ShardingSphereServiceLoader;
import org.apache.shardingsphere.spi.type.required.RequiredSPIRegistry;
import org.apache.shardingsphere.spi.type.typed.TypedSPIRegistry;

import java.util.Properties;

/**
 * Key generate algorithm factory.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KeyGenerateAlgorithmFactory {
    
    static {
        ShardingSphereServiceLoader.register(KeyGenerateAlgorithm.class);
    }
    
    /**
     * Create new instance of key generate algorithm.
     *
     * @return new instance of key generate algorithm
     */
    public static KeyGenerateAlgorithm newInstance() {
        return RequiredSPIRegistry.getRegisteredService(KeyGenerateAlgorithm.class);
    }
    
    /**
     * Create new instance of key generate algorithm.
     *
     * @param keyGenerateAlgorithmConfig key generate algorithm configuration
     * @return new instance of key generate algorithm
     */
    public static KeyGenerateAlgorithm newInstance(final ShardingSphereAlgorithmConfiguration keyGenerateAlgorithmConfig) {
        return ShardingSphereAlgorithmFactory.createAlgorithm(keyGenerateAlgorithmConfig, KeyGenerateAlgorithm.class);
    }
    
    /**
     * Judge whether contains key generate algorithm.
     *
     * @param keyGenerateAlgorithmType key generate algorithm type
     * @return contains key generate algorithm or not
     */
    public static boolean contains(final String keyGenerateAlgorithmType) {
        return TypedSPIRegistry.findRegisteredService(KeyGenerateAlgorithm.class, keyGenerateAlgorithmType, new Properties()).isPresent();
    }
}