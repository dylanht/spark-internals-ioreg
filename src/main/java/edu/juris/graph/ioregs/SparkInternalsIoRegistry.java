/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package edu.juris.graph.ioregs;

import org.apache.tinkerpop.gremlin.structure.io.AbstractIoRegistry;
import org.apache.tinkerpop.gremlin.structure.io.gryo.GryoIo;
import org.apache.spark.util.collection.CompactBuffer;
// import edu.juris.graph.ioregs.CompactBufferSerializer;

public final class SparkInternalsIoRegistry extends AbstractIoRegistry {

    private static final SparkInternalsIoRegistry INSTANCE = new SparkInternalsIoRegistry();

    private SparkInternalsIoRegistry() {
	register(GryoIo.class, CompactBuffer.class, new CompactBufferSerializer());
	register(GryoIo.class, CompactBuffer[].class, null);
    }

    public static SparkInternalsIoRegistry getInstance() {
	return INSTANCE;
    }   
}
