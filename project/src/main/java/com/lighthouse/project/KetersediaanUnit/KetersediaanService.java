package com.lighthouse.project.KetersediaanUnit;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface KetersediaanService {
    List<TransaksiKetersediaanModel> getRecentSearch();
    CompletableFuture<Void> storeSearch(List<TransaksiKetersediaanModel> list);
}
