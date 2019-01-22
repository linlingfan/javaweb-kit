package glinlf.growth.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author 唐芳华
 */
public final class RestUtils {

    final static Logger LOG = LoggerFactory.getLogger(RestUtils.class);

    private RestUtils() {

    }

    /**
     * http basic 身份验证后
     *
     * @param authorization http.headers[Authorization]
     * @param user          正确的用户名
     * @param pwd           正确的密码
     * @param proc
     * @return
     */
    public static ResponseEntity afterHttpBasic(String authorization, String user, String pwd, Supplier<ResponseEntity> proc) {
        Objects.requireNonNull(proc);
        final boolean valid = authValid(authorization, user, pwd);
        if (!valid) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        final ResponseEntity ret = proc.get();
        return ret;
    }

    /**
     * 检查authorization 是否匹配用户名密码
     *
     * @param authorization
     * @param user
     * @param pwd
     * @return
     */
    public static boolean authValid(String authorization, String user, String pwd) {
        if (StringUtils.isAnyBlank(authorization, user, pwd)) {
            LOG.debug("authorization & user & pwd can't empty");
            return false;
        }
        final String authHeader = AuthKit.basicAuth(user, pwd);
        final boolean ret = authHeader.equalsIgnoreCase(authorization);
        return ret;
    }
}
