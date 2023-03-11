package com.sportgames.liness.quiz;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.sportgames.liness.quiz.databinding.ActivityDiffBindingImpl;
import com.sportgames.liness.quiz.databinding.ActivityGameBindingImpl;
import com.sportgames.liness.quiz.databinding.ActivityMainBindingImpl;
import com.sportgames.liness.quiz.databinding.ActivityOptionsBindingImpl;
import com.sportgames.liness.quiz.databinding.ActivityPrivacyBindingImpl;
import com.sportgames.liness.quiz.databinding.ListItemBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYDIFF = 1;

  private static final int LAYOUT_ACTIVITYGAME = 2;

  private static final int LAYOUT_ACTIVITYMAIN = 3;

  private static final int LAYOUT_ACTIVITYOPTIONS = 4;

  private static final int LAYOUT_ACTIVITYPRIVACY = 5;

  private static final int LAYOUT_LISTITEM = 6;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(6);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.sportgames.liness.quiz.R.layout.activity_diff, LAYOUT_ACTIVITYDIFF);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.sportgames.liness.quiz.R.layout.activity_game, LAYOUT_ACTIVITYGAME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.sportgames.liness.quiz.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.sportgames.liness.quiz.R.layout.activity_options, LAYOUT_ACTIVITYOPTIONS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.sportgames.liness.quiz.R.layout.activity_privacy, LAYOUT_ACTIVITYPRIVACY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.sportgames.liness.quiz.R.layout.list_item, LAYOUT_LISTITEM);
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
        case  LAYOUT_ACTIVITYDIFF: {
          if ("layout/activity_diff_0".equals(tag)) {
            return new ActivityDiffBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_diff is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYGAME: {
          if ("layout/activity_game_0".equals(tag)) {
            return new ActivityGameBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_game is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYMAIN: {
          if ("layout/activity_main_0".equals(tag)) {
            return new ActivityMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYOPTIONS: {
          if ("layout/activity_options_0".equals(tag)) {
            return new ActivityOptionsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_options is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYPRIVACY: {
          if ("layout/activity_privacy_0".equals(tag)) {
            return new ActivityPrivacyBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_privacy is invalid. Received: " + tag);
        }
        case  LAYOUT_LISTITEM: {
          if ("layout/list_item_0".equals(tag)) {
            return new ListItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for list_item is invalid. Received: " + tag);
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
    static final SparseArray<String> sKeys = new SparseArray<String>(1);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(6);

    static {
      sKeys.put("layout/activity_diff_0", com.sportgames.liness.quiz.R.layout.activity_diff);
      sKeys.put("layout/activity_game_0", com.sportgames.liness.quiz.R.layout.activity_game);
      sKeys.put("layout/activity_main_0", com.sportgames.liness.quiz.R.layout.activity_main);
      sKeys.put("layout/activity_options_0", com.sportgames.liness.quiz.R.layout.activity_options);
      sKeys.put("layout/activity_privacy_0", com.sportgames.liness.quiz.R.layout.activity_privacy);
      sKeys.put("layout/list_item_0", com.sportgames.liness.quiz.R.layout.list_item);
    }
  }
}
