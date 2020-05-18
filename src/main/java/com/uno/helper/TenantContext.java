package com.uno.helper;

import com.uno.model.TenantDb;

public class TenantContext {
    private static ThreadLocal<TenantDb> currentTenant = new ThreadLocal<>();
    public static void setCurrentTenant(TenantDb tenant) {
        currentTenant.set(tenant);
    }
    public static TenantDb getCurrentTenant() {
        return currentTenant.get();
    }
    public static void clear() {
        currentTenant.set(null);
    }
}
