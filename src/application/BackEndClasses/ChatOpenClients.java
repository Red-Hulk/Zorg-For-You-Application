package application.BackEndClasses;

public class ChatOpenClients {
    private final int gesprek_id;
    private final int client_id;
    private final String onderwerp;

    public ChatOpenClients(int gesprek_id, int client_id, String onderwerp) {
        this.gesprek_id = gesprek_id;
        this.client_id = client_id;
        this.onderwerp = onderwerp;
    }

    public int getGesprek_id() {
        return gesprek_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public String getOnderwerp() {
        return onderwerp;
    }
}
