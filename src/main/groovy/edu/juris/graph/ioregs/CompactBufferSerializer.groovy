package edu.juris.graph.ioregs

import org.apache.spark.util.collection.CompactBuffer
import org.apache.tinkerpop.gremlin.structure.io.gryo.kryoshim.InputShim
import org.apache.tinkerpop.gremlin.structure.io.gryo.kryoshim.KryoShim
import org.apache.tinkerpop.gremlin.structure.io.gryo.kryoshim.OutputShim
import org.apache.tinkerpop.gremlin.structure.io.gryo.kryoshim.SerializerShim
import org.apache.tinkerpop.shaded.kryo.Kryo
import org.apache.tinkerpop.shaded.kryo.Serializer
import org.apache.tinkerpop.shaded.kryo.io.Input
import org.apache.tinkerpop.shaded.kryo.io.Output
import scala.reflect.ClassTag

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 * @author Dylan Bethune-Waddell
 */
public final class CompactBufferSerializer<T> implements SerializerShim<CompactBuffer<T>> {

  @Override
  public <O extends OutputShim> void write(final KryoShim<?, O> kryo, final O output, final CompactBuffer<T> compactBuffer) {
        kryo.writeClassAndObject(output, compactBuffer.evidence$1);
        kryo.writeClassAndObject(output, compactBuffer.element0);
        kryo.writeClassAndObject(output, compactBuffer.element1);
        output.flush();
        output.writeVarInt(compactBuffer.org$apache$spark$util$collection$CompactBuffer$$curSize, true);
        kryo.writeClassAndObject(output, compactBuffer.otherElements);
        output.flush();
    }


  @Override
  public <I extends InputShim> CompactBuffer<T> read(final KryoShim<I, ?> kryo, I input, final Class<CompactBuffer<T>> clazz) {
        final ClassTag<T> classTag = kryo.readClassAndObject(input);
        final CompactBuffer<T> compactBuffer = new CompactBuffer<>(classTag);
        compactBuffer.element0 = kryo.readClassAndObject(input);
        compactBuffer.element1 = kryo.readClassAndObject(input);
        compactBuffer.org$apache$spark$util$collection$CompactBuffer$$curSize = input.readVarInt(true);
        compactBuffer.otherElements = kryo.readClassAndObject(input);
        return compactBuffer;
    }
}
