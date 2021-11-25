import com.insect.pojo.Insect;
import com.insect.service.InsectService;
import lombok.SneakyThrows;
import org.junit.Test;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javax.script.*;
import org.python.util.PythonInterpreter;
import java.io.*;
import static java.lang.System.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class test {

    @Test
    public void test6() throws IOException, InterruptedException {
        Runtime rt = Runtime.getRuntime();

        Process p = rt.exec("cmd.exe /c D: && cd D:\\faster-rcnn-pytorch-master && python predict.py");

        System.out.println(p.toString());
    }


    @Test
    public void test8() throws IOException {
        String[] args1=new String[]{"D:/Anaconda/envs/tensorflow-gpu/python.exe","d:/faster-rcnn-pytorch-master/predict.py"};
        Process process=Runtime.getRuntime().exec(args1);
        out.println(process.toString());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line=null;
        while ((line=bufferedReader.readLine())!=null){
            out.println(line);
        }
    }





}


