package com.tom.lib.sample.rxbus;

/**
 * Author:yuanzeyao<br/>
 * Date:16/9/5 下午9:07
 * Email:yuanzeyao@qiyi.com
 */
public class StickyEvent {

    private int eventId;

    public StickyEvent(int eventId){
        this.eventId=eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getEventId() {
        return eventId;
    }
}
