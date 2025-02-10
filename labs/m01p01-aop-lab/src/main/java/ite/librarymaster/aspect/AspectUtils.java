package ite.librarymaster.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

public class AspectUtils {

    static public String createJoinPointTraceName(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        StringBuilder sb = new StringBuilder();
        sb.append(signature.getDeclaringType().getSimpleName());
        sb.append('.').append(signature.getName()).append("()");
        return sb.toString();
    }

    static public String getDetailedJoinPointInfo(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        Method[] declaredMethods = signature.getDeclaringType().getDeclaredMethods();
        StringBuilder sb = new StringBuilder();
        for (Method m : declaredMethods) {
            if (m.getName().equals(signature.getName())) {
                Parameter[] parameters = m.getParameters();
                int paramIndex = 0;

                for (Parameter p : parameters) {
                    sb.append(p.getName()).append('=').append(Arrays.stream(joinPoint.getArgs()).toList().get(paramIndex++)).append("\n");
                }
            }
        }
        return sb.toString();
    }
}
