package com.lighthouse.project.KetersediaanUnit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class KetServiceImplementasi implements KetersediaanService {

    private List<TransaksiKetersediaanModel> list = new ArrayList<>();
    private Map<String, String> map = new HashMap<>();

    @Override
    public List<TransaksiKetersediaanModel> getRecentSearch() {
        
        return new ArrayList<>(list);    
    }

    @Override
    public void storeSearch(List<TransaksiKetersediaanModel> list) {

        list.clear();
        list.addAll(list);
    
    }

    @Override
    public Map<String, String> getRecentMap() {
        return new HashMap<>(map);
    }

    @Override
    public void storeSearch(Map<String, String> map) {
        map.clear();
        map.putAll(map);
    }
    
}
