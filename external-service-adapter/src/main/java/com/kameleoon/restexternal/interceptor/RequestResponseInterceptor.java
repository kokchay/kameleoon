package com.kameleoon.restexternal.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class RequestResponseInterceptor implements ClientHttpRequestInterceptor {

  private final ObjectMapper objectMapper;
  private static final String PATTERN = "dd.MM.yyyy HH:mm:ss";

  public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
    ZonedDateTime start = LocalDateTime.now().atZone(ZoneId.systemDefault());
    ClientHttpResponse response = execution.execute(request, body);
    ZonedDateTime end = LocalDateTime.now().atZone(ZoneId.systemDefault());
    this.logRequest(response, request, body, start, end);
    return response;
  }

  private synchronized void logRequest(ClientHttpResponse response, HttpRequest request, byte[] body, ZonedDateTime start, ZonedDateTime end) {
    try {
      String requestStr = new String(body, StandardCharsets.UTF_8);
      String responseStr = StreamUtils.copyToString(response.getBody(), Charset.defaultCharset());
      long difference = end.toInstant().toEpochMilli() - start.toInstant().toEpochMilli();
      Map<String, String> map = new HashMap<>();
      map.put("ClassName:", this.getClass().getName());
      map.put("RequestURI", String.format("%s", request.getURI()));
      map.put("RequestMethod", request.getMethod().toString());
      map.put("RequestHeaders", String.format("%s", request.getHeaders()));
      map.put("RequestContentLength", String.format("%s", body.length));
      map.put("RequestBody", requestStr);
      map.put("ResponseContentLength", String.format("%s", response.getBody().available()));
      map.put("ResponseHeaders", String.format("%s", response.getHeaders()));
      map.put("ResponseStatusCode", String.format("%s", response.getStatusCode()));
      map.put("ResponseStatusMessage", response.getStatusText());
      map.put("ResponseBody", responseStr);
      map.put("RequestStartedAt", DateTimeFormatter.ofPattern(PATTERN).format(start));
      map.put("ResponseFinishedAt", DateTimeFormatter.ofPattern(PATTERN).format(end));
      map.put("TotalRequestTime", String.format("%sms", difference));
      MDC.put("responseHttpStatus", String.valueOf(response.getStatusCode()));
      MDC.put("traceId", request.getHeaders().getFirst("X-B3-TraceId"));
      log.info(this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map));
      MDC.clear();
    } catch (Exception var11) {
      log.error(var11.getMessage());
    }

  }
}
