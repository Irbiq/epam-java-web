package com.bsu.exchange;

import com.bsu.exchange.entity.Client;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Statistic implements Runnable {
    ReentrantLock lock = new ReentrantLock();
    private List<Client> clients;

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public void run() {
        while(true){
            System.out.println("__________________________________");
            try {
                TimeUnit.SECONDS.sleep(5);
                lock.tryLock();
                clients.forEach(System.out::println);
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
