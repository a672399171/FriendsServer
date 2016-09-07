package com.zzuzl.util;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.zzuzl.common.Constants;
import com.zzuzl.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthUtil {
    @Resource
    private JWTSigner signer;
    @Resource
    private JWTVerifier verifier;

    public String genToken(User user) {
        final long iat = System.currentTimeMillis() / 1000l;
        final long exp = iat + 30 * 60L;
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put(Constants.USER, user);
        claims.put("exp", exp);
        claims.put("iat", iat);

        return signer.sign(claims);
    }

    public Map<String, Object> verifyToken(String token) throws Exception {
        return verifier.verify(token);
    }

    public boolean hasInvalid(String token) throws Exception {
        boolean result = true;
        Map<String, Object> claims = verifyToken(token);
        if (claims != null) {
            result = System.currentTimeMillis() / 1000l > (Integer) claims.get("exp");
        }
        return result;
    }
}
