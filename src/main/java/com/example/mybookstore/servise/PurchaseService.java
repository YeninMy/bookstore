package com.example.mybookstore.servise;

import com.example.mybookstore.repository.PersonRepo;
import com.example.mybookstore.repository.PurchaseRepo;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    private final PurchaseRepo purchaseRepo;

    public PurchaseService(PurchaseRepo purchaseRepo) {
        this.purchaseRepo = purchaseRepo;
    }
}
