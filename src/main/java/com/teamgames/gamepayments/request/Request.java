package com.teamgames.gamepayments.request;

import com.teamgames.gamepayments.request.result.Result;
import org.apache.http.client.fluent.Executor;


/**
 * Created by Jason MK on 2020-01-02 at 2:20 p.m.
 */
public interface Request<R extends Result> {

    R create(Executor httpClient) throws Exception;
}
