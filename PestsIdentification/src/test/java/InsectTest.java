import com.insect.pojo.Insect;
import com.insect.service.InsectService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class InsectTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    InsectService insectServiceImpl = context.getBean("InsectServiceImpl", InsectService.class);
    @Test
    public void test1(){
        for (Insect insect : insectServiceImpl.searchAllInsect(0)) {
            System.out.println(insect);
        }
    }
    @Test
    public void test2(){
        System.out.println(insectServiceImpl.queryInsectById("C15102015005"));
    }
    @Test
    public void test3(){
        insectServiceImpl.addInsect("1","InsectName","ScienceName","C15102015000","Crop","Area");
    }
    @Test
    public void test4(){
        insectServiceImpl.deleteInsectById("1");
    }
    @Test
    public void test5(){
        System.out.println(insectServiceImpl.queryInsectByName("钝肩普缘蝽(钝角普缘蝽)"));
    }
    @Test
    public void test6(){
        System.out.println(insectServiceImpl.queryInsectByScientificName("Geisha distinctissima（Walker）"));
    }
    @Test
    public void test7(){
        insectServiceImpl.updateInsect("1","","","C15104005000","","");
    }
    @Test
    public void test8(){
        for (Insect insect : insectServiceImpl.searchInsectByGenusId("C22342015000")) {
            System.out.println(insect);
        }
    }
    @Test
    public void test9(){
        List<Insect> list = insectServiceImpl.searchInsectLike("蝉");
        for (Insect insect : list) {
            System.out.println(insect.getInsectName());
        }
    }
    @Test
    public void test10(){
        for (Insect insect : insectServiceImpl.searchAllPest()) {
            System.out.println(insect.getInsectName());
        }
    }
}
