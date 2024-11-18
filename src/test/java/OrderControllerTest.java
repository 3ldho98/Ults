import Ults.common.command.domain.OrderRequest;
import Ults.common.create.domain.OrderDao;
import Ults.common.create.domain.OrderStatus;

@RunWith(SpringJunit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class)
@EnableWebMvc
@EnableWebSecurity
@WebAppConfiguration

public class OrderControllerTest {
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private OrderDao orderDao;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void createsOrder() throws Exception {
        final OrderRequest request = new OrderRequest();
        this.mockMvc.perform(post("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                        .andExpect(status().isCreated());

        final Order order = orderDao.findById(1L).get();
        assertThat(order.getId()).isEqualTo(1);
        assertThat(order.getorderNumber()).isEqualTo("QWF123");
        assertThat(order.getQuantity()).isEqualTo(10);
        assertThat(order.getStatus()).isEqualTo(OrderStatus.CREATED);
        assertThat(order.getTotalAmount()).isEqualTo(10);
        assertThat(order.getCreatedAt()).isNotNull();
        assertThat(order.getItems()).hasSize(1);

        mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.orderNumber").value("QWF123"))
                .andExpect(jsonPath("$.quantity").value(10))
                .andExpect(jsonPath("$.status").value("CREATED"))
                .andExpect(jsonPath("$.totalAmount").value(10))
                .andExpect(jsonPath("$.createdAt").exists())
                .andExpect(jsonPath("$.items[0].id").exists())
                .andExpect(jsonPath("$.items[0].product").exists())
                .andExpect(jsonPath("$.items[0].quantity").value(10));
    }
}
