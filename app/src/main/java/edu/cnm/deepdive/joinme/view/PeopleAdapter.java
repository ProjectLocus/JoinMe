package edu.cnm.deepdive.joinme.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.cnm.deepdive.joinme.R;
import edu.cnm.deepdive.joinme.controller.MainActivity;
import edu.cnm.deepdive.joinme.model.entity.Invitation;
import edu.cnm.deepdive.joinme.model.entity.Person;
import java.util.List;
import java.util.Objects;
import java.util.Random;
/**
 * Describes an adapter for a {@link RecyclerView}.
 */
public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.Holder> {

  private static final String TAG = "SearchFragAdapter";

  private List<Person> listForRecycler;
  private Activity activity;
  private Context context;


  /**
   * Constructor
   * @param activity
   * @param listForRecycler
   */
  public PeopleAdapter(Activity activity, List<Person> listForRecycler, Context context) {
    this.activity = activity;
    this.listForRecycler = listForRecycler;
    this.context = context;
  }


  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.rv_item_person_list, parent, false);
    return new Holder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull final Holder holder, int position) {
    String[] picRes = {"https://rawcdn.githack.com/ProjectLocus/project-content/0bf856eff9c7e79f9066ddbb8b2eec53ee390060/join-me-pics/alex.jpg",
        "https://rawcdn.githack.com/ProjectLocus/project-content/0bf856eff9c7e79f9066ddbb8b2eec53ee390060/join-me-pics/brian.jpg",
        "https://rawcdn.githack.com/ProjectLocus/project-content/0bf856eff9c7e79f9066ddbb8b2eec53ee390060/join-me-pics/johnrow.JPG",
        "https://rawcdn.githack.com/ProjectLocus/project-content/0bf856eff9c7e79f9066ddbb8b2eec53ee390060/join-me-pics/lily.jpg",
        "https://rawcdn.githack.com/ProjectLocus/project-content/0bf856eff9c7e79f9066ddbb8b2eec53ee390060/join-me-pics/notdeb.jpg",
        "https://rawcdn.githack.com/ProjectLocus/project-content/0bf856eff9c7e79f9066ddbb8b2eec53ee390060/join-me-pics/weenie.jpg"};
    Random rng = new Random();
    Glide.with(context).load(picRes[rng.nextInt(picRes.length)]).into(holder.image);
    // holder.name.setText(listForRecycler.get(position).getFirstName());
    //TODO fix error for above execution: java.lang.NullPointerException: Attempt to invoke virtual method 'void android.widget.TextView.setText(java.lang.CharSequence)' on a null object reference
    String[] locDet = {"Ear shot", "Arms length", "Eye sight"};
    holder.comments.setText(locDet[rng.nextInt(locDet.length)]);
  }



  @Override
  public int getItemCount() {
    return listForRecycler.size();
  }

  /**
   * Stores references to {@link View}.
   */
  class Holder extends RecyclerView.ViewHolder {

    private CircleImageView image;
    private TextView name;
    private TextView comments;

    /**
     * Instantiates a new Holder.
     *
     * @param itemView the item view
     */
    public Holder(@NonNull View itemView) {
      super(itemView);
      image = itemView.findViewById(R.id.civ_rv_item_person_profile_image);
      name = itemView.findViewById(R.id.tv_frag_people_rv_title);
      comments = itemView.findViewById(R.id.tv_rv_item_person_comment);
    }
  }
}
