package com.orangecat.android.newsreportapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by xwang17 on 11/26/15.
 */
public class NewsListFragment extends Fragment {
    private RecyclerView mNewsRecyclerView;
    private NewsAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);

        mNewsRecyclerView = (RecyclerView) view.findViewById(R.id.news_recycler_view);
        mNewsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();


        try {
            Logger.makeRequest(Logger.logEvent("CreateListView"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();


        try {
            Logger.makeRequest(Logger.logEvent("resumeListView"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateUI() {
        NewsLab newsLab = NewsLab.get(getActivity());
        List<News> newses = newsLab.getNewses();
        if (mAdapter == null){
            mAdapter = new NewsAdapter(newses);
            mNewsRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }

    }

    private class NewsHolder extends RecyclerView.ViewHolder
        implements  View.OnClickListener {
        public TextView mTitleTextView;
        public TextView mDateTextView;
        public CheckBox mPublicedCheckBox;

        private News mNews;

        public void bindNews(News news){
            mNews = news;
            mTitleTextView.setText(news.getTitle());
            mDateTextView.setText(news.getDate().toString());
            mPublicedCheckBox.setChecked(news.isPubliced());
        }

        public NewsHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_news_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_news_date_text_view);
            mPublicedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_news_publiced_check_box);
        }

        @Override
        public void onClick(View v) {
            Intent intent = NewsPagerActivity.newIntent(getActivity(), mNews.getId());
            startActivity(intent);
        }
    }

    private class NewsAdapter extends RecyclerView.Adapter<NewsHolder> {
        private List<News> mNewses;
        public NewsAdapter(List<News> newses) {
            mNewses = newses;
        }

        @Override
        public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_news, parent, false);
            return new NewsHolder(view);
        }

        @Override
        public void onBindViewHolder(NewsHolder holder, int position) {
            News news = mNewses.get(position);
            holder.bindNews(news);
        }

        @Override
        public int getItemCount() {
            return mNewses.size();
        }
    }
}
