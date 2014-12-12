package de.androbit.wob.jira.gadget;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;
import java.util.List;

@JsonSerialize(using = StatisticsValues.StatisticsSerializer.class)
public class StatisticsValues {
    public static class StatisticsSerializer extends JsonSerializer<StatisticsValues> {
        public void serialize(StatisticsValues statistics, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeStartObject();
            jgen.writeObjectField(statistics.getName(), statistics.getValues());
            jgen.writeEndObject();
        }
    }

    final String name;
    final List<Integer> values;

    public StatisticsValues(String name, List<Integer> values) {
        this.name = name;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getValues() {
        return values;
    }
}
