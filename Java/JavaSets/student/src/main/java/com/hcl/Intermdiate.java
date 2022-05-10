package com.hcl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Intermdiate {
    public static void main(String[] args) {



        List<Product> productList = new ArrayList<Product>();
        productList.add(new Product(1, "HP Laptop", 2500));
        productList.add(new Product(2, "Lenovo Laptop", 1000));
        productList.add(new Product(3, "Dell Laptop", 1300));
        productList.add(new Product(4, "Macbook Pro", 2800));
        productList.add(new Product(5, "Macbook Air", 1800));

        Optional<Product> product = productList.stream()
                .filter(x -> x.getId() == 4)
                .findAny();

        // *** this returns product = null

        Optional<Product> product1 = productList.stream()
                .filter(x -> x.getId() == 4)
                .findFirst();

        // *** this returns Optional<product>

        product.ifPresent(System.out::println);
        product1.ifPresent(System.out::println);


    }
}
