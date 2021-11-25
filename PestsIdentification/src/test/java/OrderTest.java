import com.insect.pojo.Order;
import com.insect.service.OrderService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OrderTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    OrderService orderServiceImpl = (OrderService) context.getBean("OrderServiceImpl");
    @Test
    public void test1(){

        for (Order order : orderServiceImpl.searchAllOrder()) {
            System.out.println(order);
        }
    }
    @Test
    public void test2(){

        orderServiceImpl.addOrder(new Order("C111","你好"));
    }
    @Test
    public void test3(){
        System.out.println(orderServiceImpl.queryOrderById("C15000000000"));
    }
    @Test
    public void test4(){
        orderServiceImpl.deleteOrderById("C111");
    }
    @Test
    public void test5(){
        orderServiceImpl.updateOrder(new Order("C111","不好"));
    }
}
