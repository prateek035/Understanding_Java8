package com.shop;

import com.company.CompletableFutureTut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String [] args) {

        // Generating some dummy products

        List<Product> productList1 = Arrays.asList(
                new Product("bag", 500),
                new Product("bottle", 100),
                new Product("mobile", 10000),
                new Product("Notebook", 50));

        List<Product> productList2 = Arrays.asList(
                new Product("bag", 570),
                new Product("hdmi cable", 80),
                new Product("desktop", 17000),
                new Product("Notebook", 50));

        List<Product> productList3 = Arrays.asList(
                new Product("bag", 500),
                new Product("bottle", 200),
                new Product("wire", 80),
                new Product("Notebook", 450));

        List<Product> productList4 = Arrays.asList(
                new Product("bag", 900),
                new Product("desktop", 19900),
                new Product("mobile", 16000),
                new Product("Notebook", 50));

        List<Product> productList5 = Arrays.asList(
                new Product("bag", 500),
                new Product("mask", 100),
                new Product("mobile", 10000),
                new Product("earphones", 800));

        // Generating dummy shops

        Shop shop1 = new Shop("ABC Store", productList1);
        Shop shop2 = new Shop("New Market Store", productList2);
        Shop shop3 = new Shop("General Store", productList3);
        Shop shop4 = new Shop("Temporary Store", productList4);
        Shop shop5 = new Shop("Unknown Store", productList5);

        List<Shop> shopList = new ArrayList<>();
        shopList.add(shop1);
        shopList.add(shop2);
        shopList.add(shop3);
        shopList.add(shop4);
        shopList.add(shop5);


        showAllStorePriceForProduct(shopList, "mobile");
        System.out.println("_____________________________________");
        showAllStorePriceForProduct(shopList, "wire");


    }

    public static void showAllStorePriceForProduct(List<Shop>shops, String productName)   {
        List<Shop> shopListWithProduct = getAllShopWithProduct(shops, productName);

        List<CompletableFuture<String>> priceFutures = shopListWithProduct.stream()
                .map(shop -> CompletableFuture.supplyAsync(() ->String.format("%s : %d",
                        shop.getName(),
                        shop.getProductPrice(productName).orElse(null)
                         ))).collect(Collectors.toList());

        // can do any stuff here till it computes above..

       for(CompletableFuture<String> future : priceFutures){
           try {
               System.out.println(future.get());
           } catch(ExecutionException | InterruptedException e){
               e.printStackTrace();
           }
       }
    }

    public static List<Shop> getAllShopWithProduct(List<Shop> shops, String productName) {
        return  shops.stream().filter(shop -> {
                                        return shop.getProducts().stream()
                                                .anyMatch(product -> product.getName().equals(productName));
                                    }).collect(Collectors.toList());


    }
}
