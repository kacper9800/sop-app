/*
 * System Obsługi Praktyk
 * Kacper Rzymkiewicz #2020
 */

package pl.sop.payload.response;

public class MessageResponse {

    private String messagee;


    public MessageResponse(String messagee) {
        this.messagee = messagee;
    }

    public String getMessagee() {
        return messagee;
    }

    public void setMessagee(String messagee) {
        this.messagee = messagee;
    }
}
