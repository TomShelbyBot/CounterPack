package us.peaky.counterpack.api;

public class CounterPackApi {
    private static ChatCounterManager chatCounterManager;

    public CounterPackApi() {
    }

    public static ChatCounterManager getChatCounterManager() {
        return chatCounterManager;
    }

    public static void setChatCounterManager(ChatCounterManager chatCounterManager) {
        CounterPackApi.chatCounterManager = chatCounterManager;
    }
}
