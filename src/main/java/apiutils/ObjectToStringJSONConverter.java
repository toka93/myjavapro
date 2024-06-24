package apiutils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToStringJSONConverter {
	static Logger log = LogManager.getLogger(ObjectToStringJSONConverter.class);

    public static String convertToStringJSON(Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
        	log.info("exception:{}",e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
