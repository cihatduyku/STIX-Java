package io.digitalstate.stix.domainobjects;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.digitalstate.stix.domainobjects.properties.ReportProperties;
import io.digitalstate.stix.helpers.StixDataFormats;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Optional;

import static io.digitalstate.stix.domainobjects.json.CommonPropertiesDeserializationValidators.validateAllCommonProperties;
import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

@JsonDeserialize(using = Report.Deserializer.class)
public class Report extends ReportProperties implements StixDomainObject {

    private static final String TYPE = "report";

    public Report(String name,
                  LinkedHashSet<String> reportLabels,
                  ZonedDateTime publishedDateTime,
                  LinkedHashSet<String> objects) {

        setType(TYPE);
        setId(TYPE, generateUuidAsString());
        setName(name);
        setLabels(reportLabels);
        setPublished(publishedDateTime);
        setObjectRefs(objects);
    }

    public Report(String name,
                  LinkedHashSet<String> reportLabels,
                  ZonedDateTime publishedDateTime,
                  String... objects) {

        this(name, reportLabels, publishedDateTime, new LinkedHashSet<>(Arrays.asList(objects)));
    }

    public Report(String name,
                  String[] reportLabels,
                  ZonedDateTime publishedDateTime,
                  String... objects) {

        this(name, new LinkedHashSet<>(Arrays.asList(reportLabels)), publishedDateTime, new LinkedHashSet<>(Arrays.asList(objects)));
    }


    /**
     * Used for JSON Deserialization
     */
    private Report() {
    }

    public static class Deserializer extends StdDeserializer<Report> {

        public Deserializer() {
            this(null);
        }

        public Deserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public Report deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {

            JsonNode node = jp.getCodec().readTree(jp);

            Report object = new Report();

            validateAllCommonProperties(node, object, TYPE, true);

            Optional<JsonNode> name = Optional.ofNullable(node.get("name"));
            name.ifPresent(o -> {
                object.setName(o.textValue());
            });
            name.orElseThrow(() -> new IllegalArgumentException("Name is required"));

            Optional<JsonNode> description = Optional.ofNullable(node.get("description"));
            description.ifPresent(o -> {
                object.setDescription(o.textValue());
            });

            Optional<JsonNode> published = Optional.ofNullable(node.get("published"));
            published.ifPresent(o -> {
                Instant instant = Instant.parse(o.asText());
                object.setPublished(instant.atZone(ZoneId.of(StixDataFormats.DATETIMEZONE)));
            });
            published.orElseThrow(() -> new IllegalArgumentException("publish is required"));

            Optional<JsonNode> reportObjects = Optional.ofNullable(node.get("objects"));
            reportObjects.ifPresent(o -> {
                //@TODO
            });

            return object;
        }
    }
}
