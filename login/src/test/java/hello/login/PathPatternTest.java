package hello.login;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.springframework.http.server.PathContainer;
import org.springframework.web.util.*;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;
import org.springframework.web.util.pattern.PatternParseException;

public class PathPatternTest {

    @Test
    public void antPatternTest(){
        // **
        assertThat(true, is(checkAntPattern("/css/**", "/css/a.jpg")));
        assertThat(true, is(checkAntPattern("/css/**", "/css/user/a.jpg")));
        assertThat(true, is(checkAntPattern("/css/**", "/css/js/a.js")));
        assertThat(true, is(checkAntPattern("/css/**", "/css/a/b/c/d/e/f/a.css")));
        assertThat(true, is(checkAntPattern("/css/**", "/css")));
        assertThat(true, is(checkAntPattern("/css/**", "/css/")));

        // *
        assertThat(true, is(checkAntPattern("/css/*", "/css/a.css")));
        assertThat(true, is(checkAntPattern("/css/*", "/css/namkyuProfilePicture.css")));

        assertThat(false, is(checkAntPattern("/css/*", "/css/a/test.css")));
        assertThat(false, is(checkAntPattern("/css/*", "/css/a/b/c/d/test.css")));

        assertThat(true, is(checkAntPattern("/css*/*", "/css/test.css")));
        assertThat(true, is(checkAntPattern("/css*/*", "/css1/test.css")));
        assertThat(true, is(checkAntPattern("/css*/*", "/css123/test.css")));
        assertThat(true, is(checkAntPattern("/css*/*", "/css-123/test.css")));
        assertThat(true, is(checkAntPattern("/css*/*", "/css~!@#$%^&*()_+}{|/test.css")));

        assertThat(false, is(checkAntPattern("/css*/*", "/css12/a/test.css")));
        assertThat(false, is(checkAntPattern("/css*/*", "/css12/a/b/test.css")));


        // *, ** 혼합
        assertThat(true, is(checkAntPattern("/error*/**", "/error/a.jpg")));
        assertThat(true, is(checkAntPattern("/error*/**", "/error1/a.jpg")));
        assertThat(true, is(checkAntPattern("/error*/**", "/error/a/a.jpg")));
        assertThat(true, is(checkAntPattern("/error*/**", "/error/a/b/a.jpg")));
        assertThat(true, is(checkAntPattern("/error*/**", "/error/a/b/c/a.jpg")));

        assertThat(true, is(checkAntPattern("**/error/**", "a/error/a/b/c/a.jpg")));
        assertThat(true, is(checkAntPattern("**/error/**", "a/b/error/a/b/c/a.jpg")));

        // ?
        assertThat(true, is(checkAntPattern("/static-?/**", "/static-a/a.jpg")));
        assertThat(true, is(checkAntPattern("/static-?/**", "/static-a/b/c/a.jpg")));
        assertThat(true, is(checkAntPattern("/static-?/*", "/static-a/abcd.jpg")));
        assertThat(true, is(checkAntPattern("/static-?/???.jpg", "/static-a/abc.jpg")));

    }
    @Test
    public void pathPatternTest(){
        Assertions.assertThatThrownBy(()-> patternMatches("pages/**/details", "pages/a/b/c/d/details")).isInstanceOf(PatternParseException.class);
    }

    private boolean checkAntPattern(String pattern, String inputStr) {
        return matches(pattern, inputStr);
    }
    public static boolean matches(String pattern, String inputStr) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        return antPathMatcher.match(pattern, inputStr);
    }
    public static int patternMatches(String pattern, String inputStr) {
        PathPatternParser parser = new PathPatternParser();
        PathPattern pattern1 = parser.parse(pattern);
        PathPattern inputPattern = parser.parse(inputStr);

        return pattern1.compareTo(inputPattern);
    }

}
