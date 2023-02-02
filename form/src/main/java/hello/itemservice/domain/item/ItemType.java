package hello.itemservice.domain.item;

public enum ItemType {
    BOOK("도서"), FOOD("식품"), ETC("기타");

    private final String itemTypeString;

    ItemType(String itemTypeString) {
        this.itemTypeString = itemTypeString;
    }

    public String getItemTypeString() {
        return itemTypeString;
    }


}
