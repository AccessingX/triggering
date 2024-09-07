package org.attomicron.event.trigger;

import org.attomicron.event.trigger.data.TriggerCondition;
import org.attomicron.event.trigger.data.TriggerMetaData;
import org.attomicron.event.trigger.interfaces.CanTriggered;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

@SuppressWarnings("unchecked")
public class TriggerExecutor<X, C extends CanTriggered, M extends TriggerMetaData<C>> {

    protected final Function<? extends Event, X> mappingFunction;

    protected final List<TriggeredMapping<X, C>> mappings = new ArrayList<>();

    protected final BiFunction<Integer, C, M> metaDataMappingFunction;

    public <E extends Event> TriggerExecutor(
            Function<E, X> mappingFunction,
            BiFunction<Integer, C, M> metaDataMappingFunction
    ) {
        this.mappingFunction = mappingFunction;
        this.metaDataMappingFunction = metaDataMappingFunction;
    }

    public <E extends Event> void execute(E event) {
        X object = ((Function<E, X>) this.mappingFunction).apply(event);
        for (TriggeredMapping<X, C> mapping : mappings) {
            Map<Integer, C> triggered = mapping.mappingFunction.apply(object);
            if (triggered == null || triggered.isEmpty()) {
                continue;
            }

            for (Map.Entry<Integer, C> triggeredEntry : triggered.entrySet()) {
                int index = triggeredEntry.getKey();
                C canTriggered = triggeredEntry.getValue();

                loop:
                for (Trigger<?, ?, ?, ?> tr : canTriggered.getTriggers()) {
                    if (!(tr.canTriggered(event))) {
                        continue;
                    }

                    Trigger<E, C, X, M> trigger = (Trigger<E, C, X, M>) tr;

                    M metadata = this.metaDataMappingFunction.apply(index, canTriggered);
                    for (TriggerCondition<E, C, X, M> condition : trigger.conditions) {
                        if (!(condition.test(event, mapping.getType(), canTriggered, object, metadata))) {
                            continue loop;
                        }
                    }
                    trigger.activate(event, mapping.getType(), object, canTriggered, metadata);
                }
            }
        }
    }

}
