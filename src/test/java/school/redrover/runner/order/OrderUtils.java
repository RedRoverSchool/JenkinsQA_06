package school.redrover.runner.order;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class OrderUtils {

    private static <T> void orderMethod(
            T currentMethod, Map<String, T> methodMap, Map<String, Set<T>> dependedMap, Set<T> usedSet,
            List<T> destinationList, Function<T, String> getNameFunction, Function<T, String[]> getDependencyFunction) {
        usedSet.add(currentMethod);

        for (String methodName : getDependencyFunction.apply(currentMethod)) {
            methodMap.computeIfPresent(methodName, (k, method) -> {
                if (!usedSet.contains(method)) {
                    orderMethod(method, methodMap, dependedMap, usedSet, destinationList,
                            getNameFunction, getDependencyFunction);
                }

                return method;
            });
        }

        destinationList.add(currentMethod);

        dependedMap.computeIfPresent(getNameFunction.apply(currentMethod), (k, v) -> {
            for (T method : v) {
                if (!usedSet.contains(method)) {
                    orderMethod(method, methodMap, dependedMap, usedSet, destinationList,
                            getNameFunction, getDependencyFunction);
                }
            }

            return v;
        });
    }

    static <T> List<List<T>> orderMethods(
            List<T> sourceList, Function<T, String> getNameFunction, Function<T, String[]> getDependencyFunction) {

        Map<String, Set<T>> dependedMap = new HashMap<>();
        for (T method : sourceList) {
            for (String dependedName : getDependencyFunction.apply(method)) {
                dependedMap.computeIfAbsent(dependedName, key -> new HashSet<>()).add(method);
            }
        }
        Map<String, T> methodMap = sourceList.stream().collect(Collectors.toMap(getNameFunction, Function.identity()));
        Set<T> usedSet = new HashSet<>();
        List<List<T>> resultList = new ArrayList<>();

        for (T method : sourceList) {
            if (!usedSet.contains(method)) {
                List<T> destinationList = new ArrayList<>();
                resultList.add(destinationList);

                orderMethod(method, methodMap, dependedMap, usedSet, destinationList,
                        getNameFunction, getDependencyFunction);
            }
        }

        return resultList;
    }

    public abstract static class MethodsOrder<T> {

        private final Map<T, Boolean> methodInvokedMap;
        private final Map<T, List<T>> methodListMap;
        private final List<List<T>> methodList;

        public MethodsOrder(List<List<T>> methodList) {
            this.methodList = methodList;
            this.methodInvokedMap = new HashMap<>();
            this.methodListMap = new HashMap<>();

            for (List<T> list : methodList) {
                for (T method : list) {
                    methodInvokedMap.put(method, false);
                    methodListMap.put(method, list);
                }
            }
        }

        public boolean markAsInvoked(T method) {
            return Boolean.TRUE.equals(this.methodInvokedMap.put(method, true));
        }

        public boolean isInvoked(T method) {
            return this.methodInvokedMap.get(method);
        }

        public List<T> getGroupList(T method) {
            return new ArrayList<>(this.methodListMap.get(method));
        }

        public boolean isGroupFinished(T method) {
            for (T _method : getGroupList(method)) {
                if (!isInvoked(_method)) {
                    return false;
                }
            }

            return true;
        }

        public boolean isGroupStarted(T method) {
            for (T _method : getGroupList(method)) {
                if (isInvoked(_method)) {
                    return true;
                }
            }

            return false;
        }

        public List<T> getFlatList() {
            return methodList.stream().flatMap(List::stream).collect(Collectors.toList());
        }
    }

    private static class _MethodsOrder<T> extends MethodsOrder<T> {

        public _MethodsOrder(List<List<T>> methodList) {
            super(methodList);
        }
    }

    public static <T> MethodsOrder<T> createMethodsOrder(
            List<T> sourceList, Function<T, String> getNameFunction, Function<T, String[]> getDependencyFunction) {
        return new _MethodsOrder<>(orderMethods(sourceList, getNameFunction, getDependencyFunction));
    }
}
