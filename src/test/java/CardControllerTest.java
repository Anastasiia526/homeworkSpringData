import com.controllers.CardController;
import com.model.Card;
import com.persistence.dao.services.interfaces.CardSimpleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CardControllerTest {

    private MockMvc mockMvc;
    private CardSimpleService cardSimpleService;
    private CardController cardController;
    private Card testCard;

    @BeforeEach
    void setUp() {
        cardSimpleService = Mockito.mock(CardSimpleService.class);
        cardController = new CardController();
        cardController.setCardSimpleService(cardSimpleService);

        mockMvc = MockMvcBuilders
                .standaloneSetup(cardController)
                .build();

        testCard = new Card();
        testCard.setAuthor("John Simpson");
        testCard.setThema("Nature");
        testCard.setType("Postcard");
        testCard.setCountry("Italy");
        testCard.setSent(true);
        testCard.setYear(2023);
        testCard.setValuable("High");
    }

    @Test
    void testAddCard() throws Exception {
        mockMvc.perform(post("/card/add")
                        .param("thema", "Thema")
                        .param("type", "TestType")
                        .param("sent", "true")
                        .param("country", "Australia")
                        .param("year", "2008")
                        .param("author", "Test Author")
                        .param("valuable", "Ordinary"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/card/all"));
    }

    @Test
    void testListAllCard() throws Exception {
        when(cardSimpleService.findAll()).thenReturn(Arrays.asList(testCard));

        mockMvc.perform(get("/card/all"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("cards"))
                .andExpect(model().attribute("cards", hasItem(
                        allOf(
                                hasProperty("author", is("John Simpson")),
                                hasProperty("thema", is("Nature"))
                        )
                )))
                .andExpect(view().name("card"));
    }

    @Test
    void testRemoveCard() throws Exception {
        mockMvc.perform(get("/card/remove/10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/card/all"));

        verify(cardSimpleService, times(1)).deleteCardById(10L);
    }

    @Test
    void testFindCardByType() throws Exception {
        when(cardSimpleService.findCardByType("Postcard")).thenReturn(Collections.singletonList(testCard));

        mockMvc.perform(post("/card/findCardByType")
                        .param("type", "Postcard"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("cards"))
                .andExpect(model().attribute("cards", hasItem(
                        hasProperty("type", is("Postcard"))
                )))
                .andExpect(view().name("/search-results"));
    }

    @Test
    void testFindCardByYearAndSent() throws Exception {
        when(cardSimpleService.findCardByYearAndSent(2023, true))
                .thenReturn(Collections.singletonList(testCard));

        mockMvc.perform(post("/card/findCardByYearAndSent")
                        .param("year", "2023")
                        .param("sent", "true"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("cards"))
                .andExpect(model().attribute("cards", hasItem(
                        hasProperty("year", is(2023))
                )))
                .andExpect(view().name("/search-results"));
    }

    @Test
    void testUpdateCard() throws Exception {
        mockMvc.perform(post("/card/update")
                        .param("type", "Sticker")
                        .param("author", "John Simpson")
                        .param("year", "2023"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/card/all"));

        verify(cardSimpleService, times(1))
                .updateCardTypeByAuthorAndYear("Sticker", "John Simpson", 2023);
    }

    @Test
    void testAddCardInvalidYear() throws Exception {
        mockMvc.perform(post("/card/add")
                        .param("thema", testCard.getThema())
                        .param("type", testCard.getType())
                        .param("sent", String.valueOf(testCard.getSent()))
                        .param("country", testCard.getCountry())
                        .param("year", "invalid")
                        .param("author", testCard.getAuthor())
                        .param("valuable", testCard.getValuable()))
                .andExpect(status().isBadRequest());
    }
}