import com.insect.pojo.Genus;
import com.insect.service.GenusService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GenusTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    GenusService genusServiceImpl = context.getBean("GenusServiceImpl", GenusService.class);
    @Test
    public void test1(){
        for (Genus list : genusServiceImpl.searchAllGenus()) {
            System.out.println(list);
        }
    }
    @Test
    public void test2(){
        System.out.println(genusServiceImpl.searchGenusById("C15102015000"));
    }
    @Test
    public void test3(){
        System.out.println(genusServiceImpl.searchGenusByName("斑衣蜡蝉属"));
    }
    @Test
    public void test4(){
        for (Genus genus : genusServiceImpl.searchGenusByFamilyId("C15408000000")) {
            System.out.println(genus);
        }
    }
    @Test
    public void test5(){
        genusServiceImpl.addGenus("1","向宇航","C15102000000");

    }
    @Test
    public void test6(){
        genusServiceImpl.deleteGenus("1");
    }
    @Test
    public void test7(){
        genusServiceImpl.updateGenus("1","xxx","C15104000000");
    }
}
