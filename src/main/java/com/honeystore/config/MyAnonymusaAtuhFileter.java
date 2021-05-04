package com.honeystore.config;

import com.honeystore.domain.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class MyAnonymusaAtuhFileter  extends AnonymousAuthenticationFilter {

    private static final String USER_SESSION_KEY = "user";
    private final String key;

    public MyAnonymusaAtuhFileter(String key) {
        super(key);
        this.key = key;
    }

    @Override
    protected Authentication createAuthentication(HttpServletRequest req) {
        HttpSession httpSession = req.getSession();
        User user = Optional.ofNullable((User) httpSession.getAttribute(USER_SESSION_KEY))
                .orElseGet(() -> {
                    User anon = new User();
                    anon.setUsername("anonymousUser");
                    httpSession.setAttribute(USER_SESSION_KEY, anon);
                    return anon;
                });
        return new AnonymousAuthenticationToken(key, user, AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS"));
    }
}
