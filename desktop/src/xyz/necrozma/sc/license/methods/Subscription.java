package xyz.necrozma.sc.license.methods;

import xyz.necrozma.sc.license.internal.BasicResult;
import xyz.necrozma.sc.license.internal.HelperMethods;
import xyz.necrozma.sc.license.models.RecordUsageModel;

import java.util.HashMap;
import java.util.Map;

/**
 * These methods are related to the <a href="https://help.cryptolens.io/recurring-payments/index">recurring billing</a> module.
 * They can all be accessed using an access token with 'Subscription' permission.
 */
public class Subscription {
    /**
     * This method records uses Stripe's metered billing to record usage for a certain subscription.
     * In order to use this mehtod, you need to have set up recurring billing. A record will be created using Stripe's API with action set to 'increment'.
     * More info: https://app.cryptolens.io/docs/api/v3/RecordUsage
     * @param token An access token with "GetProducts" permission.
     * @return BasicResult object. Null can be returned if an error occurs.
     */
    public static BasicResult RecordUsage (String token, RecordUsageModel model) {
        Map<String, String> extraParams = new HashMap<>();
        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("subscription/recordusage/", model, extraParams, BasicResult.class);
    }
}
