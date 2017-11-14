package kz.allpay.api.model.response;

import kz.allpay.api.model.Card;

import java.io.Serializable;
import java.util.List;

public class UserSavedCardsResponse extends AbstractResponse implements Serializable{

    private List<Card> cards;

    public UserSavedCardsResponse(String userMessage, String developerMessage, List<Card> cards) {
        super(userMessage, developerMessage);
        this.cards = cards;
    }

    public UserSavedCardsResponse(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
