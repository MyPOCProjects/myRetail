
package com.hq.myretail.exception;

import java.util.Date;

/**
 */
public class ExceptionResponse {
    private Date timestamp;
    private Integer status;
    private String message;
    private String path;
    private String traceId;

    /**
     * @param timestamp
     * @param status
     * @param message
     * @param path
     * @param traceId
     */
    public ExceptionResponse(Date timestamp, Integer status, String message, String path, String traceId) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.path = path;
        this.traceId = traceId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }
}
