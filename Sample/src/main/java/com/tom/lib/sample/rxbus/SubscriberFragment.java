package com.tom.lib.sample.rxbus;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.rxbus.RxBus;
import com.base.rxbus.RxBusSubscriber;
import com.base.rxbus.RxSubscriptions;
import com.tom.lib.sample.R;

import rx.Subscription;


public class SubscriberFragment extends Fragment implements View.OnClickListener{

    private StringBuffer bufferEvent=new StringBuffer();

    private TextView mTvEvent;
    private TextView mTvStickyEvent;
    private Subscription rxBus;
    private Subscription rxStickyBus;

    public SubscriberFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment SubscriberFragment.
     */
    public static SubscriberFragment newInstance() {
        SubscriberFragment fragment = new SubscriberFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mView=inflater.inflate(R.layout.fragment_subscriber, container, false);
        mTvEvent=(TextView) mView.findViewById(R.id.tv_event);
        mTvStickyEvent=(TextView) mView.findViewById(R.id.tv_sticky_event);
        mView.findViewById(R.id.btnSubscribeStickyEvent).setOnClickListener(this);
        subscriberEvent();
        return mView;
    }

    private void subscriberEvent(){
        rxBus=RxBus.getDefault().toObservable(Event.class)

                .subscribe(new RxBusSubscriber<Event>() {
                    @Override
                    protected void onEvent(Event event) {
                        bufferEvent.delete(0,bufferEvent.length());
                        bufferEvent.append(mTvEvent.getText().toString()).append(",").append(event.getEventId());
                        mTvEvent.setText(bufferEvent.toString());
                    }
                });

        RxSubscriptions.add(rxBus);

    }

    private void subscribeStickyEvent(){
        rxStickyBus=RxBus.getDefault().toStickyObservable(StickyEvent.class)
                .subscribe(new RxBusSubscriber<StickyEvent>() {
                    @Override
                    protected void onEvent(StickyEvent stickyEvent) {
                        bufferEvent.delete(0,bufferEvent.length());
                        bufferEvent.append(mTvStickyEvent.getText().toString()).append(",").append(stickyEvent.getEventId());
                        mTvStickyEvent.setText(bufferEvent.toString());
                    }
                });

        RxSubscriptions.add(rxStickyBus);
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
        if(v.getId()==R.id.btnSubscribeStickyEvent){
            subscribeStickyEvent();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxSubscriptions.remove(rxBus);
        RxSubscriptions.remove(rxStickyBus);
    }
}
