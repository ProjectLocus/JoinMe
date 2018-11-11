package edu.cnm.deepdive.joinme.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import edu.cnm.deepdive.joinme.R;

public class FragPeopleRV extends Fragment {

  private static final String TAG = "FragPeopleRV";

  private FragPeopleRVListener fragPeopleRVListener;
  private TextView listTitle;
  private RecyclerView recyclerView;

  public interface FragPeopleRVListener{
    int getCalledPeopleListType();
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View theView = inflater.inflate(R.layout.rv_item_person_list, container, false);
    initViews(theView);
    initData();
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  private void initData() {
  }

  private void initViews(View theView) {
    listTitle = theView.findViewById(R.id.tv_frag_people_rv_title);
    recyclerView = theView.findViewById(R.id.rv_frag_people_rv_peoplelist);
  }




  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    try {
      fragPeopleRVListener = (FragPeopleRVListener) getActivity();
    } catch (ClassCastException e) {
      Log.e(TAG, "onAttach: ClassCastException" + e.getMessage());
    }

  }
}
