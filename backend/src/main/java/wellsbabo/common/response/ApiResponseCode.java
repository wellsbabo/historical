package wellsbabo.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiResponseCode {

	SUCCESS(0, "요청이 성공하였습니다."),
	FAIL(1000, "요청이 실패하였습니다."),
	BAD_PARAMETER(1001,"요청 파라미터가 잘못되었습니다."),
	NOT_ALLOWED_DOMAIN(1002,"허용되지 않은 도메인입니다."),
	UNAUTHORIZED(8000, "인증에 실패하였습니다."),
	ERROR(9000, "서버 에러입니다.");

	private final int code;
	private final String message;

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
