package com.coppermobile.myapplication.utils;

public class SingleEvent<T> {

    private T content;
    private boolean hasBeenHandled = false;

    public SingleEvent(T content) {
        this.content = content;
    }

    /**
     * Returns the content and prevents its use again.
     */
    public T getContentIfNotHandled() {
        if(hasBeenHandled){
            return null;
        }else {
            hasBeenHandled = true;
            return content;
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    public T peekContent() {
        return content;
    }
}
