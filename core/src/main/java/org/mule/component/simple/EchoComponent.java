/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.component.simple;

import org.mule.api.MuleEventContext;
import org.mule.api.component.simple.EchoService;
import org.mule.api.lifecycle.Callable;

/**
 * <code>EchoComponent</code> will log the message and return the payload back as
 * the result.
 */
public class EchoComponent implements EchoService, Callable
{
    @Override
    public Object onCall(MuleEventContext context) throws Exception
    {
        return context.getMessage();
    }

    public String echo(String echo)
    {
        return echo;
    }

}
