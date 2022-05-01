package op.mobile.app.dev.graysono.travelling.helpers;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 2, d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0007\u001a\u0018\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007\u001a\u001a\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0007\u001a\u0018\u0010\u0011\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007\u00a8\u0006\u0012"}, d2 = {"setCountryListData", "", "rv", "Landroidx/recyclerview/widget/RecyclerView;", "data", "", "Lop/mobile/app/dev/graysono/travelling/model/Country;", "setRoundImage", "iv", "Landroid/widget/ImageView;", "imageUrl", "", "setServiceStatus", "tv", "Landroid/widget/TextView;", "status", "Lop/mobile/app/dev/graysono/travelling/api/ServiceStatus;", "setSquareImage", "app_debug"})
public final class BindingAdapterKt {
    
    @androidx.databinding.BindingAdapter(value = {"country_list_data"})
    public static final void setCountryListData(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView rv, @org.jetbrains.annotations.Nullable()
    java.util.List<op.mobile.app.dev.graysono.travelling.model.Country> data) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"service_status"})
    public static final void setServiceStatus(@org.jetbrains.annotations.NotNull()
    android.widget.TextView tv, @org.jetbrains.annotations.Nullable()
    op.mobile.app.dev.graysono.travelling.api.ServiceStatus status) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"round_image"})
    public static final void setRoundImage(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView iv, @org.jetbrains.annotations.NotNull()
    java.lang.String imageUrl) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"square_image"})
    public static final void setSquareImage(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView iv, @org.jetbrains.annotations.NotNull()
    java.lang.String imageUrl) {
    }
}