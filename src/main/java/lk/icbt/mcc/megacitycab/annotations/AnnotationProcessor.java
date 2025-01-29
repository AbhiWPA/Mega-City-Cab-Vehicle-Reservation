package lk.icbt.mcc.megacitycab.annotations;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Title: Mega-City-Cab
 * Description: AnnotationProssesor Class
 * Created by Abhishek Ashinsa on 1/27/2025
 * Email: abhi.ashinsa@gmail.com
 * Company: Epic Lanka (Pvt) Ltd.
 * Java Version: 17
 */

public class AnnotationProcessor {
    public static void processAnnotations(Object controller, HttpServletRequest request) {
        Method[] methods = controller.getClass().getDeclaredMethods();
        String requestURI = request.getRequestURI();

        for (Method method : methods) {
            if (method.isAnnotationPresent(GetMapping.class)) {
                GetMapping getMapping = method.getAnnotation(GetMapping.class);
                if (getMapping.value().equals(requestURI)) {
                    try {
                        method.invoke(controller, request);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            if (method.isAnnotationPresent(PostMapping.class)) {
                PostMapping postMapping = method.getAnnotation(PostMapping.class);
                if (postMapping.value().equals(requestURI)) {
                    try {
                        method.invoke(controller, request);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            if (method.isAnnotationPresent(PutMapping.class)) {
                PutMapping postMapping = method.getAnnotation(PutMapping.class);
                if (postMapping.value().equals(requestURI)) {
                    try {
                        method.invoke(controller, request);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            if (method.isAnnotationPresent(DeleteMapping.class)) {
                DeleteMapping postMapping = method.getAnnotation(DeleteMapping.class);
                if (postMapping.value().equals(requestURI)) {
                    try {
                        method.invoke(controller, request);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
