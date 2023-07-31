package web.server.boku;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import web.server.boku.controller.SumController;
import web.server.boku.dto.InputDto;
import web.server.boku.service.SumService;

@WebMvcTest(SumController.class)
public class SumControllerTests {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private SumService sumService;

        @BeforeEach
        public void setUp() {

        }

        @Test
        public void testHandleRequestWithValidNumber() throws Exception {
                InputDto inputDto = new InputDto("10");
                when(sumService.handleRequest(inputDto)).thenReturn(" ");

                mockMvc.perform(MockMvcRequestBuilders.post("/")
                                .contentType("application/json")
                                .content("{\"inputValue\": \"10\"}"))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.content().string(" "));
                verify(sumService).handleRequest(inputDto);
        }

        @Test
        public void testHandleRequestWithInvalidInput() throws Exception {
                InputDto inputDto = new InputDto("10L");
                when(sumService.handleRequest(inputDto))
                                .thenReturn("Invalid Input. Please provide a whole number or 'end anyValue' to get the Total sum.");

                mockMvc.perform(MockMvcRequestBuilders.post("/")
                                .contentType("application/json")
                                .content("{\"inputValue\": \"10L\"}"))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.content().string(
                                                "Invalid Input. Please provide a whole number or 'end anyValue' to get the Total sum."));
                verify(sumService).handleRequest(inputDto);
        }

        @Test
        public void testHandleRequestWithADecimalNumber() throws Exception {
                InputDto inputDto = new InputDto("10.5");
                when(sumService.handleRequest(inputDto))
                                .thenReturn("Invalid Input. Please provide a whole number or 'end anyValue' to get the Total sum.");

                mockMvc.perform(MockMvcRequestBuilders.post("/")
                                .contentType("application/json")
                                .content("{\"inputValue\": \"10.5\"}"))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.content().string(
                                                "Invalid Input. Please provide a whole number or 'end anyValue' to get the Total sum."));
                verify(sumService).handleRequest(inputDto);
        }

        @Test
        public void testHandleRequestWithoutASpaceWithEnd() throws Exception {
                InputDto inputDto = new InputDto("endLoop");
                when(sumService.handleRequest(inputDto))
                                .thenReturn("Invalid Input. Please provide a whole number or 'end anyValue' to get the Total sum.");

                mockMvc.perform(MockMvcRequestBuilders.post("/")
                                .contentType("application/json")
                                .content("{\"inputValue\": \"endLoop\"}"))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.content().string(
                                                "Invalid Input. Please provide a whole number or 'end anyValue' to get the Total sum."));
                verify(sumService).handleRequest(inputDto);
        }

        @Test
        public void testHandleRequestWithEnd() throws Exception {
                InputDto inputDto1 = new InputDto("4");
                InputDto inputDto2 = new InputDto("7");
                InputDto inputDto3 = new InputDto("end X");

                when(sumService.handleRequest(inputDto1)).thenReturn(" ");
                when(sumService.handleRequest(inputDto2)).thenReturn(" ");
                when(sumService.handleRequest(inputDto3)).thenReturn("11 X");

                mockMvc.perform(MockMvcRequestBuilders.post("/")
                                .contentType("application/json")
                                .content("{\"inputValue\": \"4\"}"))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.content().string(" "));

                mockMvc.perform(MockMvcRequestBuilders.post("/")
                                .contentType("application/json")
                                .content("{\"inputValue\": \"7\"}"))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.content().string(" "));

                mockMvc.perform(MockMvcRequestBuilders.post("/")
                                .contentType("application/json")
                                .content("{\"inputValue\": \"end X\"}"))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.content().string("11 X"));

                verify(sumService).handleRequest(inputDto1);
                verify(sumService).handleRequest(inputDto2);
                verify(sumService).handleRequest(inputDto3);
        }
}