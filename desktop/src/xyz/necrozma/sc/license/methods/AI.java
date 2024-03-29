package xyz.necrozma.sc.license.methods;

import xyz.necrozma.sc.license.internal.BasicResult;
import xyz.necrozma.sc.license.internal.HelperMethods;
import xyz.necrozma.sc.license.models.RegisterEventModel;

import java.util.HashMap;
import java.util.Map;

public class AI {

    /**
     * <p>This method will register an event that has occured in either the client app
     * (eg. start of a certain feature or interaction within a feature) or in a third
     * party provider (eg. a payment has occured, etc).</p>
     * <p>Note: You can either use this method standalone (eg. by only providing a machine code/device identifier)
     * or together with Cryptolens Licensing module (which requires productId and optionally keyid to be set).
     * The more information that is provided, the better insights can be provided.</p>
     * @param token The access token with 'RegisterEvent' permission.
     * @param model The input parameters of the method.
     * @return
     */
    public static BasicResult RegisterEvent(String token, RegisterEventModel model) {
        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("AI/RegisterEvent", model, extraParams, BasicResult.class);
    }
}
