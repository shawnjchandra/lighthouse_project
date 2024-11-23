package com.lighthouse.project.KetersediaanUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class KetServiceImplementasi implements KetersediaanService {

    private List<TransaksiKetersediaanModel> list = new ArrayList<>();

    @Override
    public List<TransaksiKetersediaanModel> getRecentSearch() {
        
        return new ArrayList<>(list);    
    }

    @Override
    @Async
    public CompletableFuture<Void> storeSearch(List<TransaksiKetersediaanModel> list) {
        System.out.println("2");
        
        
        
        
        list.clear();
        list.addAll(list);
        System.out.println(" from service "+list.size());
        System.out.println("3");
        
        return CompletableFuture.completedFuture(null);
    }
    
}
