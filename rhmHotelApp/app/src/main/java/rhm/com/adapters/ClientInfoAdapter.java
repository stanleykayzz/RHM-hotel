package rhm.com.adapters;

import android.content.Context;
import android.drm.DrmStore;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.release.rhm.rhmhotelapp.R;
import java.util.List;
import rhm.com.entities.ClientInformation;

/**
 * Created by alvin.ondzounga on 27/06/2017.
 */

public class ClientInfoAdapter extends ArrayAdapter<ClientInformation> {

    private Context context;
    private List<ClientInformation> infos;
    private ImageView editIcon;

    public ClientInfoAdapter(Context context, List<ClientInformation> i) {
        super(context, R.layout.account_info_layout, i);
        this.context = context;
        this.infos = i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layout = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layout.inflate(R.layout.account_info_layout,parent,false);
        if(infos.get(position).getLeftIcon() != 0){
            ImageView leftIcon = (ImageView) v.findViewById(R.id.leftIcon);
            leftIcon.setImageResource(infos.get(position).getLeftIcon());
        }
        if(infos.get(position).getRightIcon() != 0) {
            editIcon = (ImageView) v.findViewById(R.id.editIcon);
            editIcon.setImageResource(infos.get(position).getRightIcon());
        }
        else if(infos.get(position).getRightIcon() == 0) {
            editIcon = (ImageView) v.findViewById(R.id.editIcon);
            editIcon.setVisibility(View.GONE);
            editIcon.setImageResource(infos.get(position).getRightIcon());
        }
        //leftIcon.setImageResource(R.drawable.cogs);
        //editIcon.setImageResource(R.drawable.edit);
        TextView title = (TextView) v.findViewById(R.id.title);
        title.setText(infos.get(position).getTitle());
        TextView informationLabel = (TextView)v.findViewById(R.id.info);
        informationLabel.setText(infos.get(position).getInfo());
        return v;
    }

    @NonNull
    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<ClientInformation> getInfos() {
        return infos;
    }

    public void setInfos(List<ClientInformation> infos) {
        this.infos = infos;
    }

    public ImageView getEditIcon() {
        return editIcon;
    }

    public void setEditIcon(ImageView editIcon) {
        this.editIcon = editIcon;
    }
}