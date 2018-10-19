package com.xmw.servlet;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xmw.annotation.Controller;
import com.xmw.annotation.Qualifier;
import com.xmw.annotation.Repository;
import com.xmw.annotation.RequestMapping;
import com.xmw.annotation.Service;
import com.xmw.controller.UserController;

/**
 * DispatcherServlet
 * 在Spring MVC中，DispatcherServlet是核心，下面我们来实现它。
 * 首先来说，Spring MVC中的DispatcherServlet说到底，还是HttpServlet的子类，
 * 因此我这边自己的DispatcherSerlvet需要extends HttpServlet。
 *
 * @author mingwei.xia
 * @date 2018/9/25 10:28
 * @since V1.0
 */

/**
 * @WebServlet是什么？ 其实，以前我们定义一个Servlet，需要在web.xml中去配置，不过在Servlet3.0后出现了基于注解的Servlet。
 * 仔细观察，你会发现，这个DispatcherServlet是自启动，而且传入了一个参数。
 * 要知道，在Spring MVC中，要想基于注解，需要在配置中指明扫描的包路径，就像这个样子：
 * 为了方便，我这里就通过初始化参数直接将需要扫描的基包路径传入。
 */
@WebServlet(name = "dispatcherServlet", urlPatterns = "/*", loadOnStartup = 1,
        initParams = {@WebInitParam(name = "base-package", value = "com.xmw")})
public class DispatcherServlet extends HttpServlet {
    // 扫描的基包
    private String basePackage = "";
    // 基包下面所有的带包路径全限定类名
    private List<String> packageNames = new ArrayList<>();
    // 注解实例化  注解上的名称: 实例化对象
    private Map<String, Object> instanceMap = new HashMap<>();
    // 带包路径的全限定名称: 注解上的名称
    private Map<String, String> nameMap = new HashMap<>();
    // URL地址和方法的映射关系  Spring Mvc就是方法调用链
    private Map<String, Method> urlMethodMap = new HashMap<>();
    // Method和全限定名映射关系, 主要是为了通过Method找到该方法的对象, 利用反射执行
    private Map<Method, String> methodPackageMap = new HashMap<>();

    /**
     * 其实，在init中，我们主要是完成了什么呢？
     * 第一，我们应该去扫描基包下的类，得到信息A
     * 第二，对于@Controller/@Service/@Repository注解而言，我们需要拿到对应的名称，并初始化它们修饰的类，形成映射关系B
     * 第三，我们还得扫描类中的字段，如果发现有@Qualifier的话，我们需要完成注入
     * 第四，我们还需要扫描@RequestMapping，完成URL到某一个Controller的某一个方法上的映射关系C
     * <p>
     * 其实，Spring MVC的处理流程，就是类似这样的！
     */
    @Override
    public void init(ServletConfig config) {
        basePackage = config.getInitParameter("base-package");
        try {
            //1. 扫描基包得到全部的带包路径 全限定名
            this.scanBasePackage(basePackage);
            //2. 把带有@Controller/@Service/@Repository的类实例化, 放入map中, key为注解上的名称
            this.instance(packageNames);
            //3. Spring IOC 注入
            this.springIOC();
            //4. 完成URL地址与方法的映射关系
            this.handlerUrlMethodMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 注意，基包是X.Y.Z的形式，而URL是X/Y/Z的形式，需要转换。
    private void scanBasePackage(String basePackage) {
        // 注意为了得到基包下面的URL路径 需要对basePackage做转换: 将.替换为/
        URL url = this.getClass().getClassLoader().getResource(basePackage.replaceAll("\\.", "/"));
        System.out.println("scan: " + url.getPath());
        File basePackageFile = new File(url.getPath());
        File[] childFiles = basePackageFile.listFiles();
        if (childFiles == null) {
            return;
        }
        for (File file : childFiles) {
            // 目录继续递归扫描
            if (file.isDirectory()) {
                scanBasePackage(basePackage + "." + file.getName());
            } else if (file.isFile()) {
                // 类似这中: com.xmw.service.impl.UserServiceImpl.class 要去掉class
                packageNames.add(basePackage + "." + file.getName().split("\\.")[0]);
            }
        }
    }

    // 完成了被注解标注的类的实例化，以及和注解名称的映射。
    private void instance(List<String> packageNames) throws Exception {
        if (packageNames.size() < 1) {
            return;
        }
        for (String classLimitName : packageNames) {
            Class c = Class.forName(classLimitName);
            // 有controller注解
            if (c.isAnnotationPresent(Controller.class)) {
                Controller controller = (Controller) c.getAnnotation(Controller.class);
                String controllerName = controller.value();
                // 实例化对象并保存
                instanceMap.put(controllerName, c.newInstance());
                // 保存全限定名称: 注解上的名称
                nameMap.put(classLimitName, controllerName);
                System.out.println("Controller : " + classLimitName + ", value : " + controller.value());
            } else if (c.isAnnotationPresent(Service.class)) {
                Service service = (Service) c.getAnnotation(Service.class);
                String serviceName = service.value();

                instanceMap.put(serviceName, c.newInstance());
                nameMap.put(classLimitName, serviceName);
                System.out.println("Service : " + classLimitName + ", value : " + service.value());
            } else if (c.isAnnotationPresent(Repository.class)) {
                Repository repository = (Repository) c.getAnnotation(Repository.class);
                String repositoryName = repository.value();

                instanceMap.put(repositoryName, c.newInstance());
                nameMap.put(classLimitName, repositoryName);
                System.out.println("Repository : " + classLimitName + ", value : " + repository.value());
            }
        }
    }

    // 依赖注入
    private void springIOC() throws IllegalAccessException {
        for (Map.Entry<String, Object> entry : instanceMap.entrySet()) {
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Qualifier.class)) {
                    String name = field.getAnnotation(Qualifier.class).value();
                    field.setAccessible(true);
                    field.set(entry.getValue(), instanceMap.get(name));
                }
            }
        }
    }

    // URL映射处理
    //TODO 对url进行校验
    private void handlerUrlMethodMap() throws ClassNotFoundException {
        if (packageNames.size() < 1) {
            return;
        }
        for (String classLimitName : packageNames) {
            Class c = Class.forName(classLimitName);
            // 对controller注解进行解析处理
            if (c.isAnnotationPresent(Controller.class)) {
                Method[] methods = c.getMethods();
                StringBuilder baseUrl = new StringBuilder();
                if (c.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping requestMapping = (RequestMapping) c.getAnnotation(RequestMapping.class);
                    baseUrl.append(requestMapping.value());
                }
                for (Method method : methods) {
                    if (method.isAnnotationPresent(RequestMapping.class)) {
                        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                        baseUrl.append(requestMapping.value());
                        // 保存 url 对应的方法
                        urlMethodMap.put(baseUrl.toString(), method);
                        // 保存 method: 方法所在类的全限定名
                        methodPackageMap.put(method, classLimitName);
                    }
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String path = uri.replaceAll(contextPath, "");

        // 通过path 找到Method
        Method method = urlMethodMap.get(path);
        if (method != null) {
            // 通过Method拿到Controller对象, 准备进行发射
            String packageName = methodPackageMap.get(method);
            String controllerName = nameMap.get(packageName);

            // 拿到Controller对象, 执行方法调用
            UserController userController = (UserController) instanceMap.get(controllerName);
            try {
                method.setAccessible(true);
                method.invoke(userController);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
