/*
 * Copyright (c) 2018, Jamie Mansfield <https://jamiemansfield.me/>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 *  Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 *  Neither the name of the copyright holder nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package me.jamiemansfield.bombe.test.type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import me.jamiemansfield.bombe.type.ArrayType;
import me.jamiemansfield.bombe.type.ObjectType;
import me.jamiemansfield.bombe.type.PrimitiveType;
import me.jamiemansfield.bombe.type.Type;
import me.jamiemansfield.bombe.type.VoidType;
import org.junit.jupiter.api.Test;

/**
 * Unit tests pertaining to the type model in Bombe.
 */
public final class TypeTest {

    @Test
    public void arrayType() {
        final String raw = "[[I";
        final Type type = Type.of(raw);
        assertTrue(type instanceof ArrayType, "Type should be an ArrayType!");
        assertEquals(raw, type.toString());
        final ArrayType array = (ArrayType) type;
        assertEquals(2, array.getDimCount());
        assertEquals(PrimitiveType.INT, array.getComponent());
    }

    @Test
    public void objectTest() {
        final String raw = "Lme/jamiemansfield/Test;";
        final Type type = Type.of(raw);
        assertTrue(type instanceof ObjectType, "Type should be an ObjectType!");
        assertEquals(raw, type.toString());
    }

    @Test
    public void primitiveTest() {
        final String raw = "Z";
        final Type type = Type.of(raw);
        assertTrue(type instanceof PrimitiveType, "Type should be an PrimitiveType!");
        assertEquals(PrimitiveType.BOOLEAN, type);
        assertEquals(raw, type.toString());
    }

    @Test
    public void voidTest() {
        final String raw = "V";
        final Type type = Type.of(raw);
        assertTrue(type instanceof VoidType, "Type should be an VoidType!");
        assertEquals(VoidType.INSTANCE, type);
        assertEquals(raw, type.toString());
    }

    @Test
    public void invalidTest() {
        assertThrows(RuntimeException.class, () -> Type.of("Jungle"));
        assertThrows(RuntimeException.class, () -> Type.of("A"));
    }

}
