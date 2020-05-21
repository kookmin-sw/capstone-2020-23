package com.capstone.moayo.util.Async;

public interface AsyncExecutorAware<T> {
    public void setAsyncExecutor(AsyncExecutor<T> asyncExecutor);
}
