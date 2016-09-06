package com.tom.lib.sample.rxbus;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.rxbus.RxBus;
import com.tom.lib.sample.R;

import java.util.concurrent.atomic.AtomicInteger;


public class ObservableFragment extends Fragment implements View.OnClickListener{
    private AtomicInteger eventId=new AtomicInteger();
    private AtomicInteger stickyEventId=new AtomicInteger();

    private TextView mTvEvent;
    private TextView mTvStickyEvent;

    public ObservableFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment ObservableFragment.
     */
    public static ObservableFragment newInstance() {
        ObservableFragment fragment = new ObservableFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView=inflater.inflate(R.layout.fragment_observable, container, false);
        mView.findViewById(R.id.btn_event).setOnClickListener(this);
        mView.findViewById(R.id.btn_sticky_event).setOnClickListener(this);
        mTvEvent=(TextView)mView.findViewById(R.id.tv_event);
        mTvStickyEvent=(TextView)mView.findViewById(R.id.tv_sticky_event);
        return mView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_event){
            Event mEvent=new Event(eventId.incrementAndGet());
            RxBus.getDefault().post(mEvent);
            StringBuilder builder=new StringBuilder(mTvEvent.getText().toString());
            builder.append(",").append(eventId.get());
            mTvEvent.setText(builder.toString());
        }else if(v.getId()==R.id.btn_sticky_event){
            StickyEvent stickyEvent=new StickyEvent(stickyEventId.incrementAndGet());
            RxBus.getDefault().postSticky(stickyEvent);
            StringBuilder builder=new StringBuilder(mTvStickyEvent.getText().toString());
            builder.append(",").append(stickyEventId.get());
            mTvStickyEvent.setText(builder.toString());
        }
    }
}
