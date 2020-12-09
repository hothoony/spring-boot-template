package study.springbootbasic.boardExample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BoardController.class)
class BoardControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BoardService boardService;

    @Test
    void readAll_01() throws Exception {
        mvc.perform(get("/boards"))
                .andDo(print())
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void readAll_02() throws Exception {
        mvc.perform(get("/boards")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void readAll_03() throws Exception {
        List<Board> boards = Arrays.asList(
                Board.builder().id(1L).title("title1").content("content1").build(),
                Board.builder().id(2L).title("title2").content("content2").build(),
                Board.builder().id(3L).title("title3").content("content3").build()
        );

        given(boardService.findAll()).willReturn(boards);

        mvc.perform(get("/boards")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}