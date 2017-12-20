package com.bsu.exchange;

import com.bsu.exchange.constants.Currency;
import com.bsu.exchange.constants.ExchangeRate;
import com.bsu.exchange.constants.TransactionType;
import com.bsu.exchange.entity.Client;
import com.bsu.exchange.singleton.ClientsList;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class MarketFlow implements Runnable {

    static Random random = new Random();
    private List<Client> clients = ClientsList.getInstance().getClients();


    @Override
    public void run() {
;
        int a;
        int b;
        int amount;
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true) {
                a = random.nextInt(clients.size());
                b = random.nextInt(clients.size());
                amount = random.nextInt(100);
                if (a != b) {
                    break;
                }
            }
            transaction(clients.get(a), clients.get(b), TransactionType.BUY, Currency.EURO, amount);
        }
    }

    private static void transaction(Client from, Client to, TransactionType type, Currency currency, int amount) {
        AtomicInteger fstClientAmount = from.getAmountByCurrency(currency);
        AtomicInteger scndClientAmount = to.getAmountByCurrency(currency);
        switch (type) {
            case SELL:
                if (amount <= fstClientAmount.get() && to.getCurrentAmountByr().get() >= amount * ExchangeRate.getRate(currency)) {
                    fstClientAmount.addAndGet(-amount);
                    scndClientAmount.addAndGet(amount);
                    from.getCurrentAmountByr().addAndGet( (amount * ExchangeRate.getRate(currency)));
                    to.getCurrentAmountByr().addAndGet( -(amount * ExchangeRate.getRate(currency)));
                    System.out.println("transaction succeed");
                } else {
                    System.out.println("not enough money");
                }
                break;
            case BUY:
                if (from.getCurrentAmountByr().get() >= amount * ExchangeRate.getRate(currency)&&scndClientAmount.get()>=amount ) {
                    fstClientAmount.addAndGet(amount);
                    scndClientAmount.addAndGet(-amount);
                    from.getCurrentAmountByr().addAndGet( -(amount * ExchangeRate.getRate(currency)));
                    to.getCurrentAmountByr().addAndGet(( (amount * ExchangeRate.getRate(currency))));
                    System.out.println("transaction succeed");
                } else {
                    System.out.println("not enough money");
                }
                break;

        }
    }


    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
