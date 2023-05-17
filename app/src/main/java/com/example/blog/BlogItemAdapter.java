package com.example.blog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class BlogItemAdapter extends BaseAdapter {
    private final Context context;
    private final List<BlogItem> listBlogItem;
    private final LayoutInflater inflater;

    public BlogItemAdapter(Context context, List<BlogItem> listBlogItem) {
        this.context = context;
        this.listBlogItem = listBlogItem;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listBlogItem.size();
    }

    @Override
    public BlogItem getItem(int position) {
        return listBlogItem.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.blog_item, null);
            View convertView1 = inflater.inflate(R.layout.activity_blog_details, null);

            viewHolder = new ViewHolder();
            viewHolder.auteurTextView = convertView1.findViewById(R.id.auteurTextView);
            viewHolder.titreTextView = convertView.findViewById(R.id.titre_blog_txt);
            viewHolder.textTextView = convertView.findViewById(R.id.text_blog_txt);
            viewHolder.dateTextView = convertView1.findViewById(R.id.dateTextView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        BlogItem blogItem = listBlogItem.get(position);
        viewHolder.auteurTextView.setText(blogItem.getAuteur());
        viewHolder.titreTextView.setText(blogItem.getTitre());
        viewHolder.textTextView.setText(blogItem.getText());
        viewHolder.dateTextView.setText(blogItem.getDate());

        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(context, BlogDetailsActivity.class);
            intent.putExtra("auteur", blogItem.getAuteur());
            intent.putExtra("titre", blogItem.getTitre());
            intent.putExtra("text", blogItem.getText());
            intent.putExtra("date", blogItem.getDate());
            context.startActivity(intent);
        });
        return convertView;
    }


    static class ViewHolder {
        TextView auteurTextView;
        TextView titreTextView;
        TextView textTextView;
        TextView dateTextView;

    }

}
