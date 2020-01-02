package com.teamgames.gamepayments.request;

import com.teamgames.gamepayments.request.result.Result;

/**
 * Created by Jason MK on 2020-01-02 at 3:18 p.m.
 */
public interface RequestEventResultOkListener<T extends Result, R extends Request<T>> {

    void onOk();

    void onFailedErroneously();

    void onTimedOut();

}
