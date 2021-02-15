package gov.va.api.health.r4.api.resources;

import static java.util.stream.Collectors.toSet;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Iterables;
import com.google.common.collect.Streams;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.bundle.MixedBundle;
import gov.va.api.health.r4.api.elements.Meta;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Set;
import javax.validation.constraints.Pattern;
import lombok.SneakyThrows;

@Schema(description = "https://www.hl7.org/fhir/R4/resource.html")
public interface Resource {
  @Pattern(regexp = Fhir.STRING)
  String id();

  @Pattern(regexp = Fhir.STRING)
  String implicitRules();

  @Pattern(regexp = Fhir.STRING)
  String language();

  Meta meta();

  public final class ResourceDeserializer extends StdDeserializer<Resource> {
    public ResourceDeserializer() {
      this(null);
    }

    public ResourceDeserializer(Class<?> vc) {
      super(vc);
    }

    @Override
    @SneakyThrows
    public Resource deserialize(JsonParser jp, DeserializationContext ctxt) {
      ObjectMapper mapper = (ObjectMapper) jp.getCodec();
      ObjectNode root = mapper.readTree(jp);
      String type = root.get("resourceType").asText();
      if (!type.equals("Bundle")) {
        Class<?> clazz = Class.forName(Resource.class.getPackageName() + "." + type);
        return (Resource) mapper.readValue(root.toString(), clazz);
      }
      // Bundle
      Set<String> entryTypes =
          Streams.stream((ArrayNode) root.get("entry"))
              .map(node -> node.get("resource").get("resourceType").asText())
              .collect(toSet());
      if (entryTypes.size() != 1) {
        return mapper.readValue(root.toString(), MixedBundle.class);
      }
      String highlander = Iterables.getOnlyElement(entryTypes);
      if (highlander.equals("Bundle")) {
        return mapper.readValue(root.toString(), MixedBundle.class);
      }
      Class<?> bundleClazz =
          Class.forName(Resource.class.getPackageName() + "." + highlander + "$Bundle");
      return (Resource) mapper.readValue(root.toString(), bundleClazz);
    }
  }

  public final class ResourceModule extends SimpleModule {
    public ResourceModule() {
      addDeserializer(Resource.class, new ResourceDeserializer());
    }
  }
}
