package java8test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lixuejiao on 16/8/29.
 * 找出product里name为xx category 为xx 价格在xx区间 的前x个
 */
public class StreamTest {
    private static class Product{
        private String name;
        private String category;
        private double price;

        public Product(String name, String category, double price) {
            this.name = name;
            this.category = category;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "name:" + name + " category:" + category + " price" + price;
        }
    }

    public static List<Product> getPs(List<Product> products) {
        return products.stream()
                .filter(product -> product.getName().equals("xx"))
                .filter(product -> product.getCategory().equals("yy"))
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .limit(3)
                .collect(Collectors.toList());
    }

    public static void main(String[] a){
        Product p1 = new Product("xx","yy",2);
        Product p2 = new Product("xx","syy",3);
        Product p3 = new Product("xx","dyy",4);
        Product p4 = new Product("xx","yy",4);
        Product p5 = new Product("xx","yy",2.3);
        Product p6 = new Product("xx","yy",4.6);
        List<Product> products = new ArrayList<Product>(){{add(p1);add(p2);add(p3);add(p4);add(p5);add(p6);}};
        List<Product> products1 = getPs(products);
        products.forEach(System.out::println);
        System.out.println();
        products1.forEach(System.out::println);

    }
}
