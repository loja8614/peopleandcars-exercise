package training.peopleandcars.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapperJson {
    public static String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper =new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
