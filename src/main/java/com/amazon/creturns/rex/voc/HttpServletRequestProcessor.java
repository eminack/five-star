package com.amazon.creturns.rex.voc;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.stream.Collectors;

@Log4j2
@Component
public class HttpServletRequestProcessor {

    /**
     * Returns the request body extracted from httpServletRequest
     * @param request the request whose body needs to be extracted
     * @return json string containing request Body.
     */
    public static String convertRequestBodyToJsonString(final HttpServletRequest request) {

        try (final BufferedReader reader = request.getReader()) {
             return reader.lines().collect(Collectors.joining());
        } catch (final Exception e) {
            log.error("JSON body form servlet request is empty", e);
        }

        /*return empty String if body is empty, will handle exceptions due to this in serializer &
        deserializer classes*/
        return "";
    }
}
