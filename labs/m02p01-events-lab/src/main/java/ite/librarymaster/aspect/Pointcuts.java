package ite.librarymaster.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(public * ite.librarymaster.*.*Repository+.*(..))")
    public void allRepositoriesMethods(){};

}
