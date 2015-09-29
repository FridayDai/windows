package utils

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.*;
import com.google.api.services.gmail.Gmail

public class GmailAPI {
    /** Application name. */
    private static final String APPLICATION_NAME =
            "Gmail API Java";

    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File("./gmail-api");

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
            JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required by this quickstart. */
    private static final List<String> SCOPES =
            Arrays.asList(GmailScopes.MAIL_GOOGLE_COM);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream input = GmailAPI.class.getResourceAsStream("/client_secret.json");
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(input));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                        .setDataStoreFactory(DATA_STORE_FACTORY)
                // .setAccessType("offline")
                        .build();
        Credential credential = new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    /**
     * Build and return an authorized Gmail client service.
     * @return an authorized Gmail client service
     * @throws IOException
     */
    public static Gmail getGmailService() throws IOException {
        Credential credential = authorize();
        return new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static List<Message> listMessagesMatchingQuery(Gmail service, String userId,
                                                          String query) throws IOException {
        ListMessagesResponse response = service.users().messages().list(userId).setQ(query).execute();

        List<Message> messages = new ArrayList<Message>();
        while (response.getMessages() != null) {
            messages.addAll(response.getMessages());
            if (response.getNextPageToken() != null) {
                String pageToken = response.getNextPageToken();
                response = service.users().messages().list(userId).setQ(query)
                        .setPageToken(pageToken).execute();
            } else {
                break;
            }
        }

        for (Message message : messages) {
            System.out.println(message.toPrettyString());
        }

        return messages;
    }

    public static String getMessage(Gmail service, String userId, String messageId)
            throws IOException {
        String messageContents = new String();
        Message message = service.users().messages().get(userId, messageId).setFormat("full").execute();

        System.out.println("Message snippet: " + message.getSnippet());

        List<MessagePart> parts = message.getPayload().getParts();
        if (parts.size() == 0) {
            System.out.println("No messages part found.");
        } else {
            for (MessagePart part : parts) {
                if (part.getMimeType().equalsIgnoreCase("text/plain")) {
                    String text = new String(part.getBody().decodeData());
                    messageContents = text;

                    System.out.println("Message filename: " + part.getFilename());
                    System.out.println("Message mimeType: " + part.getMimeType());
                    System.out.println("Message data: " + text);
                    System.out.println("");
                }
            }
        }

        return messageContents;
    }

    public static void modifyThread(Gmail service, String userId, String threadId,
                                    List<String> labelsToAdd, List<String> labelsToRemove) throws IOException {
        ModifyThreadRequest mods = new ModifyThreadRequest().setAddLabelIds(labelsToAdd)
                .setRemoveLabelIds(labelsToRemove);
        Thread thread = service.users().threads().modify(userId, threadId, mods).execute();

        System.out.println("Thread id: " + thread.getId());
        System.out.println(thread.toPrettyString());
    }

//    public static void main(String[] args) throws IOException {
//        // Build a new authorized API client service.
//        Gmail service = getGmailService();
//
//        // Print the labels in the user's account.
//        String user = "me";
//        // ListLabelsResponse listResponse =
//        //     service.users().labels().list(user).execute();
//
//        List<Message> messages = listMessagesMatchingQuery(service, user, "FN+car1440590895514");
//        //with the q, you can also use filter, e.g "FN+car1440590895514 is:unread"
//
//        // List<Label> labels = listResponse.getLabels();
//        if (messages.size() == 0) {
//            System.out.println("No messages found.");
//        } else {
//            System.out.println("Matched messages:");
//            for (Message message : messages) {
//                getMessage(service, user, message.getId());
//            }
//        }
//    }

}