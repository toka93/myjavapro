package apiutils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Objects;

public class JsonSchemaValidator {
	static Logger log = LogManager.getLogger(JsonSchemaValidator.class);
    private static final String JSON_SCHEMA_FILE_LOCATION = System.getProperty("os.name")
            .toLowerCase().contains("windows") ? "/jsonschemas/" : "\\jsonschemas\\";

    public static boolean isJsonSchemaValid(String validSchemaName, String json) {
        try {
            JSONObject jsonSchema = new JSONObject(
                    new JSONTokener(Objects.requireNonNull(JsonSchemaValidator.class
                            .getResourceAsStream(JSON_SCHEMA_FILE_LOCATION + validSchemaName))));

            JSONObject jsonSubject = new JSONObject(
                    new JSONTokener(json));

            Schema schema = SchemaLoader.load(jsonSchema);
            schema.validate(jsonSubject);
        } catch (JSONException je) {
        	log.info(je.toString());
        	
            return false;
        }
        return true;
    }
}
