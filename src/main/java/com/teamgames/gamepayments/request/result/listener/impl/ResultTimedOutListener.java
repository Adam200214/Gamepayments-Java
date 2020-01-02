package com.teamgames.gamepayments.request.result.listener.impl;

import com.teamgames.gamepayments.request.Request;
import com.teamgames.gamepayments.request.result.Result;
import com.teamgames.gamepayments.request.result.listener.ResultListener;

/**
 * Created by Jason MK on 2020-01-02 at 3:27 p.m.
 */
public interface ResultTimedOutListener<T extends Result, R extends Request<T>> extends ResultListener<T, R> {

}
