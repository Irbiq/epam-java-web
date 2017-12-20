package com.bsu.exchange.singleton;

import com.bsu.exchange.entity.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ClientsList {
    private static ClientsList instance;
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static ReentrantLock lock = new ReentrantLock();

    private List<Client> clients;

    private ClientsList() {
        clients = new ArrayList<>();
    }

    public static ClientsList getInstance() {
        if (!isCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ClientsList();
                    isCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public List<Client> getClients() {
        return clients;
    }

}
    
    
