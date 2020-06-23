package com.amazon.creturns.rex.voc;

import com.amazonaws.services.dynamodbv2.xspec.S;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
@Log4j2
@Component
public class HttpServletRequestProcessor {
    private static final StringBuilder builder=new StringBuilder();

    public String convertRequestBodyToJsonString(HttpServletRequest request){
        String jsonString="";
        try (final BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            jsonString = builder.toString();
            builder.setLength(0);
        } catch (final Exception e) {
            log.error("Error in Extracting JSON body form servlet request " + e.getMessage());
            e.printStackTrace();
        }
        return jsonString;
    }
}
