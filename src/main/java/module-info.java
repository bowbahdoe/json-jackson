import com.fasterxml.jackson.databind.Module;
import dev.mccue.json.jackson.JsonModule;

module dev.mccue.json.jackson {
    requires transitive com.fasterxml.jackson.databind;
    requires transitive dev.mccue.json;

    exports dev.mccue.json.jackson;

    provides Module with JsonModule;
}