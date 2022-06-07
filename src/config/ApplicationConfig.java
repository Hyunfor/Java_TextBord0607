package config;

public class ApplicationConfig {

    // 권한(로그인)이 필요없는 요청
    public static String[] anonymousUrlList = {
        "/members/join",
            "/members/login"

    };

    // 권한이 필요한 요청
    public static String[] hasAuthUrlList = {
            "members/logout"
    };

}
