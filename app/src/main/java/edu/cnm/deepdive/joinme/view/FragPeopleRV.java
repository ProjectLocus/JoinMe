package edu.cnm.deepdive.joinme.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import edu.cnm.deepdive.joinme.R;
import edu.cnm.deepdive.joinme.controller.MainActivity;
import edu.cnm.deepdive.joinme.model.entity.Person;
import edu.cnm.deepdive.joinme.model.utility.DummyInvitationGenerator;
import edu.cnm.deepdive.joinme.model.utility.DummyPersonGenerator;
import java.util.List;

/**
 * Recycler View fragment which shows lists of Person objects.
 */
public class FragPeopleRV extends Fragment {

  private static final String TAG = "FragPeopleRV";

  private FragPeopleRVListener fragPeopleRVListener;
  private TextView listTitle;
  private RecyclerView recyclerView;
  private List<Person> persons;
  private DummyInvitationGenerator dummyInvitationGenerator;


  /**
   * Public interface listener.
   */
  public interface FragPeopleRVListener {

    int getCalledPeopleListType();

    MainActivity getParentActivity();
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View theView = inflater.inflate(R.layout.fragment_people_rv, container, false);
    initViews();
    initRecyclerview(theView);
    initData();
    listTitle = theView.findViewById(R.id.tv_frag_people_rv_title);
    listTitle.setText("People Near Me");
    recyclerView = theView.findViewById(R.id.rv_frag_people_rv_peoplelist);
    persons = fragPeopleRVListener.getParentActivity().getPeopleAroundMeList();
    PeopleAdapter adapter = new PeopleAdapter(getActivity(), persons, getContext());
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    recyclerView.setAdapter(adapter);
    Glide.with(this);
    return theView;
  }

  @Override
  public void onResume() {
    super.onResume();
  }

  private void initData() {
  }

  private void initViews() {

  }


  private void initRecyclerview(View theView) {
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

  // TODO Add gson retrofit service object setup function here.


}
