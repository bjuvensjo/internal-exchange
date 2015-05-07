package org.mbj.camel.component.internal.exchange;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultExchange;

public class ExchangeProcessor implements Processor {
    private String endpointUri;
    private ProducerTemplate producerTemplate;
    private boolean copy;
    private String propertyName;

    public ExchangeProcessor(String endpointUri, ProducerTemplate producerTemplate, boolean copy, String propertyName) {
        this.endpointUri = endpointUri;
        this.producerTemplate = producerTemplate;
        this.copy = copy;
        this.propertyName = propertyName;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Exchange internalExchange = send(exchange);

        exchange.setProperty(propertyName, internalExchange);
    }

    private Exchange send(Exchange exchange) {
        Exchange internalExchange = copy ? exchange.copy() : new DefaultExchange(exchange.getContext(), exchange.getPattern());

        return producerTemplate.send(endpointUri, internalExchange);
    }
}
