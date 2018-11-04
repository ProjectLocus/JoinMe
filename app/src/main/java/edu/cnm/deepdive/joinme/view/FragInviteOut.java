package edu.cnm.deepdive.joinme.view;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import edu.cnm.deepdive.joinme.R;

public class FragInviteOut extends Fragment {

  private static final String TAG = "FragInviteOut";

  private TextView tempText;
  private FragInviteOutListener fragInviteOutListener;

  public interface FragInviteOutListener{
    public void goToFragInviteIn();
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View theView = inflater.inflate(R.layout.fragment_invite_out, container, false);
    //do stuff
    initViews(theView);

    return theView;
  }

  private void initViews(View view) {
    tempText = view.findViewById(R.id.temp_title0);
    tempText.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        fragInviteOutListener.goToFragInviteIn();
      }
    });
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    try {
      fragInviteOutListener = (FragInviteOutListener) getActivity();
    } catch (ClassCastException e) {
      Log.e(TAG, "onAttach: ClassCastException" + e.getMessage());
    }

  }
}
