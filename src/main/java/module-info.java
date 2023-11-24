module dev.mccue.json.jackson {
    requires transitive com.fasterxml.jackson.databind;
    requires transitive dev.mccue.json;

    exports dev.mccue.json.jackson;
}