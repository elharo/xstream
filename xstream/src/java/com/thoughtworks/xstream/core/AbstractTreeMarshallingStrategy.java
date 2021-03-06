/*
 * Copyright (C) 2006, 2007, 2008, 2009, 2014 XStream Committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 * 
 * Created on 26.09.2007 by Joerg Schaible
 */
package com.thoughtworks.xstream.core;

import com.thoughtworks.xstream.MarshallingStrategy;
import com.thoughtworks.xstream.converters.ConverterLookup;
import com.thoughtworks.xstream.converters.DataHolder;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;


/**
 * Basic functionality of a tree based marshalling strategy.
 * 
 * @author Joe Walnes
 * @author J&ouml;rg Schaible
 * @since 1.3
 */
public abstract class AbstractTreeMarshallingStrategy implements MarshallingStrategy {

    @Override
    public Object unmarshal(final Object root, final HierarchicalStreamReader reader, final DataHolder dataHolder,
            final ConverterLookup converterLookup, final Mapper mapper) {
        final TreeUnmarshaller context = createUnmarshallingContext(root, reader, converterLookup, mapper);
        return context.start(dataHolder);
    }

    @Override
    public void marshal(final HierarchicalStreamWriter writer, final Object obj, final ConverterLookup converterLookup,
            final Mapper mapper, final DataHolder dataHolder) {
        final TreeMarshaller context = createMarshallingContext(writer, converterLookup, mapper);
        context.start(obj, dataHolder);
    }

    protected abstract TreeUnmarshaller createUnmarshallingContext(Object root, HierarchicalStreamReader reader,
            ConverterLookup converterLookup, Mapper mapper);

    protected abstract TreeMarshaller createMarshallingContext(HierarchicalStreamWriter writer,
            ConverterLookup converterLookup, Mapper mapper);
}
