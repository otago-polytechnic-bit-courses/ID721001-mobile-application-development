package op.mobile.app.dev.graysono.travelling.helpers.recyclerview;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00112\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u0010\u0011B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001c\u0010\f\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lop/mobile/app/dev/graysono/travelling/helpers/recyclerview/CountryRVAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lop/mobile/app/dev/graysono/travelling/model/Country;", "Lop/mobile/app/dev/graysono/travelling/helpers/recyclerview/CountryRVAdapter$CountryRVViewHolder;", "listener", "Lop/mobile/app/dev/graysono/travelling/helpers/IOnClickListener;", "(Lop/mobile/app/dev/graysono/travelling/helpers/IOnClickListener;)V", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "CountryRVViewHolder", "DiffCallback", "app_debug"})
public final class CountryRVAdapter extends androidx.recyclerview.widget.ListAdapter<op.mobile.app.dev.graysono.travelling.model.Country, op.mobile.app.dev.graysono.travelling.helpers.recyclerview.CountryRVAdapter.CountryRVViewHolder> {
    private final op.mobile.app.dev.graysono.travelling.helpers.IOnClickListener listener = null;
    @org.jetbrains.annotations.NotNull()
    public static final op.mobile.app.dev.graysono.travelling.helpers.recyclerview.CountryRVAdapter.DiffCallback DiffCallback = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public op.mobile.app.dev.graysono.travelling.helpers.recyclerview.CountryRVAdapter.CountryRVViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    op.mobile.app.dev.graysono.travelling.helpers.recyclerview.CountryRVAdapter.CountryRVViewHolder holder, int position) {
    }
    
    public CountryRVAdapter(@org.jetbrains.annotations.NotNull()
    op.mobile.app.dev.graysono.travelling.helpers.IOnClickListener listener) {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lop/mobile/app/dev/graysono/travelling/helpers/recyclerview/CountryRVAdapter$CountryRVViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroid/view/View$OnClickListener;", "binding", "Lop/mobile/app/dev/graysono/travelling/databinding/RecyclerViewCountryItemBinding;", "(Lop/mobile/app/dev/graysono/travelling/helpers/recyclerview/CountryRVAdapter;Lop/mobile/app/dev/graysono/travelling/databinding/RecyclerViewCountryItemBinding;)V", "bind", "", "country", "Lop/mobile/app/dev/graysono/travelling/model/Country;", "onClick", "view", "Landroid/view/View;", "app_debug"})
    public final class CountryRVViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder implements android.view.View.OnClickListener {
        private op.mobile.app.dev.graysono.travelling.databinding.RecyclerViewCountryItemBinding binding;
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        op.mobile.app.dev.graysono.travelling.model.Country country) {
        }
        
        @java.lang.Override()
        public void onClick(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
        }
        
        public CountryRVViewHolder(@org.jetbrains.annotations.NotNull()
        op.mobile.app.dev.graysono.travelling.databinding.RecyclerViewCountryItemBinding binding) {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lop/mobile/app/dev/graysono/travelling/helpers/recyclerview/CountryRVAdapter$DiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lop/mobile/app/dev/graysono/travelling/model/Country;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
    public static final class DiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<op.mobile.app.dev.graysono.travelling.model.Country> {
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        op.mobile.app.dev.graysono.travelling.model.Country oldItem, @org.jetbrains.annotations.NotNull()
        op.mobile.app.dev.graysono.travelling.model.Country newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        op.mobile.app.dev.graysono.travelling.model.Country oldItem, @org.jetbrains.annotations.NotNull()
        op.mobile.app.dev.graysono.travelling.model.Country newItem) {
            return false;
        }
        
        private DiffCallback() {
            super();
        }
    }
}