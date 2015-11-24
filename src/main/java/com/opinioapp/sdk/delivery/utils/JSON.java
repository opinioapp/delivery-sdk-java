package com.opinioapp.sdk.delivery.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;


/**
 * Created by lokesh on 4/11/15.
 *
 * Utility for handling JSON
 * - uses Jackson
 */
public class JSON {

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    public static <T> T convert(Object input, Class<T> output) {
        return mapper.convertValue(input, output);
    }

    /**
     * just to make sure that this class is not instantiated
     */
    private JSON() {
    }

    /**
     * parses String in json format and maps it to
     * an object of given class
     *
     * @param json     String
     * @param classOfT class to map the json to
     * @return T
     */
    public static <T> T parse(String json, Class<T> classOfT, Class<?>... innerClassTypes) {
        try {
            if (json == null) {
                return null;
            }
            JavaType parametricType = mapper.getTypeFactory().constructParametrizedType(classOfT, classOfT, innerClassTypes);
            return mapper.readValue(json, parametricType);

        } catch (JsonParseException e) {
            System.err.println("JsonParseException Failed to parse json correctly. " + e);
        } catch (JsonMappingException e) {
            System.err.println("JsonMappingException Failed to parse json correctly. " + e);
        } catch (IOException e) {
            System.err.println("IOException Failed to parse json correctly. " + e);
        }
        return null;
    }

    /**
     * parses String in json format and maps it to
     * an object of given class
     *
     * @param json     String
     * @param classOfT class to map the json to
     * @return T
     */
    public static <T> T parse(String json, Class<T> classOfT) {
        try {
            if (json == null) {
                return null;
            }
            return mapper.readValue(json, classOfT);

        } catch (JsonParseException e) {
            System.err.println("JsonParseException Failed to parse json correctly. " + e);
        } catch (JsonMappingException e) {
            System.err.println("JsonMappingException Failed to parse json correctly. " + e);
        } catch (IOException e) {
            System.err.println("IOException Failed to parse json correctly. " + e);
        }
        return null;
    }

    /**
     * Convert java object to JSON
     * @param object object to parse
     * @return JSON representation
     */

    public static String stringify(Object object) {
        try {
            return mapper.writeValueAsString(object);

        } catch (JsonProcessingException e) {
            System.err.println("JsonProcessingException Failed to stringify json correctly. " + e);
            return null;
        }
    }

}
