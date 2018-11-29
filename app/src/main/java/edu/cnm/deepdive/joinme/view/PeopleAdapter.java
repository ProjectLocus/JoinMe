package edu.cnm.deepdive.joinme.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.p2p.WifiP2pManager.DnsSdTxtRecordListener;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.cnm.deepdive.joinme.R;
import edu.cnm.deepdive.joinme.model.entity.Invitation;
import java.util.List;

/**
 * Describes an adapter for a {@link RecyclerView}.
 */
public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.Holder> {

  private static final String TAG = "SearchFragAdapter";

  private List<Invitation> listForRecycler;
  private Activity activity;

  public PeopleAdapter(Activity activity, List<Invitation> listForRecycler) {
    this.activity = activity;
    this.listForRecycler = listForRecycler;
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
    //TODO add image for invitation with Glide/Picasson implementation.
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
