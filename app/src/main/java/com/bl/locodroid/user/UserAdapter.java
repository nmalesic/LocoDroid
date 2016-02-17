package com.bl.locodroid.user;

    import android.graphics.Color;
    import android.widget.ArrayAdapter;

    import java.util.ArrayList;

    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.TextView;

    import com.bl.locodroid.R;
    import com.bl.locodroid.model.LocoModel;

public class UserAdapter extends ArrayAdapter<User>{

    LocoModel model = LocoModel.getInstance();

    ArrayList<User> users;
    int viewRes;

    public UserAdapter(Context context, int tvResId, ArrayList<User> users) {
        super(context, tvResId, users);
        this.users = users;
        this.viewRes = tvResId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View maVue = convertView;
        if(maVue == null) {
            LayoutInflater vi = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            maVue = vi.inflate(this.viewRes, parent, false);
        }

        User personne = this.users.get(position);
        if(personne != null) {
            
            this.renseigneTextView(maVue, R.id.lastName, personne.getLastName() + " " + personne.getFirstName());
            this.renseigneTextView(maVue, R.id.address, personne.getAdress1()+" "+personne.getAdress2()+" "+ personne.getCodePostal()+" "+personne.getCity());
            this.renseigneTextView(maVue, R.id.email, personne.getEmail());
            this.renseigneTextView(maVue, R.id.phone, personne.getTelephone());

        }
        return maVue;
    }

    private void renseigneTextView(View maVue, int tvId, String valeur) {
        TextView tvView = (TextView)maVue.findViewById(tvId);
        if(tvView != null) {
            tvView.setText(valeur);
        }
    }
}
