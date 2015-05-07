package org.mbj.camel.component.internal.exchange;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.ProcessorEndpoint;
import org.apache.camel.spi.UriParam;

import java.util.Set;

public class ExchangeProcessorEndpoint extends ProcessorEndpoint {
    @UriParam
    private boolean copy;
    @UriParam
    private String property;

    public ExchangeProcessorEndpoint(String endpointUri, CamelContext camelContext) {
        super(endpointUri, camelContext, null);
        this.copy = true;
    }

    public void setCopy(boolean copy) {
        this.copy = copy;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    protected org.apache.camel.Processor createProcessor() throws Exception {
        return new ExchangeProcessor(this.getEndpointUri(), getProducerTemplate(), copy, property);
    }

    private ProducerTemplate getProducerTemplate() {
        Set<ProducerTemplate> producerTemplates = this.getCamelContext().getRegistry().findByType(ProducerTemplate.class);
        ProducerTemplate producerTemplate;

        if (producerTemplates.isEmpty()) {
            producerTemplate = this.getCamelContext().createProducerTemplate();
        } else {
            producerTemplate = producerTemplates.iterator().next();
        }
        return producerTemplate;
    }
}
