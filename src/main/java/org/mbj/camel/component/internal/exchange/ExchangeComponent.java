package org.mbj.camel.component.internal.exchange;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;

import java.util.Map;

public class ExchangeComponent extends DefaultComponent {

    @Override
    protected Endpoint createEndpoint(final String uri, final String remaining, final Map<String, Object> parameters) throws Exception {
        return new ExchangeProcessorEndpoint(remaining, this.getCamelContext());
    }
}
