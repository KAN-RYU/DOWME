import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.puresushi.cse364project.data.MealMenu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MealMenuControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testNewMealMenu() throws Exception {
        String url = "/meal";
        MealMenu menu = new MealMenu(230522, "Lunch", "Korean", "Dormitory",
                "rice / kimchi");
        String json = new ObjectMapper().writeValueAsString(menu);
        ResultActions actions = mvc.perform(put(url).contentType(MediaType.APPLICATION_JSON)
                .content(json));

        actions.andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", equalTo("230522")))
                .andDo(print());
    }

}
