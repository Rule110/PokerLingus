package network.framework;

import network.implementation.TwitterNetwork;

public class NetworkFactory {
    public static Network getNetwork(String type, String userHandle){
        Network network;
        
        switch (type){
        case "Twitter":
            network = new TwitterNetwork(userHandle);
            break;
        default:
            throw new RuntimeException();
        }
        
        return network;
    }
}
