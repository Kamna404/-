package edu.sbs.cs.view.dto;

public class ResponseWrapper<T> {
    private int code;
    private String message;
    private T data;

    public ResponseWrapper() {}

    public ResponseWrapper(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // ...existing code: getters and setters...
    
    public static <T> ResponseWrapper<T> success(T data) {
        return new ResponseWrapper<>(200, "success", data);
    }
    
    public static <T> ResponseWrapper<T> error(int code, String message) {
        return new ResponseWrapper<>(code, message, null);
    }
}
