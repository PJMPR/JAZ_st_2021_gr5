package org.example.tests;

import org.example.model.Gender;
import org.example.model.People;
import org.example.queries.QueryProcessor;
import org.example.queries.results.Results;
import org.example.queries.search.Funcs;
import org.example.queries.search.FunctionsParameters;
import org.example.queries.search.Page;
import org.example.queries.search.SearchParameters;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestQueryProcessor {

    @Test
    public void test_should_check_if_data_is_properly_filtered(){

        SearchParameters params =new SearchParameters();
        params.setAgeFrom(20);
        params.setAgeTo(40);
        params.setIncomeFrom(2000);
        params.setPage(new Page(9,1));
        params.getSelectedGenders().add(Gender.FEMALE);
        params.getSelectedGenders().add(Gender.OTHER);
        params.getFunctions().add(new FunctionsParameters("age", Funcs.AVARAGE));
        params.getFunctions().add(new FunctionsParameters("income", Funcs.SUM));
        params.getFunctions().add(new FunctionsParameters("income", Funcs.AVARAGE));

        Results result = new QueryProcessor().GetResults(params);
        assertThat(result.getItems(),hasSize(3));
        assertThat(result.getItems(),hasItems(
                People.AnnaBuda,
                People.ConchitaWurst,
                People.AnetaUrban
        ));
        assertThat(result.getCurrentPage(), is(1));
        assertThat(result.getPages(), is(1));
        assertThat(result.getFunctionResults(),  hasSize(3));
    }

    @Test
    public void test_should_check_if_paging_works()
    {
        SearchParameters params = new SearchParameters();
        params.setPage(new Page(3, 2));
        Results results = new QueryProcessor().GetResults(params);
        assertThat(results.getItems(), hasSize(3));
        assertThat(results.getItems(),hasItems(
                People.AnnaBuda,
                People.ConchitaWurst,
                People.AnetaUrban
        ));
    }

    @Test
    public void test_should_check_if_search_by_name_works(){

        SearchParameters params = new SearchParameters();
        params.setName("Jan");
        Results results = new QueryProcessor().GetResults(params);

        assertThat(results.getItems(), hasSize(2));
        assertThat(results.getItems(),hasItems(
                People.JanAnrusowski,
                People.JanKowalski));
    }
}
