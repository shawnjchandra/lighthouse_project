package com.lighthouse.project.KetersediaanUnit;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface KetersediaanService {
    List<TransaksiKetersediaanModel> getRecentSearch();
    void storeSearch(List<TransaksiKetersediaanModel> list);
    Map<String,String> getRecentMap();
    void storeSearch(Map<String,String> map);
}
