/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.module.extension.internal.config;

import static org.mule.module.extension.internal.config.XmlExtensionParserUtils.getResolverSet;
import org.mule.api.processor.MessageProcessor;
import org.mule.extension.introspection.Operation;
import org.mule.module.extension.internal.runtime.processor.OperationMessageProcessor;
import org.mule.module.extension.internal.runtime.resolver.ResolverSet;
import org.mule.module.extension.internal.runtime.resolver.ValueResolver;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.FactoryBean;

/**
 * A {@link FactoryBean} which yields instances of {@link OperationMessageProcessor}
 *
 * @since 3.7.0
 */
public class OperationFactoryBean implements FactoryBean<OperationMessageProcessor>
{

    private final ValueResolver<Object> configurationValueResolver;
    private final Operation operation;
    private final ElementDescriptor element;
    private final Map<String, List<MessageProcessor>> nestedOperations;

    public OperationFactoryBean(ValueResolver<Object> configurationValueResolver,
                                Operation operation,
                                ElementDescriptor element,
                                Map<String, List<MessageProcessor>> nestedOperations)
    {
        this.configurationValueResolver = configurationValueResolver;
        this.operation = operation;
        this.element = element;
        this.nestedOperations = nestedOperations;
    }

    @Override
    public OperationMessageProcessor getObject() throws Exception
    {
        ResolverSet resolverSet = getResolverSet(element, operation.getParameters(), nestedOperations);
        return new OperationMessageProcessor(configurationValueResolver, operation, resolverSet);
    }

    /**
     * @return {@link OperationMessageProcessor}
     */
    @Override
    public Class<?> getObjectType()
    {
        return OperationMessageProcessor.class;
    }

    /**
     * @return {@value false}
     */
    @Override
    public boolean isSingleton()
    {
        return false;
    }
}
