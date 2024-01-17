package xyz.necrozma.sc.license.models;

import com.google.gson.annotations.SerializedName;
import xyz.necrozma.sc.license.internal.BasicResult;

import java.util.List;

public class GetMessagesResult extends BasicResult {

    @SerializedName(value = "messages", alternate = {"Messages"})
    public List<MessageObject> Messages;
}
