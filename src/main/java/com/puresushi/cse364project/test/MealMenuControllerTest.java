package com.puresushi.cse364project.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MealMenuControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testNewMealMenu() throws Exception {
        return;
    }

}
