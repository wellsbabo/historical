package wellsbabo.common.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wellsbabo.common.response.ApiResponseCode;
import wellsbabo.common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@Order(1)
public class AllowDomainFilter extends OncePerRequestFilter {

    @Value("${site.allowed.domain}")
    private String allowedDomains;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String[] allowedDomain = allowedDomains.split(",");
        if (Arrays.asList(allowedDomain).contains(httpServletRequest.getServerName())) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            CommonUtils.setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, httpServletResponse, ApiResponseCode.NOT_ALLOWED_DOMAIN, ApiResponseCode.NOT_ALLOWED_DOMAIN.getMessage());
        }
    }

}
