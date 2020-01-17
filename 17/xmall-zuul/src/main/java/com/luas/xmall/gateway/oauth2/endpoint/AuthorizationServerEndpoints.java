package com.luas.xmall.gateway.oauth2.endpoint;

public interface AuthorizationServerEndpoints {

    String AUTHORIZATION_ENDPOINT = "/oauth/authorize";

    String TOKEN_ENDPOINT = "/oauth/token";

    String CHECK_TOKEN_ENDPOINT = "/oauth/check_token";

    String TOKEN_KEY_ENDPOINT = "/oauth/token_key";

    String USER_INFO_ENDPOINT = "/oauth/user";

    String USER_APPROVAL_ENDPOINT = "/oauth/confirm_access";

    String ERROR_ENDPOINT = "/oauth/error";

}
