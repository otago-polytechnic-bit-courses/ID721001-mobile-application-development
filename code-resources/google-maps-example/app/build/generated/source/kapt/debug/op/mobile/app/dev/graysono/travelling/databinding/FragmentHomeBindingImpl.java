package op.mobile.app.dev.graysono.travelling.databinding;
import op.mobile.app.dev.graysono.travelling.R;
import op.mobile.app.dev.graysono.travelling.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentHomeBindingImpl extends FragmentHomeBinding  {

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
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentHomeBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }
    private FragmentHomeBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (androidx.recyclerview.widget.RecyclerView) bindings[2]
            , (android.widget.TextView) bindings[1]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rvCountries.setTag(null);
        this.tvStatus.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
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
        if (BR.homeViewModel == variableId) {
            setHomeViewModel((op.mobile.app.dev.graysono.travelling.ui.home.HomeViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setHomeViewModel(@Nullable op.mobile.app.dev.graysono.travelling.ui.home.HomeViewModel HomeViewModel) {
        this.mHomeViewModel = HomeViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.homeViewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeHomeViewModelStatus((androidx.lifecycle.LiveData<op.mobile.app.dev.graysono.travelling.api.ServiceStatus>) object, fieldId);
            case 1 :
                return onChangeHomeViewModelResponse((androidx.lifecycle.LiveData<java.util.List<op.mobile.app.dev.graysono.travelling.model.Country>>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeHomeViewModelStatus(androidx.lifecycle.LiveData<op.mobile.app.dev.graysono.travelling.api.ServiceStatus> HomeViewModelStatus, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeHomeViewModelResponse(androidx.lifecycle.LiveData<java.util.List<op.mobile.app.dev.graysono.travelling.model.Country>> HomeViewModelResponse, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
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
        op.mobile.app.dev.graysono.travelling.ui.home.HomeViewModel homeViewModel = mHomeViewModel;
        androidx.lifecycle.LiveData<op.mobile.app.dev.graysono.travelling.api.ServiceStatus> homeViewModelStatus = null;
        java.util.List<op.mobile.app.dev.graysono.travelling.model.Country> homeViewModelResponseGetValue = null;
        androidx.lifecycle.LiveData<java.util.List<op.mobile.app.dev.graysono.travelling.model.Country>> homeViewModelResponse = null;
        op.mobile.app.dev.graysono.travelling.api.ServiceStatus homeViewModelStatusGetValue = null;

        if ((dirtyFlags & 0xfL) != 0) {


            if ((dirtyFlags & 0xdL) != 0) {

                    if (homeViewModel != null) {
                        // read homeViewModel.status
                        homeViewModelStatus = homeViewModel.getStatus();
                    }
                    updateLiveDataRegistration(0, homeViewModelStatus);


                    if (homeViewModelStatus != null) {
                        // read homeViewModel.status.getValue()
                        homeViewModelStatusGetValue = homeViewModelStatus.getValue();
                    }
            }
            if ((dirtyFlags & 0xeL) != 0) {

                    if (homeViewModel != null) {
                        // read homeViewModel.response
                        homeViewModelResponse = homeViewModel.getResponse();
                    }
                    updateLiveDataRegistration(1, homeViewModelResponse);


                    if (homeViewModelResponse != null) {
                        // read homeViewModel.response.getValue()
                        homeViewModelResponseGetValue = homeViewModelResponse.getValue();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0xeL) != 0) {
            // api target 1

            op.mobile.app.dev.graysono.travelling.helpers.BindingAdapterKt.setCountryListData(this.rvCountries, homeViewModelResponseGetValue);
        }
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            op.mobile.app.dev.graysono.travelling.helpers.BindingAdapterKt.setServiceStatus(this.tvStatus, homeViewModelStatusGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): homeViewModel.status
        flag 1 (0x2L): homeViewModel.response
        flag 2 (0x3L): homeViewModel
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}