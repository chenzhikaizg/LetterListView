package com.example.letterlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LetterSideBar.LetterTouchListener {
    private TextView mLetterTv;
    private LetterSideBar mLetterSideBar;
    private List<ContactBean> mLists;
    private RecyclerView rvLetter;
    private List<ContactBean> mCbList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLetterTv = (TextView) findViewById(R.id.letter_tv);
        mLetterSideBar = (LetterSideBar) findViewById(R.id.letter_side_bar);
        mLetterSideBar.setOnLetterTouchListener(this);

        rvLetter = (RecyclerView)findViewById(R.id.rv_letter);
        mLists = new ArrayList<>();
        for (int i=0;i<27;i++){
            ContactBean contactBean = new ContactBean();
            if (i==26){
                contactBean.setLetter("#");
            }else {
                contactBean.setLetter(String.valueOf((char)(i+'A')));
            }
            List<ContactBean.ContactBeanDetail> mCbList=  new ArrayList<>();
            for (int a=0;a<10;a++){
                ContactBean.ContactBeanDetail contactBeanDetail = new ContactBean.ContactBeanDetail();
                contactBeanDetail.setContactPerson("I am"+i);
                mCbList.add(contactBeanDetail);
            }
            contactBean.setContactBeanDetail(mCbList);
        mLists.add(contactBean);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvLetter.setLayoutManager(linearLayoutManager);
        rvLetter.setItemAnimator(new DefaultItemAnimator());
        rvLetter.setAdapter(new MyAdapter());
    }

    @Override
    public void touch(CharSequence letter,boolean isTouch,int current) {
        if(isTouch) {
            mLetterTv.setVisibility(View.VISIBLE);
            mLetterTv.setText(letter);
            rvLetter.scrollToPosition(current);
            //让RecyclerView滚动到顶部
            if (current != -1) {
                rvLetter.scrollToPosition(current);
                LinearLayoutManager mLayoutManager =
                        (LinearLayoutManager) rvLetter.getLayoutManager();
                mLayoutManager.scrollToPositionWithOffset(current, 0);
            }

        }else{
            mLetterTv.setVisibility(View.GONE);

        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.adapter_item, parent, false);

            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tvText.setText(mLists.get(position).getLetter());
            holder.rvChild.setAdapter(new MyChildAdapter(position));
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            holder.rvChild.setLayoutManager(linearLayoutManager);
            holder.rvChild.setItemAnimator(new DefaultItemAnimator());



        }

        @Override
        public int getItemCount() {
            return mLists.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private  TextView tvText;
        private  RecyclerView rvChild;



        public MyViewHolder(View itemView) {
            super(itemView);
            tvText = (TextView)itemView.findViewById(R.id.tv_text);
            rvChild = (RecyclerView)itemView.findViewById(R.id.rv_child);

        }
    }

    class MyChildAdapter extends RecyclerView.Adapter<MyChildViewHolder>{

        private int  mPosition  ;
        public MyChildAdapter(int  position){
            this.mPosition = position;
        }

        @Override
        public MyChildViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.adapter_child_item, parent, false);

            return new MyChildViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MyChildViewHolder holder, int position) {
            holder.tvChild.setText(mLists.get(mPosition).getContactBeanDetail().get(position).getContactPerson());
        }

        @Override
        public int getItemCount() {
            return mLists.get(mPosition).getContactBeanDetail().size();
        }
    }

    class MyChildViewHolder extends RecyclerView.ViewHolder{

        private  TextView tvChild;


        public MyChildViewHolder(View itemView) {
            super(itemView);
            tvChild = (TextView)itemView.findViewById(R.id.tv_child_text);


        }
    }


}
