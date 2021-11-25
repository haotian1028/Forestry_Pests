import com.insect.pojo.Insect;
import com.insect.service.InsectService;
import org.junit.Test;
import org.python.antlr.ast.Str;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class test2 {
    int a=18;
    int b=23;
    @Test
    public void test() throws Exception{
        String[] args = new String[]{"python","C:\\Users\\张浩天\\Desktop\\PestsIdentification\\src\\main\\python\\__main__.py",String.valueOf(a),String.valueOf(b)};
        Process process=Runtime.getRuntime().exec(args);
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line= null;
        while ((line=in.readLine())!=null) {
            System.out.println(line);
        }
        in.close();
        process.waitFor();
    }
    @Test
    public void test1(){
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("src/main/python/__init__.py");
        PyFunction add = interpreter.get("add", PyFunction.class);
        int a=5,b=10;
        PyObject pyObject = add.__call__(new PyInteger(a), new PyInteger(b));
        System.out.println("结果为"+pyObject);
    }
}
