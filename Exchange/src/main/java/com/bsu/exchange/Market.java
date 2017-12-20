package com.bsu.exchange;

import com.bsu.exchange.entity.Client;
import com.bsu.exchange.singleton.ClientsList;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Market {

    public static void main(String[] args) {

        List<Client> clients = ClientsList.getInstance().getClients();

        Random random = new Random(1000);
        int usd,euro,byr;
        for (int i =0; i<100;i++){
            usd = random.nextInt(100);
            euro = random.nextInt(100);
            byr = random.nextInt(100);
            clients.add(new Client(new AtomicInteger(byr),new AtomicInteger(usd),new AtomicInteger(euro)));
        }

        Statistic statistic = new Statistic() ;
        MarketFlow mf1 =  new MarketFlow();
        MarketFlow mf2 =  new MarketFlow();
        MarketFlow mf3 =  new MarketFlow();

        statistic.setClients(clients);
        mf1.setClients(clients);
        mf2.setClients(clients);
        mf3.setClients(clients);

        ExecutorService service = Executors.newFixedThreadPool(4);

        service.execute(statistic);
        service.execute(mf1);
        service.execute(mf2);
        service.execute(mf3);


    }

}
