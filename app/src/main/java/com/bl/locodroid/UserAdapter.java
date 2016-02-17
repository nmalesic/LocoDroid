package com.bl.locodroid;



    import android.widget.BaseAdapter;

    import java.util.ArrayList;

    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.TextView;

    import com.bl.locodroid.user.User;

public class UserAdapter extends BaseAdapter {

        private ArrayList<User> users;
        private LayoutInflater myInflater;



        public UserAdapter (Context context, ArrayList<User> _users)
        {
            this.myInflater = LayoutInflater.from(context);
            this.users = _users;
        }

        @Override
        public int getCount() {
            return this.users.size();
        }

        @Override
        public Object getItem(int arg0) {
            return this.users.get(arg0);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public static class ViewHolder {
            TextView text01;
            TextView text02;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null)
            {
                convertView = myInflater.inflate(R.layout.list_view_row, null);
                holder = new ViewHolder();
                holder.text01 = (TextView) convertView.findViewById(R.id.lastName);
                holder.text02 = (TextView) convertView.findViewById(R.id.firstName);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.text01.setText(users.get(position).getLastName());
            holder.text02.setText(users.get(position).getFirstName());

            return convertView;

        }

    }
