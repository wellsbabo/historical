package wellsbabo.common.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Getter
@NoArgsConstructor
@ToString
public class ApiResponse<T> {

    private int resultCode;
    private String resultMessage;
    private T data;

    private ApiResponse(int code, String resultMessage, T data) {
        this.resultCode = code;
        this.resultMessage = resultMessage;
        this.data = data;
    }

    public static <String> ApiResponse<String> success() {
        return new ApiResponse<>(ApiResponseCode.SUCCESS.getCode(), ApiResponseCode.SUCCESS.getMessage(), null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(ApiResponseCode.SUCCESS.getCode(), ApiResponseCode.SUCCESS.getMessage(), data);
    }

    public static <T> ApiResponse<T> create(ApiResponseCode code) {
        return new ApiResponse<>(code.getCode(), code.getMessage(), null);
    }

    public static <T> ApiResponse<T> create(ApiResponseCode code, String message) {
        return new ApiResponse<>(code.getCode(), message, null);
    }

    public static <T> ApiResponse<T> create(ApiResponseCode code, T data) {
        return new ApiResponse<>(code.getCode(), code.getMessage(), data);
    }

    public static <T> ApiResponse<T> create(ApiResponseCode code, String message, T data) {
        return new ApiResponse<>(code.getCode(), message, data);
    }

    public static <T> ApiResponse<T> create(int code, String message, T data) {
        return new ApiResponse<>(code, message, data);
    }

    public static <T> ApiResponse<T> convert(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(json, Map.class);
        return ApiResponse.create((int)map.get("resultCode"), map.get("resultMessage").toString(), (T)map.get("data"));
    }

}
