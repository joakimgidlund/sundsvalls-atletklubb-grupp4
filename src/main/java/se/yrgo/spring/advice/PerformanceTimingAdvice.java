package se.yrgo.spring.advice;

import org.aspectj.lang.ProceedingJoinPoint;

/** 
 * Aspect advice for measuring execution time of methods in service and DAO classes.
 * This advice uses around advice to log the time taken by a method in nanoseconds,
 * converted to milliseconds for readability.
 * 
 * @author anomalin, joakimgidlund
 */
public class PerformanceTimingAdvice {
    public Object performTimingMeasurement(ProceedingJoinPoint method) throws Throwable {
        long startTime = System.nanoTime();
        try {
            Object value = method.proceed();
            return value;
        } finally {
            double endTime = System.nanoTime();
            double timeTaken = endTime - startTime;
            System.out.println("The method " + method.getSignature().getName() + " from the class " + method.getSignature().getDeclaringTypeName() + " took " + timeTaken/1000000);
        }
    }
}
