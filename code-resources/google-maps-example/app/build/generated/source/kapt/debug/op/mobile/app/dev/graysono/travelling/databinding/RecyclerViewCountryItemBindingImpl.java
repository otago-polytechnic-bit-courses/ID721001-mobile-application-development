package op.mobile.app.dev.graysono.travelling.databinding;
import op.mobile.app.dev.graysono.travelling.R;
import op.mobile.app.dev.graysono.travelling.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class RecyclerViewCountryItemBindingImpl extends RecyclerViewCountryItemBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final androidx.cardview.widget.CardView mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public RecyclerViewCountryItemBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }
    private RecyclerViewCountryItemBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageView) bindings[1]
            , (android.widget.TextView) bindings[2]
            );
        this.ivFlagImg.setTag(null);
        this.mboundView0 = (androidx.cardview.widget.CardView) bindings[0];
        this.mboundView0.setTag(null);
        this.tvTitle.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.country == variableId) {
            setCountry((op.mobile.app.dev.graysono.travelling.model.Country) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setCountry(@Nullable op.mobile.app.dev.graysono.travelling.model.Country Country) {
        this.mCountry = Country;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.country);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        op.mobile.app.dev.graysono.travelling.model.Country country = mCountry;
        java.lang.String countryFlagImg = null;
        java.lang.String countryName = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (country != null) {
                    // read country.flagImg
                    countryFlagImg = country.getFlagImg();
                    // read country.name
                    countryName = country.getName();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 4
            if(getBuildSdkInt() >= 4) {

                this.ivFlagImg.setContentDescription(countryName);
            }
            // api target 1

            op.mobile.app.dev.graysono.travelling.helpers.BindingAdapterKt.setRoundImage(this.ivFlagImg, countryFlagImg);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvTitle, countryName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): country
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}