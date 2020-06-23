package testdata;

import com.epam.mjc.dao.entity.SearchParams;
import com.epam.mjc.dao.entity.SortType;
import com.epam.mjc.dao.entity.SortParams;

import java.util.ArrayList;
import java.util.List;

public class TestData {
    public static final List<String> tags = new ArrayList<>();
    public static final SearchParams SEARCH_PARAMS_NOT_EMPTY = new SearchParams(tags, "omp", new SortParams("date", SortType.ASC));
    public static final SearchParams SEARCH_PARAMS_EMPTY = new SearchParams(null, null, null);

    private static void addTags() {
        tags.add("FUN");
    }

}
