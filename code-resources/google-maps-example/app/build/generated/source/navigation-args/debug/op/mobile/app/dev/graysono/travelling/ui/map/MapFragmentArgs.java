package op.mobile.app.dev.graysono.travelling.ui.map;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.navigation.NavArgs;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.lang.System;
import java.util.HashMap;
import op.mobile.app.dev.graysono.travelling.model.Attraction;

public class MapFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private MapFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private MapFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static MapFragmentArgs fromBundle(@NonNull Bundle bundle) {
    MapFragmentArgs __result = new MapFragmentArgs();
    bundle.setClassLoader(MapFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("attractions")) {
      Attraction[] attractions;
      Parcelable[] __array = bundle.getParcelableArray("attractions");
      if (__array != null) {
        attractions = new Attraction[__array.length];
        System.arraycopy(__array, 0, attractions, 0, __array.length);
      } else {
        attractions = null;
      }
      if (attractions == null) {
        throw new IllegalArgumentException("Argument \"attractions\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("attractions", attractions);
    } else {
      throw new IllegalArgumentException("Required argument \"attractions\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Attraction[] getAttractions() {
    return (Attraction[]) arguments.get("attractions");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("attractions")) {
      Attraction[] attractions = (Attraction[]) arguments.get("attractions");
      __result.putParcelableArray("attractions", attractions);
    }
    return __result;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    MapFragmentArgs that = (MapFragmentArgs) object;
    if (arguments.containsKey("attractions") != that.arguments.containsKey("attractions")) {
      return false;
    }
    if (getAttractions() != null ? !getAttractions().equals(that.getAttractions()) : that.getAttractions() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + java.util.Arrays.hashCode(getAttractions());
    return result;
  }

  @Override
  public String toString() {
    return "MapFragmentArgs{"
        + "attractions=" + getAttractions()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(MapFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(@NonNull Attraction[] attractions) {
      if (attractions == null) {
        throw new IllegalArgumentException("Argument \"attractions\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("attractions", attractions);
    }

    @NonNull
    public MapFragmentArgs build() {
      MapFragmentArgs result = new MapFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setAttractions(@NonNull Attraction[] attractions) {
      if (attractions == null) {
        throw new IllegalArgumentException("Argument \"attractions\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("attractions", attractions);
      return this;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public Attraction[] getAttractions() {
      return (Attraction[]) arguments.get("attractions");
    }
  }
}
