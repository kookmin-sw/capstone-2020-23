package com.capstone.moayo.util.Async;

import android.os.AsyncTask;
import android.util.Log;

import java.util.concurrent.Callable;

public class AsyncExecutor<T> extends AsyncTask<Void, Void, T> {
    private static final String TAG = "Exception error on AsyncExecutor";

    private AsyncCallback<T> callback;
    private Callable<T> callable;
    private Exception occuredException;

    public AsyncExecutor<T> setCallable(Callable<T> callable) {
        this.callable = callable;
        return this;
    }

    public AsyncExecutor<T> setCallback(AsyncCallback<T> callback) {
        this.callback = callback;
        processAsyncExecutorAware(callback);
        return this;
    }

    @SuppressWarnings("unchecked")
    private void processAsyncExecutorAware(AsyncCallback<T> callback) {
        if(callback instanceof AsyncExecutorAware)
        ((AsyncExecutorAware<T>) callback).setAsyncExecutor(this);
    }

    @Override
    protected void onPostExecute(T t) {
        if(isCancelled()) {
            notifyCancelled();
        }
        if(isExceptionOccured()) {
            notifyException();
            return;
        }

        notifyResult(t);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected T doInBackground(Void... ts) {
        try {
           return  callable.call();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            this.occuredException = e;
        }
        return null;
    }

    private void notifyCancelled() {
        if(callback != null)
            callback.cancelled();
    }

    private boolean isExceptionOccured() {
        return occuredException != null;
    }

    private void notifyException() {
        if(callback != null)
            callback.exceptionOccured(occuredException);
    }

    private void notifyResult(T result) {
        if(callback != null)
            callback.onResult(result);
    }
}
