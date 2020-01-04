package com.teamgames.gamepayments.deprecated_request;

import com.teamgames.gamepayments.deprecated_request.result.Result;

import java.util.concurrent.Callable;

/**
 * Created by Jason MK on 2020-01-02 at 2:20 p.m.
 */
public interface Request<R extends Result> extends Callable<R> {

}
