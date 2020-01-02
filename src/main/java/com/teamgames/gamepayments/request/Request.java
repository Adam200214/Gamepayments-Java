package com.teamgames.gamepayments.request;

import com.teamgames.gamepayments.request.result.Result;

import java.util.concurrent.Callable;

/**
 * Created by Jason MK on 2020-01-02 at 2:20 p.m.
 */
public interface Request<R extends Result> extends Callable<R> {

}
