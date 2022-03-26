package com.company.design.builder;

public class PizzaTest {

    public static void main(String[] args) {
        Pizza 채식윤피자 = new YoonPizza.Builder(YoonPizza.Size.중간).addTopping(Pizza.Topping.머시룸).addTopping(Pizza.Topping.양파).build();
        Pizza 육식윤피자 = new YoonPizza.Builder(YoonPizza.Size.큰거).addTopping(Pizza.Topping.초리조).addTopping(Pizza.Topping.햄).build();

        Pizza 클래식피자 = new ClassPizza.Builder().addTopping(Pizza.Topping.햄).addTopping(Pizza.Topping.머시룸).sauceInside().build();
        System.out.println(채식윤피자);
        System.out.println(육식윤피자);
        System.out.println(클래식피자);

    }
}
