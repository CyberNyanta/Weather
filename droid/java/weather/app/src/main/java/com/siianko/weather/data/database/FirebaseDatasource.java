package com.siianko.weather.data.database;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.siianko.weather.data.model.BaseFirebaseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by evgeniy.siyanko on 03.01.2017.
 */

public class FirebaseDatasource<M extends BaseFirebaseModel> extends ArrayList<M> implements ChildEventListener {

    public interface OnChangedListener{
        enum EventType {ADDED, CHANGED, REMOVED, MOVED}

        void onChanged(OnChangedListener.EventType type, int index, int oldIndex);

        void onCancelled(DatabaseError databaseError); //// TODO: 10.01.2017 change DatabaseError
    }

    private Query mDatabaseReference;
    private List<OnChangedListener> mListeners;
    private Class<M> type;

    public FirebaseDatasource(Query ref, Class<M> type) {
        super();
        mDatabaseReference = ref;
        this.type = type;
        mDatabaseReference.addChildEventListener(this);
        mListeners = new ArrayList<>();

    }

    public void cleanup() {
        mDatabaseReference.removeEventListener(this);
    }

    @Override
    public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
        M model = snapshot.getValue(type);
        model.setId(snapshot.getKey());
        super.add(model);
        notifyChangedListeners(OnChangedListener.EventType.ADDED, this.size() - 1);
    }

    @Override
    public void onChildChanged(DataSnapshot snapshot, String previousChildKey) {
        //GenericTypeIndicator<M> t = new GenericTypeIndicator<M>(){};
        M model = snapshot.getValue(type);
        model.setId(snapshot.getKey());
        int index = -1;
        for (int i = 0; i < this.size(); i++) {
            if (Objects.equals(this.get(i).getId(), model.getId())) {
                super.set(i, model);
                index = i;
                break;
            }
        }
        notifyChangedListeners(OnChangedListener.EventType.CHANGED, index);
    }

    @Override
    public void onChildRemoved(DataSnapshot snapshot) {
        final String key = snapshot.getKey();
        int index = -1;
        for (int i = 0; i < this.size(); i++) {
            if (Objects.equals(this.get(i).getId(), key)) {
                super.remove(i);
                index = i;
                break;
            }
        }
        notifyChangedListeners(OnChangedListener.EventType.REMOVED, index);
    }

    @Override
    public void onChildMoved(DataSnapshot snapshot, String previousChildKey) {
       /* int oldIndex = getIndexForKey(snapshot.getKey());
        mSnapshots.remove(oldIndex);
        int newIndex = previousChildKey == null ? 0 : (getIndexForKey(previousChildKey) + 1);
        mSnapshots.add(newIndex, snapshot);
        notifyChangedListeners(FirebaseArray.OnChangedListener.EventType.MOVED, newIndex, oldIndex);*/
    }

    @Override
    public void onCancelled(DatabaseError error) {
        notifyCancelledListeners(error);
    }

    @Deprecated
    @Override
    public M set(int index, M element) {
        String id = this.get(index).getId();
        mDatabaseReference.getRef().child(id).setValue(element);
        return element;
    }

    public void set(M element) {
        mDatabaseReference.getRef().child(element.getId()).setValue(element);
    }

    public M get(String id){
        return null;
    }

    @Override
    public boolean add(M element) {
        mDatabaseReference.getRef().push().setValue(element).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {

            }
        });
        return true;
    }

    public void add(M element, OnCompleteListener<Void> onCompleteListener) {
        mDatabaseReference.getRef().push().setValue(element).addOnCompleteListener(onCompleteListener);
    }

    public void set(M element, OnCompleteListener<Void> onCompleteListener) {
        mDatabaseReference.getRef().child(element.getId()).setValue(element).addOnCompleteListener(onCompleteListener);
    }

    public void remove(String id) {
        mDatabaseReference.getRef().child(id).setValue(null);
    }

    public void addOnChangedListener(OnChangedListener listener) {
        mListeners.add(listener);
    }

    protected void notifyChangedListeners(OnChangedListener.EventType type, int index) {
        notifyChangedListeners(type, index, -1);
    }

    protected void notifyChangedListeners(OnChangedListener.EventType type, int index, int oldIndex) {
        if (mListeners.size() != 0) {
            for (OnChangedListener listener : mListeners) {
                listener.onChanged(type, index, oldIndex);
            }
        }
    }

    protected void notifyCancelledListeners(DatabaseError databaseError) {
        if (mListeners.size() != 0) {
            for (OnChangedListener listener : mListeners) {
                listener.onCancelled(databaseError);
            }
        }
    }

}