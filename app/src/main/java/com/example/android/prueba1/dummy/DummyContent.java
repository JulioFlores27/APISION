package com.example.android.prueba1.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

//    private static final int COUNT = 25;
//
//    static {
//        // Add some sample items.
//        for (int i = 1; i <= COUNT; i++) {
//            addItem(createDummyItem(i));
//        }
//    }

    static {
        //Add 3 sample items
        addItem(new DummyItem("1", "18050400", "Belausteguigoitia", "javierb@nervion.com.mx", "5537176964"));
        addItem(new DummyItem("2", "18050401", "Belausteguigoitia", "javierb@nervion.com.mx", "5537176964"));
        addItem(new DummyItem("3", "18050402", "Belausteguigoitia", "javierb@nervion.com.mx", "5537176964"));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
//
//    private static DummyItem createDummyItem(int position) {
//        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
//    }
//
//    private static String makeDetails(int position) {
//        StringBuilder builder = new StringBuilder();
//        builder.append("Details about Item: ").append(position);
//        for (int i = 0; i < position; i++) {
//            builder.append("\nMore details information here.");
//        }
//        return builder.toString();
//    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String pedido;
        public String apellido;
        public String correo;
        public String telefono;

        public DummyItem(String id, String pedido, String apellido, String correo, String telefono) {
            this.id = id;
            this.pedido = pedido;
            this.apellido = apellido;
            this.correo = correo;
            this.telefono = telefono;
        }

        @Override
        public String toString() {
            return pedido;
        }
    }
}
