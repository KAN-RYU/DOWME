package com.puresushi.cse364project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.puresushi.cse364project.data.BusTime;
import com.puresushi.cse364project.data.MealMenu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BusTimeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetBusIds() throws Exception {
        String url = "/bus";

        ResultActions actions = mvc.perform(get(url));

        actions.andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$[0]", equalTo("133(Flower)")))
                .andDo(print());
    }

    @Test
    public void testGetDepartBus() throws Exception {
        String url = "/bus/depart/1000";

        ResultActions actions = mvc.perform(get(url));

        actions.andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$[0].busId", equalTo("743(Taewha)")))
                .andExpect(jsonPath("$[1].busId", equalTo("133(Flower)")))
                .andDo(print());

        String url2 = "/bus/depart/2359";

        ResultActions actions2 = mvc.perform(get(url2));

        actions2.andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", equalTo(0)))
                .andDo(print());
    }

    @Test
    public void testGetBusData() throws Exception {
        String url = "/bus/times/133(Flower)";

        ResultActions actions = mvc.perform(get(url));

        actions.andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.times[1]", equalTo(515)))
                .andExpect(jsonPath("$.busId", equalTo("133(Flower)")))
                .andDo(print());
    }

    @Test
    public void testGetBusDataError() throws Exception {
        String url = "/bus/times/NonBus";

        ResultActions actions = mvc.perform(get(url));

        actions.andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void testNewBusTime() throws Exception {
        String url = "/bus";
        BusTime busTime = new BusTime(0520, "777(Lucky)");
        String json = new ObjectMapper().writeValueAsString(busTime);
        ResultActions actions = mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                .content(json));

        actions.andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.busId", equalTo("777(Lucky)")))
                .andDo(print());
    }

    @Test
    public void testNewBusTimeError() throws Exception {
        String url = "/bus";
        BusTime busTime = new BusTime(0561, "777(Lucky)");
        String json = new ObjectMapper().writeValueAsString(busTime);
        ResultActions actions = mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                .content(json));

        actions.andExpect(status().isBadRequest())
                .andDo(print());

        busTime = new BusTime(-1, "777(Lucky)");
        json = new ObjectMapper().writeValueAsString(busTime);
        actions = mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                .content(json));

        actions.andExpect(status().isBadRequest())
                .andDo(print());

        busTime = new BusTime(2500, "777(Lucky)");
        json = new ObjectMapper().writeValueAsString(busTime);
        actions = mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                .content(json));

        actions.andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void testNewBusTimeError2() throws Exception {
        String url = "/bus";
        BusTime busTime = new BusTime(850, "304(Yul-li)");
        String json = new ObjectMapper().writeValueAsString(busTime);
        ResultActions actions = mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                .content(json));

        actions.andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.busId", equalTo("304(Yul-li)")))
                .andDo(print());
    }

    @Test
    public void testDeleteBusTime() throws Exception {
        String url = "/bus/304(Yul-li)/0850";
        BusTime busTime = new BusTime(850, "304(Yul-li)");
        ResultActions actions = mvc.perform(delete(url));

        actions.andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.busId", equalTo("304(Yul-li)")))
                .andDo(print());

        actions = mvc.perform(delete(url));

        actions.andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.busId", equalTo("304(Yul-li)")))
                .andDo(print());
    }
}
