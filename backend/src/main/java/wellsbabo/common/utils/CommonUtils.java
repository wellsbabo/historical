package wellsbabo.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import wellsbabo.common.response.ApiResponse;
import wellsbabo.common.response.ApiResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CommonUtils {

    public static void setErrorResponse(HttpStatus status, HttpServletResponse response, ApiResponseCode errCode) {
        setErrorResponse(status, response, errCode, errCode.getMessage());
    }

    public static void setErrorResponse(HttpStatus status, HttpServletResponse response, ApiResponseCode errCode, String errMessage) {
        response.setStatus(status.value());
        response.setContentType("application/json;charset=UTF-8");
        ApiResponse apiResponse = ApiResponse.create(errCode, errMessage);
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(apiResponse);
            response.getWriter().write(json);
        } catch (IOException e) {
            log.error("setErrorResponse error");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

}
