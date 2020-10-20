import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。
 *
 * @author zhoudy
 * @date 2020/10/19
 */
public class HelloClassloader extends ClassLoader {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> helloClass = new HelloClassloader().findClass("Hello");
        Object o = helloClass.newInstance();
        Method method = helloClass.getMethod("hello");
        method.invoke(o);
    }

    @Override
    protected Class<?> findClass(String name) {
        File file = new File(this.getClass().getResource("Hello.xlass").getPath());
        byte[] bytes = new byte[(int) file.length()];
        FileInputStream ins = null;
        try {
            ins = new FileInputStream(file);
            ins.read(bytes);
            decode(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return defineClass(name, bytes, 0, bytes.length);
    }

    private void decode(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }
    }
}
