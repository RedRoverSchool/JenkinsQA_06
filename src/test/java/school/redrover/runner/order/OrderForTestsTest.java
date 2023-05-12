package school.redrover.runner.order;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OrderForTestsTest {

    private static class Item {
        public String name;
        public String[] dependencies;

        public Item(String name, String... dependencies) {
            this.name = name;
            this.dependencies = dependencies;
        }
    }

    @Test
    public void orderMethodsTest() {
        final Item one = new Item("1. one");
        final Item two = new Item("2. two", "1. one");
        final Item three = new Item("3. three", "1. one");
        final Item five = new Item("5. five", "3. three");
        final Item six = new Item("6. six", "3. three");
        final Item four = new Item("4. four");
        final List<Item> sourceList = List.of(one, two, three, four, five, six);

        final List<List<Item>> expectedList = List.of(
                List.of(one, two, three, five, six),
                List.of(four));
        final List<List<Item>> expectedList2 = List.of(
                List.of(one, three, five, six, two),
                List.of(four));
        final List<List<Item>> expectedList3 = List.of(
                List.of(one, two, three, six, five),
                List.of(four));
        final List<List<Item>> expectedList4 = List.of(
                List.of(one, three, six, five, two),
                List.of(four));

        List<List<Item>> result = OrderUtils.orderMethods(sourceList,
                (Item item) -> item.name,
                (Item item) -> item.dependencies);

        Assert.assertTrue(result.equals(expectedList) || result.equals(expectedList2)
                || result.equals(expectedList3) || result.equals(expectedList4));
    }

    @Test
    public void orderMethodsOneGroupDepTest() {
        final Item one = new Item("1. one");
        final Item two = new Item("2. two", "1. one");
        final Item three = new Item("3. three", "2. two");
        final Item four = new Item("4. four");
        final List<Item> sourceList = List.of(one, two, three, four);

        final List<List<Item>> expectedList = List.of(
                List.of(one, two, three),
                List.of(four));

        List<List<Item>> result = OrderUtils.orderMethods(sourceList,
                (Item item) -> item.name,
                (Item item) -> item.dependencies);

        Assert.assertEquals(Set.of(result), Set.of(expectedList));
    }

    @Test
    public void orderMethodsTwoDifferentGroupsDepTest() {
        final Item one = new Item("1. one");
        final Item two = new Item("2. two", "1. one");
        final Item three = new Item("3. three");
        final Item four = new Item("4. four", "3. three");
        final Item five = new Item("5. five");
        final List<Item> sourceList = List.of(one, two, three, four, five);

        final List<List<Item>> expectedList = List.of(
                List.of(one, two),
                List.of(three, four),
                List.of(five));

        List<List<Item>> result = OrderUtils.orderMethods(sourceList,
                (Item item) -> item.name,
                (Item item) -> item.dependencies);

        Assert.assertEquals(Set.of(result), Set.of(expectedList));
    }

    @Test
    public void orderMethodsForkDownDepTest() {
        final Item one = new Item("1. one");
        final Item two = new Item("2. two", "1. one");
        final Item three = new Item("3. three", "1. one");
        final Item four = new Item("4. four");
        final List<Item> sourceList = List.of(one, two, three, four);

        final List<List<Item>> expectedList1 = List.of(
                List.of(one, two, three),
                List.of(four));
        final List<List<Item>> expectedList2 = List.of(
                List.of(one, three, two),
                List.of(four));

        List<List<Item>> result = OrderUtils.orderMethods(sourceList,
                (Item item) -> item.name,
                (Item item) -> item.dependencies);

        Assert.assertTrue(result.equals(expectedList1) || result.equals(expectedList2));
    }

    @Test
    public void orderMethodsForkUpDepTest() {
        final Item one = new Item("1. one");
        final Item two = new Item("2. two");
        final Item three = new Item("3. three", "1. one", "2. two");
        final Item four = new Item("4. four", "5. five");
        final Item five = new Item("5. five");
        final Item six = new Item("6. six");
        final List<Item> sourceList = List.of(one, two, three, four, five, six);

        final List<List<Item>> expectedList1 = List.of(
                List.of(one, two, three),
                List.of(five, four),
                List.of(six));

        final List<List<Item>> expectedList2 = List.of(
                List.of(two, one, three),
                List.of(five, four),
                List.of(six));

        List<List<Item>> result = OrderUtils.orderMethods(sourceList,
                (Item item) -> item.name,
                (Item item) -> item.dependencies);

        Assert.assertTrue(result.equals(expectedList1) || result.equals(expectedList2));
    }

    @Test
    public void orderMethodsNoDepTest() {
        final Item one = new Item("1. one");
        final Item two = new Item("2. two");
        final Item three = new Item("3. three");
        final Item four = new Item("4. four");
        final Item five = new Item("5. five");
        final List<Item> sourceList = List.of(one, two, three, four, five);

        final List<List<Item>> expectedList = List.of(
                List.of(one),
                List.of(two),
                List.of(three),
                List.of(four),
                List.of(five));

        List<List<Item>> result = OrderUtils.orderMethods(sourceList,
                (Item item) -> item.name,
                (Item item) -> item.dependencies);

        Assert.assertEquals(result, expectedList);
    }

    @Test
    public void orderMethodsCycleDepTest() {
        final Item one = new Item("1. one", "5. five");
        final Item two = new Item("2. two", "1. one");
        final Item three = new Item("3. three", "2. two");
        final Item four = new Item("4. four", "3. three");
        final Item five = new Item("5. five", "4. four");
        final List<Item> sourceList = new ArrayList<>(List.of(one, two, three, four, five));

        final List<List<Item>> expectedList = new ArrayList<>(List.of(
                List.of(two, three, four, five, one)));

        List<List<Item>> result = OrderUtils.orderMethods(sourceList,
                (Item item) -> item.name,
                (Item item) -> item.dependencies);

        Assert.assertEquals(result, expectedList);
    }
}
