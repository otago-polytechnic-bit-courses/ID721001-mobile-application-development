package op.mobile.app.dev.graysono.travelling;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import op.mobile.app.dev.graysono.travelling.databinding.FragmentHomeBindingImpl;
import op.mobile.app.dev.graysono.travelling.databinding.MarkerInfoWindowBindingImpl;
import op.mobile.app.dev.graysono.travelling.databinding.RecyclerViewCountryItemBindingImpl;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_FRAGMENTHOME = 1;

  private static final int LAYOUT_MARKERINFOWINDOW = 2;

  private static final int LAYOUT_RECYCLERVIEWCOUNTRYITEM = 3;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(3);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(op.mobile.app.dev.graysono.travelling.R.layout.fragment_home, LAYOUT_FRAGMENTHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(op.mobile.app.dev.graysono.travelling.R.layout.marker_info_window, LAYOUT_MARKERINFOWINDOW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(op.mobile.app.dev.graysono.travelling.R.layout.recycler_view_country_item, LAYOUT_RECYCLERVIEWCOUNTRYITEM);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_FRAGMENTHOME: {
          if ("layout/fragment_home_0".equals(tag)) {
            return new FragmentHomeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_home is invalid. Received: " + tag);
        }
        case  LAYOUT_MARKERINFOWINDOW: {
          if ("layout/marker_info_window_0".equals(tag)) {
            return new MarkerInfoWindowBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for marker_info_window is invalid. Received: " + tag);
        }
        case  LAYOUT_RECYCLERVIEWCOUNTRYITEM: {
          if ("layout/recycler_view_country_item_0".equals(tag)) {
            return new RecyclerViewCountryItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for recycler_view_country_item is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(3);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "country");
      sKeys.put(2, "homeViewModel");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(3);

    static {
      sKeys.put("layout/fragment_home_0", op.mobile.app.dev.graysono.travelling.R.layout.fragment_home);
      sKeys.put("layout/marker_info_window_0", op.mobile.app.dev.graysono.travelling.R.layout.marker_info_window);
      sKeys.put("layout/recycler_view_country_item_0", op.mobile.app.dev.graysono.travelling.R.layout.recycler_view_country_item);
    }
  }
}
