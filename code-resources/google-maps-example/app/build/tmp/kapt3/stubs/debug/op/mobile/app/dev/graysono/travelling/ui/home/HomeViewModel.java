package op.mobile.app.dev.graysono.travelling.ui.home;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082D\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\f8F\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\f8F\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u000e\u00a8\u0006\u0011"}, d2 = {"Lop/mobile/app/dev/graysono/travelling/ui/home/HomeViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_response", "Landroidx/lifecycle/MutableLiveData;", "", "Lop/mobile/app/dev/graysono/travelling/model/Country;", "_status", "Lop/mobile/app/dev/graysono/travelling/api/ServiceStatus;", "baseUrl", "", "response", "Landroidx/lifecycle/LiveData;", "getResponse", "()Landroidx/lifecycle/LiveData;", "status", "getStatus", "app_debug"})
public final class HomeViewModel extends androidx.lifecycle.ViewModel {
    private final java.lang.String baseUrl = "https://gist.github.com/Grayson-Orr/49223bcae755ef9479b3150182dc125e/";
    private final androidx.lifecycle.MutableLiveData<op.mobile.app.dev.graysono.travelling.api.ServiceStatus> _status = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<op.mobile.app.dev.graysono.travelling.model.Country>> _response = null;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<op.mobile.app.dev.graysono.travelling.api.ServiceStatus> getStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<op.mobile.app.dev.graysono.travelling.model.Country>> getResponse() {
        return null;
    }
    
    public HomeViewModel() {
        super();
    }
}