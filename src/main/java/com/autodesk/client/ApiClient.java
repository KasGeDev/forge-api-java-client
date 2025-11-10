package com.autodesk.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
// If you still use RFC3339DateFormat in your project, keep this import:
import com.autodesk.client.RFC3339DateFormat;

import com.fasterxml.jackson.jakarta.rs.json.JacksonJsonProvider;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status.Family;

import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.filter.EncodingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.message.GZipEncoder;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.stream.Collectors;

import com.autodesk.client.auth.*;

/**
 * Jakarta/Jersey 3 compatible ApiClient
 */
public class ApiClient {

    private final Map<String, String> defaultHeaderMap = new HashMap<>();
    private String basePath = "https://developer.api.autodesk.com/";
    private boolean debugging = false;
    private int connectionTimeout = 0;

    private Client httpClient;
    private ObjectMapper objectMapper;

    private int statusCode;
    private Map<String, List<String>> responseHeaders;

    private DateFormat dateFormat;

    public ApiClient() {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        // Joda module removed; use java.time or Date
        objectMapper.setDateFormat(ApiClient.buildDefaultDateFormat());

        dateFormat = ApiClient.buildDefaultDateFormat();
        rebuildHttpClient();
    }

    public static DateFormat buildDefaultDateFormat() {
        return new RFC3339DateFormat();
    }

    /**
     * Build the Client used to make HTTP requests with the latest settings.
     */
    public ApiClient rebuildHttpClient() {
        ClientBuilder builder = ClientBuilder.newBuilder();

        // Register Jackson support with our ObjectMapper
        builder.register(new JacksonJsonProvider(objectMapper));
        builder.register(JacksonFeature.class);

        // Multipart / file upload
        builder.register(MultiPartFeature.class);

        // GZIP (request + transparent response decoding)
        builder.register(EncodingFilter.class);
        builder.register(GZipEncoder.class);

        // Logging (debug only)
        if (debugging) {
            builder.register(new LoggingFeature(
                    java.util.logging.Logger.getLogger(ApiClient.class.getName()),
                    Level.INFO,
                    LoggingFeature.Verbosity.PAYLOAD_ANY,
                    8192));
        }

        Client client = builder.build();

        // Apply connect timeout if set (>0). (Read timeout can be added similarly if needed)
        if (connectionTimeout > 0) {
            client.property(ClientProperties.CONNECT_TIMEOUT, connectionTimeout);
        }

        this.httpClient = client;
        return this;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public ApiClient setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        rebuildHttpClient();
        return this;
    }

    public Client getHttpClient() {
        return httpClient;
    }

    public ApiClient setHttpClient(Client httpClient) {
        this.httpClient = httpClient;
        return this;
    }

    public String getBasePath() {
        return basePath;
    }

    public ApiClient setBasePath(String basePath) {
        this.basePath = basePath;
        return this;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Map<String, List<String>> getResponseHeaders() {
        return responseHeaders;
    }

    public ApiClient setUserAgent(String userAgent) {
        addDefaultHeader("User-Agent", userAgent);
        return this;
    }

    public ApiClient addDefaultHeader(String key, String value) {
        defaultHeaderMap.put(key, value);
        return this;
    }

    public boolean isDebugging() {
        return debugging;
    }

    public ApiClient setDebugging(boolean debugging) {
        this.debugging = debugging;
        rebuildHttpClient();
        return this;
    }

    public int getConnectTimeout() {
        return connectionTimeout;
    }

    public ApiClient setConnectTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
        if (httpClient != null) {
            httpClient.property(ClientProperties.CONNECT_TIMEOUT, connectionTimeout);
        }
        return this;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public ApiClient setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
        this.objectMapper.setDateFormat((DateFormat) dateFormat.clone());
        rebuildHttpClient();
        return this;
    }

