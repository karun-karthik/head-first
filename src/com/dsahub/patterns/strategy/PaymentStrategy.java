package com.dsahub.patterns.strategy;

interface IPaymentStrategy {
    void pay(double amount);
}

class CreditCardPayment implements IPaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using credit card");
    }
}

class PaypalPayment implements IPaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using paypal");
    }
}

class UPIPayment implements IPaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using UPI");
    }
}

class PaymentContext {
    private IPaymentStrategy paymentStrategy;

    public PaymentContext(IPaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void setPaymentStrategy(IPaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void pay(double amount) {
        paymentStrategy.pay(amount);
    }
}

public class PaymentStrategy {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext(new CreditCardPayment());
        context.pay(100);

        context.setPaymentStrategy(new PaypalPayment());
        context.pay(200);

        context.setPaymentStrategy(new UPIPayment());
        context.pay(300);
    }
}
