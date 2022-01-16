package com.pjwstk.sakila.diagnostics.selftest;

import com.pjwstk.sakila.diagnostics.result.SelftestResult;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;

@AllArgsConstructor
public class TheMovieDbServiceConnectionSelfTest extends SelfTestBase {
    @Value("${themoviesdb.api.key}")String apiKey;
    private final RestTemplate rest = new RestTemplate();

    public TheMovieDbServiceConnectionSelfTest() {
        name = "TheMovieDbServiceConnectionSelfTest";
        description = "Checks connection to movie db api.";
    }

    @Override
    public SelftestResult execute() {
        SelftestResult selftestResult = new SelftestResult(name, description, false, null);
        try{
            var movie = rest.getForEntity("https://api.themoviedb.org/3/movie/" +
                    1122 +
                    "?api_key=" + apiKey, Object.class).getBody();
            System.out.println(movie);
            selftestResult.setPassed(true);
        } catch (HttpClientErrorException e){
            selftestResult.setPassed(false);
            selftestResult.setErrors(Arrays.asList(e.getMessage()));
        }

        return selftestResult;
    }


}