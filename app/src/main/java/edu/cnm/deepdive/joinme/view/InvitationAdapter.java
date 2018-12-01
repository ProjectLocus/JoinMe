package edu.cnm.deepdive.joinme.view;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.cnm.deepdive.joinme.R;
import edu.cnm.deepdive.joinme.model.entity.Invitation;
import java.util.List;
import java.util.Random;

/**
 * Describes an adapter for a {@link RecyclerView}.
 */
public class InvitationAdapter extends RecyclerView.Adapter<InvitationAdapter.Holder> {

  private static final String TAG = "SearchFragAdapter";

  private List<Invitation> listForRecycler;
  private Activity activity;

  public InvitationAdapter(Activity activity, List<Invitation> listForRecycler) {
    this.activity = activity;
    this.listForRecycler = listForRecycler;
  }


  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.rv_item_invitation_list, parent, false);
    return new Holder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull final Holder holder, int position) {
    int[] picRes = {R.drawable.alex,R.drawable.brian,R.drawable.notdeb,R.drawable.johnrow,R.drawable.lily,R.drawable.weenie};
    Random rng = new Random();
    holder.image.setImageResource(picRes[rng.nextInt(picRes.length-1)]);
    holder.comments.setText(listForRecycler.get(position).getDescription());

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
    private TextView title;
    private TextView comments;

    /**
     * Instantiates a new Holder.
     *
     * @param itemView the item view
     */
    public Holder(@NonNull View itemView) {
      super(itemView);
      image = itemView.findViewById(R.id.civ_rv_item_invitationlist_profile_image);
      title = itemView.findViewById(R.id.tv_frag_invitation_rv_title);
      comments = itemView.findViewById(R.id.tv_rv_item_invitationlist_comments);
    }
  }
}
