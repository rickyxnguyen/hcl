package com.hcl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Intermdiate {
    public static void main(String[] args) {
        // create a Optional
        Optional<Product> product1 = Optional.empty();

        // print value
        System.out.println("Optional: "+ product1);
        List<Product> productList = new ArrayList<Product>();
        productList.add(new Product(1, "HP Laptop", 2500));
        productList.add(new Product(2, "Lenovo Laptop", 1000));
        productList.add(new Product(3, "Dell Laptop", 1300));
        productList.add(new Product(4, "Macbook Pro", 2800));
        productList.add(new Product(5, "Macbook Air", 1800));

        List<Product> product = productList.stream()
                .filter(x -> x.getId() == 4)
                .collect(Collectors.toList());
        System.out.println(product);

        // *** this returns product = null

        Optional<Product> product1 = productsList.stream()
        .filter(x -> x.getId() == 8)
        .collect(Collectors.toList()));

        //*** this returns Optional<product>

    }
}
