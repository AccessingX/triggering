package org.attomicron.event.trigger;

import org.attomicron.event.trigger.interfaces.CanTriggered;

import java.util.Map;
import java.util.function.Function;

public class TriggeredMapping<X, C extends CanTriggered> {

    private final String type;

    protected final Function<X, Map<Integer, C>> mappingFunction;

    public TriggeredMapping(String type, Function<X, Map<Integer, C>> mappingFunction) {
        this.type = type;
        this.mappingFunction = mappingFunction;
    }

    public String getType() {
        return type;
    }

    public Function<X, Map<Integer, C>> getMappingFunction() {
        return mappingFunction;
    }

}
