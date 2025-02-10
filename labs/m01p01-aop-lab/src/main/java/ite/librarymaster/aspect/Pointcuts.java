package ite.librarymaster.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("within(ite.librarymaster.service..*)")
    public void allServicesMethods(){}

    @Pointcut("within(ite.librarymaster.repository..*)")
    public void allRepositoriesMethods(){}

    @Pointcut("allServicesMethods() || allRepositoriesMethods()")
    public void servicesAndRepositories(){}



}
