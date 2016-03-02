package com.tty.retrofit_okhttp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tty.retrofit_okhttp.R;
import com.tty.retrofit_okhttp.model.Repo;

import java.util.List;

/**
 * author: tangyiwu
 * create: 16/3/2
 * email: tangyiwu@haizhi.com
 * description: ...
 */
public class RepoAdapter extends BaseAdapter {
    private List<Repo> dataSource;
    private Context mContext;

    public RepoAdapter(List<Repo> repos, Context context) {
        this.dataSource = repos;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.repo_list_item, null);
            holder.repo_name = (TextView) convertView.findViewById(R.id.repo_name);
            holder.repo_url = (TextView) convertView.findViewById(R.id.tv_repo_url);
            holder.repo_star_count = (TextView) convertView.findViewById(R.id.tv_star_count);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Repo repo = dataSource.get(position);
        holder.repo_name.setText(repo.name);
        holder.repo_url.setText(repo.url);
        holder.repo_star_count.setText(String.valueOf(repo.stargazers_count));
        return convertView;
    }

    private static class ViewHolder {
        TextView repo_name;
        TextView repo_url;
        TextView repo_star_count;
    }
}
