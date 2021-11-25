import com.insect.mapper.FamilyMapper;
import com.insect.pojo.Family;
import com.insect.service.FamilyService;
import com.insect.service.OrderService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FamilyTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    FamilyService familyServiceImpl = context.getBean("FamilyServiceImpl", FamilyService.class);
    @Test
    public void test1(){

        for (Family family : familyServiceImpl.searchAllFamily()) {
            System.out.println(family);
        }
    }
    @Test
    public void test2(){
        System.out.println(familyServiceImpl.queryFamilyById("C15105000000"));
    }
    @Test
    public void test3(){
        System.out.println(familyServiceImpl.queryFamilyByName("向宇航"));
    }
    @Test
    public void test4(){
        for (Family family : familyServiceImpl.searchFamilyByOrderId("C15000000000")) {
            System.out.println(family);
        }
    }
    @Test
    public void test5(){
        familyServiceImpl.addFamily("1","向宇航","C15000000000");
    }
    @Test
    public void test6(){
        familyServiceImpl.deleteFamilyById("1");
    }
    @Test
    public void test7(){
        familyServiceImpl.updateFamily("1","新消息","C22000000000");
    }

}
