package com.puresushi.cse364project;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.puresushi.cse364project.data.MealMenu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MealMenuControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testBean() {
        assertNotNull(mvc);
    }

    @Test
    public void testNewMealMenu() throws Exception {
        String url = "/meal";
        MealMenu menu = new MealMenu(230522, "Lunch", "Korean", "Dormitory",
                "rice / kimchi");
        String json = new ObjectMapper().writeValueAsString(menu);
        ResultActions actions = mvc.perform(put(url).contentType(MediaType.APPLICATION_JSON)
                .content(json));

        actions.andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.date", equalTo(230522)))
                .andDo(print());
    }

    @Test
    public void testUpdateMealMenu() throws Exception {
        String url = "/meal";
        MealMenu menu = new MealMenu(230501, "Lunch", "Korean", "Dormitory",
                "rice / kimchi");
        String json = new ObjectMapper().writeValueAsString(menu);
        ResultActions actions = mvc.perform(put(url).contentType(MediaType.APPLICATION_JSON)
                .content(json));

        actions.andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.date", equalTo(230501)))
                .andDo(print());
    }

    @Test
    public void testMealMenuClass() throws Exception {
        String url = "/meal";
        MealMenu menu = new MealMenu(230522, "Lunch", "Korean", "Dormitory",
                "rice / kimchi");
        MealMenu newOne = new MealMenu(0, "", "","","");

        newOne.setDate(230522);
        newOne.setTime("Lunch");
        newOne.setCategory("Korean");
        newOne.setRestaurant("Dormitory");
        newOne.setMenu("rice / kimchi");
        Assertions.assertEquals(menu, newOne);
    }

    @Test
    public void testGetMealMenu() throws Exception {
        String url = "/meal/230501/Lunch/Dormitory/Korean";
        MealMenu menu = new MealMenu(230522, "Lunch", "Korean", "Dormitory",
                "rice / kimchi");
        ResultActions actions = mvc.perform(get(url));

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.category", equalTo("Korean")))
                .andDo(print());

        url = "/meal/230501/Lunch/Dormitory";

        actions = mvc.perform(get(url));

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].category", equalTo("Korean")))
                .andDo(print());

        url = "/meal/230501/Lunch";

        actions = mvc.perform(get(url));

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].category", equalTo("Korean")))
                .andDo(print());

        url = "/meal/230501";

        actions = mvc.perform(get(url));

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].category", equalTo("Korean")))
                .andDo(print());
    }

    @Test
    public void testGetMealMenuError() throws Exception {
        String url = "/meal/230533/Lunch/Dormitory/Korean";
        MealMenu menu = new MealMenu(230522, "Lunch", "Korean", "Dormitory",
                "rice / kimchi");
        ResultActions actions = mvc.perform(get(url));

        actions.andExpect(status().isNotFound())
                .andDo(print());

        url = "/meal/230533/Lunch/Dormitory";

        actions = mvc.perform(get(url));

        actions.andExpect(status().isNotFound())
                .andDo(print());

        url = "/meal/230533/Lunch";

        actions = mvc.perform(get(url));

        actions.andExpect(status().isNotFound())
                .andDo(print());

        url = "/meal/230533";

        actions = mvc.perform(get(url));

        actions.andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void testSearchMealWithMenu() throws Exception {
        String url = "/meal/search/Iced+Tea";
        ResultActions actions = mvc.perform(get(url));

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", equalTo(11)))
                .andExpect(jsonPath("$[2].date", equalTo(230503)))
                .andDo(print());
    }

}