    public Date parseDate(String str) {
        try {
            return dateFormat.parse(str);
        } catch (java.text.ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String formatDate(Date date) {
        return dateFormat.format(date);
    }

    public String parameterToString(Object param) {
        if (param == null) {
            return "";
        } else if (param instanceof Date) {
            return formatDate((Date) param);
        } else if (param instanceof Collection) {
            StringBuilder b = new StringBuilder();
            for (Object o : (Collection<?>) param) {
                if (b.length() > 0) {
                    b.append(",");
                }
                b.append(String.valueOf(o));
            }
            return b.toString();
        } else {
            return String.valueOf(param);
        }
    }

    public List<Pair> parameterToPairs(String collectionFormat, String name, Object value) {
        List<Pair> params = new ArrayList<>();

        if (name == null || name.isEmpty() || value == null) return params;

        Collection<?> valueCollection = (value instanceof Collection<?>) ? (Collection<?>) value : null;
        if (valueCollection == null) {
            params.add(new Pair(name, parameterToString(value)));
            return params;
        }
        if (valueCollection.isEmpty()) return params;

        collectionFormat = (collectionFormat == null || collectionFormat.isEmpty()) ? "csv" : collectionFormat;

        if (collectionFormat.equals("multi")) {
            for (Object item : valueCollection) {
                params.add(new Pair(name, parameterToString(item)));
            }
            return params;
        }

        String delimiter = ",";
        if (collectionFormat.equals("ssv")) delimiter = " ";
        else if (collectionFormat.equals("tsv")) delimiter = "\t";
        else if (collectionFormat.equals("pipes")) delimiter = "|";

        StringBuilder sb = new StringBuilder();
        for (Object item : valueCollection) {
            sb.append(delimiter).append(parameterToString(item));
        }
        params.add(new Pair(name, sb.substring(1)));
        return params;
    }

    public boolean isJsonMime(String mime) {
        return mime != null && mime.matches("(?i)application\\/json(;.*)?");
    }

    public String selectHeaderAccept(String[] accepts) {
        if (accepts.length == 0) return null;
        for (String accept : accepts) {
            if (isJsonMime(accept)) return accept;
        }
        return StringUtil.join(accepts, ",");
    }

    public String selectHeaderContentType(String[] contentTypes) {
        if (contentTypes.length == 0) return "application/json";
        for (String contentType : contentTypes) {
            if (isJsonMime(contentType)) return contentType;
        }
        return contentTypes[0];
    }

    public String escapeString(String str) {
        try {
            return URLEncoder.encode(str, StandardCharsets.UTF_8.name()).replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    public Object serialize(Object obj, String contentType, Map<String, Object> formParams) throws ApiException {
        if (contentType.startsWith("multipart/form-data")) {
            FormDataMultiPart mp = new FormDataMultiPart();
            for (Entry<String, Object> param : formParams.entrySet()) {
                if (param.getValue() instanceof File) {
                    File file = (File) param.getValue();
                    mp.bodyPart(new FileDataBodyPart(param.getKey(), file, MediaType.MULTIPART_FORM_DATA_TYPE));
                } else {
                    mp.field(param.getKey(), parameterToString(param.getValue()), MediaType.MULTIPART_FORM_DATA_TYPE);
                }
            }
            return mp;
        } else if (contentType.startsWith("application/x-www-form-urlencoded")) {
            return this.getXWWWFormUrlencodedParams(formParams);
        } else {
            // Let Jersey serialize as JSON via JacksonFeature
            return obj;
        }
    }

    private String buildUrl(String path, List<Pair> queryParams) {
        final StringBuilder url = new StringBuilder();
        url.append(basePath).append(path);

        if (queryParams != null && !queryParams.isEmpty()) {
            String prefix = path.contains("?") ? "&" : "?";
            for (Pair param : queryParams) {
                if (param.getValue() != null) {
                    if (prefix != null) {
                        url.append(prefix);
                        prefix = null;
                    } else {
                        url.append("&");
                    }
                    String value = parameterToString(param.getValue());
                    url.append(escapeString(param.getName())).append("=").append(escapeString(value));
                }
            }
        }
        return url.toString();
    }

    private Response getAPIResponse(
            Credentials credentials,
            String path,
            String method,
            List<Pair> queryParams,
            Object body,
            Map<String, String> headerParams,
            Map<String, Object> formParams,
            String accept,
            String contentType
    ) throws ApiException {

        if (body != null && !formParams.isEmpty()) {
            throw new ApiException(500, "Cannot have body and form params");
        }

        Object requestBody = null;
        MediaType requestMediaType = contentType != null ? MediaType.valueOf(contentType) : MediaType.APPLICATION_JSON_TYPE;

        if (body != null) {
            if (body.getClass().equals(File.class)) {
                try {
                    File file = (File) body;
                    requestBody = new FileInputStream(file);
                    headerParams.put("Content-Length", Long.toString(file.length()));
                    requestMediaType = MediaType.APPLICATION_OCTET_STREAM_TYPE;
                } catch (IOException e) {
                    throw new ApiException(400, "There was a problem reading the file: " + e.getMessage());
                }
            } else {
                requestBody = serialize(body, contentType != null ? contentType : MediaType.APPLICATION_JSON, formParams);
            }
        }

        updateParamsForAuth(credentials, headerParams);

        final String url = buildUrl(path, queryParams);
        WebTarget target = httpClient.target(url);
        Invocation.Builder builder = (accept == null) ? target.request() : target.request().accept(accept);

        for (String key : headerParams.keySet()) {
            builder = builder.header(key, headerParams.get(key));
        }
        for (String key : defaultHeaderMap.keySet()) {
            if (!headerParams.containsKey(key)) {
                builder = builder.header(key, defaultHeaderMap.get(key));
            }
        }

        Response response;
        switch (method) {
            case "GET":
                response = builder.get();
                break;
            case "POST":
                response = builder.method("POST", Entity.entity(requestBody, requestMediaType));
                break;
            case "PUT":
                response = builder.method("PUT", Entity.entity(requestBody, requestMediaType));
                break;
            case "DELETE":
                if (requestBody != null) {
                    response = builder.method("DELETE", Entity.entity(requestBody, requestMediaType));
                } else {
                    response = builder.delete();
                }
                break;
            case "PATCH":
                response = builder.method("PATCH", Entity.entity(requestBody, requestMediaType));
                break;
            default:
                throw new ApiException(500, "unknown method type " + method);
        }

        return response;
    }

    /**
     * Invoke API by sending HTTP request with the given options.
     */
    public <T> ApiResponse<T> invokeAPI(
            Authentication oauth2,
            Credentials credentials,
            String path,
            String method,
            List<Pair> queryParams,
            Object body,
            Map<String, String> headerParams,
            Map<String, Object> formParams,
            String accept,
            String contentType,
            GenericType<T> returnType
    ) throws ApiException, Exception {

        // Auto refresh tokens when applicable
        if (oauth2 instanceof OAuth2TwoLegged) {
            OAuth2TwoLegged oauth2TwoLegged = (OAuth2TwoLegged) oauth2;
            if (oauth2TwoLegged.isAutoRefresh() && oauth2TwoLegged.isAccessTokenExpired()) {
                credentials = oauth2TwoLegged.authenticate();
            }
        } else if (oauth2 instanceof OAuth2ThreeLegged && credentials instanceof ThreeLeggedCredentials) {
            OAuth2ThreeLegged oauth2ThreeLegged = (OAuth2ThreeLegged) oauth2;
            if (oauth2ThreeLegged.isAutoRefresh() && !oauth2ThreeLegged.isAuthorized((ThreeLeggedCredentials) credentials)) {
                credentials = oauth2ThreeLegged.refreshAccessToken(((ThreeLeggedCredentials) credentials).getRefreshToken());
            }
        }

        Response response = getAPIResponse(credentials, path, method, queryParams, body, headerParams, formParams, accept, contentType);

        statusCode = response.getStatus();
        responseHeaders = toStringHeaderMap(response.getHeaders());

        if (statusCode == Response.Status.NO_CONTENT.getStatusCode()) {
            return null;
        } else if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
            if (returnType == null || statusCode == 202) {
                return new ApiResponse<>(statusCode, responseHeaders, null);
            } else {
                T entity = response.readEntity(returnType);
                return new ApiResponse<>(statusCode, responseHeaders, entity);
            }
        } else {
            String message = "error";
            String respBody = null;
            try {
                if (response.hasEntity()) {
                    respBody = response.readEntity(String.class);
                    if (respBody != null && !respBody.isEmpty()) {
                        message = respBody;
                    }
                }
            } catch (RuntimeException ignored) {
            }
            throw new ApiException(statusCode, message, responseHeaders, respBody);
        }
    }

    private static Map<String, List<String>> toStringHeaderMap(MultivaluedMap<String, Object> headers) {
        Map<String, List<String>> map = new HashMap<>();
        for (Map.Entry<String, List<Object>> e : headers.entrySet()) {
            List<String> vals = e.getValue() == null
                    ? Collections.emptyList()
                    : e.getValue().stream().map(String::valueOf).collect(Collectors.toList());
            map.put(e.getKey(), vals);
        }
        return map;
    }

    /**
     * Add Authorization header parameters.
     */
    private void updateParamsForAuth(Credentials credentials, Map<String, String> headerParams) {
        if (credentials != null && credentials.getAccessToken() != null) {
            headerParams.put("Authorization", "Bearer " + credentials.getAccessToken());
        }
    }

    /**
     * Encode the given form parameters as request body.
     */
    private String getXWWWFormUrlencodedParams(Map<String, Object> formParams) {
        StringBuilder formParamBuilder = new StringBuilder();
        for (Entry<String, Object> param : formParams.entrySet()) {
            String valueStr = parameterToString(param.getValue());
            try {
                formParamBuilder
                        .append(URLEncoder.encode(param.getKey(), StandardCharsets.UTF_8.name()))
                        .append("=")
                        .append(URLEncoder.encode(valueStr, StandardCharsets.UTF_8.name()))
                        .append("&");
            } catch (UnsupportedEncodingException ignored) {
            }
        }
        String encoded = formParamBuilder.toString();
        if (encoded.endsWith("&")) {
            encoded = encoded.substring(0, encoded.length() - 1);
        }
        return encoded;
    }
}
