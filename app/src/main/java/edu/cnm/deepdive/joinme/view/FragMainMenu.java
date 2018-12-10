package edu.cnm.deepdive.joinme.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import edu.cnm.deepdive.joinme.R;
import edu.cnm.deepdive.joinme.controller.MainActivity;
import edu.cnm.deepdive.joinme.model.entity.Invitation;

/**
 * The Main circle button view after signing in. Also defines which buttons go to which fragment.
 */
public class FragMainMenu extends Fragment {
  private static final String TAG = "FragMainMenu";

  private CircleMenuView circleMenuView;
  private Invitation invitation;
  private Context context;
  private FragMainMenuListener fragMainMenuListener;

  public interface FragMainMenuListener{
    MainActivity getParentActivity();
  }


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View theView = inflater.inflate(R.layout.fragment_main_screen_ar, container, false);
    initViews(theView);

    return theView;
  }

  private void initViews(View view) {
    final CircleMenuView menu = view.findViewById(R.id.cm_fragmainscreen_menu);
    menu.setEventListener(new CircleMenuView.EventListener() {
      @Override
      public void onMenuOpenAnimationStart(@NonNull CircleMenuView view) {
        Log.d("D", "onMenuOpenAnimationStart");
      }

      @Override
      public void onMenuOpenAnimationEnd(@NonNull CircleMenuView view) {
        Log.d("D", "onMenuOpenAnimationEnd");
      }

      @Override
      public void onMenuCloseAnimationStart(@NonNull CircleMenuView view) {
        Log.d("D", "onMenuCloseAnimationStart");
      }

      @Override
      public void onMenuCloseAnimationEnd(@NonNull CircleMenuView view) {
        Log.d("D", "onMenuCloseAnimationEnd");
      }

      @Override
      public void onButtonClickAnimationStart(@NonNull CircleMenuView view, int index) {
        Log.d("D", "onButtonClickAnimationStart| index: " + index);
      }

      @Override
      public void onButtonClickAnimationEnd(@NonNull CircleMenuView view, int index) {
        Log.d("D", "onButtonClickAnimationEnd| index: " + index);
        switch (index){
          case 0:
            MainActivity.switchFragment(new FragUserProf(), true, "",getFragmentManager());
            break;
          case 1:
            MainActivity.switchFragment(new FragPeopleRV(), true, "",getFragmentManager());
            break;
          case 2:
            MainActivity.switchFragment(new FragInviteCreate(), true, "",getFragmentManager());
            break;
          case 3:
            fragMainMenuListener.getParentActivity().setUseInviteListToMeForRV(false);
            MainActivity.switchFragment(new FragInvitationRV(), true, "", getFragmentManager());
            break;
          case 4:
            fragMainMenuListener.getParentActivity().setUseInviteListToMeForRV(true);
            MainActivity.switchFragment(new FragInvitationRV(), true, "",getFragmentManager());
            break;
        }
      }
    });
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    try {
      fragMainMenuListener = (FragMainMenuListener) getActivity();
    } catch (ClassCastException e) {
      Log.e(TAG, "onAttach: ClassCastException" + e.getMessage());
    }

  }

}

