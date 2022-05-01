package op.mobile.app.dev.graysono.travelling.ui.home;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import op.mobile.app.dev.graysono.travelling.R;
import op.mobile.app.dev.graysono.travelling.model.Attraction;

public class HomeFragmentDirections {
  private HomeFragmentDirections() {
  }

  @NonNull
  public static ActionHomeFragmentToMapFragment actionHomeFragmentToMapFragment(
      @NonNull Attraction[] attractions) {
    return new ActionHomeFragmentToMapFragment(attractions);
  }

  public static class ActionHomeFragmentToMapFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionHomeFragmentToMapFragment(@NonNull Attraction[] attractions) {
      if (attractions == null) {
        throw new IllegalArgumentException("Argument \"attractions\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("attractions", attractions);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionHomeFragmentToMapFragment setAttractions(@NonNull Attraction[] attractions) {
      if (attractions == null) {
        throw new IllegalArgumentException("Argument \"attractions\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("attractions", attractions);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("attractions")) {
        Attraction[] attractions = (Attraction[]) arguments.get("attractions");
        __result.putParcelableArray("attractions", attractions);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_home_fragment_to_map_fragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public Attraction[] getAttractions() {
      return (Attraction[]) arguments.get("attractions");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionHomeFragmentToMapFragment that = (ActionHomeFragmentToMapFragment) object;
      if (arguments.containsKey("attractions") != that.arguments.containsKey("attractions")) {
        return false;
      }
      if (getAttractions() != null ? !getAttractions().equals(that.getAttractions()) : that.getAttractions() != null) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + java.util.Arrays.hashCode(getAttractions());
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionHomeFragmentToMapFragment(actionId=" + getActionId() + "){"
          + "attractions=" + getAttractions()
          + "}";
    }
  }
}
