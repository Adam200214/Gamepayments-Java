package com.teamgames.gamepayments.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AbstractAPIResponse {

    protected String message, extendedMessage;

}
