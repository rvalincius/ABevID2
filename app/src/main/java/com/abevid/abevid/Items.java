package com.abevid.abevid;

/**
 * Used to establish the structure of any new items created
 */
public class Items {
    public String iIdentifier;
    public String iContent;

    /**
     * Constructor used to format each item correctly
     * @param id The string identifier used to identify each item
     * @param content The value of each item
     */
    public Items(String id, String content) {
        this.iIdentifier = id;
        this.iContent = content;
    }

}
