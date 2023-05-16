package school.redrover.runner.order;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.List;
import java.util.stream.Collectors;

public class OrderForTests implements IMethodInterceptor {

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> list, ITestContext iTestContext) {
        return OrderUtils.orderMethods(list, m -> m.getMethod().getQualifiedName(), m -> m.getMethod().getMethodsDependedUpon())
                .stream().flatMap(List::stream).collect(Collectors.toList());
    }
}