package wellsbabo.common.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
@ToString
public class ApiPageResponse<T> {

    private int resultCode;
    private String resultMessage;
    private List<T> data;
    private int totalCount;

    private ApiPageResponse(int code, String resultMessage, List<T> data, int totalCount) {
        this.resultCode = code;
        this.resultMessage = resultMessage;
        this.data = data;
        this.totalCount = totalCount;
    }

    public static <T> ApiPageResponse<T> success(List<T> data, int totalCount) {
        return new ApiPageResponse<>(ApiResponseCode.SUCCESS.getCode(), ApiResponseCode.SUCCESS.getMessage(), data, totalCount);
    }

    public static <T> ApiPageResponse<T> create(ApiResponseCode code, List<T> data, int totalCount) {
        return new ApiPageResponse<>(code.getCode(), code.getMessage(), data, totalCount);
    }

    public static <T> ApiPageResponse<T> create(ApiResponseCode code, String message, List<T> data, int totalCount) {
        return new ApiPageResponse<>(code.getCode(), message, data, totalCount);
    }

    public static <T> ApiPageResponse<T> create(int code, String message, List<T> data, int totalCount) {
        return new ApiPageResponse<>(code, message, data, totalCount);
    }

    public static <T> ApiPageResponse<T> convert(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(json, Map.class);
        return ApiPageResponse.create((int)map.get("resultCode"), map.get("resultMessage").toString(), (List<T>)map.get("data"), (int)map.get("totalCount"));
    }

}
