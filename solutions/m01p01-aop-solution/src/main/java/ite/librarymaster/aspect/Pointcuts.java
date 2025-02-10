package ite.librarymaster.aspect;

import org.aspectj.lang.annotation.Pointcut;

/**
 * Class used as container for named Pointcut definitions.
 */
public class Pointcuts {

    @Pointcut("within(ite.librarymaster.service..*)")
    public void allServicesMethods(){}

    @Pointcut("within(ite.librarymaster.repository..*)")
    public void allRepositoriesMethods(){}

    @Pointcut("allServicesMethods() || allRepositoriesMethods()")
    public void servicesAndRepositories(){}



}
