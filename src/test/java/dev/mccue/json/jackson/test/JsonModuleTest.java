package dev.mccue.json.jackson.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mccue.json.Json;
import dev.mccue.json.JsonEncodable;
import dev.mccue.json.jackson.JsonModule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonModuleTest {

    static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new JsonModule());

    @Test
    public void testBasicJson() throws JsonProcessingException {
        assertEquals("\"hello, world\"", OBJECT_MAPPER.writeValueAsString(Json.of("hello, world")));
        assertEquals("12", OBJECT_MAPPER.writeValueAsString(Json.of(12)));
        assertEquals("true", OBJECT_MAPPER.writeValueAsString(Json.ofTrue()));
        assertEquals("false", OBJECT_MAPPER.writeValueAsString(Json.ofFalse()));
        assertEquals("null", OBJECT_MAPPER.writeValueAsString(Json.ofNull()));
    }

    @Test
    public void testObject() throws JsonProcessingException {
        assertEquals("{\"a\":\"b\",\"c\":\"d\"}", OBJECT_MAPPER.writeValueAsString(
                Json.objectBuilder()
                        .put("a", "b")
                        .put("c", "d")
                        .build()
        ));
    }

    @Test
    public void testArray() throws JsonProcessingException {
        assertEquals("[\"a\",\"b\",\"c\",\"d\",null]", OBJECT_MAPPER.writeValueAsString(
                Json.arrayBuilder()
                        .add("a")
                        .add("b")
                        .add("c")
                        .add("d")
                        .addNull()
                        .build()
        ));
    }

    @Test
    public void testCustomEncodable() throws JsonProcessingException {
        record Apple(int size, String shape) implements JsonEncodable {

            @Override
            public Json toJson() {
                return Json.objectBuilder()
                        .put("SIZE", this.size)
                        .put("SHAPE", this.shape)
                        .build();
            }
        }

        assertEquals("{\"SIZE\":5,\"SHAPE\":\"round\"}", OBJECT_MAPPER.writeValueAsString(new Apple(5, "round")));
    }
}
