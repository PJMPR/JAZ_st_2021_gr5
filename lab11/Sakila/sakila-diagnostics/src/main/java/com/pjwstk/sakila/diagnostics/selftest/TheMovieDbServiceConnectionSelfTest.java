package selftest;

import com.pjwstk.sakila.diagnostics.outcome.SelftestOutcome;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import org.springframework.stereotype.Component;
import com.pjwstk.sakila.diagnostics.selftest.SelfTestBase;

@Component
@AllArgsConstructor
public class TheMovieDbServiceConnectionSelfTest extends SelfTestBase {
    @Value("${themoviesdb.api.key}")String apiKey;
    private final RestTemplate rest = new RestTemplate();

    public TheMovieDbServiceConnectionSelfTest() {
        name = "TheMovieDbServiceConnectionSelfTest";
        description = "Checks connection to movie db api.";
    }

    @Override
    public SelftestOutcome execute() {
        SelftestOutcome selftestResult = new SelftestOutcome(name, description, false, null);
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